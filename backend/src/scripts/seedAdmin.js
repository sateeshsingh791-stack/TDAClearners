import dotenv from 'dotenv';
import bcrypt from 'bcryptjs';
import mongoose from 'mongoose';
import { User } from '../models/User.js';

dotenv.config();

/**
 * CLI Script to securely provision an Administrator account.
 * Admin accounts can NEVER be registered through public APIs.
 * Usage: npm run seed:admin
 */
const seedAdmin = async () => {
  const mongoURI = process.env.MONGODB_URI || 'mongodb://localhost:27017/tdaclearners';
  const adminName = process.env.ADMIN_NAME || 'TDAC System Admin';
  const adminEmail = (process.env.ADMIN_EMAIL || 'admin@tdaclearners.edu').toLowerCase();
  const adminPassword = process.env.ADMIN_PASSWORD || 'AdminSecret2026!';

  try {
    await mongoose.connect(mongoURI);
    console.log('[Seed Admin] Connected to MongoDB Atlas...');

    const existingAdmin = await User.findOne({ email: adminEmail });
    if (existingAdmin) {
      console.log(`[Seed Admin] Admin account already exists for ${adminEmail}. Updating role to admin if needed...`);
      existingAdmin.role = 'admin';
      await existingAdmin.save();
      console.log('[Seed Admin] Existing admin verified.');
      process.exit(0);
    }

    const salt = await bcrypt.genSalt(12);
    const passwordHash = await bcrypt.hash(adminPassword, salt);

    const adminUser = await User.create({
      name: adminName,
      email: adminEmail,
      passwordHash,
      role: 'admin',
      targetSemester: 1,
      targetYear: 1
    });

    console.log(`[Seed Admin] SUCCESS! Admin account created:`);
    console.log(`  Name:  ${adminUser.name}`);
    console.log(`  Email: ${adminUser.email}`);
    console.log(`  Role:  ${adminUser.role}`);

    process.exit(0);
  } catch (error) {
    console.error(`[Seed Admin Error] ${error.message}`);
    process.exit(1);
  }
};

seedAdmin();
