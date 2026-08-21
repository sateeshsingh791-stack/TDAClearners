package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.screens.*
import com.example.ui.theme.BVocTextileDesignTheme
import com.example.ui.theme.TerracottaContainer
import com.example.ui.theme.TerracottaOnContainer
import com.example.ui.theme.TerracottaPrimary

sealed class Screen {
    data object Home : Screen()
    data object Syllabus : Screen()
    data object Search : Screen()
    data object Career : Screen()
    data object Practicals : Screen()
    data class QuizHub(
        val initialSemester: Int? = null,
        val initialSubject: String? = null,
        val initialUnit: Int? = null,
        val initialTopic: String? = null
    ) : Screen()
    data class Flashcards(
        val initialSemester: Int? = null,
        val initialSubject: String? = null,
        val initialUnit: Int? = null,
        val initialTopic: String? = null
    ) : Screen()
    data object Resources : Screen()
    data object Bookmarks : Screen()
    data class SubjectDetail(val code: String) : Screen()
    data class TopicLearning(val topicId: String) : Screen()
    data class AiTutor(val query: String? = null) : Screen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BVocTextileDesignTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    val screenStack = remember { mutableStateListOf<Screen>(Screen.Home) }

    fun navigateTo(screen: Screen) {
        screenStack.add(screen)
        currentScreen = screen
    }

    fun navigateBack() {
        if (screenStack.size > 1) {
            screenStack.removeAt(screenStack.size - 1)
            currentScreen = screenStack.last()
        } else {
            currentScreen = Screen.Home
        }
    }

    Scaffold(
        bottomBar = {
            val isMainTab = currentScreen is Screen.Home ||
                    currentScreen is Screen.Syllabus ||
                    currentScreen is Screen.Search ||
                    currentScreen is Screen.Career

            if (isMainTab) {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 8.dp,
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth().height(64.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BottomNavItem(
                            label = "Home",
                            icon = Icons.Default.Home,
                            isSelected = currentScreen is Screen.Home,
                            onClick = {
                                screenStack.clear()
                                screenStack.add(Screen.Home)
                                currentScreen = Screen.Home
                            },
                            testTag = "nav_home"
                        )
                        BottomNavItem(
                            label = "Curriculum",
                            icon = Icons.Default.AutoStories,
                            isSelected = currentScreen is Screen.Syllabus,
                            onClick = {
                                screenStack.clear()
                                screenStack.add(Screen.Syllabus)
                                currentScreen = Screen.Syllabus
                            },
                            testTag = "nav_courses"
                        )
                        BottomNavItem(
                            label = "Search",
                            icon = Icons.Default.Search,
                            isSelected = currentScreen is Screen.Search,
                            onClick = {
                                screenStack.clear()
                                screenStack.add(Screen.Search)
                                currentScreen = Screen.Search
                            },
                            testTag = "nav_search"
                        )
                        BottomNavItem(
                            label = "Careers",
                            icon = Icons.Default.WorkOutline,
                            isSelected = currentScreen is Screen.Career,
                            onClick = {
                                screenStack.clear()
                                screenStack.add(Screen.Career)
                                currentScreen = Screen.Career
                            },
                            testTag = "nav_career"
                        )
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(targetState = currentScreen, label = "screen_transition") { screen ->
                when (screen) {
                    is Screen.Home -> HomeScreen(
                        onNavigateToSubject = { code -> navigateTo(Screen.SubjectDetail(code)) },
                        onNavigateToSyllabus = { navigateTo(Screen.Syllabus) },
                        onNavigateToPracticals = { navigateTo(Screen.Practicals) },
                        onNavigateToQuizHub = { navigateTo(Screen.QuizHub()) },
                        onNavigateToFlashcards = { navigateTo(Screen.Flashcards()) },
                        onNavigateToAiAssist = { navigateTo(Screen.AiTutor()) },
                        onNavigateToTopic = { topicId -> navigateTo(Screen.TopicLearning(topicId)) }
                    )
                    is Screen.Syllabus -> SyllabusBrowserScreen(
                        onNavigateBack = { navigateBack() },
                        onNavigateToSubject = { code -> navigateTo(Screen.SubjectDetail(code)) }
                    )
                    is Screen.SubjectDetail -> SubjectDetailScreen(
                        subjectCode = screen.code,
                        onNavigateBack = { navigateBack() },
                        onNavigateToTopic = { topicId -> navigateTo(Screen.TopicLearning(topicId)) },
                        onNavigateToPracticals = { navigateTo(Screen.Practicals) },
                        onNavigateToQuiz = { sCode -> navigateTo(Screen.QuizHub(initialSubject = sCode)) },
                        onNavigateToFlashcards = { sCode -> navigateTo(Screen.Flashcards(initialSubject = sCode)) }
                    )
                    is Screen.TopicLearning -> TopicLearningScreen(
                        topicId = screen.topicId,
                        onNavigateBack = { navigateBack() },
                        onAskAiTutor = { query -> navigateTo(Screen.AiTutor(query)) },
                        onPracticeQuiz = { sem, sCode, uNum, tId ->
                            navigateTo(Screen.QuizHub(initialSemester = sem, initialSubject = sCode, initialUnit = uNum, initialTopic = tId))
                        },
                        onReviseFlashcards = { sem, sCode, uNum, tId ->
                            navigateTo(Screen.Flashcards(initialSemester = sem, initialSubject = sCode, initialUnit = uNum, initialTopic = tId))
                        }
                    )
                    is Screen.Practicals -> PracticalLabScreen(
                        onNavigateBack = { navigateBack() }
                    )
                    is Screen.QuizHub -> QuizHubScreen(
                        initialSemester = screen.initialSemester,
                        initialSubject = screen.initialSubject,
                        initialUnit = screen.initialUnit,
                        initialTopic = screen.initialTopic,
                        onNavigateBack = { navigateBack() },
                        onNavigateToFlashcards = { sem, subj, unit, topic ->
                            navigateTo(Screen.Flashcards(initialSemester = sem, initialSubject = subj, initialUnit = unit, initialTopic = topic))
                        }
                    )
                    is Screen.Flashcards -> FlashcardsScreen(
                        initialSemester = screen.initialSemester,
                        initialSubject = screen.initialSubject,
                        initialUnit = screen.initialUnit,
                        initialTopic = screen.initialTopic,
                        onNavigateBack = { navigateBack() },
                        onStartQuizForScope = { sem, subj, unit, topic ->
                            navigateTo(Screen.QuizHub(initialSemester = sem, initialSubject = subj, initialUnit = unit, initialTopic = topic))
                        }
                    )
                    is Screen.AiTutor -> AiTutorScreen(
                        initialQuery = screen.query,
                        onNavigateBack = { navigateBack() }
                    )
                    is Screen.Career -> CareerIndustryScreen(
                        onNavigateBack = { navigateBack() }
                    )
                    is Screen.Resources -> ResourcesScreen(
                        onNavigateBack = { navigateBack() }
                    )
                    is Screen.Search -> GlobalSearchScreen(
                        onNavigateBack = { navigateBack() },
                        onNavigateToSubject = { code -> navigateTo(Screen.SubjectDetail(code)) },
                        onNavigateToTopic = { topicId -> navigateTo(Screen.TopicLearning(topicId)) }
                    )
                    is Screen.Bookmarks -> BookmarksProgressScreen(
                        onNavigateBack = { navigateBack() },
                        onNavigateToTopic = { topicId -> navigateTo(Screen.TopicLearning(topicId)) }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavItem(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    testTag: String
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .testTag(testTag),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isSelected) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                        .size(20.dp)
                )
            }
        } else {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}
