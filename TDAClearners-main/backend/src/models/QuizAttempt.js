import mongoose from 'mongoose';

const quizAttemptSchema = new mongoose.Schema(
  {
    userId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'User',
      required: true,
      index: true
    },
    subjectCode: { type: String, required: true },
    subjectName: { type: String, required: true },
    scopeLabel: { type: String, required: true },
    quizMode: { type: String, required: true },
    difficulty: { type: String, required: true },
    score: { type: Number, required: true },
    totalQuestions: { type: Number, required: true },
    percentage: { type: Number, required: true },
    timeTakenSeconds: { type: Number, default: 0 },
    timestamp: { type: Date, default: Date.now }
  },
  { timestamps: true }
);

export const QuizAttempt = mongoose.model('QuizAttempt', quizAttemptSchema);
