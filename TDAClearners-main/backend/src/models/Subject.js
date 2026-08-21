import mongoose from 'mongoose';

const subjectSchema = new mongoose.Schema(
  {
    code: {
      type: String,
      required: true,
      unique: true,
      trim: true,
      index: true
    },
    name: {
      type: String,
      required: true,
      trim: true
    },
    semesterNumber: {
      type: Number,
      required: true,
      index: true
    },
    yearNumber: {
      type: Number,
      required: true
    },
    category: {
      type: String,
      enum: ['MAJOR', 'MINOR', 'ABILITY_ENHANCEMENT', 'VALUE_ADDED'],
      required: true
    },
    type: {
      type: String,
      enum: ['THEORY', 'PRACTICAL', 'THEORY_AND_PRACTICAL'],
      required: true
    },
    hoursPerWeek: { type: Number, default: 0 },
    lectureCredits: { type: Number, default: 0 },
    tutorialCredits: { type: Number, default: 0 },
    practicalCredits: { type: Number, default: 0 },
    totalCredits: { type: Number, default: 0 },
    theoryMarks: { type: Number, default: null },
    practicalMarks: { type: Number, default: null },
    internalAssessmentMarks: { type: Number, default: 0 },
    totalMarks: { type: Number, default: 0 },
    syllabusPageRef: { type: String, default: '' },
    overview: { type: String, default: '' },
    timeDurationHours: { type: Number, default: 3 },
    mediumOfExam: { type: String, default: null },
    instructionsForPaperSetters: { type: String, default: null },
    courseObjectives: [{ type: String }],
    learningObjectives: [{ type: String }],
    courseOutcomes: [{ type: String }],
    booksPrescribed: [{ type: String }],
    officialSyllabusContents: [{ type: String }]
  },
  { timestamps: true }
);

export const Subject = mongoose.model('Subject', subjectSchema);
