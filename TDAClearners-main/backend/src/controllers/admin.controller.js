import { Subject } from '../models/Subject.js';
import { Topic } from '../models/Topic.js';
import { QuizQuestion } from '../models/QuizQuestion.js';
import { Flashcard } from '../models/Flashcard.js';

export const createSubject = async (req, res, next) => {
  try {
    const subject = await Subject.create(req.body);
    res.status(201).json({ success: true, data: { subject } });
  } catch (error) {
    next(error);
  }
};

export const createTopic = async (req, res, next) => {
  try {
    const topic = await Topic.create(req.body);
    res.status(201).json({ success: true, data: { topic } });
  } catch (error) {
    next(error);
  }
};

export const createQuizQuestion = async (req, res, next) => {
  try {
    const question = await QuizQuestion.create(req.body);
    res.status(201).json({ success: true, data: { question } });
  } catch (error) {
    next(error);
  }
};

export const createFlashcard = async (req, res, next) => {
  try {
    const flashcard = await Flashcard.create(req.body);
    res.status(201).json({ success: true, data: { flashcard } });
  } catch (error) {
    next(error);
  }
};
