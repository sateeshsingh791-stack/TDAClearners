import { verifyToken } from '../utils/jwt.js';
import { User } from '../models/User.js';

/**
 * Middleware to authenticate requests using JWT Bearer Token.
 */
export const authenticateToken = async (req, res, next) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.startsWith('Bearer ') ? authHeader.split(' ')[1] : null;

  if (!token) {
    return res.status(401).json({
      success: false,
      error: { message: 'Access denied. Authentication token required.' }
    });
  }

  try {
    const decoded = verifyToken(token);
    const user = await User.findById(decoded.id).select('-passwordHash');

    if (!user) {
      return res.status(401).json({
        success: false,
        error: { message: 'Invalid token. User no longer exists.' }
      });
    }

    req.user = user;
    next();
  } catch (err) {
    return res.status(403).json({
      success: false,
      error: { message: 'Invalid or expired token.' }
    });
  }
};

/**
 * Middleware to restrict access exclusively to Admin users.
 */
export const requireAdmin = (req, res, next) => {
  if (!req.user || req.user.role !== 'admin') {
    return res.status(403).json({
      success: false,
      error: { message: 'Access forbidden. Administrator privileges required.' }
    });
  }
  next();
};
