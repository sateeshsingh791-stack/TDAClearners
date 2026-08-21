import { Router } from 'express';
import {
  createSubject,
  createTopic,
  createQuizQuestion,
  createFlashcard
} from '../controllers/admin.controller.js';
import { authenticateToken, requireAdmin } from '../middleware/auth.middleware.js';

const router = Router();

// All admin routes require authentication AND admin role
router.use(authenticateToken, requireAdmin);

router.post('/subjects', createSubject);
router.post('/topics', createTopic);
router.post('/quizzes', createQuizQuestion);
router.post('/flashcards', createFlashcard);

export default router;
