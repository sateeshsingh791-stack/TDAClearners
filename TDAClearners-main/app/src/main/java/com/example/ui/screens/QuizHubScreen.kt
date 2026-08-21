package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.*
import com.example.data.repository.QuizAttemptRecord
import com.example.data.repository.QuizFlashcardRepository
import com.example.data.repository.SyllabusRepository
import com.example.data.repository.UserLearningRepository
import com.example.ui.components.AppHeader
import com.example.ui.components.CategoryBadge
import com.example.ui.components.OfficialSyllabusBadge
import com.example.ui.components.QuizAccuracyBreakdownView
import com.example.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

enum class QuizScreenState {
    SELECTION,
    ACTIVE_QUIZ,
    RESULTS
}

@Composable
fun QuizHubScreen(
    initialSemester: Int? = null,
    initialSubject: String? = null,
    initialUnit: Int? = null,
    initialTopic: String? = null,
    onNavigateBack: () -> Unit,
    onNavigateToFlashcards: (semester: Int, subjectCode: String, unitNumber: Int?, topicId: String?) -> Unit
) {
    val learningState by UserLearningRepository.learningState.collectAsState()

    // Active Screen Tab (0: Quiz System, 1: Quiz History)
    var activeTab by remember { mutableIntStateOf(0) }

    // Quiz Workflow State
    var screenState by remember { mutableStateOf(QuizScreenState.SELECTION) }

    // Scope Selection Settings
    var selectedSemesterNum by remember { mutableIntStateOf(initialSemester ?: learningState.selectedSemester) }
    val currentSemester = SyllabusRepository.semesters.find { it.number == selectedSemesterNum }
        ?: SyllabusRepository.semesters.first()

    val availableSubjects = currentSemester.subjects
    var selectedSubjectCode by remember {
        mutableStateOf(
            initialSubject ?: availableSubjects.firstOrNull()?.code ?: "BVTD111"
        )
    }

    val currentSubject = SyllabusRepository.getSubjectByCode(selectedSubjectCode)
        ?: availableSubjects.firstOrNull() ?: SyllabusRepository.getAllSubjects().first()

    var selectedScopeType by remember {
        mutableStateOf(
            when {
                initialTopic != null -> QuizScopeType.SPECIFIC_TOPIC
                initialUnit != null -> QuizScopeType.SPECIFIC_UNIT
                else -> QuizScopeType.ENTIRE_SUBJECT
            }
        )
    }

    var selectedUnitNum by remember { mutableStateOf<Int?>(initialUnit) }
    var selectedTopicId by remember { mutableStateOf<String?>(initialTopic) }

    var selectedMode by remember { mutableStateOf(QuizMode.PRACTICE) }
    var selectedDifficulty by remember { mutableStateOf(QuizDifficulty.MIXED) }
    var selectedCount by remember { mutableIntStateOf(10) }

    // Active Quiz Session Variables
    var activeQuestions by remember { mutableStateOf<List<QuizQuestion>>(emptyList()) }
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var userAnswers by remember { mutableStateOf<Map<Int, Int>>(emptyMap()) }
    var quizStartTime by remember { mutableLongStateOf(0L) }
    var elapsedTimeSeconds by remember { mutableIntStateOf(0) }

    // Timer Effect for active quiz
    LaunchedEffect(screenState) {
        if (screenState == QuizScreenState.ACTIVE_QUIZ) {
            quizStartTime = System.currentTimeMillis()
            while (screenState == QuizScreenState.ACTIVE_QUIZ) {
                kotlinx.coroutines.delay(1000)
                elapsedTimeSeconds = ((System.currentTimeMillis() - quizStartTime) / 1000).toInt()
            }
        }
    }

    fun startQuizSession() {
        val scope = QuizScopeSelection(
            semesterNumber = selectedSemesterNum,
            subjectCode = selectedSubjectCode,
            scopeType = selectedScopeType,
            unitNumber = if (selectedScopeType == QuizScopeType.SPECIFIC_UNIT) selectedUnitNum else null,
            topicId = if (selectedScopeType == QuizScopeType.SPECIFIC_TOPIC) selectedTopicId else null,
            quizMode = selectedMode,
            questionCount = selectedCount,
            difficulty = selectedDifficulty
        )

        val questions = QuizFlashcardRepository.getScopedQuestions(scope)
        if (questions.isNotEmpty()) {
            activeQuestions = questions
            currentQuestionIndex = 0
            userAnswers = emptyMap()
            elapsedTimeSeconds = 0
            screenState = QuizScreenState.ACTIVE_QUIZ
        }
    }

    fun submitQuiz() {
        val score = userAnswers.count { (qIdx, ansIdx) ->
            activeQuestions.getOrNull(qIdx)?.correctIndex == ansIdx
        }
        val total = activeQuestions.size
        val percentage = if (total > 0) (score * 100) / total else 0

        val scopeLabel = when (selectedScopeType) {
            QuizScopeType.ENTIRE_SUBJECT -> "Entire Subject"
            QuizScopeType.SPECIFIC_UNIT -> "Unit $selectedUnitNum"
            QuizScopeType.SPECIFIC_TOPIC -> {
                val topic = currentSubject.units.flatMap { it.topics }.find { it.id == selectedTopicId }
                "Topic: ${topic?.title ?: "Selected Topic"}"
            }
        }

        val record = QuizAttemptRecord(
            subjectCode = currentSubject.code,
            subjectName = currentSubject.name,
            scopeLabel = scopeLabel,
            quizMode = selectedMode.displayName,
            difficulty = selectedDifficulty.label,
            score = score,
            totalQuestions = total,
            percentage = percentage,
            timeTakenSeconds = elapsedTimeSeconds
        )

        UserLearningRepository.recordQuizAttempt(record)
        screenState = QuizScreenState.RESULTS
    }

    Scaffold(
        topBar = {
            AppHeader(
                title = when (screenState) {
                    QuizScreenState.SELECTION -> "Curriculum Quiz Hub"
                    QuizScreenState.ACTIVE_QUIZ -> "${currentSubject.code} ${selectedMode.displayName}"
                    QuizScreenState.RESULTS -> "Quiz Evaluation & Review"
                },
                subtitle = "TDAClearners • B.Voc Textile Design",
                showBackButton = true,
                onBackClick = {
                    if (screenState != QuizScreenState.SELECTION) {
                        screenState = QuizScreenState.SELECTION
                    } else {
                        onNavigateBack()
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Tab Selector (Take Quiz vs Quiz History) - only visible in Selection mode
            if (screenState == QuizScreenState.SELECTION) {
                TabRow(
                    selectedTabIndex = activeTab,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Tab(
                        selected = activeTab == 0,
                        onClick = { activeTab = 0 },
                        text = {
                            Text("New Quiz", fontWeight = FontWeight.Bold)
                        },
                        icon = { Icon(Icons.Default.Quiz, contentDescription = null) },
                        modifier = Modifier.testTag("tab_take_quiz")
                    )
                    Tab(
                        selected = activeTab == 1,
                        onClick = { activeTab = 1 },
                        text = {
                            Text("Quiz History (${learningState.quizAttempts.size})", fontWeight = FontWeight.Bold)
                        },
                        icon = { Icon(Icons.Default.History, contentDescription = null) },
                        modifier = Modifier.testTag("tab_quiz_history")
                    )
                }
            }

            Crossfade(
                targetState = if (screenState == QuizScreenState.SELECTION && activeTab == 1) "HISTORY" else screenState.name,
                label = "quiz_screen_transition"
            ) { target ->
                when (target) {
                    "HISTORY" -> {
                        QuizHistoryView(
                            attempts = learningState.quizAttempts,
                            onRetrySubject = { code ->
                                selectedSubjectCode = code
                                activeTab = 0
                                screenState = QuizScreenState.SELECTION
                            }
                        )
                    }
                    QuizScreenState.SELECTION.name -> {
                        QuizSelectionView(
                            selectedSemesterNum = selectedSemesterNum,
                            onSelectSemester = {
                                selectedSemesterNum = it
                                val newSubjs = SyllabusRepository.semesters.find { s -> s.number == it }?.subjects ?: emptyList()
                                selectedSubjectCode = newSubjs.firstOrNull()?.code ?: "BVTD111"
                                selectedScopeType = QuizScopeType.ENTIRE_SUBJECT
                                selectedUnitNum = null
                                selectedTopicId = null
                            },
                            availableSubjects = availableSubjects,
                            selectedSubjectCode = selectedSubjectCode,
                            onSelectSubject = {
                                selectedSubjectCode = it
                                selectedScopeType = QuizScopeType.ENTIRE_SUBJECT
                                selectedUnitNum = null
                                selectedTopicId = null
                            },
                            currentSubject = currentSubject,
                            selectedScopeType = selectedScopeType,
                            onSelectScopeType = {
                                selectedScopeType = it
                                if (it == QuizScopeType.ENTIRE_SUBJECT) {
                                    selectedUnitNum = null
                                    selectedTopicId = null
                                } else if (it == QuizScopeType.SPECIFIC_UNIT && selectedUnitNum == null) {
                                    selectedUnitNum = currentSubject.units.firstOrNull()?.unitNumber ?: 1
                                } else if (it == QuizScopeType.SPECIFIC_TOPIC) {
                                    if (selectedUnitNum == null) selectedUnitNum = currentSubject.units.firstOrNull()?.unitNumber ?: 1
                                    selectedTopicId = currentSubject.units.flatMap { u -> u.topics }.firstOrNull()?.id
                                }
                            },
                            selectedUnitNum = selectedUnitNum,
                            onSelectUnitNum = {
                                selectedUnitNum = it
                                if (selectedScopeType == QuizScopeType.SPECIFIC_TOPIC) {
                                    val u = currentSubject.units.find { unit -> unit.unitNumber == it }
                                    selectedTopicId = u?.topics?.firstOrNull()?.id
                                }
                            },
                            selectedTopicId = selectedTopicId,
                            onSelectTopicId = { selectedTopicId = it },
                            selectedMode = selectedMode,
                            onSelectMode = { selectedMode = it },
                            selectedDifficulty = selectedDifficulty,
                            onSelectDifficulty = { selectedDifficulty = it },
                            selectedCount = selectedCount,
                            onSelectCount = { selectedCount = it },
                            onStartQuiz = { startQuizSession() }
                        )
                    }
                    QuizScreenState.ACTIVE_QUIZ.name -> {
                        ActiveQuizView(
                            questions = activeQuestions,
                            currentIndex = currentQuestionIndex,
                            onSelectIndex = { currentQuestionIndex = it },
                            userAnswers = userAnswers,
                            onAnswerSelected = { qIdx, ansIdx ->
                                userAnswers = userAnswers + (qIdx to ansIdx)
                            },
                            elapsedTimeSeconds = elapsedTimeSeconds,
                            quizMode = selectedMode,
                            onSubmit = { submitQuiz() },
                            onQuit = { screenState = QuizScreenState.SELECTION }
                        )
                    }
                    QuizScreenState.RESULTS.name -> {
                        QuizResultsView(
                            questions = activeQuestions,
                            userAnswers = userAnswers,
                            elapsedTimeSeconds = elapsedTimeSeconds,
                            quizMode = selectedMode,
                            subject = currentSubject,
                            scopeType = selectedScopeType,
                            unitNum = selectedUnitNum,
                            topicId = selectedTopicId,
                            onRetry = { startQuizSession() },
                            onNewQuiz = { screenState = QuizScreenState.SELECTION },
                            onReviewFlashcards = {
                                onNavigateToFlashcards(
                                    selectedSemesterNum,
                                    selectedSubjectCode,
                                    if (selectedScopeType == QuizScopeType.SPECIFIC_UNIT) selectedUnitNum else null,
                                    if (selectedScopeType == QuizScopeType.SPECIFIC_TOPIC) selectedTopicId else null
                                )
                            },
                            onStartTargetedQuiz = { targetUnit, targetTopic ->
                                selectedScopeType = if (targetTopic != null) QuizScopeType.SPECIFIC_TOPIC else QuizScopeType.SPECIFIC_UNIT
                                selectedUnitNum = targetUnit
                                selectedTopicId = targetTopic
                                startQuizSession()
                            },
                            onOpenTopicFlashcards = { targetUnit, targetTopic ->
                                onNavigateToFlashcards(selectedSemesterNum, selectedSubjectCode, targetUnit, targetTopic)
                            }
                        )
                    }
                }
            }
        }
    }
}

// =========================================================================
// 1. QUIZ SELECTION VIEW (Step 1 -> Step 2 -> Step 3 -> Step 4)
// =========================================================================
@Composable
private fun QuizSelectionView(
    selectedSemesterNum: Int,
    onSelectSemester: (Int) -> Unit,
    availableSubjects: List<Subject>,
    selectedSubjectCode: String,
    onSelectSubject: (String) -> Unit,
    currentSubject: Subject,
    selectedScopeType: QuizScopeType,
    onSelectScopeType: (QuizScopeType) -> Unit,
    selectedUnitNum: Int?,
    onSelectUnitNum: (Int) -> Unit,
    selectedTopicId: String?,
    onSelectTopicId: (String) -> Unit,
    selectedMode: QuizMode,
    onSelectMode: (QuizMode) -> Unit,
    selectedDifficulty: QuizDifficulty,
    onSelectDifficulty: (QuizDifficulty) -> Unit,
    selectedCount: Int,
    onSelectCount: (Int) -> Unit,
    onStartQuiz: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(top = 14.dp, bottom = 32.dp)
    ) {
        // Step 1: Semester Selection
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier.fillMaxWidth().testTag("step_semester_card")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "STEP 1: SELECT SEMESTER",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 1.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(1, 2).forEach { sem ->
                            val isSelected = selectedSemesterNum == sem
                            Surface(
                                onClick = { onSelectSemester(sem) },
                                shape = RoundedCornerShape(12.dp),
                                color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                                border = androidx.compose.foundation.BorderStroke(
                                    1.5.dp,
                                    if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                                ),
                                modifier = Modifier.weight(1f).testTag("select_semester_$sem")
                            ) {
                                Row(
                                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                        contentDescription = null,
                                        tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Semester $sem",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Step 2: Subject Selection (Theory + Practical)
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier.fillMaxWidth().testTag("step_subject_card")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "STEP 2: SELECT SUBJECT (THEORY & PRACTICAL)",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary,
                                letterSpacing = 1.sp
                            )
                        )
                        Text(
                            text = "${availableSubjects.size} Available",
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        availableSubjects.forEach { subj ->
                            val isSelected = subj.code == selectedSubjectCode
                            val isPractical = subj.type == SubjectType.PRACTICAL

                            Surface(
                                onClick = { onSelectSubject(subj.code) },
                                shape = RoundedCornerShape(12.dp),
                                color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                                border = androidx.compose.foundation.BorderStroke(
                                    1.5.dp,
                                    if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                                ),
                                modifier = Modifier.fillMaxWidth().testTag("select_subject_${subj.code}")
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(36.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(
                                                    if (isPractical) TerracottaContainer else MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = if (isPractical) "🧵" else "📖",
                                                fontSize = 16.sp
                                            )
                                        }

                                        Column {
                                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                                Text(
                                                    text = subj.code,
                                                    fontWeight = FontWeight.ExtraBold,
                                                    fontSize = 12.sp,
                                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                                )
                                                Surface(
                                                    shape = RoundedCornerShape(4.dp),
                                                    color = if (isPractical) TerracottaPrimary else MaterialTheme.colorScheme.secondary
                                                ) {
                                                    Text(
                                                        text = if (isPractical) "PRACTICAL" else "THEORY",
                                                        fontSize = 8.sp,
                                                        fontWeight = FontWeight.ExtraBold,
                                                        color = Color.White,
                                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                                    )
                                                }
                                            }
                                            Text(
                                                text = subj.name,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                        }
                                    }

                                    Icon(
                                        imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                        contentDescription = null,
                                        tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Step 3: Quiz Scope Selection (Entire Subject / Specific Unit / Specific Topic)
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier.fillMaxWidth().testTag("step_scope_card")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "STEP 3: CHOOSE QUIZ SCOPE",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 1.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Scope Radio Selection
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        QuizScopeType.values().forEach { scopeType ->
                            val isSelected = selectedScopeType == scopeType
                            FilterChip(
                                selected = isSelected,
                                onClick = { onSelectScopeType(scopeType) },
                                label = {
                                    Text(
                                        text = scopeType.label,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                                    selectedLabelColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.weight(1f).testTag("scope_${scopeType.name}")
                            )
                        }
                    }

                    // Dynamic Sub-options if Specific Unit or Topic is selected
                    if (selectedScopeType != QuizScopeType.ENTIRE_SUBJECT && currentSubject.units.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Select Unit:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            items(currentSubject.units) { unit ->
                                val isUnitSelected = selectedUnitNum == unit.unitNumber
                                FilterChip(
                                    selected = isUnitSelected,
                                    onClick = { onSelectUnitNum(unit.unitNumber) },
                                    label = {
                                        Text(
                                            text = "Unit ${unit.unitNumber}: ${unit.title}",
                                            fontSize = 11.sp,
                                            maxLines = 1
                                        )
                                    },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                                        selectedLabelColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.testTag("unit_chip_${unit.unitNumber}")
                                )
                            }
                        }
                    }

                    if (selectedScopeType == QuizScopeType.SPECIFIC_TOPIC) {
                        val activeUnit = currentSubject.units.find { it.unitNumber == selectedUnitNum } ?: currentSubject.units.firstOrNull()
                        val topics = activeUnit?.topics ?: emptyList()
                        if (topics.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Select Topic:",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(6.dp))

                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                topics.forEach { topic ->
                                    val isTopicSelected = selectedTopicId == topic.id
                                    Surface(
                                        onClick = { onSelectTopicId(topic.id) },
                                        shape = RoundedCornerShape(10.dp),
                                        color = if (isTopicSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                                        border = androidx.compose.foundation.BorderStroke(
                                            1.dp,
                                            if (isTopicSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                                        ),
                                        modifier = Modifier.fillMaxWidth().testTag("topic_option_${topic.id}")
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(10.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = topic.title,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                modifier = Modifier.weight(1f)
                                            )
                                            Icon(
                                                imageVector = if (isTopicSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                                contentDescription = null,
                                                tint = if (isTopicSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Step 4: Quiz Mode, Difficulty & Questions Count
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier.fillMaxWidth().testTag("step_mode_card")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "STEP 4: QUIZ MODE & CONFIGURATION",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 1.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Quiz Modes
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        QuizMode.values().forEach { mode ->
                            val isSelected = selectedMode == mode
                            Surface(
                                onClick = { onSelectMode(mode) },
                                shape = RoundedCornerShape(10.dp),
                                color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                                border = androidx.compose.foundation.BorderStroke(
                                    1.dp,
                                    if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                                ),
                                modifier = Modifier.fillMaxWidth().testTag("mode_${mode.name}")
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = mode.displayName,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = mode.subtitle,
                                            fontSize = 10.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Icon(
                                        imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                        contentDescription = null,
                                        tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Question Count Selector
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Question Count:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            listOf(5, 10, 20).forEach { count ->
                                FilterChip(
                                    selected = selectedCount == count,
                                    onClick = { onSelectCount(count) },
                                    label = { Text("$count Qs", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                                        selectedLabelColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Difficulty Selector
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Difficulty:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            QuizDifficulty.values().forEach { diff ->
                                FilterChip(
                                    selected = selectedDifficulty == diff,
                                    onClick = { onSelectDifficulty(diff) },
                                    label = { Text(diff.label, fontSize = 10.sp) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                                        selectedLabelColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Start Quiz Button
        item {
            Button(
                onClick = onStartQuiz,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("start_quiz_action_btn")
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Start ${selectedMode.displayName} ($selectedCount Qs)",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.sp
                )
            }
        }
    }
}

// =========================================================================
// 2. ACTIVE QUIZ VIEW
// =========================================================================
@Composable
private fun ActiveQuizView(
    questions: List<QuizQuestion>,
    currentIndex: Int,
    onSelectIndex: (Int) -> Unit,
    userAnswers: Map<Int, Int>,
    onAnswerSelected: (Int, Int) -> Unit,
    elapsedTimeSeconds: Int,
    quizMode: QuizMode,
    onSubmit: () -> Unit,
    onQuit: () -> Unit
) {
    val currentQuestion = questions.getOrNull(currentIndex) ?: return
    val selectedOption = userAnswers[currentIndex]
    val isAnswered = selectedOption != null
    val isPractice = quizMode == QuizMode.PRACTICE

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            // Header Bar with Question Progress and Timer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = "Question ${currentIndex + 1} / ${questions.size}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Timer, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    val mins = elapsedTimeSeconds / 60
                    val secs = elapsedTimeSeconds % 60
                    Text(
                        text = String.format("%02d:%02d", mins, secs),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                IconButton(
                    onClick = onQuit,
                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Exit Quiz", modifier = Modifier.size(16.dp))
                }
            }

            LinearProgressIndicator(
                progress = { (currentIndex + 1).toFloat() / questions.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.outlineVariant
            )

            // Question Card
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier.fillMaxWidth().testTag("active_question_card")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Text(
                                text = "${currentQuestion.subjectCode} • Unit ${currentQuestion.unitNumber}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        if (currentQuestion.isPracticalViva) {
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = TerracottaContainer
                            ) {
                                Text(
                                    text = "🧵 Practical / Viva",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TerracottaOnContainer,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = currentQuestion.question,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 22.sp
                        )
                    )
                }
            }

            // Options List
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                currentQuestion.options.forEachIndexed { optIdx, optText ->
                    val isSelected = selectedOption == optIdx
                    val isCorrectOption = optIdx == currentQuestion.correctIndex
                    val showPracticeFeedback = isPractice && isAnswered

                    val (containerColor, contentColor, borderColor) = when {
                        showPracticeFeedback && isCorrectOption -> Triple(SuccessGreenContainer, SuccessGreenOnContainer, SuccessGreen)
                        showPracticeFeedback && isSelected && !isCorrectOption -> Triple(ErrorRedContainer, ErrorRedOnContainer, ErrorRed)
                        isSelected -> Triple(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.onPrimaryContainer, MaterialTheme.colorScheme.primary)
                        else -> Triple(MaterialTheme.colorScheme.surface, MaterialTheme.colorScheme.onSurface, MaterialTheme.colorScheme.outlineVariant)
                    }

                    Surface(
                        onClick = { onAnswerSelected(currentIndex, optIdx) },
                        shape = RoundedCornerShape(12.dp),
                        color = containerColor,
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor),
                        modifier = Modifier.fillMaxWidth().testTag("option_btn_$optIdx")
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Surface(
                                    shape = CircleShape,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = ('A' + optIdx).toString(),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 11.sp,
                                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                Text(
                                    text = optText,
                                    fontSize = 13.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = contentColor
                                )
                            }

                            if (showPracticeFeedback) {
                                if (isCorrectOption) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = "Correct", tint = SuccessGreen, modifier = Modifier.size(20.dp))
                                } else if (isSelected) {
                                    Icon(Icons.Default.Cancel, contentDescription = "Incorrect", tint = ErrorRed, modifier = Modifier.size(20.dp))
                                }
                            }
                        }
                    }
                }
            }

            // In Practice Mode: Step-by-Step Explanation Box when answered
            if (isPractice && isAnswered) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Lightbulb, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Curriculum Insight",
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = currentQuestion.explanation,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }

        // Bottom Navigation Buttons (Previous / Next / Submit)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = {
                    if (currentIndex > 0) onSelectIndex(currentIndex - 1)
                },
                enabled = currentIndex > 0,
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Previous")
            }

            if (currentIndex < questions.size - 1) {
                Button(
                    onClick = { onSelectIndex(currentIndex + 1) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.testTag("next_question_btn")
                ) {
                    Text("Next Question")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                }
            } else {
                Button(
                    onClick = onSubmit,
                    colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.testTag("submit_quiz_btn")
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Submit Quiz", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// =========================================================================
// 3. QUIZ RESULTS & EXPLANATION REVIEW VIEW (+ FLASHCARD CONNECTOR!)
// =========================================================================
@Composable
private fun QuizResultsView(
    questions: List<QuizQuestion>,
    userAnswers: Map<Int, Int>,
    elapsedTimeSeconds: Int,
    quizMode: QuizMode,
    subject: Subject,
    scopeType: QuizScopeType,
    unitNum: Int?,
    topicId: String?,
    onRetry: () -> Unit,
    onNewQuiz: () -> Unit,
    onReviewFlashcards: () -> Unit,
    onStartTargetedQuiz: ((unitNumber: Int, topicId: String?) -> Unit)? = null,
    onOpenTopicFlashcards: ((unitNumber: Int, topicId: String?) -> Unit)? = null
) {
    val correctCount = userAnswers.count { (qIdx, ansIdx) ->
        questions.getOrNull(qIdx)?.correctIndex == ansIdx
    }
    val totalCount = questions.size
    val percentage = if (totalCount > 0) (correctCount * 100) / totalCount else 0

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(top = 14.dp, bottom = 32.dp)
    ) {
        // Score Header Card
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (percentage >= 70) SuccessGreenContainer else MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier.fillMaxWidth().testTag("quiz_result_card")
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (percentage >= 80) "🎉 Outstanding Performance!" else if (percentage >= 60) "👍 Good Effort!" else "📚 Revision Recommended",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "$correctCount / $totalCount",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )

                    Text(
                        text = "$percentage% Overall Accuracy • Time: ${elapsedTimeSeconds / 60}m ${elapsedTimeSeconds % 60}s",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Quick Action Buttons (Retry, New Quiz, Review Flashcards)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = onRetry,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f).testTag("retry_quiz_btn")
                        ) {
                            Text("Retry", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = onNewQuiz,
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f).testTag("new_quiz_btn")
                        ) {
                            Text("New Quiz", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // VISUALIZATION COMPONENT: Breakdown of Accuracy per Unit & Topic (Weak Area Diagnostic)
        item {
            QuizAccuracyBreakdownView(
                questions = questions,
                userAnswers = userAnswers,
                subject = subject,
                onStartTargetedQuiz = onStartTargetedQuiz,
                onOpenTopicFlashcards = onOpenTopicFlashcards
            )
        }

        // CONNECT QUIZ TO FLASHCARDS BANNER (Crucial User Requirement!)
        item {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface,
                border = androidx.compose.foundation.BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary),
                modifier = Modifier.fillMaxWidth().testTag("review_flashcards_cta_card")
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("📇", fontSize = 20.sp)
                        }
                        Column {
                            Text(
                                text = "Need more practice on this?",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Revise key terms & practical viva flashcards for ${subject.code}.",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Button(
                        onClick = onReviewFlashcards,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.testTag("goto_flashcards_btn")
                    ) {
                        Text("Flashcards", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Section Title: Detailed Answers Breakdown
        item {
            Text(
                text = "Question-by-Question Review",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }

        // Questions List
        items(questions.size) { idx ->
            val q = questions[idx]
            val chosen = userAnswers[idx]
            val isCorrect = chosen == q.correctIndex

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (isCorrect) SuccessGreen.copy(alpha = 0.5f) else ErrorRed.copy(alpha = 0.5f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Question ${idx + 1}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = if (isCorrect) SuccessGreenContainer else ErrorRedContainer
                        ) {
                            Text(
                                text = if (isCorrect) "✓ Correct" else "✗ Incorrect",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isCorrect) SuccessGreenOnContainer else ErrorRedOnContainer,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = q.question,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    if (chosen != null && !isCorrect) {
                        Text(
                            text = "Your Answer: ${q.options.getOrNull(chosen) ?: "None"}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = ErrorRed
                        )
                    }

                    Text(
                        text = "Correct Answer: ${q.options[q.correctIndex]}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SuccessGreen
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "💡 ${q.explanation}",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

// =========================================================================
// 4. QUIZ HISTORY TAB VIEW
// =========================================================================
@Composable
private fun QuizHistoryView(
    attempts: List<QuizAttemptRecord>,
    onRetrySubject: (String) -> Unit
) {
    if (attempts.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(Icons.Default.HistoryEdu, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(48.dp))
                Text("No past quiz attempts yet.", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                Text("Take a quiz to start building your academic revision record.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center)
            }
        }
    } else {
        val dateFormat = remember { SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()) }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(top = 14.dp, bottom = 28.dp)
        ) {
            items(attempts) { record ->
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth().testTag("history_record_${record.id}")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = MaterialTheme.colorScheme.primaryContainer
                                ) {
                                    Text(
                                        text = record.subjectCode,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                                Text(
                                    text = record.quizMode,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Text(
                                text = "${record.score} / ${record.totalQuestions} (${record.percentage}%)",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 13.sp,
                                color = if (record.percentage >= 70) SuccessGreen else MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = record.subjectName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = "Scope: ${record.scopeLabel} • Difficulty: ${record.difficulty}",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = dateFormat.format(Date(record.timestamp)),
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            TextButton(
                                onClick = { onRetrySubject(record.subjectCode) },
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text("Retake Quiz", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}
