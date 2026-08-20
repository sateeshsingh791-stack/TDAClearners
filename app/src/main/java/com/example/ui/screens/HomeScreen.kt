package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Subject
import com.example.data.repository.SyllabusRepository
import com.example.data.repository.UserLearningRepository
import com.example.ui.components.QuickActionButton
import com.example.ui.theme.TerracottaContainer
import com.example.ui.theme.TerracottaOnContainer
import com.example.ui.theme.TerracottaPrimary

@Composable
fun HomeScreen(
    onNavigateToSubject: (String) -> Unit,
    onNavigateToSyllabus: () -> Unit,
    onNavigateToPracticals: () -> Unit,
    onNavigateToQuizHub: () -> Unit,
    onNavigateToAiAssist: () -> Unit,
    onNavigateToTopic: (String) -> Unit
) {
    val learningState by UserLearningRepository.learningState.collectAsState()
    val currentSemesterNum = learningState.selectedSemester
    val currentSemester = SyllabusRepository.semesters.find { it.number == currentSemesterNum }
        ?: SyllabusRepository.semesters.first()
    val semesterProgress = UserLearningRepository.calculateSemesterProgress(currentSemesterNum)
    val progressPercentage = (semesterProgress * 100).toInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        // Top College Branding Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "KHALSA COLLEGE, AMRITSAR",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp,
                        color = TerracottaPrimary
                    )
                )
                Text(
                    text = "B.Voc Textile Design",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
            Surface(
                shape = CircleShape,
                color = TerracottaContainer,
                border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
                modifier = Modifier.size(40.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "KC",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = TerracottaOnContainer
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Hero Course Progress Card (High Density)
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = TerracottaPrimary,
            shadowElevation = 4.dp,
            modifier = Modifier.fillMaxWidth().testTag("hero_progress_card")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Course Progress",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.White.copy(alpha = 0.85f),
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Text(
                            text = "Year 1 · Sem $currentSemesterNum",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "${currentSemester.totalCredits} Credits • ${currentSemester.totalMarks} Marks",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.White.copy(alpha = 0.9f)
                            )
                        )
                    }

                    // Progress Ring
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.15f))
                    ) {
                        CircularProgressIndicator(
                            progress = { if (progressPercentage == 0) 0.05f else semesterProgress },
                            modifier = Modifier.fillMaxSize().padding(4.dp),
                            color = Color.White,
                            trackColor = Color.White.copy(alpha = 0.25f),
                            strokeWidth = 4.dp
                        )
                        Text(
                            text = "$progressPercentage%",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Progress Bar
                LinearProgressIndicator(
                    progress = { semesterProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.3f)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Action Chips
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onNavigateToSyllabus,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White.copy(alpha = 0.2f),
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.height(34.dp).testTag("view_syllabus_btn")
                    ) {
                        Text("View Syllabus", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = { onNavigateToSubject(currentSemester.subjects.first().code) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = TerracottaPrimary
                        ),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.height(34.dp).testTag("continue_study_btn")
                    ) {
                        Text("Continue Study", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    // Semester Toggle Chip
                    FilterChip(
                        selected = currentSemesterNum == 2,
                        onClick = {
                            UserLearningRepository.selectSemester(if (currentSemesterNum == 1) 2 else 1)
                        },
                        label = { Text("Sem ${if (currentSemesterNum == 1) "II" else "I"}", fontSize = 10.sp, color = Color.White) },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.White.copy(alpha = 0.15f),
                            selectedContainerColor = Color.White.copy(alpha = 0.3f)
                        ),
                        border = null,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.height(34.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Quick Actions Row (AI Assist, Practicals, Quiz Hub)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            QuickActionButton(
                icon = Icons.Default.Lightbulb,
                label = "AI Assist",
                onClick = onNavigateToAiAssist,
                modifier = Modifier.weight(1f).testTag("quick_ai_assist")
            )
            QuickActionButton(
                icon = Icons.Default.Science,
                label = "Practicals",
                onClick = onNavigateToPracticals,
                modifier = Modifier.weight(1f).testTag("quick_practicals")
            )
            QuickActionButton(
                icon = Icons.Default.Quiz,
                label = "Quiz Hub",
                onClick = onNavigateToQuizHub,
                modifier = Modifier.weight(1f).testTag("quick_quiz_hub")
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Section Title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Academic Subjects (Sem $currentSemesterNum)",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            TextButton(
                onClick = onNavigateToSyllabus,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "View All (${currentSemester.subjects.size})",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TerracottaPrimary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        // Subjects High-Density Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(currentSemester.subjects) { subject ->
                SubjectCompactCard(
                    subject = subject,
                    onClick = { onNavigateToSubject(subject.code) }
                )
            }
        }
    }
}

@Composable
fun SubjectCompactCard(
    subject: Subject,
    onClick: () -> Unit
) {
    val progress = UserLearningRepository.calculateSubjectProgress(subject.code)
    val iconEmoji = when {
        subject.code.contains("111") || subject.code.contains("121") -> "🎨"
        subject.code.contains("112") || subject.code.contains("123") -> "🔬"
        subject.code.contains("113") || subject.code.contains("122") -> "✂️"
        subject.code.contains("114") || subject.code.contains("124") -> "📈"
        subject.code.contains("CS") -> "💻"
        subject.code.contains("BCSV") -> "🗣️"
        subject.code.contains("BHPB") -> "🪡"
        else -> "🌿"
    }

    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 1.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF1E7E2)),
        modifier = Modifier
            .fillMaxWidth()
            .height(138.dp)
            .testTag("subject_card_${subject.code.replace(" ", "_")}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = TerracottaContainer.copy(alpha = 0.5f),
                    modifier = Modifier.size(30.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = iconEmoji, fontSize = 14.sp)
                    }
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFFF3EBE6),
                    modifier = Modifier.padding(2.dp)
                ) {
                    Text(
                        text = "${subject.totalCredits} Cr",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = TerracottaPrimary
                        ),
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                    )
                }
            }

            Column {
                Text(
                    text = subject.code,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF94A3B8)
                    )
                )
                Text(
                    text = subject.name,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 14.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${subject.units.size} Units • ${subject.type.label}",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 9.sp,
                        color = Color(0xFF64748B)
                    )
                )

                // Progress Indicator Dot
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                progress >= 0.8f -> Color(0xFF22C55E)
                                progress > 0.1f -> Color(0xFFEAB308)
                                else -> Color(0xFFCBD5E1)
                            }
                        )
                )
            }
        }
    }
}
