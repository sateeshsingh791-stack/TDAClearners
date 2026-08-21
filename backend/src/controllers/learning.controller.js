import mongoose from 'mongoose';
import { QuizQuestion } from '../models/QuizQuestion.js';
import { Flashcard } from '../models/Flashcard.js';
import { INITIAL_QUIZZES, INITIAL_FLASHCARDS } from '../data/initialData.js';

const isDbConnected = () => mongoose.connection.readyState === 1;

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

    let questions = [];
    if (isDbConnected()) {
      try {
        questions = await QuizQuestion.find(filter);
      } catch (e) {
        questions = [];
      }
    }

    if (!questions || questions.length === 0) {
      const cleanSubj = subjectCode ? subjectCode.replace(/\s+/g, '').toUpperCase() : null;
      questions = INITIAL_QUIZZES.filter((q) => {
        if (cleanSubj && q.subjectCode.replace(/\s+/g, '').toUpperCase() !== cleanSubj) return false;
        if (unitNumber && parseInt(unitNumber, 10) > 0 && q.unitNumber !== parseInt(unitNumber, 10)) return false;
        if (topicId && q.topicId !== topicId) return false;
        if (quizMode === 'VIVA' && !q.isPracticalViva) return false;
        if (difficulty && difficulty !== 'MIXED' && q.difficulty.toUpperCase() !== difficulty.toUpperCase()) return false;
        return true;
      });
    }

    const targetCount = quizMode === 'QUICK' ? 5 : (parseInt(count, 10) || 10);
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

    let flashcards = [];
    if (isDbConnected()) {
      try {
        flashcards = await Flashcard.find(filter);
      } catch (e) {
        flashcards = [];
      }
    }

    if (!flashcards || flashcards.length === 0) {
      const cleanSubj = subjectCode ? subjectCode.replace(/\s+/g, '').toUpperCase() : null;
      flashcards = INITIAL_FLASHCARDS.filter((f) => {
        if (cleanSubj && f.subjectCode.replace(/\s+/g, '').toUpperCase() !== cleanSubj) return false;
        if (unitNumber && parseInt(unitNumber, 10) > 0 && f.unitNumber !== parseInt(unitNumber, 10)) return false;
        if (topicId && f.topicId !== topicId) return false;
        if (type && f.type.toUpperCase() !== type.toUpperCase()) return false;
        return true;
      });
    }

    res.status(200).json({
      success: true,
      data: { flashcards }
    });
  } catch (error) {
    next(error);
  }
};
