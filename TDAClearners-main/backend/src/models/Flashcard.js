import mongoose from 'mongoose';

const flashcardSchema = new mongoose.Schema(
  {
    cardId: {
      type: String,
      required: true,
      unique: true,
      index: true
    },
    subjectCode: { type: String, required: true, index: true },
    unitNumber: { type: Number, default: 1, index: true },
    topicId: { type: String, default: '', index: true },
    type: {
      type: String,
      enum: ['DEFINITION', 'IDENTIFICATION', 'CONCEPT', 'PROCESS', 'PRACTICAL', 'VIVA'],
      default: 'CONCEPT'
    },
    front: { type: String, required: true },
    back: { type: String, required: true },
    categoryHint: { type: String, default: 'Textile & Apparel Curriculum' },
    practicalTag: { type: String, default: null },
    isOfficial: { type: Boolean, default: true }
  },
  { timestamps: true }
);

export const Flashcard = mongoose.model('Flashcard', flashcardSchema);
