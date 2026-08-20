package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.data.model.Subject
import com.example.data.model.TopicContent
import com.example.data.repository.SyllabusRepository
import com.example.ui.components.AppHeader
import com.example.ui.theme.TerracottaContainer
import com.example.ui.theme.TerracottaOnContainer
import com.example.ui.theme.TerracottaPrimary

@Composable
fun GlobalSearchScreen(
    onNavigateBack: () -> Unit,
    onNavigateToSubject: (String) -> Unit,
    onNavigateToTopic: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val allSubjects = SyllabusRepository.getAllSubjects()
    val allTopics = allSubjects.flatMap { it.units.flatMap { u -> u.topics } }

    val matchedSubjects = remember(searchQuery) {
        if (searchQuery.isBlank()) emptyList()
        else allSubjects.filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
            it.code.contains(searchQuery, ignoreCase = true) ||
            it.overview.contains(searchQuery, ignoreCase = true)
        }
    }

    val matchedTopics = remember(searchQuery) {
        if (searchQuery.isBlank()) emptyList()
        else allTopics.filter {
            it.title.contains(searchQuery, ignoreCase = true) ||
            it.overview.contains(searchQuery, ignoreCase = true) ||
            it.importantTerms.keys.any { term -> term.contains(searchQuery, ignoreCase = true) }
        }
    }

    Scaffold(
        topBar = {
            AppHeader(
                title = "Global Syllabus Search",
                subtitle = "Khalsa College Course Search",
                showBackButton = true,
                onBackClick = onNavigateBack
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search subjects, fibres, weaves, seams, terms...", fontSize = 12.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TerracottaPrimary) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .testTag("global_search_input"),
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )

            if (searchQuery.isBlank()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Default.ManageSearch, contentDescription = null, tint = Color(0xFFCBD5E1), modifier = Modifier.size(54.dp))
                        Text("Search syllabus topics, subjects or definitions", color = Color(0xFF94A3B8), fontSize = 13.sp)
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 20.dp)
                ) {
                    if (matchedSubjects.isNotEmpty()) {
                        item {
                            Text(
                                text = "Subjects (${matchedSubjects.size})",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = TerracottaPrimary)
                            )
                        }
                        items(matchedSubjects) { sub ->
                            Card(
                                onClick = { onNavigateToSubject(sub.code) },
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(text = "${sub.code}: ${sub.name}", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    Text(text = sub.overview, fontSize = 11.sp, color = Color(0xFF64748B), maxLines = 2)
                                }
                            }
                        }
                    }

                    if (matchedTopics.isNotEmpty()) {
                        item {
                            Text(
                                text = "Topics & Concepts (${matchedTopics.size})",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = TerracottaPrimary),
                                modifier = Modifier.padding(top = 10.dp)
                            )
                        }
                        items(matchedTopics) { topic ->
                            Card(
                                onClick = { onNavigateToTopic(topic.id) },
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(text = topic.title, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    Text(text = topic.overview, fontSize = 11.sp, color = Color(0xFF64748B), maxLines = 2)
                                }
                            }
                        }
                    }

                    if (matchedSubjects.isEmpty() && matchedTopics.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("No matching syllabus results found for \"$searchQuery\"", color = Color(0xFF94A3B8), fontSize = 13.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
