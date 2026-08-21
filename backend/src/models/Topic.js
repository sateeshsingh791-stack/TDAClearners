import mongoose from 'mongoose';

const topicSchema = new mongoose.Schema(
  {
    topicId: {
      type: String,
      required: true,
      unique: true,
      index: true
    },
    subjectCode: {
      type: String,
      required: true,
      index: true
    },
    unitNumber: {
      type: Number,
      required: true,
      default: 1
    },
    unitTitle: {
      type: String,
      default: ''
    },
    title: {
      type: String,
      required: true
    },
    overview: {
      type: String,
      default: ''
    },
    keyPoints: [{ type: String }],
    importantTerms: {
      type: Map,
      of: String,
      default: {}
    },
    visualExplanation: { type: String, default: '' },
    industrialRelevance: { type: String, default: '' },
    quickRevisionSummary: { type: String, default: '' },
    practicalApplication: { type: String, default: null },
    isOfficialSyllabusTopic: { type: Boolean, default: true },
    sourceLabel: { type: String, default: 'Official University Syllabus' }
  },
  { timestamps: true }
);

export const Topic = mongoose.model('Topic', topicSchema);
