package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// High-Contrast Accessible Colors for AI Markdown Rendering
private val AiTextNormal = Color(0xFFF3F4F6)       // Ultra crisp light text (WCAG AAA)
private val AiTextSecondary = Color(0xFFD1D5DB)    // Light secondary text
private val AiTextMuted = Color(0xFF9CA3AF)        // Accessible muted text
private val AiHeadingWhite = Color(0xFFFFFFFF)     // High-contrast pure white
private val AiHeadingAccent = Color(0xFFFFDBCB)    // Bespoke Terracotta warm light heading
private val AiAccentOrange = Color(0xFFFB923C)     // Vivid Orange for bullets & badges
private val AiCodeText = Color(0xFF38BDF8)         // Bright Sky Blue for inline code & keywords
private val AiCodeBg = Color(0xFF0F172A)           // High-contrast dark slate for code blocks
private val AiInlineCodeBg = Color(0xFF334155)     // Pill background for inline code
private val AiCodeBorder = Color(0xFF475569)       // Code block border
private val AiLinkBlue = Color(0xFF60A5FA)         // High-contrast readable link color
private val AiQuoteBar = Color(0xFFFB923C)         // Blockquote accent line
private val AiQuoteBg = Color(0xFF1E293B).copy(alpha = 0.6f)
private val AiTableBg = Color(0xFF1E293B)
private val AiTableHeaderBg = Color(0xFF334155)
private val AiTableBorder = Color(0xFF475569)

/**
 * Renders rich AI response Markdown with high contrast, crisp headings,
 * styled code blocks, formatted bullet/numbered lists, quotes, tables, and clickable links.
 */
@Composable
fun AiMarkdownRenderer(
    markdownText: String,
    modifier: Modifier = Modifier,
    onLinkClick: (String) -> Unit = {}
) {
    val blocks = remember(markdownText) { parseMarkdownIntoBlocks(markdownText) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        blocks.forEachIndexed { index, block ->
            when (block) {
                is MarkdownBlock.Heading -> {
                    HeadingBlock(block)
                }
                is MarkdownBlock.Paragraph -> {
                    ParagraphBlock(block.text, onLinkClick)
                }
                is MarkdownBlock.BulletItem -> {
                    BulletItemBlock(block, onLinkClick)
                }
                is MarkdownBlock.NumberedItem -> {
                    NumberedItemBlock(block, onLinkClick)
                }
                is MarkdownBlock.CodeBlock -> {
                    CodeBlockView(block)
                }
                is MarkdownBlock.BlockQuote -> {
                    BlockQuoteView(block.text, onLinkClick)
                }
                is MarkdownBlock.Table -> {
                    TableView(block)
                }
                is MarkdownBlock.HorizontalRule -> {
                    HorizontalDivider(
                        color = Color(0xFF475569),
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun HeadingBlock(block: MarkdownBlock.Heading) {
    val (fontSize, fontWeight, color) = when (block.level) {
        1 -> Triple(18.sp, FontWeight.ExtraBold, AiHeadingWhite)
        2 -> Triple(16.sp, FontWeight.Bold, AiHeadingAccent)
        3 -> Triple(14.sp, FontWeight.Bold, AiHeadingWhite)
        else -> Triple(13.sp, FontWeight.SemiBold, AiHeadingAccent)
    }

    Text(
        text = block.text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = color,
        lineHeight = (fontSize.value * 1.35f).sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = if (block.level <= 2) 4.dp else 2.dp, bottom = 2.dp)
    )
}

@Composable
private fun ParagraphBlock(text: String, onLinkClick: (String) -> Unit) {
    val annotatedString = remember(text) { buildAnnotatedMarkdown(text) }

    ClickableText(
        text = annotatedString,
        style = TextStyle(
            color = AiTextNormal,
            fontSize = 13.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily.Default
        ),
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    onLinkClick(annotation.item)
                }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun BulletItemBlock(block: MarkdownBlock.BulletItem, onLinkClick: (String) -> Unit) {
    val annotatedString = remember(block.text) { buildAnnotatedMarkdown(block.text) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = (block.indentLevel * 12).dp, top = 2.dp, bottom = 2.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 7.dp, end = 8.dp)
                .size(6.dp)
                .clip(CircleShape)
                .background(AiAccentOrange)
        )

        ClickableText(
            text = annotatedString,
            style = TextStyle(
                color = AiTextNormal,
                fontSize = 13.sp,
                lineHeight = 20.sp
            ),
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                    .firstOrNull()?.let { annotation ->
                        onLinkClick(annotation.item)
                    }
            },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun NumberedItemBlock(block: MarkdownBlock.NumberedItem, onLinkClick: (String) -> Unit) {
    val annotatedString = remember(block.text) { buildAnnotatedMarkdown(block.text) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = (block.indentLevel * 12).dp, top = 2.dp, bottom = 2.dp),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            color = AiAccentOrange.copy(alpha = 0.2f),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.padding(top = 2.dp, end = 8.dp)
        ) {
            Text(
                text = "${block.number}.",
                color = AiAccentOrange,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
            )
        }

        ClickableText(
            text = annotatedString,
            style = TextStyle(
                color = AiTextNormal,
                fontSize = 13.sp,
                lineHeight = 20.sp
            ),
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                    .firstOrNull()?.let { annotation ->
                        onLinkClick(annotation.item)
                    }
            },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun CodeBlockView(block: MarkdownBlock.CodeBlock) {
    val context = LocalContext.current
    var copied by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Surface(
        color = AiCodeBg,
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, AiCodeBorder),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column {
            // Code header bar with language and copy button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1E293B))
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = block.language.ifBlank { "code" }.uppercase(),
                    color = AiTextSecondary,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            clipboard.setPrimaryClip(ClipData.newPlainText("Code", block.code))
                            copied = true
                            Toast.makeText(context, "Code copied to clipboard", Toast.LENGTH_SHORT).show()
                            scope.launch {
                                delay(2000)
                                copied = false
                            }
                        }
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = if (copied) Icons.Default.Done else Icons.Default.ContentCopy,
                        contentDescription = "Copy Code",
                        tint = if (copied) Color(0xFF4ADE80) else AiTextSecondary,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = if (copied) "Copied" else "Copy",
                        fontSize = 10.sp,
                        color = if (copied) Color(0xFF4ADE80) else AiTextSecondary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Code Content with horizontal scroll
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(12.dp)
            ) {
                Text(
                    text = block.code,
                    color = AiCodeText,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Composable
private fun BlockQuoteView(text: String, onLinkClick: (String) -> Unit) {
    val annotatedString = remember(text) { buildAnnotatedMarkdown(text) }

    Surface(
        color = AiQuoteBg,
        shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(2.dp))
                    .background(AiQuoteBar)
            )

            Spacer(modifier = Modifier.width(10.dp))

            ClickableText(
                text = annotatedString,
                style = TextStyle(
                    color = AiTextSecondary,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    lineHeight = 18.sp
                ),
                onClick = { offset ->
                    annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                        .firstOrNull()?.let { annotation ->
                            onLinkClick(annotation.item)
                        }
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun TableView(table: MarkdownBlock.Table) {
    Surface(
        color = AiTableBg,
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, AiTableBorder),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            // Header row
            if (table.headers.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .background(AiTableHeaderBg)
                        .padding(vertical = 6.dp, horizontal = 8.dp)
                ) {
                    table.headers.forEachIndexed { i, header ->
                        Text(
                            text = header,
                            color = AiHeadingWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .widthIn(min = 100.dp, max = 220.dp)
                                .padding(horizontal = 6.dp)
                        )
                    }
                }
                HorizontalDivider(color = AiTableBorder, thickness = 1.dp)
            }

            // Data rows
            table.rows.forEachIndexed { rowIndex, row ->
                val rowBg = if (rowIndex % 2 == 0) AiTableBg else Color(0xFF162032)
                Row(
                    modifier = Modifier
                        .background(rowBg)
                        .padding(vertical = 6.dp, horizontal = 8.dp)
                ) {
                    row.forEachIndexed { _, cell ->
                        Text(
                            text = cell,
                            color = AiTextNormal,
                            fontSize = 11.sp,
                            modifier = Modifier
                                .widthIn(min = 100.dp, max = 220.dp)
                                .padding(horizontal = 6.dp)
                        )
                    }
                }
                if (rowIndex < table.rows.size - 1) {
                    HorizontalDivider(color = AiTableBorder.copy(alpha = 0.5f), thickness = 0.5.dp)
                }
            }
        }
    }
}

/**
 * Builds an AnnotatedString parsing **bold**, *italic*, `inline code`, and [link](url).
 */
fun buildAnnotatedMarkdown(text: String): AnnotatedString {
    val builder = AnnotatedString.Builder()
    var i = 0
    val length = text.length

    while (i < length) {
        // 1. Inline Code: `code`
        if (text[i] == '`') {
            val end = text.indexOf('`', i + 1)
            if (end != -1) {
                val codeContent = text.substring(i + 1, end)
                builder.pushStyle(
                    SpanStyle(
                        fontFamily = FontFamily.Monospace,
                        color = AiCodeText,
                        background = AiInlineCodeBg,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                builder.append(" $codeContent ")
                builder.pop()
                i = end + 1
                continue
            }
        }

        // 2. Bold text: **bold** or __bold__
        if (i + 1 < length && (text.substring(i, i + 2) == "**" || text.substring(i, i + 2) == "__")) {
            val marker = text.substring(i, i + 2)
            val end = text.indexOf(marker, i + 2)
            if (end != -1) {
                val boldContent = text.substring(i + 2, end)
                builder.pushStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = AiHeadingWhite
                    )
                )
                builder.append(boldContent)
                builder.pop()
                i = end + 2
                continue
            }
        }

        // 3. Italic text: *italic* or _italic_
        if (text[i] == '*' || text[i] == '_') {
            val marker = text[i]
            val end = text.indexOf(marker, i + 1)
            if (end != -1 && end > i + 1 && (end + 1 >= length || text[end + 1] != marker)) {
                val italicContent = text.substring(i + 1, end)
                builder.pushStyle(
                    SpanStyle(
                        fontStyle = FontStyle.Italic,
                        color = AiTextSecondary
                    )
                )
                builder.append(italicContent)
                builder.pop()
                i = end + 1
                continue
            }
        }

        // 4. Markdown Link: [Title](https://...)
        if (text[i] == '[') {
            val closeBracket = text.indexOf(']', i + 1)
            if (closeBracket != -1 && closeBracket + 1 < length && text[closeBracket + 1] == '(') {
                val closeParen = text.indexOf(')', closeBracket + 2)
                if (closeParen != -1) {
                    val linkTitle = text.substring(i + 1, closeBracket)
                    val linkUrl = text.substring(closeBracket + 2, closeParen)

                    builder.pushStringAnnotation(tag = "URL", annotation = linkUrl)
                    builder.pushStyle(
                        SpanStyle(
                            color = AiLinkBlue,
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    builder.append(linkTitle)
                    builder.pop()
                    builder.pop()
                    i = closeParen + 1
                    continue
                }
            }
        }

        // Default: Plain character
        builder.append(text[i])
        i++
    }

    return builder.toAnnotatedString()
}

// Sealed hierarchy of parsed markdown elements
sealed class MarkdownBlock {
    data class Heading(val level: Int, val text: String) : MarkdownBlock()
    data class Paragraph(val text: String) : MarkdownBlock()
    data class BulletItem(val text: String, val indentLevel: Int = 0) : MarkdownBlock()
    data class NumberedItem(val number: String, val text: String, val indentLevel: Int = 0) : MarkdownBlock()
    data class CodeBlock(val code: String, val language: String = "") : MarkdownBlock()
    data class BlockQuote(val text: String) : MarkdownBlock()
    data class Table(val headers: List<String>, val rows: List<List<String>>) : MarkdownBlock()
    object HorizontalRule : MarkdownBlock()
}

/**
 * Parses raw markdown text into structural blocks.
 */
fun parseMarkdownIntoBlocks(content: String): List<MarkdownBlock> {
    val blocks = mutableListOf<MarkdownBlock>()
    val lines = content.lines()
    var lineIdx = 0

    while (lineIdx < lines.size) {
        val rawLine = lines[lineIdx]
        val trimmed = rawLine.trim()

        // 1. Check for Code Block (```)
        if (trimmed.startsWith("```")) {
            val language = trimmed.removePrefix("```").trim()
            val codeBuilder = StringBuilder()
            lineIdx++
            while (lineIdx < lines.size && !lines[lineIdx].trim().startsWith("```")) {
                codeBuilder.append(lines[lineIdx]).append("\n")
                lineIdx++
            }
            if (lineIdx < lines.size && lines[lineIdx].trim().startsWith("```")) {
                lineIdx++ // Skip closing ```
            }
            blocks.add(MarkdownBlock.CodeBlock(code = codeBuilder.toString().trimEnd(), language = language))
            continue
        }

        // 2. Check for Table (| Header | Header |)
        if (trimmed.startsWith("|") && trimmed.endsWith("|") && lineIdx + 1 < lines.size && lines[lineIdx + 1].trim().contains("---")) {
            val headerCells = trimmed.split("|").map { it.trim() }.filter { it.isNotEmpty() }
            lineIdx += 2 // skip header and separator lines
            val rows = mutableListOf<List<String>>()
            while (lineIdx < lines.size && lines[lineIdx].trim().startsWith("|") && lines[lineIdx].trim().endsWith("|")) {
                val cells = lines[lineIdx].trim().split("|").map { it.trim() }.filter { it.isNotEmpty() }
                if (cells.isNotEmpty()) {
                    rows.add(cells)
                }
                lineIdx++
            }
            blocks.add(MarkdownBlock.Table(headers = headerCells, rows = rows))
            continue
        }

        // 3. Skip empty lines
        if (trimmed.isEmpty()) {
            lineIdx++
            continue
        }

        // 4. Horizontal Rule
        if (trimmed == "---" || trimmed == "***" || trimmed == "___") {
            blocks.add(MarkdownBlock.HorizontalRule)
            lineIdx++
            continue
        }

        // 5. Headings (#, ##, ###, ####)
        if (trimmed.startsWith("#")) {
            val hashCount = trimmed.takeWhile { it == '#' }.length
            val headingText = trimmed.removePrefix("#".repeat(hashCount)).trim()
            blocks.add(MarkdownBlock.Heading(level = hashCount.coerceIn(1, 4), text = headingText))
            lineIdx++
            continue
        }

        // 6. Blockquotes (> ...)
        if (trimmed.startsWith(">")) {
            val quoteText = trimmed.removePrefix(">").trim()
            blocks.add(MarkdownBlock.BlockQuote(text = quoteText))
            lineIdx++
            continue
        }

        // 7. Bullet lists (•, -, *)
        if (trimmed.startsWith("•") || trimmed.startsWith("- ") || trimmed.startsWith("* ")) {
            val indent = (rawLine.takeWhile { it.isWhitespace() }.length / 2).coerceAtMost(3)
            val itemText = if (trimmed.startsWith("•")) {
                trimmed.removePrefix("•").trim()
            } else {
                trimmed.substring(2).trim()
            }
            blocks.add(MarkdownBlock.BulletItem(text = itemText, indentLevel = indent))
            lineIdx++
            continue
        }

        // 8. Numbered lists (1., 2., etc.)
        val numberMatch = Regex("""^(\d+)[\.\)]\s+(.*)""").find(trimmed)
        if (numberMatch != null) {
            val number = numberMatch.groupValues[1]
            val itemText = numberMatch.groupValues[2]
            val indent = (rawLine.takeWhile { it.isWhitespace() }.length / 2).coerceAtMost(3)
            blocks.add(MarkdownBlock.NumberedItem(number = number, text = itemText, indentLevel = indent))
            lineIdx++
            continue
        }

        // 9. Standard Paragraph
        blocks.add(MarkdownBlock.Paragraph(text = trimmed))
        lineIdx++
    }

    return blocks
}
