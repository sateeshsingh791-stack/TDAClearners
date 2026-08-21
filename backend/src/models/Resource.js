import mongoose from 'mongoose';

const resourceSchema = new mongoose.Schema(
  {
    resourceId: { type: String, required: true, unique: true },
    title: { type: String, required: true },
    subjectCode: { type: String, required: true, index: true },
    category: { type: String, required: true },
    description: { type: String, required: true },
    downloadSize: { type: String, required: true },
    format: { type: String, required: true }
  },
  { timestamps: true }
);

export const Resource = mongoose.model('Resource', resourceSchema);
