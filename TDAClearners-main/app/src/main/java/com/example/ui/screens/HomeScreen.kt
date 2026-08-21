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
import com.example.ui.components.OfficialSyllabusBadge
import com.example.ui.components.QuickActionButton
import com.example.ui.components.TDAClearnersLogo
import com.example.ui.theme.*

@Composable
fun HomeScreen(
    onNavigateToSubject: (String) -> Unit,
    onNavigateToSyllabus: () -> Unit,
    onNavigateToPracticals: () -> Unit,
    onNavigateToQuizHub: () -> Unit,
    onNavigateToFlashcards: () -> Unit,
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
        // Top Branding Header with TDAClearners Official Logo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TDAClearnersLogo(
                    size = 44.dp,
                    showTextDetails = false
                )
                Column {
                    Text(
                        text = "TDAClearners",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.5.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                    Text(
                        text = "B.Voc Textile Design & Apparel Tech",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 0.2.sp
                        )
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "KC · GNDU",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Hero Course Progress Card (High Density)
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.primary,
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
                            text = "LEARN • PRACTICE • CREATE • GROW",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.White.copy(alpha = 0.9f),
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 9.sp,
                                letterSpacing = 0.8.sp
                            )
                        )
                        Text(
                            text = "TDAClearners · Sem $currentSemesterNum",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        Text(
                            text = "${currentSemester.totalCredits} Credits • ${currentSemester.totalMarks} Total Marks (NEP 2020)",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.White.copy(alpha = 0.95f),
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }

                    // Progress Ring
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f))
                    ) {
                        CircularProgressIndicator(
                            progress = { if (progressPercentage == 0) 0.05f else semesterProgress },
                            modifier = Modifier.fillMaxSize().padding(4.dp),
                            color = Color.White,
                            trackColor = Color.White.copy(alpha = 0.3f),
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
                    trackColor = Color.White.copy(alpha = 0.35f)
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
                            containerColor = Color.White.copy(alpha = 0.25f),
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
                        label = { Text("Sem ${if (currentSemesterNum == 1) "II" else "I"}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White) },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.White.copy(alpha = 0.2f),
                            selectedContainerColor = Color.White.copy(alpha = 0.4f)
                        ),
                        border = null,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.height(34.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Quick Actions Row (AI Assist, Practicals, Quiz Hub, Flashcards)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            QuickActionButton(
                icon = Icons.Default.Lightbulb,
                label = "AI Tutor",
                onClick = onNavigateToAiAssist,
                modifier = Modifier.weight(1f).testTag("quick_ai_assist")
            )
            QuickActionButton(
                icon = Icons.Default.Science,
                label = "Lab Work",
                onClick = onNavigateToPracticals,
                modifier = Modifier.weight(1f).testTag("quick_practicals")
            )
            QuickActionButton(
                icon = Icons.Default.Quiz,
                label = "Quiz Hub",
                onClick = onNavigateToQuizHub,
                modifier = Modifier.weight(1f).testTag("quick_quiz_hub")
            )
            QuickActionButton(
                icon = Icons.Default.Style,
                label = "Flashcards",
                onClick = onNavigateToFlashcards,
                modifier = Modifier.weight(1f).testTag("quick_flashcards")
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
                text = "Course Subjects (Sem $currentSemesterNum)",
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
                        color = MaterialTheme.colorScheme.primary,
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
        subject.code.contains("111") && subject.code.startsWith("BVTD") -> "🎨"
        subject.code.contains("112") -> "🔬"
        subject.code.contains("113") -> "🪡"
        subject.code.contains("114") -> "💼"
        subject.code.contains("121") && subject.code.startsWith("BVTD") -> "👗"
        subject.code.contains("122") -> "✂️"
        subject.code.contains("123") -> "🧵"
        subject.code.contains("124") -> "📈"
        subject.code.contains("CS") -> "💻"
        subject.code.contains("BCSV") -> "🗣️"
        subject.code.contains("BHPB") -> "🏛️"
        subject.code.contains("ZDA") -> "🛡️"
        else -> "🌿"
    }

    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 2.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .fillMaxWidth()
            .height(144.dp)
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
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = iconEmoji, fontSize = 15.sp)
                    }
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.padding(2.dp)
                ) {
                    Text(
                        text = "${subject.totalCredits} Cr",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                }
            }

            Column {
                Text(
                    text = subject.code,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Text(
                    text = subject.name,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 15.sp
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
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

                // Progress Indicator Dot
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                progress >= 0.8f -> SuccessGreen
                                progress > 0.1f -> WarningAmber
                                else -> MaterialTheme.colorScheme.outline
                            }
                        )
                )
            }
        }
    }
}
