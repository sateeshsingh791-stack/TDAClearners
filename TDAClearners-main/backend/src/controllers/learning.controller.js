import { QuizQuestion } from '../models/QuizQuestion.js';
import { Flashcard } from '../models/Flashcard.js';

/**
 * Get scoped quiz questions with scope-aware filtering
 */
export const getScopedQuizzes = async (req, res, next) => {
  try {
    const { subjectCode, unitNumber, topicId, quizMode, difficulty, count } = req.query;

    const filter = {};
    if (subjectCode) {
      filter.subjectCode = subjectCode.replace(/\s+/g, '').toUpperCase();
    }
    if (unitNumber && parseInt(unitNumber, 10) > 0) {
      filter.unitNumber = parseInt(unitNumber, 10);
    }
    if (topicId) {
      filter.topicId = topicId;
    }
    if (quizMode === 'VIVA') {
      filter.isPracticalViva = true;
    }
    if (difficulty && difficulty !== 'MIXED') {
      filter.difficulty = difficulty.toUpperCase();
    }

    let questions = await QuizQuestion.find(filter);

    // Limit count (default 10, Quick mode 5)
    const targetCount = quizMode === 'QUICK' ? 5 : (parseInt(count, 10) || 10);
    
    // Shuffle and pick target count
    questions = questions.sort(() => 0.5 - Math.random()).slice(0, targetCount);

    res.status(200).json({
      success: true,
      data: { questions }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * Get scoped flashcards with type filtering
 */
export const getScopedFlashcards = async (req, res, next) => {
  try {
    const { subjectCode, unitNumber, topicId, type } = req.query;

    const filter = {};
    if (subjectCode) {
      filter.subjectCode = subjectCode.replace(/\s+/g, '').toUpperCase();
    }
    if (unitNumber && parseInt(unitNumber, 10) > 0) {
      filter.unitNumber = parseInt(unitNumber, 10);
    }
    if (topicId) {
      filter.topicId = topicId;
    }
    if (type) {
      filter.type = type.toUpperCase();
    }

    const flashcards = await Flashcard.find(filter);

    res.status(200).json({
      success: true,
      data: { flashcards }
    });
  } catch (error) {
    next(error);
  }
};
