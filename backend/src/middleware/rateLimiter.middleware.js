import rateLimit from 'express-rate-limit';

/**
 * Global rate limiter: Max 100 requests per 15 minutes per IP
 */
export const globalLimiter = rateLimit({
  windowMs: 15 * 60 * 1000,
  max: 100,
  standardHeaders: true,
  legacyHeaders: false,
  message: {
    success: false,
    error: { message: 'Too many requests from this IP, please try again after 15 minutes.' }
  }
});

/**
 * Auth rate limiter: Max 10 attempts per 15 minutes per IP
 */
export const authLimiter = rateLimit({
  windowMs: 15 * 60 * 1000,
  max: 10,
  standardHeaders: true,
  legacyHeaders: false,
  message: {
    success: false,
    error: { message: 'Too many authentication attempts, please try again later.' }
  }
});

/**
 * AI Tutor rate limiter: Max 20 queries per 1 minute per IP
 */
export const aiLimiter = rateLimit({
  windowMs: 1 * 60 * 1000,
  max: 20,
  standardHeaders: true,
  legacyHeaders: false,
  message: {
    success: false,
    error: { message: 'AI Tutor query limit reached. Please wait a minute before asking another question.' }
  }
});
