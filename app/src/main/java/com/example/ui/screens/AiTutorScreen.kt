package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.api.AssistantRole
import com.example.data.api.ChatTurn
import com.example.data.api.GeminiModel
import com.example.data.api.GeminiRepository
import com.example.ui.components.AiMarkdownRenderer
import com.example.ui.components.AppHeader
import com.example.ui.theme.TerracottaContainer
import com.example.ui.theme.TerracottaOnContainer
import com.example.ui.theme.TerracottaPrimary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// High Contrast Theme Colors for Chat Workspace
private val ChatDarkBg = Color(0xFF0F172A)          // Dark modern slate background
private val AiBubbleDarkBg = Color(0xFF1E293B)      // Contrast surface for AI card
private val AiBubbleBorder = Color(0xFF334155)      // Border for AI card
private val UserBubbleBg = Color(0xFFB34700)        // Distinct Terracotta brand color for User
private val UserBubbleBorder = Color(0xFFEA580C)    // Vibrant border for user bubble
private val InputBarBg = Color(0xFF1E293B)          // Surface for bottom input bar
private val InputBorder = Color(0xFF475569)         // Border for input fields
private val TextWhite = Color(0xFFFFFFFF)           // High contrast white text
private val TextLight = Color(0xFFF3F4F6)           // High contrast light text
private val TextMutedLight = Color(0xFF94A3B8)      // Readable muted light text

@Composable
fun AiTutorScreen(
    initialQuery: String? = null,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    var selectedModel by remember { mutableStateOf(GeminiModel.FLASH) }
    var selectedRole by remember { mutableStateOf(AssistantRole.ACADEMIC_PROFESSOR) }
    var isSearchGroundingEnabled by remember { mutableStateOf(false) }

    var inputText by remember { mutableStateOf(initialQuery ?: "") }
    var isLoading by remember { mutableStateOf(false) }

    var conversationHistory by remember {
        mutableStateOf(
            listOf(
                ChatTurn(
                    id = "init",
                    role = "model",
                    text = "### 🎓 Welcome to the B.Voc Textile Design AI Assistant!\n\n" +
                            "I am specialized in your **Khalsa College Amritsar** syllabus (Guru Nanak Dev University NEP curriculum).\n\n" +
                            "**What I can help you with:**\n" +
                            "- **Fibre Science & Identification**: Burning tests, chemical solubility, microscopic analysis (BVTD 111/112).\n" +
                            "- **Garment Construction**: SPI standards, seam classification, lockstitch tension & feed-dog adjustments (BVTD 113).\n" +
                            "- **Woven Fabric Analysis**: Plain (1/1), Twill (2/2), Satin weaves, count & GSM equations (BVTD 123).\n" +
                            "- **Surface Ornamentation & Heritage**: Traditional Punjabi Phulkari, Kantha, screen printing (BVTD 114 / BHPB 1101).\n\n" +
                            "> *Tip: Use the controls above to switch AI roles, choose between Gemini models, or turn on Google Search Grounding for live textile trends.*",
                    modelUsed = GeminiModel.FLASH
                )
            )
        )
    }

    var showConfigPanel by remember { mutableStateOf(false) }

    val samplePrompts = listOf(
        "Burning Test: Silk vs Polyester (BVTD 112)",
        "5 Stages of the Fashion Life Cycle (BVTD 121)",
        "Plain (1/1) vs Twill (2/2) Weave Interlacement",
        "Punjab Traditional Phulkari Motifs & Stitches",
        "Industrial Lockstitch Machine Tension Adjustment",
        "Global Sustainable Textile Trends in 2026"
    )

    fun sendPrompt(query: String) {
        if (query.isBlank() || isLoading) return
        val currentQuery = query.trim()
        inputText = ""

        val userTurn = ChatTurn(
            id = System.currentTimeMillis().toString(),
            role = "user",
            text = currentQuery
        )

        val updatedHistory = conversationHistory + userTurn
        conversationHistory = updatedHistory
        isLoading = true

        coroutineScope.launch {
            // Scroll down
            listState.animateScrollToItem(updatedHistory.size - 1)

            val result = GeminiRepository.sendMessage(
                history = updatedHistory,
                newUserMessage = currentQuery,
                selectedModel = selectedModel,
                selectedRole = selectedRole,
                enableSearchGrounding = isSearchGroundingEnabled
            )

            result.onSuccess { modelTurn ->
                conversationHistory = conversationHistory + modelTurn
                isLoading = false
                listState.animateScrollToItem(conversationHistory.size - 1)
            }.onFailure {
                val errorTurn = ChatTurn(
                    id = System.currentTimeMillis().toString(),
                    role = "model",
                    text = "### ⚠️ Network Alert\n\nUnable to connect to the Gemini API service. Please verify your internet connection and try again.",
                    modelUsed = selectedModel
                )
                conversationHistory = conversationHistory + errorTurn
                isLoading = false
            }
        }
    }

    // Auto-execute if initial query passed
    LaunchedEffect(initialQuery) {
        if (!initialQuery.isNullOrBlank()) {
            sendPrompt(initialQuery)
        }
    }

    Scaffold(
        topBar = {
            AppHeader(
                title = "Gemini Academic Chatbot",
                subtitle = "${selectedRole.iconLabel} ${selectedRole.title} • ${selectedModel.displayName}",
                showBackButton = true,
                onBackClick = onNavigateBack,
                actions = {
                    IconButton(
                        onClick = { showConfigPanel = !showConfigPanel },
                        modifier = Modifier.testTag("toggle_ai_settings_btn")
                    ) {
                        Icon(
                            imageVector = if (showConfigPanel) Icons.Default.Tune else Icons.Outlined.Tune,
                            contentDescription = "AI Controls",
                            tint = TerracottaPrimary
                        )
                    }
                    IconButton(
                        onClick = {
                            conversationHistory = listOf(
                                ChatTurn(
                                    id = System.currentTimeMillis().toString(),
                                    role = "model",
                                    text = "### 🧹 Conversation Reset\n\nChat history cleared. How can I assist you with your Textile Design & Apparel studies?",
                                    modelUsed = selectedModel
                                )
                            )
                        },
                        modifier = Modifier.testTag("clear_chat_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.DeleteSweep,
                            contentDescription = "Clear Chat",
                            tint = Color(0xFF94A3B8)
                        )
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                color = InputBarBg,
                shadowElevation = 12.dp,
                border = androidx.compose.foundation.BorderStroke(1.dp, InputBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                    // Sample Prompt Chips
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        items(samplePrompts) { prompt ->
                            Surface(
                                onClick = { sendPrompt(prompt) },
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFF334155),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF475569)),
                                modifier = Modifier.padding(vertical = 2.dp)
                            ) {
                                Text(
                                    text = prompt,
                                    fontSize = 11.sp,
                                    color = Color(0xFFFFDBCB),
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                )
                            }
                        }
                    }

                    // Input Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            placeholder = {
                                Text(
                                    text = if (isSearchGroundingEnabled) "Ask anything (Google Search Grounding active)..." else "Ask syllabus questions, weave equations, burning tests...",
                                    fontSize = 12.sp,
                                    color = TextMutedLight
                                )
                            },
                            textStyle = LocalTextStyle.current.copy(
                                color = TextLight,
                                fontSize = 13.sp
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("gemini_chat_input"),
                            shape = RoundedCornerShape(20.dp),
                            maxLines = 4,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFFB923C),
                                unfocusedBorderColor = InputBorder,
                                focusedContainerColor = Color(0xFF0F172A),
                                unfocusedContainerColor = Color(0xFF0F172A),
                                cursorColor = Color(0xFFFB923C)
                            )
                        )

                        IconButton(
                            onClick = { sendPrompt(inputText) },
                            enabled = inputText.isNotBlank() && !isLoading,
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = UserBubbleBg,
                                contentColor = TextWhite,
                                disabledContainerColor = Color(0xFF334155),
                                disabledContentColor = Color(0xFF64748B)
                            ),
                            modifier = Modifier
                                .size(46.dp)
                                .testTag("gemini_send_button")
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = TextWhite,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.Send,
                                    contentDescription = "Send",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        },
        containerColor = ChatDarkBg
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ChatDarkBg)
                .padding(paddingValues)
        ) {
            // Collapsible AI Config Panel (Model selector, Personas, Google Search Grounding)
            AnimatedVisibility(visible = showConfigPanel) {
                Surface(
                    color = Color(0xFF1E293B),
                    shadowElevation = 6.dp,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // 1. Model Selector
                        Text(
                            text = "🤖 Select Gemini Model",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFDBCB)
                            )
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            GeminiModel.entries.forEach { model ->
                                val isSelected = selectedModel == model
                                Surface(
                                    onClick = { selectedModel = model },
                                    shape = RoundedCornerShape(10.dp),
                                    color = if (isSelected) TerracottaPrimary else Color(0xFF334155),
                                    border = androidx.compose.foundation.BorderStroke(
                                        1.dp,
                                        if (isSelected) Color(0xFFFB923C) else Color(0xFF475569)
                                    ),
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("model_select_${model.name}")
                                ) {
                                    Column(
                                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 6.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = model.displayName,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = TextWhite
                                        )
                                        Text(
                                            text = model.tag,
                                            fontSize = 9.sp,
                                            color = if (isSelected) Color(0xFFFFDBCB) else Color(0xFF94A3B8)
                                        )
                                    }
                                }
                            }
                        }

                        HorizontalDivider(color = Color(0xFF334155))

                        // 2. Persona Role Selector
                        Text(
                            text = "🎭 System Persona & Role",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFDBCB)
                            )
                        )
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(AssistantRole.entries) { role ->
                                val isSelected = selectedRole == role
                                Surface(
                                    onClick = { selectedRole = role },
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isSelected) TerracottaPrimary else Color(0xFF334155),
                                    border = androidx.compose.foundation.BorderStroke(
                                        1.dp,
                                        if (isSelected) Color(0xFFFB923C) else Color(0xFF475569)
                                    ),
                                    modifier = Modifier.testTag("role_chip_${role.name}")
                                ) {
                                    Text(
                                        text = "${role.iconLabel} ${role.title}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = TextWhite,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                    )
                                }
                            }
                        }

                        HorizontalDivider(color = Color(0xFF334155))

                        // 3. Google Search Grounding Toggle
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.TravelExplore,
                                        contentDescription = null,
                                        tint = if (isSearchGroundingEnabled) Color(0xFF4ADE80) else Color(0xFF94A3B8),
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Google Search Grounding",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSearchGroundingEnabled) Color(0xFF4ADE80) else TextLight
                                        )
                                    )
                                }
                                Text(
                                    text = "Fetches live textile market data, 2026 fashion trends & external web research.",
                                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp, color = TextMutedLight)
                                )
                            }

                            Switch(
                                checked = isSearchGroundingEnabled,
                                onCheckedChange = { isSearchGroundingEnabled = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = TextWhite,
                                    checkedTrackColor = Color(0xFF16A34A),
                                    uncheckedThumbColor = Color(0xFF94A3B8),
                                    uncheckedTrackColor = Color(0xFF334155)
                                ),
                                modifier = Modifier.testTag("grounding_toggle_switch")
                            )
                        }
                    }
                }
            }

            // Chat Messages Scrollable Thread
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(top = 12.dp, bottom = 16.dp)
            ) {
                // Search Grounding active banner indicator
                if (isSearchGroundingEnabled) {
                    item {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF064E3B),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF059669)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("grounding_active_badge")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Language,
                                    contentDescription = null,
                                    tint = Color(0xFF4ADE80),
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Google Search Grounding Active • Real-time Web Intelligence",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFA7F3D0)
                                )
                            }
                        }
                    }
                }

                items(conversationHistory, key = { it.id }) { turn ->
                    ChatTurnBubble(turn = turn, onOpenUrl = { url ->
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Toast.makeText(context, "Cannot open URL: $url", Toast.LENGTH_SHORT).show()
                        }
                    })
                }

                if (isLoading) {
                    item {
                        Surface(
                            shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 2.dp),
                            color = AiBubbleDarkBg,
                            border = androidx.compose.foundation.BorderStroke(1.dp, AiBubbleBorder),
                            modifier = Modifier.widthIn(max = 300.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(18.dp),
                                    color = Color(0xFFFB923C),
                                    strokeWidth = 2.dp
                                )
                                Text(
                                    text = if (isSearchGroundingEnabled) "Searching Google & analyzing textile data..." else "Gemini is generating academic explanation...",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = TextLight
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChatTurnBubble(
    turn: ChatTurn,
    onOpenUrl: (String) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isCopied by remember { mutableStateOf(false) }

    if (turn.role == "user") {
        // User Message Bubble (Right-aligned, vibrant Terracotta container, white high-contrast text)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("user_message_${turn.id}"),
            contentAlignment = Alignment.CenterEnd
        ) {
            Surface(
                shape = RoundedCornerShape(18.dp, 18.dp, 2.dp, 18.dp),
                color = UserBubbleBg,
                border = androidx.compose.foundation.BorderStroke(1.dp, UserBubbleBorder),
                shadowElevation = 2.dp,
                modifier = Modifier.widthIn(max = 310.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = turn.text,
                        color = TextWhite,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 13.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.weight(1f, fill = false)
                    )
                }
            }
        }
    } else {
        // AI Response Card (Left-aligned, Dark Slate High-Contrast container with Rich Markdown)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("model_message_${turn.id}"),
            contentAlignment = Alignment.CenterStart
        ) {
            Surface(
                shape = RoundedCornerShape(18.dp, 18.dp, 18.dp, 2.dp),
                color = AiBubbleDarkBg,
                shadowElevation = 3.dp,
                border = androidx.compose.foundation.BorderStroke(1.dp, AiBubbleBorder),
                modifier = Modifier.fillMaxWidth(0.95f)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    // Header with Model badge, Grounding badge & Copy action
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = Color(0xFFFB923C),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = turn.modelUsed?.displayName ?: "Gemini Academic AI",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFFFDBCB),
                                    fontSize = 11.sp
                                )
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            if (turn.isSearchGrounded) {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = Color(0xFF064E3B),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF059669))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            Icons.Default.TravelExplore,
                                            contentDescription = null,
                                            tint = Color(0xFF4ADE80),
                                            modifier = Modifier.size(10.dp)
                                        )
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = "Grounded",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFA7F3D0)
                                        )
                                    }
                                }
                            }

                            // Copy message button
                            IconButton(
                                onClick = {
                                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                    clipboard.setPrimaryClip(ClipData.newPlainText("AI Response", turn.text))
                                    isCopied = true
                                    Toast.makeText(context, "Response copied to clipboard", Toast.LENGTH_SHORT).show()
                                    scope.launch {
                                        delay(2000)
                                        isCopied = false
                                    }
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = if (isCopied) Icons.Default.Done else Icons.Default.ContentCopy,
                                    contentDescription = "Copy Response",
                                    tint = if (isCopied) Color(0xFF4ADE80) else Color(0xFF94A3B8),
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Rich High-Contrast Markdown Renderer
                    AiMarkdownRenderer(
                        markdownText = turn.text,
                        onLinkClick = onOpenUrl
                    )

                    // Grounding Queries and Web Citations
                    if (turn.searchQueries.isNotEmpty() || turn.groundingSources.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(color = Color(0xFF334155), thickness = 1.dp)
                        Spacer(modifier = Modifier.height(8.dp))

                        if (turn.searchQueries.isNotEmpty()) {
                            Text(
                                text = "🔍 Google Search Queries:",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFDBCB)
                            )
                            turn.searchQueries.forEach { query ->
                                Text(
                                    text = "• \"$query\"",
                                    fontSize = 11.sp,
                                    color = TextLight,
                                    modifier = Modifier.padding(vertical = 1.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                        }

                        if (turn.groundingSources.isNotEmpty()) {
                            Text(
                                text = "🌐 Web Sources & Citations:",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFDBCB)
                            )
                            turn.groundingSources.take(3).forEach { source ->
                                Surface(
                                    onClick = { onOpenUrl(source.uri) },
                                    shape = RoundedCornerShape(6.dp),
                                    color = Color(0xFF0F172A),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF475569)),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 3.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                            contentDescription = "Open Link",
                                            tint = Color(0xFF60A5FA),
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = source.title.ifBlank { source.uri },
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = Color(0xFF60A5FA),
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

