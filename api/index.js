import app from '../backend/src/app.js';
import { connectDB } from '../backend/src/config/db.js';

// Safe non-blocking connection trigger
try {
  connectDB();
} catch (e) {
  console.warn('[Vercel API Warning] DB init error:', e.message);
}

export default app;
