import dotenv from 'dotenv';
import app from './app.js';
import { connectDB } from './config/db.js';

dotenv.config();

const PORT = process.env.PORT || 5000;

app.listen(PORT, () => {
  console.log(`=======================================================`);
  console.log(`  TDAClearners Backend Server running on port ${PORT} `);
  console.log(`  Environment: ${process.env.NODE_ENV || 'development'}`);
  console.log(`  Health Check: http://localhost:${PORT}/health`);
  console.log(`=======================================================`);
  
  // Connect to MongoDB Atlas / local instance in background
  connectDB();
});
