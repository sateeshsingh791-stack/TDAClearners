import bcrypt from 'bcryptjs';
import { User } from '../models/User.js';
import { UserProgress } from '../models/UserProgress.js';
import { generateToken } from '../utils/jwt.js';

/**
 * Public User Registration
 * CRITICAL SECURITY REQUIREMENT:
 * Public registration creates ONLY 'student' accounts.
 * Admin roles sent in request bodies are ignored.
 */
export const register = async (req, res, next) => {
  try {
    const { name, email, password, targetSemester, targetYear } = req.body;

    if (!name || !email || !password) {
      return res.status(400).json({
        success: false,
        error: { message: 'Please provide name, email, and password.' }
      });
    }

    const existingUser = await User.findOne({ email: email.toLowerCase() });
    if (existingUser) {
      return res.status(409).json({
        success: false,
        error: { message: 'An account with this email already exists.' }
      });
    }

    const salt = await bcrypt.genSalt(12);
    const passwordHash = await bcrypt.hash(password, salt);

    // Force role to 'student' for ALL public registrations
    const newUser = await User.create({
      name,
      email: email.toLowerCase(),
      passwordHash,
      role: 'student',
      targetSemester: targetSemester || 1,
      targetYear: targetYear || 1
    });

    // Create initial UserProgress record
    await UserProgress.create({
      userId: newUser._id,
      completedTopicIds: ["bvtd111_u1_t1", "bvtd112_t1", "bvtd113_u1_t1"],
      bookmarkedTopicIds: ["bvtd111_u1_t1", "bvtd113_u1_t1", "bvtd121_u1_t1", "bvtd123_t1"],
      flashcardMastery: {
        "fc_bvtd112_1": "KNOW_IT",
        "fc_bvtd112_3": "KNOW_IT",
        "fc_bvtd112_2": "NEED_PRACTICE",
        "fc_bvtd111_2": "DIFFICULT"
      },
      selectedSemester: targetSemester || 1,
      selectedYear: targetYear || 1
    });

    const token = generateToken(newUser._id, newUser.role);

    res.status(201).json({
      success: true,
      message: 'Registration successful',
      data: {
        token,
        user: {
          id: newUser._id,
          name: newUser.name,
          email: newUser.email,
          role: newUser.role,
          targetSemester: newUser.targetSemester,
          targetYear: newUser.targetYear
        }
      }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * User Login Endpoint
 */
export const login = async (req, res, next) => {
  try {
    const { email, password } = req.body;

    if (!email || !password) {
      return res.status(400).json({
        success: false,
        error: { message: 'Please provide email and password.' }
      });
    }

    const user = await User.findOne({ email: email.toLowerCase() });
    if (!user) {
      return res.status(401).json({
        success: false,
        error: { message: 'Invalid email or password.' }
      });
    }

    const isMatch = await bcrypt.compare(password, user.passwordHash);
    if (!isMatch) {
      return res.status(401).json({
        success: false,
        error: { message: 'Invalid email or password.' }
      });
    }

    const token = generateToken(user._id, user.role);

    res.status(200).json({
      success: true,
      message: 'Login successful',
      data: {
        token,
        user: {
          id: user._id,
          name: user.name,
          email: user.email,
          role: user.role,
          targetSemester: user.targetSemester,
          targetYear: user.targetYear
        }
      }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * Get Authenticated Profile
 */
export const getMe = async (req, res, next) => {
  try {
    res.status(200).json({
      success: true,
      data: {
        user: {
          id: req.user._id,
          name: req.user.name,
          email: req.user.email,
          role: req.user.role,
          targetSemester: req.user.targetSemester,
          targetYear: req.user.targetYear
        }
      }
    });
  } catch (error) {
    next(error);
  }
};
