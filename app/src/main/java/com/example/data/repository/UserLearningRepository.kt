package com.example.data.repository

import com.example.data.model.TopicContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class QuizScoreRecord(
    val quizTitle: String,
    val score: Int,
    val totalQuestions: Int,
    val timestamp: Long = System.currentTimeMillis()
)

data class LearningState(
    val completedTopicIds: Set<String> = setOf("bvtd111_u1_t1", "bvtd112_t1"),
    val bookmarkedTopicIds: Set<String> = setOf("bvtd111_u1_t1", "bvtd121_u1_t1", "bvtd123_t1"),
    val quizScores: List<QuizScoreRecord> = listOf(
        QuizScoreRecord("Design Elements Quiz", 2, 2)
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
