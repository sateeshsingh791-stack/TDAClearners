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

enum class QuizDifficulty(val label: String) {
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    MIXED("Mixed")
}

enum class QuizMode(val displayName: String, val subtitle: String) {
    PRACTICE("Practice Quiz", "Instant feedback & step-by-step explanations"),
    EXAM("Exam Quiz", "Timed assessment with comprehensive review at end"),
    VIVA("Viva / Practical Quiz", "Practical laboratory procedures & viva voce questions"),
    QUICK("Quick Quiz", "Fast 5-question rapid revision")
}

enum class QuizScopeType(val label: String) {
    ENTIRE_SUBJECT("Entire Subject"),
    SPECIFIC_UNIT("Specific Unit"),
    SPECIFIC_TOPIC("Specific Topic")
}

data class QuizScopeSelection(
    val semesterNumber: Int = 1,
    val subjectCode: String = "BVTD111",
    val scopeType: QuizScopeType = QuizScopeType.ENTIRE_SUBJECT,
    val unitNumber: Int? = null,
    val topicId: String? = null,
    val quizMode: QuizMode = QuizMode.PRACTICE,
    val questionCount: Int = 10,
    val difficulty: QuizDifficulty = QuizDifficulty.MIXED
)

enum class FlashcardType(val label: String, val emoji: String) {
    DEFINITION("Definition", "📖"),
    IDENTIFICATION("Tools & Identification", "🔍"),
    CONCEPT("Core Concept", "💡"),
    PROCESS("Step-by-Step Process", "⚙️"),
    PRACTICAL("Practical Technique", "🧵"),
    VIVA("Viva Voce", "🎙️")
}

enum class CardMastery(val label: String) {
    UNSEEN("Unseen"),
    KNOW_IT("Know It"),
    NEED_PRACTICE("Need Practice"),
    DIFFICULT("Difficult")
}

data class FlashcardItem(
    val id: String,
    val subjectCode: String,
    val unitNumber: Int = 1,
    val topicId: String = "",
    val type: FlashcardType = FlashcardType.CONCEPT,
    val front: String,
    val back: String,
    val categoryHint: String = "Textile & Apparel Curriculum",
    val practicalTag: String? = null,
    val isOfficial: Boolean = true
)

data class QuizQuestion(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val difficulty: QuizDifficulty = QuizDifficulty.MEDIUM,
    val isPracticalViva: Boolean = false,
    val topicId: String = "",
    val subjectCode: String = "",
    val unitNumber: Int = 1
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
    val isOfficialSyllabusTopic: Boolean = true,
    val sourceLabel: String = "Official University Syllabus",
    val quizQuestions: List<QuizQuestion> = emptyList()
)

data class SubjectUnit(
    val unitNumber: Int,
    val title: String,
    val description: String = "",
    val isOfficialUnit: Boolean = true,
    val topics: List<TopicContent> = emptyList()
)

data class PracticalActivity(
    val id: String,
    val title: String,
    val subjectCode: String,
    val objective: String,
    val materialsRequired: List<String> = emptyList(),
    val theory: String = "",
    val stepByStepProcedure: List<String> = emptyList(),
    val expectedObservations: String = "",
    val precautions: List<String> = emptyList(),
    val vivaQuestions: List<Pair<String, String>> = emptyList(),
    val isOfficialSyllabusPractical: Boolean = true,
    val sourceLabel: String = "Official University Syllabus (Section-I)"
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
    val timeDurationHours: Int = 3,
    val mediumOfExam: String? = null,
    val instructionsForPaperSetters: String? = null,
    val courseObjectives: List<String> = emptyList(),
    val learningObjectives: List<String> = emptyList(),
    val courseOutcomes: List<String> = emptyList(),
    val booksPrescribed: List<String> = emptyList(),
    val officialSyllabusContents: List<String> = emptyList(),
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
