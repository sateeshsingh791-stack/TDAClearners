import mongoose from 'mongoose';

const practicalSchema = new mongoose.Schema(
  {
    practicalId: {
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
    title: {
      type: String,
      required: true
    },
    objective: {
      type: String,
      required: true
    },
    materialsRequired: [{ type: String }],
    theory: { type: String, default: '' },
    stepByStepProcedure: [{ type: String }],
    expectedObservations: { type: String, default: '' },
    precautions: [{ type: String }],
    vivaQuestions: [
      {
        question: { type: String, required: true },
        answer: { type: String, required: true }
      }
    ],
    isOfficialSyllabusPractical: { type: Boolean, default: true },
    sourceLabel: { type: String, default: 'Official University Syllabus' }
  },
  { timestamps: true }
);

export const Practical = mongoose.model('Practical', practicalSchema);
