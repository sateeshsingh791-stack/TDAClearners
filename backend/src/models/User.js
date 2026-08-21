import mongoose from 'mongoose';

const userSchema = new mongoose.Schema(
  {
    name: {
      type: String,
      required: [true, 'Name is required'],
      trim: true
    },
    email: {
      type: String,
      required: [true, 'Email is required'],
      unique: true,
      lowercase: true,
      trim: true,
      index: true
    },
    passwordHash: {
      type: String,
      required: [true, 'Password is required']
    },
    role: {
      type: String,
      enum: ['student', 'admin'],
      default: 'student'
    },
    targetSemester: {
      type: Number,
      default: 1
    },
    targetYear: {
      type: Number,
      default: 1
    }
  },
  { timestamps: true }
);

export const User = mongoose.model('User', userSchema);
