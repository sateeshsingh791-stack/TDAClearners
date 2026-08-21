package com.example.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.*
import com.example.data.repository.QuizFlashcardRepository
import com.example.data.repository.SyllabusRepository
import com.example.data.repository.UserLearningRepository
import com.example.ui.components.AppHeader
import com.example.ui.components.CategoryBadge
import com.example.ui.components.OfficialSyllabusBadge
import com.example.ui.theme.*

@Composable
fun FlashcardsScreen(
    initialSemester: Int? = null,
    initialSubject: String? = null,
    initialUnit: Int? = null,
    initialTopic: String? = null,
    onNavigateBack: () -> Unit,
    onStartQuizForScope: (semester: Int, subjectCode: String, unitNumber: Int?, topicId: String?) -> Unit
) {
    val learningState by UserLearningRepository.learningState.collectAsState()

    // Selection State
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
    var selectedTypeFilter by remember { mutableStateOf<FlashcardType?>(null) }
    var showOnlyDifficult by remember { mutableStateOf(false) }

    // Cards Deck State
    var cardIndex by remember { mutableIntStateOf(0) }
    var isFlipped by remember { mutableStateOf(false) }
    var isShuffled by remember { mutableStateOf(false) }

    // Fetch scoped cards
    val rawCards = remember(
        selectedSemesterNum,
        selectedSubjectCode,
        selectedScopeType,
        selectedUnitNum,
        selectedTopicId,
        selectedTypeFilter
    ) {
        val uNum = if (selectedScopeType == QuizScopeType.SPECIFIC_UNIT) selectedUnitNum else null
        val tId = if (selectedScopeType == QuizScopeType.SPECIFIC_TOPIC) selectedTopicId else null
        QuizFlashcardRepository.getScopedFlashcards(
            semesterNumber = selectedSemesterNum,
            subjectCode = selectedSubjectCode,
            unitNumber = uNum,
            topicId = tId,
            filterType = selectedTypeFilter
        )
    }

    val displayCards = remember(rawCards, showOnlyDifficult, learningState.flashcardMastery, isShuffled) {
        var list = if (showOnlyDifficult) {
            rawCards.filter {
                val mastery = learningState.flashcardMastery[it.id]
                mastery == CardMastery.DIFFICULT || mastery == CardMastery.NEED_PRACTICE
            }
        } else {
            rawCards
        }
        if (isShuffled) list = list.shuffled()
        list
    }

    // Reset card index if out of bounds
    LaunchedEffect(displayCards.size) {
        if (cardIndex >= displayCards.size) {
            cardIndex = 0
        }
        isFlipped = false
    }

    Scaffold(
        topBar = {
            AppHeader(
                title = "Flashcards Revision Deck",
                subtitle = "${currentSubject.code} • ${currentSubject.name}",
                showBackButton = true,
                onBackClick = onNavigateBack,
                actions = {
                    IconButton(
                        onClick = { isShuffled = !isShuffled },
                        modifier = Modifier.testTag("shuffle_flashcards_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Shuffle,
                            contentDescription = "Shuffle Cards",
                            tint = if (isShuffled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Scope Selector Bar (Compact Accordion Card)
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface,
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier.fillMaxWidth().testTag("flashcards_scope_selector")
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    // Semester & Subject Selector Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Semester Switcher
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            listOf(1, 2).forEach { sem ->
                                FilterChip(
                                    selected = selectedSemesterNum == sem,
                                    onClick = {
                                        selectedSemesterNum = sem
                                        val newSubjects = SyllabusRepository.semesters.find { it.number == sem }?.subjects ?: emptyList()
                                        selectedSubjectCode = newSubjects.firstOrNull()?.code ?: "BVTD111"
                                        selectedScopeType = QuizScopeType.ENTIRE_SUBJECT
                                        selectedUnitNum = null
                                        selectedTopicId = null
                                        cardIndex = 0
                                        isFlipped = false
                                    },
                                    label = { Text("Sem $sem", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                                        selectedLabelColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.testTag("flashcard_sem_$sem")
                                )
                            }
                        }

                        // Difficulty filter toggle
                        FilterChip(
                            selected = showOnlyDifficult,
                            onClick = {
                                showOnlyDifficult = !showOnlyDifficult
                                cardIndex = 0
                                isFlipped = false
                            },
                            label = { Text(if (showOnlyDifficult) "Difficult (${displayCards.size})" else "All Cards", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                            leadingIcon = {
                                Icon(Icons.Default.PriorityHigh, contentDescription = null, modifier = Modifier.size(14.dp))
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = ErrorRedContainer,
                                selectedLabelColor = ErrorRedOnContainer
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Subject Horizontal Scroll
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(availableSubjects) { subj ->
                            val isSelected = subj.code == selectedSubjectCode
                            val isPractical = subj.type == SubjectType.PRACTICAL

                            Surface(
                                onClick = {
                                    selectedSubjectCode = subj.code
                                    selectedScopeType = QuizScopeType.ENTIRE_SUBJECT
                                    selectedUnitNum = null
                                    selectedTopicId = null
                                    cardIndex = 0
                                    isFlipped = false
                                },
                                shape = RoundedCornerShape(10.dp),
                                color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                                border = androidx.compose.foundation.BorderStroke(
                                    1.dp,
                                    if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                                ),
                                modifier = Modifier.testTag("flashcard_subj_${subj.code}")
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = if (isPractical) "🧵 ${subj.code}" else "📖 ${subj.code}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Scope Switcher (Entire / Specific Unit / Specific Topic)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        FilterChip(
                            selected = selectedScopeType == QuizScopeType.ENTIRE_SUBJECT,
                            onClick = {
                                selectedScopeType = QuizScopeType.ENTIRE_SUBJECT
                                selectedUnitNum = null
                                selectedTopicId = null
                                cardIndex = 0
                                isFlipped = false
                            },
                            label = { Text("Entire Subject", fontSize = 10.sp, fontWeight = FontWeight.SemiBold) },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        )

                        if (currentSubject.units.isNotEmpty()) {
                            FilterChip(
                                selected = selectedScopeType == QuizScopeType.SPECIFIC_UNIT,
                                onClick = {
                                    selectedScopeType = QuizScopeType.SPECIFIC_UNIT
                                    if (selectedUnitNum == null) selectedUnitNum = currentSubject.units.firstOrNull()?.unitNumber ?: 1
                                    selectedTopicId = null
                                    cardIndex = 0
                                    isFlipped = false
                                },
                                label = { Text("Unit-wise", fontSize = 10.sp, fontWeight = FontWeight.SemiBold) },
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.weight(1f)
                            )
                        }

                        val allTopics = currentSubject.units.flatMap { it.topics }
                        if (allTopics.isNotEmpty()) {
                            FilterChip(
                                selected = selectedScopeType == QuizScopeType.SPECIFIC_TOPIC,
                                onClick = {
                                    selectedScopeType = QuizScopeType.SPECIFIC_TOPIC
                                    if (selectedUnitNum == null) selectedUnitNum = currentSubject.units.firstOrNull()?.unitNumber ?: 1
                                    if (selectedTopicId == null) selectedTopicId = allTopics.firstOrNull()?.id
                                    cardIndex = 0
                                    isFlipped = false
                                },
                                label = { Text("Topic-wise", fontSize = 10.sp, fontWeight = FontWeight.SemiBold) },
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    // Unit Selection Chips if Unit or Topic scope selected
                    if (selectedScopeType != QuizScopeType.ENTIRE_SUBJECT && currentSubject.units.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            currentSubject.units.forEach { unit ->
                                FilterChip(
                                    selected = selectedUnitNum == unit.unitNumber,
                                    onClick = {
                                        selectedUnitNum = unit.unitNumber
                                        if (selectedScopeType == QuizScopeType.SPECIFIC_TOPIC) {
                                            selectedTopicId = unit.topics.firstOrNull()?.id
                                        }
                                        cardIndex = 0
                                        isFlipped = false
                                    },
                                    label = { Text("Unit ${unit.unitNumber}", fontSize = 10.sp) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                                        selectedLabelColor = Color.White
                                    )
                                )
                            }
                        }
                    }

                    // Topic Selection Chips if Topic scope selected
                    if (selectedScopeType == QuizScopeType.SPECIFIC_TOPIC) {
                        val activeUnit = currentSubject.units.find { it.unitNumber == selectedUnitNum } ?: currentSubject.units.firstOrNull()
                        val topicsInUnit = activeUnit?.topics ?: emptyList()
                        if (topicsInUnit.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                items(topicsInUnit) { topic ->
                                    FilterChip(
                                        selected = selectedTopicId == topic.id,
                                        onClick = {
                                            selectedTopicId = topic.id
                                            cardIndex = 0
                                            isFlipped = false
                                        },
                                        label = { Text(topic.title, fontSize = 10.sp, maxLines = 1) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                                            selectedLabelColor = Color.White
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Deck Mastery & Stats Bar
            if (displayCards.isNotEmpty()) {
                val knownCount = displayCards.count { learningState.flashcardMastery[it.id] == CardMastery.KNOW_IT }
                val practiceCount = displayCards.count { learningState.flashcardMastery[it.id] == CardMastery.NEED_PRACTICE }
                val difficultCount = displayCards.count { learningState.flashcardMastery[it.id] == CardMastery.DIFFICULT }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Card ${cardIndex + 1} of ${displayCards.size}",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            MasteryBadge("🟢 $knownCount", SuccessGreenContainer, SuccessGreenOnContainer)
                            MasteryBadge("🟡 $practiceCount", WarningAmberContainer, WarningAmberOnContainer)
                            MasteryBadge("🔴 $difficultCount", ErrorRedContainer, ErrorRedOnContainer)
                        }
                    }
                }

                LinearProgressIndicator(
                    progress = { (cardIndex + 1).toFloat() / displayCards.size },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp)),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.outlineVariant
                )
            }

            // =============================================================
            // INTERACTIVE 3D FLIP CARD
            // =============================================================
            if (displayCards.isEmpty()) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize().padding(20.dp), contentAlignment = Alignment.Center) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(Icons.Default.LayersClear, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(40.dp))
                            Text("No flashcards found for this selection.", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                            Text("Try switching filters or selecting Entire Subject.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            } else {
                val currentCard = displayCards[cardIndex]
                val currentMastery = learningState.flashcardMastery[currentCard.id] ?: CardMastery.UNSEEN

                // 3D Flip Animation Rotation
                val rotation by animateFloatAsState(
                    targetValue = if (isFlipped) 180f else 0f,
                    animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
                    label = "card_flip_anim"
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 280.dp)
                        .graphicsLayer {
                            rotationY = rotation
                            cameraDistance = 12f * density
                        }
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { isFlipped = !isFlipped }
                        )
                        .testTag("flashcard_flip_target"),
                    contentAlignment = Alignment.Center
                ) {
                    if (rotation <= 90f) {
                        // FRONT OF CARD
                        FlashcardFaceFront(
                            card = currentCard,
                            mastery = currentMastery,
                            onFlip = { isFlipped = true }
                        )
                    } else {
                        // BACK OF CARD (Counter-rotated by 180 so text renders upright)
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer { rotationY = 180f }
                        ) {
                            FlashcardFaceBack(
                                card = currentCard,
                                mastery = currentMastery,
                                onFlip = { isFlipped = false }
                            )
                        }
                    }
                }

                // Mastery Status Buttons (Know It, Need Practice, Difficult)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            UserLearningRepository.updateFlashcardMastery(currentCard.id, CardMastery.DIFFICULT)
                            if (cardIndex < displayCards.size - 1) {
                                cardIndex++
                                isFlipped = false
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (currentMastery == CardMastery.DIFFICULT) ErrorRed else ErrorRedContainer,
                            contentColor = if (currentMastery == CardMastery.DIFFICULT) Color.White else ErrorRedOnContainer
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f).height(44.dp).testTag("mastery_difficult_btn")
                    ) {
                        Text("🔴 Difficult", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = {
                            UserLearningRepository.updateFlashcardMastery(currentCard.id, CardMastery.NEED_PRACTICE)
                            if (cardIndex < displayCards.size - 1) {
                                cardIndex++
                                isFlipped = false
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (currentMastery == CardMastery.NEED_PRACTICE) WarningAmberOnContainer else WarningAmberContainer,
                            contentColor = if (currentMastery == CardMastery.NEED_PRACTICE) Color.White else WarningAmberOnContainer
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f).height(44.dp).testTag("mastery_practice_btn")
                    ) {
                        Text("🟡 Practice", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = {
                            UserLearningRepository.updateFlashcardMastery(currentCard.id, CardMastery.KNOW_IT)
                            if (cardIndex < displayCards.size - 1) {
                                cardIndex++
                                isFlipped = false
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (currentMastery == CardMastery.KNOW_IT) SuccessGreen else SuccessGreenContainer,
                            contentColor = if (currentMastery == CardMastery.KNOW_IT) Color.White else SuccessGreenOnContainer
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f).height(44.dp).testTag("mastery_know_btn")
                    ) {
                        Text("🟢 Know It", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }

                // Deck Navigation (Previous / Next / Restart / Practice Quiz)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = {
                            if (cardIndex > 0) {
                                cardIndex--
                                isFlipped = false
                            }
                        },
                        enabled = cardIndex > 0,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.testTag("prev_card_btn")
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Previous")
                    }

                    IconButton(
                        onClick = {
                            cardIndex = 0
                            isFlipped = false
                        },
                        colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Icon(Icons.Default.RestartAlt, contentDescription = "Restart Deck", tint = MaterialTheme.colorScheme.primary)
                    }

                    Button(
                        onClick = {
                            if (cardIndex < displayCards.size - 1) {
                                cardIndex++
                                isFlipped = false
                            } else {
                                cardIndex = 0
                                isFlipped = false
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.testTag("next_card_btn")
                    ) {
                        Text(if (cardIndex < displayCards.size - 1) "Next Card" else "Restart")
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }

                // Complete Learning Loop Card: Practice Quiz for this Scope!
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                    modifier = Modifier.fillMaxWidth().testTag("launch_quiz_from_flashcards")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Ready to test your knowledge?",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                            Text(
                                text = "Take a targeted quiz for ${currentSubject.code} (${selectedScopeType.label})",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                            )
                        }

                        Button(
                            onClick = {
                                val uNum = if (selectedScopeType == QuizScopeType.SPECIFIC_UNIT) selectedUnitNum else null
                                val tId = if (selectedScopeType == QuizScopeType.SPECIFIC_TOPIC) selectedTopicId else null
                                onStartQuizForScope(selectedSemesterNum, selectedSubjectCode, uNum, tId)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Take Quiz", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MasteryBadge(text: String, containerColor: Color, contentColor: Color) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = containerColor
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = contentColor,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}

@Composable
private fun FlashcardFaceFront(
    card: FlashcardItem,
    mastery: CardMastery,
    onFlip: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header: Type badge and hint
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
                        text = "${card.type.emoji} ${card.type.label}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                card.practicalTag?.let { tag ->
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(
                            text = tag,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Main Prompt / Question
            Text(
                text = card.front,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 26.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Footer: Flip Call to Action
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Flip, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Tap Card to Reveal Answer",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun FlashcardFaceBack(
    card: FlashcardItem,
    mastery: CardMastery,
    onFlip: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, SuccessGreen.copy(alpha = 0.6f)),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = SuccessGreenContainer
                ) {
                    Text(
                        text = "💡 Explanation & Answer",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SuccessGreenOnContainer,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Text(
                    text = card.categoryHint,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Back Answer Content
            Text(
                text = card.back,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 22.sp
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Footer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Flip, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Tap to Flip Back",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
