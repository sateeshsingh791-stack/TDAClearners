package com.example.data.repository

import com.example.data.model.CardMastery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class QuizScoreRecord(
    val quizTitle: String,
    val score: Int,
    val totalQuestions: Int,
    val timestamp: Long = System.currentTimeMillis()
)

data class QuizAttemptRecord(
    val id: String = UUID.randomUUID().toString(),
    val subjectCode: String,
    val subjectName: String,
    val scopeLabel: String,
    val quizMode: String,
    val difficulty: String,
    val score: Int,
    val totalQuestions: Int,
    val percentage: Int,
    val timeTakenSeconds: Int = 0,
    val timestamp: Long = System.currentTimeMillis()
)

data class LearningState(
    val completedTopicIds: Set<String> = setOf("bvtd111_u1_t1", "bvtd112_t1", "bvtd113_u1_t1"),
    val bookmarkedTopicIds: Set<String> = setOf("bvtd111_u1_t1", "bvtd113_u1_t1", "bvtd121_u1_t1", "bvtd123_t1"),
    val quizScores: List<QuizScoreRecord> = listOf(
        QuizScoreRecord("Design Elements & Entrepreneurship", 4, 5)
    ),
    val quizAttempts: List<QuizAttemptRecord> = listOf(
        QuizAttemptRecord(
            subjectCode = "BVTD112",
            subjectName = "Sewing Techniques — Practical",
            scopeLabel = "Entire Subject",
            quizMode = "Viva / Practical Quiz",
            difficulty = "Mixed",
            score = 7,
            totalQuestions = 8,
            percentage = 88,
            timeTakenSeconds = 114
        ),
        QuizAttemptRecord(
            subjectCode = "BVTD111",
            subjectName = "Textile Science",
            scopeLabel = "Unit 1: Introduction to Fibres",
            quizMode = "Practice Quiz",
            difficulty = "Medium",
            score = 4,
            totalQuestions = 5,
            percentage = 80,
            timeTakenSeconds = 85
        )
    ),
    val flashcardMastery: Map<String, CardMastery> = mapOf(
        "fc_bvtd112_1" to CardMastery.KNOW_IT,
        "fc_bvtd112_3" to CardMastery.KNOW_IT,
        "fc_bvtd112_2" to CardMastery.NEED_PRACTICE,
        "fc_bvtd111_2" to CardMastery.DIFFICULT
    ),
    val selectedSemester: Int = 1,
    val selectedYear: Int = 1
)

object UserLearningRepository {
    private val _learningState = MutableStateFlow(LearningState())
    val learningState: StateFlow<LearningState> = _learningState.asStateFlow()

    fun toggleTopicCompletion(topicId: String) {
        _learningState.update { current ->
            val newCompleted = if (current.completedTopicIds.contains(topicId)) {
                current.completedTopicIds - topicId
            } else {
                current.completedTopicIds + topicId
            }
            current.copy(completedTopicIds = newCompleted)
        }
    }

    fun toggleBookmark(topicId: String) {
        _learningState.update { current ->
            val newBookmarks = if (current.bookmarkedTopicIds.contains(topicId)) {
                current.bookmarkedTopicIds - topicId
            } else {
                current.bookmarkedTopicIds + topicId
            }
            current.copy(bookmarkedTopicIds = newBookmarks)
        }
    }

    fun recordQuizScore(title: String, score: Int, total: Int) {
        _learningState.update { current ->
            current.copy(
                quizScores = current.quizScores + QuizScoreRecord(title, score, total)
            )
        }
    }

    fun recordQuizAttempt(attempt: QuizAttemptRecord) {
        _learningState.update { current ->
            current.copy(
                quizAttempts = listOf(attempt) + current.quizAttempts,
                quizScores = current.quizScores + QuizScoreRecord(
                    quizTitle = "${attempt.subjectCode}: ${attempt.scopeLabel}",
                    score = attempt.score,
                    totalQuestions = attempt.totalQuestions,
                    timestamp = attempt.timestamp
                )
            )
        }
    }

    fun updateFlashcardMastery(cardId: String, mastery: CardMastery) {
        _learningState.update { current ->
            current.copy(
                flashcardMastery = current.flashcardMastery + (cardId to mastery)
            )
        }
    }

    fun addQuizResult(isCorrect: Boolean) {
        _learningState.update { current ->
            val last = current.quizScores.lastOrNull()
            if (last != null && last.quizTitle == "Curriculum Practice") {
                val updatedList = current.quizScores.dropLast(1) + last.copy(
                    score = if (isCorrect) last.score + 1 else last.score,
                    totalQuestions = last.totalQuestions + 1
                )
                current.copy(quizScores = updatedList)
            } else {
                current.copy(
                    quizScores = current.quizScores + QuizScoreRecord(
                        "Curriculum Practice",
                        if (isCorrect) 1 else 0,
                        1
                    )
                )
            }
        }
    }

    fun selectSemester(semesterNum: Int) {
        _learningState.update { it.copy(selectedSemester = semesterNum) }
    }

    fun selectYear(yearNum: Int) {
        _learningState.update { it.copy(selectedYear = yearNum) }
    }

    fun calculateSemesterProgress(semesterNum: Int): Float {
        val semester = SyllabusRepository.semesters.find { it.number == semesterNum } ?: return 0f
        val allTopics = semester.subjects.flatMap { it.units.flatMap { u -> u.topics } }
        if (allTopics.isEmpty()) return 0f
        val completed = allTopics.count { _learningState.value.completedTopicIds.contains(it.id) }
        return (completed.toFloat() / allTopics.size).coerceIn(0f, 1f)
    }

    fun calculateSubjectProgress(subjectCode: String): Float {
        val subject = SyllabusRepository.getSubjectByCode(subjectCode) ?: return 0f
        val topics = subject.units.flatMap { it.topics }
        if (topics.isEmpty()) return 0f
        val completed = topics.count { _learningState.value.completedTopicIds.contains(it.id) }
        return (completed.toFloat() / topics.size).coerceIn(0f, 1f)
    }
}
