import jwt from 'jsonwebtoken';

/**
 * Generate a JWT token for a user.
 */
export const generateToken = (userId, role) => {
  const secret = process.env.JWT_SECRET || 'fallback_jwt_secret_dev';
  const expiresIn = process.env.JWT_EXPIRES_IN || '7d';
  return jwt.sign({ id: userId, role }, secret, { expiresIn });
};

/**
 * Verify a JWT token.
 */
export const verifyToken = (token) => {
  const secret = process.env.JWT_SECRET || 'fallback_jwt_secret_dev';
  return jwt.verify(token, secret);
};
