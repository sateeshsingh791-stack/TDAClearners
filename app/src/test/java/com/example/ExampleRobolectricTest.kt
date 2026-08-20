package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.api.AssistantRole
import com.example.data.api.GeminiModel
import com.example.data.api.GeminiRepository
import com.example.data.repository.SyllabusRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Textile Design", appName)
  }

  @Test
  fun `verify syllabus repository content`() {
    val subjects = SyllabusRepository.getAllSubjects()
    assertTrue(subjects.isNotEmpty())
    val bvtd111 = SyllabusRepository.getSubjectByCode("BVTD 111")
    assertTrue(bvtd111 != null)
    assertEquals(2, bvtd111?.totalCredits)
  }

  @Test
  fun `verify gemini models and roles configuration`() {
    assertEquals("gemini-3.5-flash", GeminiModel.FLASH.modelId)
    assertEquals("gemini-3.1-pro-preview", GeminiModel.PRO.modelId)
    assertEquals("gemini-3.1-flash-lite-preview", GeminiModel.FLASH_LITE.modelId)

    assertTrue(AssistantRole.ACADEMIC_PROFESSOR.systemInstruction.contains("Khalsa College"))
    assertTrue(AssistantRole.INDUSTRY_EXPERT.systemInstruction.contains("Merchandiser"))
  }

  @Test
  fun `verify gemini repository multi turn conversation handling`() = runBlocking {
    val result = GeminiRepository.sendMessage(
      history = emptyList(),
      newUserMessage = "Explain burning test of silk",
      selectedModel = GeminiModel.FLASH,
      selectedRole = AssistantRole.ACADEMIC_PROFESSOR,
      enableSearchGrounding = false
    )
    assertTrue(result.isSuccess)
    val chatTurn = result.getOrNull()
    assertNotNull(chatTurn)
    assertTrue(chatTurn?.text?.contains("Silk") == true || chatTurn?.text?.contains("Protein") == true || chatTurn?.text?.isNotBlank() == true)
  }

  @Test
  fun `verify markdown block parser handles headings lists code and tables`() {
    val sampleMarkdown = """
      # Burning Test Protocol
      ## Fibre Classification
      - Cotton burns with yellow flame
      - Silk burns slowly with hair odor
      1. Prepare sample
      2. Hold with forceps
      ```kotlin
      val gsm = (warpCount + weftCount) * 1.5
      ```
      > Safety first in the lab
      | Fibre | Odor | Ash |
      |---|---|---|
      | Cotton | Paper | Light Gray |
      | Silk | Hair | Dark Bead |
    """.trimIndent()

    val blocks = com.example.ui.components.parseMarkdownIntoBlocks(sampleMarkdown)
    assertTrue(blocks.isNotEmpty())
    assertTrue(blocks.any { it is com.example.ui.components.MarkdownBlock.Heading })
    assertTrue(blocks.any { it is com.example.ui.components.MarkdownBlock.BulletItem })
    assertTrue(blocks.any { it is com.example.ui.components.MarkdownBlock.NumberedItem })
    assertTrue(blocks.any { it is com.example.ui.components.MarkdownBlock.CodeBlock })
    assertTrue(blocks.any { it is com.example.ui.components.MarkdownBlock.BlockQuote })
    assertTrue(blocks.any { it is com.example.ui.components.MarkdownBlock.Table })
  }
}
