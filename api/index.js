import app from '../backend/src/app.js';
import { connectDB } from '../backend/src/config/db.js';

// Initiate MongoDB Atlas connection if MONGODB_URI is provided
connectDB();

export default app;
