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
import com.example.ui.components.WeavePatternCanvas
import com.example.ui.theme.TerracottaContainer
import com.example.ui.theme.TerracottaOnContainer
import com.example.ui.theme.TerracottaPrimary

@Composable
fun TopicLearningScreen(
    topicId: String,
    onNavigateBack: () -> Unit,
    onAskAiAboutTopic: (String) -> Unit
) {
    val allTopics = SyllabusRepository.getAllSubjects().flatMap { it.units.flatMap { u -> u.topics } }
    val topic = allTopics.find { it.id == topicId } ?: allTopics.firstOrNull()
    val learningState by UserLearningRepository.learningState.collectAsState()

    if (topic == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Topic not found")
        }
        return
    }

    val isCompleted = learningState.completedTopicIds.contains(topic.id)
    val isBookmarked = learningState.bookmarkedTopicIds.contains(topic.id)

    Scaffold(
        topBar = {
            AppHeader(
                title = topic.title,
                subtitle = "Unit ${topic.unitNumber} Learning Module",
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
                            tint = if (isBookmarked) TerracottaPrimary else Color(0xFF64748B)
                        )
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { onAskAiAboutTopic(topic.title) },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f).testTag("ask_ai_topic_btn")
                    ) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp), tint = TerracottaPrimary)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Explain with AI", fontSize = 12.sp, color = TerracottaPrimary, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = { UserLearningRepository.toggleTopicCompletion(topic.id) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isCompleted) Color(0xFF16A34A) else TerracottaPrimary
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f).testTag("complete_topic_btn")
                    ) {
                        Icon(
                            imageVector = if (isCompleted) Icons.Default.Check else Icons.Default.CheckCircleOutline,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(if (isCompleted) "Completed" else "Mark Complete", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            // Header Overview Card
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth().testTag("topic_overview_card")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = topic.title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = topic.overview,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color(0xFF334155),
                                lineHeight = 20.sp
                            )
                        )
                    }
                }
            }

            // Key Study Points
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "📌 Key Academic Points",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = TerracottaPrimary
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
                                    color = TerracottaContainer,
                                    modifier = Modifier.size(16.dp).padding(top = 2.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text("✓", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TerracottaOnContainer)
                                    }
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = point,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = Color(0xFF1E293B),
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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF7ED)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "📖 Essential Terminology",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TerracottaOnContainer
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            topic.importantTerms.forEach { (term, definition) ->
                                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                                    Text(
                                        text = term,
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = TerracottaPrimary
                                        )
                                    )
                                    Text(
                                        text = definition,
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color(0xFF475569)
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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "🎨 Visual & Structural Representation",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = topic.visualExplanation,
                            style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF64748B)),
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        // Weave Canvas or Visual Representation
                        val weaveType = if (topic.title.contains("Twill", ignoreCase = true)) "Twill" else if (topic.title.contains("Satin", ignoreCase = true)) "Satin" else "Plain"
                        WeavePatternCanvas(weaveType = weaveType)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Interactive Weave Structure Simulation (Warp vs Weft interlacing)",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 10.sp,
                                color = Color(0xFF94A3B8)
                            )
                        )
                    }
                }
            }

            // Industrial Connection
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "🏭 Industry & Market Relevance",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F172A)
                            )
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = topic.industrialRelevance,
                            style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF334155))
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
        modifier = Modifier.fillMaxWidth().testTag("inline_quiz_${question.id}")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = question.question,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.height(10.dp))

            question.options.forEachIndexed { index, option ->
                val isSelected = selectedOption == index
                val isCorrect = index == question.correctIndex

                val buttonColor = when {
                    !isAnswered -> Color(0xFFF8FAFC)
                    isCorrect -> Color(0xFFDCFCE7)
                    isSelected -> Color(0xFFFEE2E2)
                    else -> Color(0xFFF8FAFC)
                }

                val borderColor = when {
                    !isAnswered -> Color(0xFFE2E8F0)
                    isCorrect -> Color(0xFF16A34A)
                    isSelected -> Color(0xFFDC2626)
                    else -> Color(0xFFE2E8F0)
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
                            color = TerracottaPrimary,
                            modifier = Modifier.width(20.dp)
                        )
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = if (isSelected || (isAnswered && isCorrect)) FontWeight.Bold else FontWeight.Normal
                            )
                        )
                    }
                }
            }

            if (isAnswered) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (selectedOption == question.correctIndex) Color(0xFFF0FDF4) else Color(0xFFFEF2F2),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "💡 ${question.explanation}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (selectedOption == question.correctIndex) Color(0xFF15803D) else Color(0xFFB91C1C),
                            fontSize = 11.sp
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
