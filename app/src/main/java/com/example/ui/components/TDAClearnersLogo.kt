package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

/**
 * Official TDAClearners Brand Emblem Logo Composable.
 * Represents: TDAClearners — B.Voc Textile Design & Apparel Technology
 */
@Composable
fun TDAClearnersLogo(
    modifier: Modifier = Modifier,
    size: Dp = 100.dp,
    showTextDetails: Boolean = true
) {
    val altText = "TDAClearners — B.Voc Textile Design & Apparel Technology"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .semantics { contentDescription = altText }
            .testTag("tdaclearners_logo_emblem")
    ) {
        // Circular Emblem Container
        Box(
            modifier = Modifier
                .size(size)
                .shadow(6.dp, CircleShape)
                .clip(CircleShape)
                .background(
                    Brush.sweepGradient(
                        listOf(
                            Color(0xFF0F172A),
                            Color(0xFF1E3A8A),
                            Color(0xFF6D28D9),
                            Color(0xFFC026D3),
                            Color(0xFF0284C7),
                            Color(0xFF0F172A)
                        )
                    )
                )
                .border(2.5.dp, Brush.linearGradient(listOf(Color(0xFF8B5CF6), Color(0xFF38BDF8))), CircleShape)
                .padding(size * 0.04f) // Outer margin
        ) {
            // Inner White Circular Badge
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                // Vector Canvas drawing the emblematic details
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = this.size.width
                    val h = this.size.height

                    // 1. Graduation Cap (Top)
                    val capPath = Path().apply {
                        moveTo(w * 0.5f, h * 0.12f)
                        lineTo(w * 0.78f, h * 0.22f)
                        lineTo(w * 0.5f, h * 0.32f)
                        lineTo(w * 0.22f, h * 0.22f)
                        close()
                    }
                    drawPath(capPath, color = Color(0xFF0F172A))

                    val skullPath = Path().apply {
                        moveTo(w * 0.38f, h * 0.28f)
                        lineTo(w * 0.38f, h * 0.38f)
                        cubicTo(w * 0.38f, h * 0.44f, w * 0.62f, h * 0.44f, w * 0.62f, h * 0.38f)
                        lineTo(w * 0.62f, h * 0.28f)
                        close()
                    }
                    drawPath(skullPath, color = Color(0xFF1E293B))

                    // Golden Tassel
                    drawLine(
                        color = Color(0xFFF59E0B),
                        start = Offset(w * 0.5f, h * 0.22f),
                        end = Offset(w * 0.72f, h * 0.34f),
                        strokeWidth = w * 0.02f
                    )
                    drawCircle(
                        color = Color(0xFFF59E0B),
                        radius = w * 0.025f,
                        center = Offset(w * 0.72f, h * 0.35f)
                    )

                    // 2. Center Fashion Mannequin with Violet Gown (The 'A' in TDAC)
                    val gownPath = Path().apply {
                        moveTo(w * 0.5f, h * 0.36f)
                        cubicTo(w * 0.46f, h * 0.42f, w * 0.42f, h * 0.50f, w * 0.34f, h * 0.62f)
                        lineTo(w * 0.66f, h * 0.62f)
                        cubicTo(w * 0.58f, h * 0.50f, w * 0.54f, h * 0.42f, w * 0.5f, h * 0.36f)
                        close()
                    }
                    drawPath(gownPath, color = Color(0xFF7C3AED))

                    // Mannequin Torso
                    drawOval(
                        color = Color(0xFFC026D3),
                        topLeft = Offset(w * 0.45f, h * 0.38f),
                        size = Size(w * 0.10f, h * 0.12f)
                    )

                    // 3. Spool on left & Measuring Tape Arc
                    drawRoundRect(
                        color = Color(0xFF0284C7),
                        topLeft = Offset(w * 0.16f, h * 0.46f),
                        size = Size(w * 0.10f, h * 0.14f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f)
                    )
                    drawLine(
                        color = Color(0xFFF59E0B),
                        start = Offset(w * 0.14f, h * 0.46f),
                        end = Offset(w * 0.28f, h * 0.46f),
                        strokeWidth = w * 0.02f
                    )
                    drawLine(
                        color = Color(0xFFF59E0B),
                        start = Offset(w * 0.14f, h * 0.60f),
                        end = Offset(w * 0.28f, h * 0.60f),
                        strokeWidth = w * 0.02f
                    )

                    // Thread Loop (Curved Stitch)
                    val threadPath = Path().apply {
                        moveTo(w * 0.20f, h * 0.36f)
                        cubicTo(w * 0.12f, h * 0.48f, w * 0.25f, h * 0.58f, w * 0.42f, h * 0.62f)
                    }
                    drawPath(
                        threadPath,
                        color = Color(0xFFC026D3),
                        style = Stroke(width = w * 0.02f)
                    )

                    // 4. Tech / Book on right (The 'C' node)
                    drawCircle(
                        color = Color(0xFF0284C7),
                        radius = w * 0.08f,
                        center = Offset(w * 0.78f, h * 0.46f)
                    )
                    drawCircle(
                        color = Color.White,
                        radius = w * 0.045f,
                        center = Offset(w * 0.78f, h * 0.46f)
                    )
                    // Play triangle
                    val playPath = Path().apply {
                        moveTo(w * 0.76f, h * 0.43f)
                        lineTo(w * 0.81f, h * 0.46f)
                        lineTo(w * 0.76f, h * 0.49f)
                        close()
                    }
                    drawPath(playPath, color = Color(0xFF0284C7))

                    // 5. Measuring Tape Strip
                    val tapePath = Path().apply {
                        moveTo(w * 0.14f, h * 0.60f)
                        cubicTo(w * 0.28f, h * 0.68f, w * 0.55f, h * 0.65f, w * 0.86f, h * 0.60f)
                        lineTo(w * 0.86f, h * 0.66f)
                        cubicTo(w * 0.55f, h * 0.71f, w * 0.28f, h * 0.74f, w * 0.14f, h * 0.66f)
                        close()
                    }
                    drawPath(tapePath, color = Color(0xFFFBBF24))

                    // 6. Bottom Banner Divider
                    drawLine(
                        color = Color(0xFF1E3A8A),
                        start = Offset(w * 0.20f, h * 0.86f),
                        end = Offset(w * 0.80f, h * 0.86f),
                        strokeWidth = w * 0.015f
                    )
                }

                // Foreground Emblem Text (Centered branding inside circular emblem)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = size * 0.12f)
                ) {
                    Text(
                        text = "TDAClearners",
                        fontWeight = FontWeight.Black,
                        fontSize = (size.value * 0.11f).sp,
                        color = Color(0xFF0F172A),
                        letterSpacing = (-0.5).sp,
                        lineHeight = (size.value * 0.12f).sp
                    )
                    Text(
                        text = "B.Voc Textile Design",
                        fontWeight = FontWeight.Bold,
                        fontSize = (size.value * 0.058f).sp,
                        color = Color(0xFF1E3A8A),
                        lineHeight = (size.value * 0.065f).sp
                    )
                }
            }
        }

        if (showTextDetails) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "TDAClearners",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    letterSpacing = 0.5.sp
                )
            )
            Text(
                text = "B.Voc Textile Design & Apparel Technology",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 0.3.sp
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "LEARN • PRACTICE • CREATE • GROW",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 9.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    letterSpacing = 1.sp
                )
            )
        }
    }
}

/**
 * Compact Header Variant of the TDAClearners Logo for Top App Bars.
 */
@Composable
fun TDAClearnersHeaderLogo(
    modifier: Modifier = Modifier,
    size: Dp = 38.dp,
    onClick: (() -> Unit)? = null
) {
    val altText = "TDAClearners — B.Voc Textile Design & Apparel Technology"

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .semantics { contentDescription = altText }
            .testTag("tdaclearners_header_logo")
    ) {
        // Mini Emblem
        Box(
            modifier = Modifier
                .size(size)
                .shadow(3.dp, CircleShape)
                .clip(CircleShape)
                .background(
                    Brush.sweepGradient(
                        listOf(
                            Color(0xFF0F172A),
                            Color(0xFF1E3A8A),
                            Color(0xFF7C3AED),
                            Color(0xFF0284C7),
                            Color(0xFF0F172A)
                        )
                    )
                )
                .border(1.5.dp, Color(0xFF8B5CF6), CircleShape)
                .padding(1.5.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "TDAC",
                    fontWeight = FontWeight.Black,
                    fontSize = (size.value * 0.28f).sp,
                    color = Color(0xFF0F172A),
                    letterSpacing = (-0.5).sp
                )
            }
        }

        // Title and Subtitle Text
        Column {
            Text(
                text = "TDAClearners",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    letterSpacing = 0.2.sp
                )
            )
            Text(
                text = "B.Voc Textile Design & Apparel Technology",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 9.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                ),
                maxLines = 1
            )
        }
    }
}

/**
 * Prominent Academic Hero Banner for Home and Landing Views.
 */
@Composable
fun TDAClearnersHeroBanner(
    modifier: Modifier = Modifier,
    semesterProgress: Float = 0f,
    onExploreClick: () -> Unit = {}
) {
    val altText = "TDAClearners — B.Voc Textile Design & Apparel Technology"

    Surface(
        shape = RoundedCornerShape(24.dp),
        color = Color(0xFF0F172A), // Deep TDAC Royal Navy
        shadowElevation = 6.dp,
        border = androidx.compose.foundation.BorderStroke(
            1.5.dp,
            Brush.horizontalGradient(
                listOf(Color(0xFF1E3A8A), Color(0xFF7C3AED), Color(0xFF0284C7))
            )
        ),
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = altText }
            .testTag("tdaclearners_hero_banner")
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // Subtle Background Radial Glow
            Canvas(modifier = Modifier.matchParentSize()) {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(Color(0xFF7C3AED).copy(alpha = 0.25f), Color.Transparent),
                        center = Offset(size.width * 0.9f, size.height * 0.2f),
                        radius = size.width * 0.5f
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ) {
                // Top Brand Tag & Verified Badge
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFF1E293B)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "🎓 TDAClearners",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF38BDF8)
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFF14532D)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = null,
                                tint = Color(0xFF4ADE80),
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "NEP 2020 Scheme",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFDCFCE7)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Hero Content Row: Logo Emblem + Course Title & Slogan
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    TDAClearnersLogo(
                        size = 72.dp,
                        showTextDetails = false
                    )

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "TDAClearners",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Black,
                                color = Color.White,
                                letterSpacing = 0.5.sp
                            )
                        )
                        Text(
                            text = "B.Voc Textile Design & Apparel Technology",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFCBD5E1),
                                lineHeight = 16.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "LEARN • PRACTICE • CREATE • GROW",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFFBBF24),
                                letterSpacing = 0.8.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Academic Semester Progress Bar
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Overall Syllabus Mastery",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF94A3B8)
                        )
                        Text(
                            text = "${(semesterProgress * 100).toInt()}% Completed",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF38BDF8)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { semesterProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = Color(0xFF38BDF8),
                        trackColor = Color(0xFF334155)
                    )
                }
            }
        }
    }
}
