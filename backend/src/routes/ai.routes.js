import { Router } from 'express';
import { chatProxy } from '../controllers/ai.controller.js';
import { authenticateToken } from '../middleware/auth.middleware.js';
import { aiLimiter } from '../middleware/rateLimiter.middleware.js';

const router = Router();

// AI Chat proxy endpoint (Protected by Auth Token & AI Rate Limiter)
router.post('/chat', authenticateToken, aiLimiter, chatProxy);

export default router;
