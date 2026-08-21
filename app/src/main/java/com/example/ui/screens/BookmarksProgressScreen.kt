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
import com.example.data.repository.SyllabusRepository
import com.example.data.repository.UserLearningRepository
import com.example.ui.components.AppHeader
import com.example.ui.theme.*

@Composable
fun BookmarksProgressScreen(
    onNavigateBack: () -> Unit,
    onNavigateToTopic: (String) -> Unit
) {
    val learningState by UserLearningRepository.learningState.collectAsState()
    val allTopics = SyllabusRepository.getAllSubjects().flatMap { it.units.flatMap { u -> u.topics } }

    val bookmarkedTopics = allTopics.filter { learningState.bookmarkedTopicIds.contains(it.id) }
    val completedTopics = allTopics.filter { learningState.completedTopicIds.contains(it.id) }

    Scaffold(
        topBar = {
            AppHeader(
                title = "My Learning & Bookmarks",
                subtitle = "TDAClearners • Progress Tracker",
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
            contentPadding = PaddingValues(top = 10.dp, bottom = 28.dp)
        ) {
            // Summary Progress Card
            item {
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth().testTag("bookmarks_summary_card")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Learning Portfolio",
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.White, fontWeight = FontWeight.ExtraBold)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("${completedTopics.size}", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
                                Text("Completed", color = Color.White.copy(alpha = 0.9f), fontSize = 11.sp)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("${bookmarkedTopics.size}", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
                                Text("Bookmarked", color = Color.White.copy(alpha = 0.9f), fontSize = 11.sp)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("${learningState.quizScores.size}", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
                                Text("Quizzes", color = Color.White.copy(alpha = 0.9f), fontSize = 11.sp)
                            }
                        }
                    }
                }
            }

            // Bookmarked Topics Section
            item {
                Text(
                    text = "Saved Bookmarks (${bookmarkedTopics.size})",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }

            if (bookmarkedTopics.isEmpty()) {
                item {
                    Text(
                        "No topics bookmarked yet. Tap the bookmark icon while studying any topic to save it for quick revision.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp
                    )
                }
            } else {
                items(bookmarkedTopics) { topic ->
                    Card(
                        onClick = { onNavigateToTopic(topic.id) },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        modifier = Modifier.fillMaxWidth().testTag("saved_bookmark_${topic.id}")
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = topic.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${topic.keyPoints.size} Key Concepts • Revision Notes",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            IconButton(onClick = { UserLearningRepository.toggleBookmark(topic.id) }) {
                                Icon(Icons.Default.Bookmark, contentDescription = "Remove Bookmark", tint = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }
            }

            // Exam Revision Checklist
            item {
                Text(
                    text = "📝 Semester Exam Revision Checklist",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }

            val checklist = listOf(
                "Classification of Natural & Synthetic Fibres (BVTD 111)",
                "Sewing Techniques & Machine Anatomy (BVTD 112)",
                "Introduction to Entrepreneurship & MSME (BVTD 113)",
                "Burning Test & Microscopic Analysis Lab Procedures (BVTD 112)",
                "5 Stages of the Fashion Life Cycle (BVTD 121)",
                "Plain (1/1) vs Twill (2/2) Weave Interlacement Graphing (BVTD 123)",
                "Traditional Phulkari Motifs & Punjab Heritage (BHPB 1101)"
            )

            items(checklist) { item ->
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SuccessGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = item,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
