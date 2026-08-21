import mongoose from 'mongoose';

/**
 * Connect to MongoDB Atlas or local MongoDB instance.
 */
export const connectDB = async () => {
  const mongoURI = process.env.MONGODB_URI || 'mongodb://localhost:27017/tdaclearners';
  try {
    const conn = await mongoose.connect(mongoURI, {
      serverSelectionTimeoutMS: 5000
    });
    console.log(`[MongoDB] Connected to database: ${conn.connection.host}/${conn.connection.name}`);
  } catch (error) {
    console.warn(`[MongoDB Warning] Connection delayed/offline: ${error.message}`);
  }
};
