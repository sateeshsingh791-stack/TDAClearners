import { Router } from 'express';
import {
  getStudentProgress,
  toggleTopicCompletion,
  toggleBookmark,
  updateFlashcardMastery,
  getQuizAttempts,
  recordQuizAttempt
} from '../controllers/student.controller.js';
import { authenticateToken } from '../middleware/auth.middleware.js';

const router = Router();

// All student routes require authentication
router.use(authenticateToken);

router.get('/progress', getStudentProgress);
router.post('/progress/topic-toggle', toggleTopicCompletion);
router.post('/progress/bookmark-toggle', toggleBookmark);
router.post('/progress/flashcard-mastery', updateFlashcardMastery);
router.get('/attempts', getQuizAttempts);
router.post('/attempts', recordQuizAttempt);

export default router;
