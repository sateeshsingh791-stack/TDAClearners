import dotenv from 'dotenv';
import mongoose from 'mongoose';
import { Subject } from '../models/Subject.js';
import { Topic } from '../models/Topic.js';
import { Practical } from '../models/Practical.js';
import { QuizQuestion } from '../models/QuizQuestion.js';
import { Flashcard } from '../models/Flashcard.js';
import { CareerRole } from '../models/CareerRole.js';
import { Resource } from '../models/Resource.js';
import {
  INITIAL_SUBJECTS,
  INITIAL_TOPICS,
  INITIAL_PRACTICALS,
  INITIAL_QUIZZES,
  INITIAL_FLASHCARDS,
  INITIAL_CAREERS,
  INITIAL_RESOURCES
} from '../data/initialData.js';

dotenv.config();

/**
 * Repeatable, safe Database Seed Script.
 * Migrates full Khalsa College B.Voc Textile Design & Apparel Technology curriculum (Semesters I-IV),
 * practicals, quizzes, flashcards, careers, and resources into MongoDB.
 * Usage: npm run seed:db
 */
const seedData = async () => {
  const mongoURI = process.env.MONGODB_URI || 'mongodb://localhost:27017/tdaclearners';
  try {
    console.log('[Seed DB] Connecting to MongoDB...');
    await mongoose.connect(mongoURI);
    console.log('[Seed DB] Connected successfully.');

    // Clear existing collections cleanly
    await Subject.deleteMany({});
    await Topic.deleteMany({});
    await Practical.deleteMany({});
    await QuizQuestion.deleteMany({});
    await Flashcard.deleteMany({});
    await CareerRole.deleteMany({});
    await Resource.deleteMany({});

    console.log('[Seed DB] Cleared old collections.');

    // 1. SEED SUBJECTS
    await Subject.insertMany(INITIAL_SUBJECTS);
    console.log(`[Seed DB] Seeded ${INITIAL_SUBJECTS.length} Subjects (Semesters 1-4).`);

    // 2. SEED TOPICS
    await Topic.insertMany(INITIAL_TOPICS);
    console.log(`[Seed DB] Seeded ${INITIAL_TOPICS.length} Topics.`);

    // 3. SEED PRACTICALS
    await Practical.insertMany(INITIAL_PRACTICALS);
    console.log(`[Seed DB] Seeded ${INITIAL_PRACTICALS.length} Practicals.`);

    // 4. SEED QUIZZES
    await QuizQuestion.insertMany(INITIAL_QUIZZES);
    console.log(`[Seed DB] Seeded ${INITIAL_QUIZZES.length} Quiz Questions.`);

    // 5. SEED FLASHCARDS
    await Flashcard.insertMany(INITIAL_FLASHCARDS);
    console.log(`[Seed DB] Seeded ${INITIAL_FLASHCARDS.length} Flashcards.`);

    // 6. SEED CAREERS & RESOURCES
    await CareerRole.insertMany(INITIAL_CAREERS);
    await Resource.insertMany(INITIAL_RESOURCES);
    console.log(`[Seed DB] Seeded Careers and Resources.`);

    console.log('\n=======================================================');
    console.log('  [SEED COMPLETE] Khalsa College Syllabus Populated!   ');
    console.log('=======================================================\n');

    process.exit(0);
  } catch (error) {
    console.error(`[Seed DB Error] ${error.message}`);
    process.exit(1);
  }
};

seedData();
