import mongoose from 'mongoose';

const careerRoleSchema = new mongoose.Schema(
  {
    title: { type: String, required: true },
    sector: { type: String, required: true },
    description: { type: String, required: true },
    keySkills: [{ type: String }],
    standardTools: [{ type: String }],
    industryScope: { type: String, required: true }
  },
  { timestamps: true }
);

export const CareerRole = mongoose.model('CareerRole', careerRoleSchema);
