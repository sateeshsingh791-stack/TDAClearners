import mongoose from 'mongoose';

const quizQuestionSchema = new mongoose.Schema(
  {
    questionId: {
      type: String,
      required: true,
      unique: true,
      index: true
    },
    question: {
      type: String,
      required: true
    },
    options: [{ type: String, required: true }],
    correctIndex: {
      type: Number,
      required: true
    },
    explanation: { type: String, default: '' },
    difficulty: {
      type: String,
      enum: ['EASY', 'MEDIUM', 'HARD', 'MIXED'],
      default: 'MEDIUM'
    },
    isPracticalViva: { type: Boolean, default: false },
    topicId: { type: String, default: '', index: true },
    subjectCode: { type: String, required: true, index: true },
    unitNumber: { type: Number, default: 1, index: true }
  },
  { timestamps: true }
);

export const QuizQuestion = mongoose.model('QuizQuestion', quizQuestionSchema);
