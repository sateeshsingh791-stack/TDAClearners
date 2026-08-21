/**
 * Centralized global error handling middleware for Express.
 * Sanitizes internal stack traces in production while returning clean JSON.
 */
export const errorHandler = (err, req, res, next) => {
  console.error(`[ERROR] ${req.method} ${req.originalUrl}:`, err);

  const statusCode = res.statusCode !== 200 ? res.statusCode : (err.statusCode || 500);
  const message = err.message || 'Internal Server Error';

  res.status(statusCode).json({
    success: false,
    error: {
      message,
      ...(process.env.NODE_ENV === 'development' && { stack: err.stack })
    }
  });
};
