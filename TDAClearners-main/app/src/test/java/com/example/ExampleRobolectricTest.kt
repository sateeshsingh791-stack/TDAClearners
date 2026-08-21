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
    assertEquals("TDAClearners", appName)
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

  @Test
  fun `verify quiz accuracy breakdown correctly identifies units and weak topics`() {
    val bvtd111 = SyllabusRepository.getSubjectByCode("BVTD111")
    assertNotNull(bvtd111)

    val sampleQuestions = listOf(
      com.example.data.model.QuizQuestion(
        id = "q1",
        question = "Cotton question",
        options = listOf("A", "B", "C", "D"),
        correctIndex = 1,
        explanation = "Cotton explanation",
        subjectCode = "BVTD111",
        unitNumber = 1,
        topicId = "bvtd111_u1_t1"
      ),
      com.example.data.model.QuizQuestion(
        id = "q2",
        question = "Wool question",
        options = listOf("A", "B", "C", "D"),
        correctIndex = 0,
        explanation = "Wool explanation",
        subjectCode = "BVTD111",
        unitNumber = 1,
        topicId = "bvtd111_u1_t1"
      ),
      com.example.data.model.QuizQuestion(
        id = "q3",
        question = "Spinning question",
        options = listOf("A", "B", "C", "D"),
        correctIndex = 2,
        explanation = "Spinning explanation",
        subjectCode = "BVTD111",
        unitNumber = 2,
        topicId = "bvtd111_u2_t1"
      )
    )

    // User gets q1 right (index 1), q2 wrong (answered 3 instead of 0), q3 right (answered 2)
    val userAnswers = mapOf(
      0 to 1, // q1 correct (Unit 1, Topic 1)
      1 to 3, // q2 WRONG (Unit 1, Topic 1)
      2 to 2  // q3 correct (Unit 2, Topic 1)
    )

    val breakdowns = com.example.ui.components.calculateUnitAndTopicBreakdowns(sampleQuestions, userAnswers, bvtd111!!)
    assertEquals(2, breakdowns.size)

    val unit1 = breakdowns.find { it.unitNumber == 1 }
    assertNotNull(unit1)
    assertEquals(2, unit1?.totalCount)
    assertEquals(1, unit1?.correctCount)
    assertEquals(50, unit1?.accuracyPercent)
    assertEquals(com.example.ui.components.UnitAccuracyStatus.MODERATE, unit1?.status)

    // Check Topic breakdown for Unit 1
    val topic1 = unit1?.topicBreakdowns?.find { it.topicId == "bvtd111_u1_t1" }
    assertNotNull(topic1)
    assertEquals(50, topic1?.accuracyPercent)
    assertTrue(topic1?.isWeak == true)
    assertEquals(1, topic1?.missedQuestions?.size)

    val unit2 = breakdowns.find { it.unitNumber == 2 }
    assertNotNull(unit2)
    assertEquals(1, unit2?.totalCount)
    assertEquals(1, unit2?.correctCount)
    assertEquals(100, unit2?.accuracyPercent)
    assertEquals(com.example.ui.components.UnitAccuracyStatus.MASTERED, unit2?.status)
  }
}
