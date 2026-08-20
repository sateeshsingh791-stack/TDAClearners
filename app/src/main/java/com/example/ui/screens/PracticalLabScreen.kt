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
import com.example.data.model.PracticalActivity
import com.example.data.repository.SyllabusRepository
import com.example.ui.components.AppHeader
import com.example.ui.theme.TerracottaContainer
import com.example.ui.theme.TerracottaOnContainer
import com.example.ui.theme.TerracottaPrimary

@Composable
fun PracticalLabScreen(
    onNavigateBack: () -> Unit
) {
    val practicals = SyllabusRepository.getAllPracticals()

    Scaffold(
        topBar = {
            AppHeader(
                title = "Practical Laboratory",
                subtitle = "Hands-on Vocational Training",
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
            item {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = TerracottaContainer,
                    modifier = Modifier.fillMaxWidth().testTag("practical_lab_banner")
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Science,
                            contentDescription = null,
                            tint = TerracottaOnContainer,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Vocational Lab Worksheets",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TerracottaOnContainer
                                )
                            )
                            Text(
                                text = "Procedures, observations, safety precautions & viva voice Q&As.",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TerracottaOnContainer.copy(alpha = 0.85f),
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }
                }
            }

            items(practicals) { practical ->
                PracticalActivityCard(practical = practical)
            }
        }
    }
}

@Composable
fun PracticalActivityCard(practical: PracticalActivity) {
    var expandedViva by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth().testTag("practical_card_${practical.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFF1F5F9)
                ) {
                    Text(
                        text = practical.subjectCode,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = TerracottaPrimary,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Text(
                    text = "LAB EXPERIMENT",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color(0xFF94A3B8),
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = practical.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "🎯 Objective: ${practical.objective}",
                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF334155), fontWeight = FontWeight.Medium)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Materials
            Text(
                text = "📦 Materials & Apparatus:",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = TerracottaPrimary
            )
            Text(
                text = practical.materialsRequired.joinToString(", "),
                fontSize = 11.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Step by step procedure
            Text(
                text = "📋 Step-by-Step Procedure:",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            practical.stepByStepProcedure.forEachIndexed { index, step ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text("${index + 1}. ", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TerracottaPrimary)
                    Text(text = step, fontSize = 11.sp, color = Color(0xFF334155))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Expected Observations
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFFF0FDF4),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "🔬 Expected Result & Observations",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF15803D)
                    )
                    Text(
                        text = practical.expectedObservations,
                        fontSize = 11.sp,
                        color = Color(0xFF166534)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Precautions
            Text(
                text = "⚠️ Laboratory Safety & Precautions:",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFDC2626)
            )
            practical.precautions.forEach { prec ->
                Text("• $prec", fontSize = 10.sp, color = Color(0xFF991B1B))
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Viva Voce Section Toggle
            Button(
                onClick = { expandedViva = !expandedViva },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF8FAFC),
                    contentColor = TerracottaPrimary
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().height(36.dp)
            ) {
                Text(
                    text = if (expandedViva) "Hide Viva Questions (${practical.vivaQuestions.size})" else "Show Viva Voce Questions (${practical.vivaQuestions.size})",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (expandedViva) {
                Spacer(modifier = Modifier.height(10.dp))
                practical.vivaQuestions.forEachIndexed { i, (q, a) ->
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFBEB)),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text("Q${i + 1}: $q", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF92400E))
                            Spacer(modifier = Modifier.height(2.dp))
                            Text("Ans: $a", fontSize = 11.sp, color = Color(0xFF78350F))
                        }
                    }
                }
            }
        }
    }
}
