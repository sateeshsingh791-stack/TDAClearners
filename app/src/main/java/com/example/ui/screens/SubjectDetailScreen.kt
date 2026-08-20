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
import com.example.data.model.Subject
import com.example.data.model.TopicContent
import com.example.data.repository.SyllabusRepository
import com.example.data.repository.UserLearningRepository
import com.example.ui.components.AppHeader
import com.example.ui.components.CategoryBadge
import com.example.ui.theme.TerracottaContainer
import com.example.ui.theme.TerracottaOnContainer
import com.example.ui.theme.TerracottaPrimary

@Composable
fun SubjectDetailScreen(
    subjectCode: String,
    onNavigateBack: () -> Unit,
    onNavigateToTopic: (String) -> Unit,
    onNavigateToPracticals: () -> Unit
) {
    val subject = SyllabusRepository.getSubjectByCode(subjectCode)
    val learningState by UserLearningRepository.learningState.collectAsState()

    if (subject == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Subject not found: $subjectCode")
        }
        return
    }

    val progress = UserLearningRepository.calculateSubjectProgress(subject.code)

    Scaffold(
        topBar = {
            AppHeader(
                title = subject.code,
                subtitle = "Semester ${subject.semesterNumber}",
                showBackButton = true,
                onBackClick = onNavigateBack
            )
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
            // Subject Title & Meta Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth().testTag("subject_header_card")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CategoryBadge(category = subject.category)
                            Text(
                                text = "${(progress * 100).toInt()}% Done",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TerracottaPrimary
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = subject.name,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = subject.overview,
                            style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF64748B))
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Marks & Credit Details Grid
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFFFFF7ED),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Credits: ${subject.totalCredits} (L:${subject.lectureCredits} T:${subject.tutorialCredits} P:${subject.practicalCredits})", fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                                    Text("Hours/Week: ${subject.hoursPerWeek}", fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Theory: ${subject.theoryMarks ?: "N/A"} | Pract: ${subject.practicalMarks ?: "N/A"}", fontSize = 11.sp, color = Color(0xFF64748B))
                                    Text("IA: ${subject.internalAssessmentMarks} | Total: ${subject.totalMarks}", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TerracottaPrimary)
                                }
                            }
                        }
                    }
                }
            }

            // Learning Objectives
            if (subject.learningObjectives.isNotEmpty()) {
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "🎯 Course Learning Objectives",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            subject.learningObjectives.forEach { obj ->
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text("•", color = TerracottaPrimary, fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 6.dp))
                                    Text(text = obj, fontSize = 12.sp, color = Color(0xFF334155))
                                }
                            }
                        }
                    }
                }
            }

            // Units & Topics Section Header
            item {
                Text(
                    text = "Syllabus Units & Study Modules",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }

            // Units List
            items(subject.units) { unit ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth().testTag("unit_card_${unit.unitNumber}")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = TerracottaContainer,
                                modifier = Modifier.size(24.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text("${unit.unitNumber}", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = TerracottaOnContainer)
                                }
                            }
                            Text(
                                text = "Unit ${unit.unitNumber}: ${unit.title}",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = unit.description,
                            style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF64748B)),
                            fontSize = 11.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Topics in this Unit
                        unit.topics.forEach { topic ->
                            val isCompleted = learningState.completedTopicIds.contains(topic.id)
                            Surface(
                                onClick = { onNavigateToTopic(topic.id) },
                                shape = RoundedCornerShape(12.dp),
                                color = if (isCompleted) Color(0xFFF0FDF4) else Color(0xFFF8FAFC),
                                border = androidx.compose.foundation.BorderStroke(
                                    1.dp,
                                    if (isCompleted) Color(0xFFBBF7D0) else Color(0xFFE2E8F0)
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .testTag("topic_item_${topic.id}")
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = topic.title,
                                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = "${topic.keyPoints.size} Key concepts • Interactive Visuals",
                                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = Color(0xFF64748B))
                                        )
                                    }
                                    if (isCompleted) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = "Completed",
                                            tint = Color(0xFF16A34A),
                                            modifier = Modifier.size(18.dp)
                                        )
                                    } else {
                                        Icon(
                                            imageVector = Icons.Default.ChevronRight,
                                            contentDescription = "Open",
                                            tint = Color(0xFF94A3B8),
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Practical Lab Activities Card
            if (subject.practicals.isNotEmpty()) {
                item {
                    Card(
                        onClick = onNavigateToPracticals,
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = TerracottaContainer.copy(alpha = 0.6f)),
                        modifier = Modifier.fillMaxWidth().testTag("subject_practicals_card")
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
                                    text = "🧪 Laboratory Practicals (${subject.practicals.size})",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = TerracottaOnContainer
                                    )
                                )
                                Text(
                                    text = "Hands-on burning tests, color mixing, and seam samples.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TerracottaOnContainer.copy(alpha = 0.8f),
                                        fontSize = 11.sp
                                    )
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Open Practicals",
                                tint = TerracottaOnContainer
                            )
                        }
                    }
                }
            }
        }
    }
}
