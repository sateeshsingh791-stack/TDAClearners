package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.QuizQuestion
import com.example.data.model.TopicContent
import com.example.data.repository.SyllabusRepository
import com.example.data.repository.UserLearningRepository
import com.example.ui.components.AppHeader
import com.example.ui.components.OfficialSyllabusBadge
import com.example.ui.components.ResearchedContentBadge
import com.example.ui.components.WeavePatternCanvas
import com.example.ui.theme.*

@Composable
fun TopicLearningScreen(
    topicId: String,
    onNavigateBack: () -> Unit,
    onAskAiTutor: (String) -> Unit,
    onPracticeQuiz: (semester: Int, subjectCode: String, unitNumber: Int?, topicId: String?) -> Unit,
    onReviseFlashcards: (semester: Int, subjectCode: String, unitNumber: Int?, topicId: String?) -> Unit
) {
    val learningState by UserLearningRepository.learningState.collectAsState()
    val allSubjects = SyllabusRepository.getAllSubjects()

    var foundTopic: TopicContent? = null
    var parentSubjectCode = ""
    var parentSemesterNum = 1

    for (s in allSubjects) {
        for (u in s.units) {
            val t = u.topics.find { it.id == topicId }
            if (t != null) {
                foundTopic = t
                parentSubjectCode = s.code
                parentSemesterNum = s.semesterNumber
                break
            }
        }
        if (foundTopic != null) break
    }

    if (foundTopic == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Topic not found: $topicId", color = MaterialTheme.colorScheme.onBackground)
        }
        return
    }

    val topic = foundTopic
    val isCompleted = learningState.completedTopicIds.contains(topic.id)
    val isBookmarked = learningState.bookmarkedTopicIds.contains(topic.id)

    Scaffold(
        topBar = {
            AppHeader(
                title = topic.title,
                subtitle = "$parentSubjectCode • Unit ${topic.unitNumber}",
                showBackButton = true,
                onBackClick = onNavigateBack,
                actions = {
                    IconButton(
                        onClick = { UserLearningRepository.toggleBookmark(topic.id) },
                        modifier = Modifier.testTag("bookmark_topic_btn")
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp,
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { onAskAiTutor("Explain ${topic.title} in the context of $parentSubjectCode syllabus with practical exam examples.") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f).height(44.dp).testTag("ask_ai_topic_btn")
                    ) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Ask AI Tutor", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }

                    Button(
                        onClick = { UserLearningRepository.toggleTopicCompletion(topic.id) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isCompleted) SuccessGreen else MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f).height(44.dp).testTag("complete_topic_btn")
                    ) {
                        Icon(
                            imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isCompleted) "Completed" else "Mark Complete",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(top = 10.dp, bottom = 32.dp)
        ) {
            // Topic Header Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth().testTag("topic_overview_card")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (topic.isOfficialSyllabusTopic) {
                                OfficialSyllabusBadge(label = topic.sourceLabel)
                            } else {
                                ResearchedContentBadge(label = topic.sourceLabel)
                            }

                            if (isCompleted) {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = SuccessGreenContainer
                                ) {
                                    Text(
                                        text = "✓ Mastered",
                                        color = SuccessGreenOnContainer,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = topic.title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = topic.overview,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 20.sp
                            )
                        )
                    }
                }
            }

            // Quick Revision & Practice Toolbar (Learning Loop)
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        onClick = { onPracticeQuiz(parentSemesterNum, parentSubjectCode, topic.unitNumber, topic.id) },
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
                        modifier = Modifier.weight(1f).testTag("practice_topic_quiz_btn")
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(Icons.Default.Quiz, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Topic Quiz", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        }
                    }

                    Surface(
                        onClick = { onReviseFlashcards(parentSemesterNum, parentSubjectCode, topic.unitNumber, topic.id) },
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)),
                        modifier = Modifier.weight(1f).testTag("revise_topic_flashcards_btn")
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(Icons.Default.Style, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondaryContainer, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Flashcards", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSecondaryContainer)
                        }
                    }
                }
            }

            // Key Academic Points
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "📌 Key Academic Points",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        topic.keyPoints.forEach { point ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Surface(
                                    shape = CircleShape,
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    modifier = Modifier.size(18.dp).padding(top = 2.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            "✓",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = point,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        lineHeight = 18.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // Important Terms Glossary
            if (topic.importantTerms.isNotEmpty()) {
                item {
                    Card(
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "📖 Essential Terminology & Definitions",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            topic.importantTerms.forEach { (term, definition) ->
                                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                                    Text(
                                        text = term,
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    )
                                    Text(
                                        text = definition,
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            lineHeight = 16.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Visual Learning Component
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "🎨 Visual & Technical Representation",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = topic.visualExplanation,
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        // Weave or Diagram Visualization
                        val weaveType = if (topic.title.contains("Twill", ignoreCase = true)) "Twill" else if (topic.title.contains("Satin", ignoreCase = true)) "Satin" else "Plain"
                        WeavePatternCanvas(weaveType = weaveType)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Interactive Technical Schematic",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                        )
                    }
                }
            }

            // Industrial Connection
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "🏭 Industry & Practical Relevance",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = topic.industrialRelevance,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 17.sp
                            )
                        )
                    }
                }
            }

            // Interactive Topic Quiz
            if (topic.quizQuestions.isNotEmpty()) {
                item {
                    Text(
                        text = "🧠 Test Your Knowledge",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }

                items(topic.quizQuestions) { question ->
                    InlineQuizItem(question = question)
                }
            }
        }
    }
}

@Composable
fun InlineQuizItem(question: QuizQuestion) {
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    val isAnswered = selectedOption != null

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth().testTag("inline_quiz_${question.id}")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = question.question,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            question.options.forEachIndexed { index, option ->
                val isSelected = selectedOption == index
                val isCorrect = index == question.correctIndex

                val buttonColor = when {
                    !isAnswered -> MaterialTheme.colorScheme.surfaceVariant
                    isCorrect -> SuccessGreenContainer
                    isSelected -> ErrorRedContainer
                    else -> MaterialTheme.colorScheme.surfaceVariant
                }

                val borderColor = when {
                    !isAnswered -> MaterialTheme.colorScheme.outlineVariant
                    isCorrect -> SuccessGreen
                    isSelected -> ErrorRed
                    else -> MaterialTheme.colorScheme.outlineVariant
                }

                Surface(
                    onClick = { if (!isAnswered) selectedOption = index },
                    shape = RoundedCornerShape(10.dp),
                    color = buttonColor,
                    border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${('A'.code + index).toChar()}.",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.width(24.dp)
                        )
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = if (isSelected || (isAnswered && isCorrect)) FontWeight.Bold else FontWeight.Normal,
                                color = if (isAnswered && isCorrect) SuccessGreenOnContainer else if (isAnswered && isSelected) ErrorRedOnContainer else MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }
                }
            }

            if (isAnswered) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (selectedOption == question.correctIndex) SuccessGreenContainer else ErrorRedContainer,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "💡 ${question.explanation}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (selectedOption == question.correctIndex) SuccessGreenOnContainer else ErrorRedOnContainer,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}
