package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CareerRole
import com.example.data.repository.SyllabusRepository
import com.example.ui.components.AppHeader
import com.example.ui.theme.TerracottaContainer
import com.example.ui.theme.TerracottaOnContainer
import com.example.ui.theme.TerracottaPrimary

@Composable
fun CareerIndustryScreen(
    onNavigateBack: () -> Unit
) {
    val careers = SyllabusRepository.careerRoles

    Scaffold(
        topBar = {
            AppHeader(
                title = "Industry & Career Pathways",
                subtitle = "Vocational Industry Opportunities",
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
                    shape = RoundedCornerShape(18.dp),
                    color = TerracottaPrimary,
                    modifier = Modifier.fillMaxWidth().testTag("career_banner")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "B.Voc Graduate Career Scope",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Discover vocational career avenues across textile mills, apparel export houses, design ateliers, and entrepreneurship in North India and globally.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.White.copy(alpha = 0.85f),
                                fontSize = 11.sp
                            )
                        )
                    }
                }
            }

            items(careers) { role ->
                CareerRoleCard(role = role)
            }
        }
    }
}

@Composable
fun CareerRoleCard(role: CareerRole) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth().testTag("career_role_${role.title.replace(" ", "_")}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = TerracottaContainer
                ) {
                    Text(
                        text = role.sector,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = TerracottaOnContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = role.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = role.description,
                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF64748B))
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "⚡ Key Industry Competencies:",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = TerracottaPrimary
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
            ) {
                role.keySkills.take(3).forEach { skill ->
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Color(0xFFF1F5F9)
                    ) {
                        Text(
                            text = skill,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF334155),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "💻 Industry Standard CAD & Tools: ${role.standardTools.joinToString(", ")}",
                fontSize = 10.sp,
                color = Color(0xFF475569)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "📍 Regional Scope: ${role.industryScope}",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF0F766E)
            )
        }
    }
}
