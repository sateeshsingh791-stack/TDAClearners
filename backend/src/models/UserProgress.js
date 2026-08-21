import mongoose from 'mongoose';

const userProgressSchema = new mongoose.Schema(
  {
    userId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'User',
      required: true,
      unique: true,
      index: true
    },
    completedTopicIds: [{ type: String }],
    bookmarkedTopicIds: [{ type: String }],
    flashcardMastery: {
      type: Map,
      of: String,
      default: {}
    },
    selectedSemester: { type: Number, default: 1 },
    selectedYear: { type: Number, default: 1 }
  },
  { timestamps: true }
);

export const UserProgress = mongoose.model('UserProgress', userProgressSchema);
