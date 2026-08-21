import { Router } from 'express';
import { getScopedQuizzes, getScopedFlashcards } from '../controllers/learning.controller.js';

const router = Router();

router.get('/quizzes', getScopedQuizzes);
router.get('/flashcards', getScopedFlashcards);

export default router;
