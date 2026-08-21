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
import com.example.data.model.SubjectType
import com.example.data.repository.SyllabusRepository
import com.example.ui.components.AppHeader
import com.example.ui.components.CategoryBadge
import com.example.ui.components.OfficialSyllabusBadge
import com.example.ui.theme.*

@Composable
fun SyllabusBrowserScreen(
    onNavigateBack: () -> Unit,
    onNavigateToSubject: (String) -> Unit
) {
    var selectedYearIndex by remember { mutableIntStateOf(0) }
    var selectedSemesterIndex by remember { mutableIntStateOf(0) }
    var selectedTypeFilter by remember { mutableStateOf<SubjectType?>(null) } // null = All

    val currentYear = SyllabusRepository.academicYears[selectedYearIndex]
    val semesters = currentYear.semesters

    Scaffold(
        topBar = {
            AppHeader(
                title = "Course Scheme & Syllabus",
                subtitle = "TDAClearners • Curriculum (NEP)",
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
            // Year Selector Tabs (Year 1, Year 2, Year 3)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SyllabusRepository.academicYears.forEachIndexed { index, year ->
                    FilterChip(
                        selected = selectedYearIndex == index,
                        onClick = {
                            selectedYearIndex = index
                            selectedSemesterIndex = 0
                            selectedTypeFilter = null
                        },
                        label = { Text("Year ${year.yearNumber}", fontWeight = FontWeight.Bold, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        ),
                        modifier = Modifier.weight(1f).testTag("year_chip_${year.yearNumber}")
                    )
                }
            }

            if (currentYear.isSyllabusAvailable && semesters.isNotEmpty()) {
                // Semester Switch Tabs
                ScrollableTabRow(
                    selectedTabIndex = selectedSemesterIndex,
                    edgePadding = 0.dp,
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth().testTag("semester_tabs")
                ) {
                    semesters.forEachIndexed { index, sem ->
                        Tab(
                            selected = selectedSemesterIndex == index,
                            onClick = { 
                                selectedSemesterIndex = index 
                                selectedTypeFilter = null
                            },
                            text = {
                                Text(
                                    sem.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = if (selectedSemesterIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        )
                    }
                }

                val activeSemester = semesters[selectedSemesterIndex]

                // Semester Metrics Summary Card
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MetricItem("Total Credits", "${activeSemester.totalCredits}")
                        VerticalDivider(modifier = Modifier.height(24.dp), color = MaterialTheme.colorScheme.outlineVariant)
                        MetricItem("Max Marks", "${activeSemester.totalMarks}")
                        VerticalDivider(modifier = Modifier.height(24.dp), color = MaterialTheme.colorScheme.outlineVariant)
                        MetricItem("Hours/Wk", "${activeSemester.totalHoursPerWeek}")
                    }
                }

                // Filter Row: All, Theory, Practical
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedTypeFilter == null,
                        onClick = { selectedTypeFilter = null },
                        label = { Text("All (${activeSemester.subjects.size})", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )
                    FilterChip(
                        selected = selectedTypeFilter == SubjectType.THEORY,
                        onClick = { selectedTypeFilter = SubjectType.THEORY },
                        label = { Text("📚 Theory (${activeSemester.subjects.count { it.type == SubjectType.THEORY }})", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )
                    FilterChip(
                        selected = selectedTypeFilter == SubjectType.PRACTICAL,
                        onClick = { selectedTypeFilter = SubjectType.PRACTICAL },
                        label = { Text("🧵 Practical (${activeSemester.subjects.count { it.type == SubjectType.PRACTICAL || it.type == SubjectType.THEORY_AND_PRACTICAL }})", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )
                }

                val filteredSubjects = activeSemester.subjects.filter { subject ->
                    when (selectedTypeFilter) {
                        null -> true
                        SubjectType.THEORY -> subject.type == SubjectType.THEORY
                        SubjectType.PRACTICAL -> subject.type == SubjectType.PRACTICAL || subject.type == SubjectType.THEORY_AND_PRACTICAL
                        else -> true
                    }
                }

                // Subject List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 20.dp)
                ) {
                    items(filteredSubjects) { subject ->
                        SyllabusSubjectCard(
                            subject = subject,
                            onClick = { onNavigateToSubject(subject.code) }
                        )
                    }
                }
            } else {
                // Semesters 3-6 / Coming Soon Placeholder
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(64.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "Coming Soon",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                        Text(
                            text = "${currentYear.title}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                        ) {
                            Text(
                                text = "🔒 Coming Soon: Learning resources and syllabus for this semester will be added soon as per university release.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    lineHeight = 20.sp
                                ),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MetricItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
            )
        )
    }
}

@Composable
fun SyllabusSubjectCard(
    subject: Subject,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth().testTag("syllabus_subject_${subject.code.replace(" ", "_")}")
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
                    CategoryBadge(category = subject.category)
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (subject.type == SubjectType.PRACTICAL) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Text(
                            text = if (subject.type == SubjectType.PRACTICAL) "🧵 Practical" else if (subject.type == SubjectType.THEORY_AND_PRACTICAL) "📚+🧵 Th+Prac" else "📚 Theory",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (subject.type == SubjectType.PRACTICAL) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    OfficialSyllabusBadge(label = "Official")
                }
                Text(
                    text = subject.syllabusPageRef,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${subject.code}: ${subject.name}",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = subject.overview,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 16.sp
                ),
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Marks & Credits breakdown row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "L-T-P: ${subject.lectureCredits}-${subject.tutorialCredits}-${subject.practicalCredits} (${subject.totalCredits} Cr)",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Marks: Th ${subject.theoryMarks ?: "-"} | P ${subject.practicalMarks ?: "-"} | IA ${subject.internalAssessmentMarks} = ${subject.totalMarks}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
