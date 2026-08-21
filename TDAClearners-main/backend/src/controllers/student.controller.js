import { UserProgress } from '../models/UserProgress.js';
import { QuizAttempt } from '../models/QuizAttempt.js';

/**
 * Get student progress and bookmarks
 */
export const getStudentProgress = async (req, res, next) => {
  try {
    let progress = await UserProgress.findOne({ userId: req.user._id });
    if (!progress) {
      progress = await UserProgress.create({
        userId: req.user._id,
        completedTopicIds: ["bvtd111_u1_t1", "bvtd112_t1", "bvtd113_u1_t1"],
        bookmarkedTopicIds: ["bvtd111_u1_t1", "bvtd113_u1_t1", "bvtd121_u1_t1", "bvtd123_t1"],
        flashcardMastery: {
          "fc_bvtd112_1": "KNOW_IT",
          "fc_bvtd112_3": "KNOW_IT",
          "fc_bvtd112_2": "NEED_PRACTICE",
          "fc_bvtd111_2": "DIFFICULT"
        },
        selectedSemester: 1,
        selectedYear: 1
      });
    }

    res.status(200).json({
      success: true,
      data: { progress }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * Toggle topic completion
 */
export const toggleTopicCompletion = async (req, res, next) => {
  try {
    const { topicId } = req.body;
    if (!topicId) {
      return res.status(400).json({ success: false, error: { message: 'topicId is required' } });
    }

    let progress = await UserProgress.findOne({ userId: req.user._id });
    if (!progress) {
      progress = new UserProgress({ userId: req.user._id });
    }

    const index = progress.completedTopicIds.indexOf(topicId);
    if (index > -1) {
      progress.completedTopicIds.splice(index, 1);
    } else {
      progress.completedTopicIds.push(topicId);
    }

    await progress.save();

    res.status(200).json({
      success: true,
      data: { completedTopicIds: progress.completedTopicIds }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * Toggle bookmark
 */
export const toggleBookmark = async (req, res, next) => {
  try {
    const { topicId } = req.body;
    if (!topicId) {
      return res.status(400).json({ success: false, error: { message: 'topicId is required' } });
    }

    let progress = await UserProgress.findOne({ userId: req.user._id });
    if (!progress) {
      progress = new UserProgress({ userId: req.user._id });
    }

    const index = progress.bookmarkedTopicIds.indexOf(topicId);
    if (index > -1) {
      progress.bookmarkedTopicIds.splice(index, 1);
    } else {
      progress.bookmarkedTopicIds.push(topicId);
    }

    await progress.save();

    res.status(200).json({
      success: true,
      data: { bookmarkedTopicIds: progress.bookmarkedTopicIds }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * Update flashcard mastery
 */
export const updateFlashcardMastery = async (req, res, next) => {
  try {
    const { cardId, mastery } = req.body;
    if (!cardId || !mastery) {
      return res.status(400).json({ success: false, error: { message: 'cardId and mastery are required' } });
    }

    let progress = await UserProgress.findOne({ userId: req.user._id });
    if (!progress) {
      progress = new UserProgress({ userId: req.user._id });
    }

    progress.flashcardMastery.set(cardId, mastery);
    await progress.save();

    res.status(200).json({
      success: true,
      data: { flashcardMastery: Object.fromEntries(progress.flashcardMastery) }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * Get student quiz attempts history
 */
export const getQuizAttempts = async (req, res, next) => {
  try {
    const attempts = await QuizAttempt.find({ userId: req.user._id }).sort({ timestamp: -1 });
    res.status(200).json({
      success: true,
      data: { attempts }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * Record a completed quiz attempt
 */
export const recordQuizAttempt = async (req, res, next) => {
  try {
    const {
      subjectCode,
      subjectName,
      scopeLabel,
      quizMode,
      difficulty,
      score,
      totalQuestions,
      percentage,
      timeTakenSeconds
    } = req.body;

    const attempt = await QuizAttempt.create({
      userId: req.user._id,
      subjectCode,
      subjectName,
      scopeLabel,
      quizMode,
      difficulty,
      score,
      totalQuestions,
      percentage,
      timeTakenSeconds
    });

    res.status(201).json({
      success: true,
      data: { attempt }
    });
  } catch (error) {
    next(error);
  }
};
