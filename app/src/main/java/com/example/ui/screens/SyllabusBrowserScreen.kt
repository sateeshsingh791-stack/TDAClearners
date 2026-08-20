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
import com.example.data.model.AcademicYear
import com.example.data.model.Semester
import com.example.data.model.Subject
import com.example.data.repository.SyllabusRepository
import com.example.ui.components.AppHeader
import com.example.ui.components.CategoryBadge
import com.example.ui.theme.TerracottaContainer
import com.example.ui.theme.TerracottaOnContainer
import com.example.ui.theme.TerracottaPrimary

@Composable
fun SyllabusBrowserScreen(
    onNavigateBack: () -> Unit,
    onNavigateToSubject: (String) -> Unit
) {
    var selectedYearIndex by remember { mutableIntStateOf(0) }
    var selectedSemesterIndex by remember { mutableIntStateOf(0) }

    val currentYear = SyllabusRepository.academicYears[selectedYearIndex]
    val semesters = currentYear.semesters

    Scaffold(
        topBar = {
            AppHeader(
                title = "Course Scheme & Syllabus",
                subtitle = "Khalsa College Curriculum",
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
            // Year Selector Tabs (Year 1, Year 2, Year 3 scalable architecture)
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
                        },
                        label = { Text("Year ${year.yearNumber}", fontWeight = FontWeight.Bold, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = TerracottaPrimary,
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
                    contentColor = TerracottaPrimary,
                    modifier = Modifier.fillMaxWidth().testTag("semester_tabs")
                ) {
                    semesters.forEachIndexed { index, sem ->
                        Tab(
                            selected = selectedSemesterIndex == index,
                            onClick = { selectedSemesterIndex = index },
                            text = { Text(sem.title, fontWeight = FontWeight.Bold, fontSize = 13.sp) }
                        )
                    }
                }

                val activeSemester = semesters[selectedSemesterIndex]

                // Semester Metrics Summary Card
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = TerracottaContainer.copy(alpha = 0.45f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MetricItem("Total Credits", "${activeSemester.totalCredits}")
                        VerticalDivider(modifier = Modifier.height(24.dp))
                        MetricItem("Max Marks", "${activeSemester.totalMarks}")
                        VerticalDivider(modifier = Modifier.height(24.dp))
                        MetricItem("Hours/Wk", "${activeSemester.totalHoursPerWeek}")
                    }
                }

                // Subject List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 20.dp)
                ) {
                    items(activeSemester.subjects) { subject ->
                        SyllabusSubjectCard(
                            subject = subject,
                            onClick = { onNavigateToSubject(subject.code) }
                        )
                    }
                }
            } else {
                // Future Year Scalability Placeholder
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = TerracottaContainer,
                            modifier = Modifier.size(64.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.School,
                                    contentDescription = "Upcoming Year",
                                    tint = TerracottaOnContainer,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                        Text(
                            text = "${currentYear.title} Roadmap",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "The 1st-year NEP curriculum has been fully integrated. Year 2 and Year 3 modules will unlock automatically as the university releases further semester syllabi.",
                            style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF64748B)),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
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
                color = TerracottaPrimary
            )
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                color = Color(0xFF64748B)
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
        modifier = Modifier.fillMaxWidth().testTag("syllabus_subject_${subject.code.replace(" ", "_")}")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CategoryBadge(category = subject.category)
                Text(
                    text = subject.syllabusPageRef,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color(0xFF94A3B8),
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${subject.code}: ${subject.name}",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = subject.overview,
                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF64748B)),
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Marks & Credits breakdown row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF8FAFC), RoundedCornerShape(8.dp))
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "L-T-P: ${subject.lectureCredits}-${subject.tutorialCredits}-${subject.practicalCredits} (${subject.totalCredits} Cr)",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF334155)
                )
                Text(
                    text = "Marks: Th ${subject.theoryMarks ?: "-"} | P ${subject.practicalMarks ?: "-"} | IA ${subject.internalAssessmentMarks} = ${subject.totalMarks}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TerracottaPrimary
                )
            }
        }
    }
}
