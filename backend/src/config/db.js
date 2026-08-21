import mongoose from 'mongoose';

/**
 * Connect to MongoDB Atlas or local MongoDB instance safely.
 */
export const connectDB = async () => {
  const mongoURI = process.env.MONGODB_URI;

  // Disable buffering so queries fail-fast to fallbacks if DB is not connected
  mongoose.set('bufferCommands', false);

  if (!mongoURI || mongoURI.includes('localhost') || mongoURI.includes('127.0.0.1')) {
    if (process.env.VERCEL || process.env.NODE_ENV === 'production') {
      console.log('[MongoDB] Skipping local connection in production environment. Using initialData fallback.');
      return;
    }
  }

  try {
    const conn = await mongoose.connect(mongoURI || 'mongodb://localhost:27017/tdaclearners', {
      serverSelectionTimeoutMS: 3000
    });
    console.log(`[MongoDB] Connected to database: ${conn.connection.host}/${conn.connection.name}`);
  } catch (error) {
    console.warn(`[MongoDB Warning] Connection failed: ${error.message}. App will use initialData fallback.`);
  }
};
