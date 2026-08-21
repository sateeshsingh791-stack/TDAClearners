package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.QuizQuestion
import com.example.data.model.Subject
import com.example.ui.theme.*

/**
 * Diagnostic data model representing accuracy for a specific syllabus topic.
 */
data class TopicAccuracyData(
    val topicId: String,
    val topicTitle: String,
    val unitNumber: Int,
    val unitTitle: String,
    val totalCount: Int,
    val correctCount: Int,
    val accuracyPercent: Int,
    val missedQuestions: List<QuizQuestion>
) {
    val isMastered: Boolean get() = totalCount > 0 && correctCount == totalCount
    val isWeak: Boolean get() = totalCount > 0 && (accuracyPercent < 60 || (totalCount <= 2 && correctCount < totalCount))
}

/**
 * Diagnostic data model representing accuracy for a specific syllabus unit.
 */
data class UnitAccuracyData(
    val unitNumber: Int,
    val unitTitle: String,
    val totalCount: Int,
    val correctCount: Int,
    val accuracyPercent: Int,
    val topicBreakdowns: List<TopicAccuracyData>
) {
    val status: UnitAccuracyStatus
        get() = when {
            accuracyPercent >= 80 -> UnitAccuracyStatus.MASTERED
            accuracyPercent >= 50 -> UnitAccuracyStatus.MODERATE
            else -> UnitAccuracyStatus.NEEDS_FOCUS
        }
}

enum class UnitAccuracyStatus(
    val label: String,
    val badgeText: String,
    val color: Color,
    val containerColor: Color,
    val onContainerColor: Color,
    val icon: @Composable () -> Unit
) {
    MASTERED(
        label = "Mastered",
        badgeText = "✓ Strong",
        color = SuccessGreen,
        containerColor = SuccessGreenContainer,
        onContainerColor = SuccessGreenOnContainer,
        icon = { Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SuccessGreen, modifier = Modifier.size(16.dp)) }
    ),
    MODERATE(
        label = "Developing",
        badgeText = "⚡ Moderate",
        color = WarningAmber,
        containerColor = WarningAmberContainer,
        onContainerColor = WarningAmberOnContainer,
        icon = { Icon(Icons.AutoMirrored.Filled.TrendingFlat, contentDescription = null, tint = WarningAmber, modifier = Modifier.size(16.dp)) }
    ),
    NEEDS_FOCUS(
        label = "Needs Focus",
        badgeText = "⚠️ Weak Area",
        color = ErrorRed,
        containerColor = ErrorRedContainer,
        onContainerColor = ErrorRedOnContainer,
        icon = { Icon(Icons.Default.WarningAmber, contentDescription = null, tint = ErrorRed, modifier = Modifier.size(16.dp)) }
    )
}

/**
 * Filter mode for the accuracy breakdown view.
 */
enum class AccuracyFilterTab(val label: String, val icon: String) {
    ALL_UNITS("All Units & Topics", "📊"),
    WEAK_AREAS("Weak Areas", "⚠️"),
    MASTERED("Mastered", "🏆")
}

/**
 * Reusable, rich visualization component on Quiz Results Screen that displays
 * a comprehensive breakdown of accuracy per Unit and per Topic to help students
 * identify and remediate their weak syllabus areas.
 */
@Composable
fun QuizAccuracyBreakdownView(
    questions: List<QuizQuestion>,
    userAnswers: Map<Int, Int>,
    subject: Subject,
    modifier: Modifier = Modifier,
    onStartTargetedQuiz: ((unitNumber: Int, topicId: String?) -> Unit)? = null,
    onOpenTopicFlashcards: ((unitNumber: Int, topicId: String?) -> Unit)? = null
) {
    // 1. Calculate Unit and Topic Level Analytics
    val unitBreakdowns = remember(questions, userAnswers, subject) {
        calculateUnitAndTopicBreakdowns(questions, userAnswers, subject)
    }

    val totalQuestions = questions.size
    val totalCorrect = userAnswers.count { (qIdx, ansIdx) ->
        questions.getOrNull(qIdx)?.correctIndex == ansIdx
    }
    val overallPercent = if (totalQuestions > 0) (totalCorrect * 100) / totalQuestions else 0

    val allTopics = remember(unitBreakdowns) {
        unitBreakdowns.flatMap { it.topicBreakdowns }
    }
    val weakTopics = remember(allTopics) {
        allTopics.filter { it.isWeak }
    }
    val masteredTopics = remember(allTopics) {
        allTopics.filter { it.isMastered }
    }

    val weakestUnit = remember(unitBreakdowns) {
        unitBreakdowns.minByOrNull { it.accuracyPercent }
    }
    val strongestUnit = remember(unitBreakdowns) {
        unitBreakdowns.maxByOrNull { it.accuracyPercent }
    }

    var selectedTab by remember { mutableStateOf(AccuracyFilterTab.ALL_UNITS) }
    var expandedUnits by remember {
        mutableStateOf(
            // Auto-expand units that need focus
            unitBreakdowns.filter { it.status == UnitAccuracyStatus.NEEDS_FOCUS }.map { it.unitNumber }.toSet()
        )
    }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier
            .fillMaxWidth()
            .testTag("quiz_accuracy_breakdown_component")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header: Component Title with Tagline
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Analytics,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Column {
                        Text(
                            text = "Unit & Topic Accuracy Breakdown",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Text(
                            text = "Curriculum Weak Area Diagnostic & Revision Plan",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.sp
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Unit Quick Diagnostic Summary Chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Weakest Unit Pill
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (weakestUnit != null && weakestUnit.accuracyPercent < 70) ErrorRedContainer else SuccessGreenContainer,
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = if (weakestUnit != null && weakestUnit.accuracyPercent < 70) Icons.Default.Warning else Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = if (weakestUnit != null && weakestUnit.accuracyPercent < 70) ErrorRedOnContainer else SuccessGreenOnContainer,
                            modifier = Modifier.size(16.dp)
                        )
                        Column {
                            Text(
                                text = if (weakestUnit != null && weakestUnit.accuracyPercent < 70) "Focus Unit" else "All Solid",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (weakestUnit != null && weakestUnit.accuracyPercent < 70) ErrorRedOnContainer else SuccessGreenOnContainer
                            )
                            Text(
                                text = weakestUnit?.let { "Unit ${it.unitNumber} (${it.accuracyPercent}%)" } ?: "100%",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = if (weakestUnit != null && weakestUnit.accuracyPercent < 70) ErrorRedOnContainer else SuccessGreenOnContainer,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                // Strongest Unit Pill
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.size(16.dp)
                        )
                        Column {
                            Text(
                                text = "Top Unit",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = strongestUnit?.let { "Unit ${it.unitNumber} (${it.accuracyPercent}%)" } ?: "N/A",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Visual Horizontal Comparison Bar Chart for all Units
            Text(
                text = "UNIT ACCURACY COMPARISON",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 0.8.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            UnitAccuracyComparisonChart(
                unitBreakdowns = unitBreakdowns,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Filter Tabs (All Units, Weak Areas, Mastered)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(3.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                AccuracyFilterTab.values().forEach { tab ->
                    val isSelected = selectedTab == tab
                    val count = when (tab) {
                        AccuracyFilterTab.ALL_UNITS -> unitBreakdowns.size
                        AccuracyFilterTab.WEAK_AREAS -> weakTopics.size
                        AccuracyFilterTab.MASTERED -> masteredTopics.size
                    }

                    Surface(
                        onClick = { selectedTab = tab },
                        shape = RoundedCornerShape(10.dp),
                        color = if (isSelected) MaterialTheme.colorScheme.surface else Color.Transparent,
                        shadowElevation = if (isSelected) 2.dp else 0.dp,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("filter_tab_${tab.name.lowercase()}")
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 6.dp, horizontal = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "${tab.icon} ${tab.label}",
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
                                color = if (isSelected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            if (count > 0 && tab != AccuracyFilterTab.ALL_UNITS) {
                                Spacer(modifier = Modifier.width(4.dp))
                                Surface(
                                    shape = CircleShape,
                                    color = if (tab == AccuracyFilterTab.WEAK_AREAS) ErrorRedContainer else SuccessGreenContainer,
                                    modifier = Modifier.size(16.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = "$count",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (tab == AccuracyFilterTab.WEAK_AREAS) ErrorRedOnContainer else SuccessGreenOnContainer
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // TAB CONTENT
            when (selectedTab) {
                AccuracyFilterTab.ALL_UNITS -> {
                    // List of Units with Expandable Topics
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        unitBreakdowns.forEach { unit ->
                            val isExpanded = expandedUnits.contains(unit.unitNumber)
                            UnitAccordionCard(
                                unit = unit,
                                isExpanded = isExpanded,
                                onToggleExpand = {
                                    expandedUnits = if (isExpanded) {
                                        expandedUnits - unit.unitNumber
                                    } else {
                                        expandedUnits + unit.unitNumber
                                    }
                                },
                                onStartTargetedQuiz = onStartTargetedQuiz,
                                onOpenTopicFlashcards = onOpenTopicFlashcards
                            )
                        }
                    }
                }

                AccuracyFilterTab.WEAK_AREAS -> {
                    if (weakTopics.isEmpty()) {
                        EmptyStatePraiseCard(
                            title = "No Weak Areas Detected! 🎉",
                            message = "You demonstrated strong mastery across all tested topics in this session. Keep up the great work!"
                        )
                    } else {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            // Weak Areas Callout Banner
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = ErrorRedContainer,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.PriorityHigh,
                                        contentDescription = null,
                                        tint = ErrorRedOnContainer,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Column {
                                        Text(
                                            text = "${weakTopics.size} Topics Need Targeted Review",
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 12.sp,
                                            color = ErrorRedOnContainer
                                        )
                                        Text(
                                            text = "Review flashcards and retake targeted unit questions to reinforce these concepts.",
                                            fontSize = 11.sp,
                                            color = ErrorRedOnContainer.copy(alpha = 0.9f)
                                        )
                                    }
                                }
                            }

                            weakTopics.forEach { topic ->
                                WeakTopicDetailCard(
                                    topic = topic,
                                    onStartTargetedQuiz = onStartTargetedQuiz,
                                    onOpenTopicFlashcards = onOpenTopicFlashcards
                                )
                            }
                        }
                    }
                }

                AccuracyFilterTab.MASTERED -> {
                    if (masteredTopics.isEmpty()) {
                        EmptyStatePraiseCard(
                            title = "Keep Practicing! 📚",
                            message = "No topics reached 100% mastery yet in this quiz. Revise key definitions and try again!"
                        )
                    } else {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = SuccessGreenContainer,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = SuccessGreenOnContainer,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Column {
                                        Text(
                                            text = "${masteredTopics.size} Topics Mastered (100% Accuracy)",
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 12.sp,
                                            color = SuccessGreenOnContainer
                                        )
                                        Text(
                                            text = "Excellent understanding of these core syllabus concepts!",
                                            fontSize = 11.sp,
                                            color = SuccessGreenOnContainer.copy(alpha = 0.9f)
                                        )
                                    }
                                }
                            }

                            masteredTopics.forEach { topic ->
                                MasteredTopicItemCard(topic = topic)
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Visual Comparative Bar Chart showing all units side-by-side.
 */
@Composable
private fun UnitAccuracyComparisonChart(
    unitBreakdowns: List<UnitAccuracyData>,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            unitBreakdowns.forEach { unit ->
                val animatedProgress by animateFloatAsState(
                    targetValue = unit.accuracyPercent / 100f,
                    animationSpec = tween(durationMillis = 600),
                    label = "unit_bar_progress"
                )

                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Unit ${unit.unitNumber}: ${unit.unitTitle}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "${unit.correctCount}/${unit.totalCount}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = unit.status.containerColor
                            ) {
                                Text(
                                    text = "${unit.accuracyPercent}%",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = unit.status.onContainerColor,
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Custom Multi-Layered Progress Bar with Target Benchmark Line (70%)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val barWidth = size.width * animatedProgress
                            val barColor = when {
                                unit.accuracyPercent >= 80 -> Color(0xFF16A34A)
                                unit.accuracyPercent >= 50 -> Color(0xFFD97706)
                                else -> Color(0xFFDC2626)
                            }

                            // Fill Bar
                            if (barWidth > 0f) {
                                drawRoundRect(
                                    color = barColor,
                                    size = Size(barWidth, size.height),
                                    cornerRadius = CornerRadius(4.dp.toPx())
                                )
                            }

                            // Benchmark 70% Target Line
                            val benchmarkX = size.width * 0.70f
                            drawLine(
                                color = Color.White.copy(alpha = 0.8f),
                                start = Offset(benchmarkX, 0f),
                                end = Offset(benchmarkX, size.height),
                                strokeWidth = 1.5.dp.toPx()
                            )
                        }
                    }
                }
            }

            // Benchmark Legend
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Benchmark: 70% Pass Standard",
                    fontSize = 9.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

/**
 * Accordion Card for an individual Unit, expanding to reveal its topics.
 */
@Composable
private fun UnitAccordionCard(
    unit: UnitAccuracyData,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    onStartTargetedQuiz: ((unitNumber: Int, topicId: String?) -> Unit)?,
    onOpenTopicFlashcards: ((unitNumber: Int, topicId: String?) -> Unit)?
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (unit.status == UnitAccuracyStatus.NEEDS_FOCUS) {
                MaterialTheme.colorScheme.surface
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.2.dp,
            if (unit.status == UnitAccuracyStatus.NEEDS_FOCUS) ErrorRed.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outlineVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .testTag("unit_accuracy_card_${unit.unitNumber}")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Unit Header Row (Clickable to Expand/Collapse)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggleExpand() }
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = unit.status.containerColor,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "U${unit.unitNumber}",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 13.sp,
                                color = unit.status.onContainerColor
                            )
                        }
                    }

                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Unit ${unit.unitNumber}",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = unit.status.containerColor
                            ) {
                                Text(
                                    text = unit.status.badgeText,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = unit.status.onContainerColor,
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                                )
                            }
                        }
                        Text(
                            text = unit.unitTitle,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "${unit.accuracyPercent}%",
                            fontWeight = FontWeight.Black,
                            fontSize = 14.sp,
                            color = unit.status.color
                        )
                        Text(
                            text = "${unit.correctCount}/${unit.totalCount} correct",
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(
                        onClick = onToggleExpand,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = if (isExpanded) "Collapse" else "Expand",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Expanded Topic List
            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "TOPIC BREAKDOWN",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 0.5.sp
                    )

                    unit.topicBreakdowns.forEach { topic ->
                        TopicRowItem(
                            topic = topic,
                            onStartTargetedQuiz = onStartTargetedQuiz,
                            onOpenTopicFlashcards = onOpenTopicFlashcards
                        )
                    }

                    // Targeted Unit Action Buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (onOpenTopicFlashcards != null) {
                            OutlinedButton(
                                onClick = { onOpenTopicFlashcards(unit.unitNumber, null) },
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Default.Style, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Unit Flashcards", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        if (onStartTargetedQuiz != null && unit.accuracyPercent < 100) {
                            Button(
                                onClick = { onStartTargetedQuiz(unit.unitNumber, null) },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Default.Quiz, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Retake Unit Quiz", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Individual Topic row inside an expanded Unit.
 */
@Composable
private fun TopicRowItem(
    topic: TopicAccuracyData,
    onStartTargetedQuiz: ((unitNumber: Int, topicId: String?) -> Unit)?,
    onOpenTopicFlashcards: ((unitNumber: Int, topicId: String?) -> Unit)?
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (topic.isWeak) ErrorRed.copy(alpha = 0.4f) else MaterialTheme.colorScheme.outlineVariant
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = if (topic.isMastered) "✓" else if (topic.isWeak) "⚠️" else "•",
                        fontSize = 12.sp,
                        color = if (topic.isMastered) SuccessGreen else if (topic.isWeak) ErrorRed else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = topic.topicTitle,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "${topic.correctCount}/${topic.totalCount}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (topic.isMastered) SuccessGreen else if (topic.isWeak) ErrorRed else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = if (topic.isMastered) SuccessGreenContainer else if (topic.isWeak) ErrorRedContainer else WarningAmberContainer
                    ) {
                        Text(
                            text = "${topic.accuracyPercent}%",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = if (topic.isMastered) SuccessGreenOnContainer else if (topic.isWeak) ErrorRedOnContainer else WarningAmberOnContainer,
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                        )
                    }
                }
            }

            // If weak topic, show direct mini quick revision buttons
            if (topic.isWeak) {
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (onOpenTopicFlashcards != null) {
                        TextButton(
                            onClick = { onOpenTopicFlashcards(topic.unitNumber, topic.topicId) },
                            contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp),
                            modifier = Modifier.height(28.dp)
                        ) {
                            Text("📇 Revise Flashcards", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Dedicated Weak Topic Card for the Weak Areas Filter Tab.
 */
@Composable
private fun WeakTopicDetailCard(
    topic: TopicAccuracyData,
    onStartTargetedQuiz: ((unitNumber: Int, topicId: String?) -> Unit)?,
    onOpenTopicFlashcards: ((unitNumber: Int, topicId: String?) -> Unit)?
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.2.dp, ErrorRed.copy(alpha = 0.5f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = ErrorRedContainer
                ) {
                    Text(
                        text = "Unit ${topic.unitNumber} • Weak Topic",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = ErrorRedOnContainer,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Text(
                    text = "${topic.correctCount}/${topic.totalCount} (${topic.accuracyPercent}%)",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp,
                    color = ErrorRed
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = topic.topicTitle,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = topic.unitTitle,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (topic.missedQuestions.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "💡 Missed Concept:",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = topic.missedQuestions.first().question,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (onOpenTopicFlashcards != null) {
                    OutlinedButton(
                        onClick = { onOpenTopicFlashcards(topic.unitNumber, topic.topicId) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Revise Flashcards", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }

                if (onStartTargetedQuiz != null) {
                    Button(
                        onClick = { onStartTargetedQuiz(topic.unitNumber, topic.topicId) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Practice Topic", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

/**
 * Card for Mastered Topic (100% Accuracy).
 */
@Composable
private fun MasteredTopicItemCard(topic: TopicAccuracyData) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(1.dp, SuccessGreen.copy(alpha = 0.5f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = SuccessGreen,
                    modifier = Modifier.size(18.dp)
                )
                Column {
                    Text(
                        text = topic.topicTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Unit ${topic.unitNumber}: ${topic.unitTitle}",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(6.dp),
                color = SuccessGreenContainer
            ) {
                Text(
                    text = "100% (${topic.correctCount}/${topic.totalCount})",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = SuccessGreenOnContainer,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@Composable
private fun EmptyStatePraiseCard(title: String, message: String) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = message,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Pure calculation helper to map active quiz questions and answers into Unit and Topic diagnostic models.
 */
fun calculateUnitAndTopicBreakdowns(
    questions: List<QuizQuestion>,
    userAnswers: Map<Int, Int>,
    subject: Subject
): List<UnitAccuracyData> {
    if (questions.isEmpty()) return emptyList()

    // 1. Group questions with user answers
    val questionResults = questions.mapIndexed { idx, q ->
        val chosen = userAnswers[idx]
        val isCorrect = chosen == q.correctIndex
        Triple(q, chosen, isCorrect)
    }

    // 2. Identify all distinct unit numbers in the questions
    val unitNumbers = questions.map { it.unitNumber }.distinct().sorted()

    return unitNumbers.map { uNum ->
        val unitSubjectDef = subject.units.find { it.unitNumber == uNum }
        val unitTitle = unitSubjectDef?.title ?: "Unit $uNum"

        val questionsInUnit = questionResults.filter { it.first.unitNumber == uNum }
        val unitTotal = questionsInUnit.size
        val unitCorrect = questionsInUnit.count { it.third }
        val unitAccuracy = if (unitTotal > 0) (unitCorrect * 100) / unitTotal else 0

        // 3. Group by topic within this unit
        val distinctTopicIds = questionsInUnit.map { it.first.topicId }.distinct()
        val topicBreakdowns = distinctTopicIds.map { tId ->
            val topicSubjectDef = unitSubjectDef?.topics?.find { it.id == tId }
                ?: subject.units.flatMap { it.topics }.find { it.id == tId }

            val topicTitle = topicSubjectDef?.title
                ?: formatFallbackTopicTitle(tId, uNum)

            val questionsInTopic = questionsInUnit.filter { it.first.topicId == tId }
            val tTotal = questionsInTopic.size
            val tCorrect = questionsInTopic.count { it.third }
            val tAccuracy = if (tTotal > 0) (tCorrect * 100) / tTotal else 0
            val missedQuestions = questionsInTopic.filter { !it.third }.map { it.first }

            TopicAccuracyData(
                topicId = tId,
                topicTitle = topicTitle,
                unitNumber = uNum,
                unitTitle = unitTitle,
                totalCount = tTotal,
                correctCount = tCorrect,
                accuracyPercent = tAccuracy,
                missedQuestions = missedQuestions
            )
        }.sortedBy { it.accuracyPercent } // Lowest accuracy topics first

        UnitAccuracyData(
            unitNumber = uNum,
            unitTitle = unitTitle,
            totalCount = unitTotal,
            correctCount = unitCorrect,
            accuracyPercent = unitAccuracy,
            topicBreakdowns = topicBreakdowns
        )
    }.sortedBy { it.unitNumber }
}

private fun formatFallbackTopicTitle(topicId: String, unitNumber: Int): String {
    if (topicId.isBlank()) return "Unit $unitNumber Core Concepts"
    return topicId.replace("_", " ")
        .replace("-", " ")
        .split(" ")
        .joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
}
