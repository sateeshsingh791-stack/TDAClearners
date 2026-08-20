package com.example.data.model

enum class CourseCategory(val displayName: String, val badgeColorHex: Long) {
    MAJOR("Major Course", 0xFFB34700),
    MINOR("Minor Course", 0xFF775748),
    ABILITY_ENHANCEMENT("Ability Enhancement", 0xFF0284C7),
    VALUE_ADDED("Value Added", 0xFF16A34A)
}

enum class SubjectType(val label: String) {
    THEORY("Theory"),
    PRACTICAL("Practical"),
    THEORY_AND_PRACTICAL("Theory + Practical")
}

data class QuizQuestion(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class TopicContent(
    val id: String,
    val title: String,
    val unitNumber: Int = 1,
    val overview: String = "",
    val keyPoints: List<String> = emptyList(),
    val importantTerms: Map<String, String> = emptyMap(),
    val visualExplanation: String = "Technical schematic and design workflow representation.",
    val industrialRelevance: String = "Key requirement in modern textile mills and apparel design houses.",
    val quickRevisionSummary: String = "Core syllabus concept for examination review.",
    val practicalApplication: String? = null,
    val quizQuestions: List<QuizQuestion> = emptyList()
)

data class SubjectUnit(
    val unitNumber: Int,
    val title: String,
    val description: String = "",
    val topics: List<TopicContent> = emptyList()
)

data class PracticalActivity(
    val id: String,
    val title: String,
    val subjectCode: String,
    val objective: String,
    val materialsRequired: List<String> = emptyList(),
    val stepByStepProcedure: List<String> = emptyList(),
    val expectedObservations: String = "",
    val precautions: List<String> = emptyList(),
    val vivaQuestions: List<Pair<String, String>> = emptyList()
)

data class Subject(
    val code: String,
    val name: String,
    val semesterNumber: Int,
    val category: CourseCategory,
    val type: SubjectType,
    val hoursPerWeek: Int,
    val lectureCredits: Int,
    val tutorialCredits: Int,
    val practicalCredits: Int,
    val totalCredits: Int,
    val theoryMarks: Int?,
    val practicalMarks: Int?,
    val internalAssessmentMarks: Int,
    val totalMarks: Int,
    val syllabusPageRef: String,
    val overview: String = "",
    val learningObjectives: List<String> = emptyList(),
    val units: List<SubjectUnit> = emptyList(),
    val practicals: List<PracticalActivity> = emptyList()
)

data class Semester(
    val number: Int,
    val title: String,
    val yearNumber: Int,
    val totalCredits: Int,
    val totalMarks: Int,
    val totalHoursPerWeek: Int,
    val subjects: List<Subject>
)

data class AcademicYear(
    val yearNumber: Int,
    val title: String,
    val semesters: List<Semester>,
    val isSyllabusAvailable: Boolean = true
)

data class CourseResource(
    val id: String,
    val title: String,
    val subjectCode: String,
    val category: String, // "Notes", "Manual", "Diagram", "Question Paper"
    val description: String,
    val downloadSize: String,
    val format: String
)

data class CareerRole(
    val title: String,
    val sector: String,
    val description: String,
    val keySkills: List<String>,
    val standardTools: List<String>,
    val industryScope: String
)
