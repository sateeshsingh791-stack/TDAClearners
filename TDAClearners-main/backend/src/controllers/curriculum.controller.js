import { Subject } from '../models/Subject.js';
import { Topic } from '../models/Topic.js';
import { Practical } from '../models/Practical.js';
import { CareerRole } from '../models/CareerRole.js';
import { Resource } from '../models/Resource.js';

/**
 * Get all subjects categorized by semester/year.
 */
export const getSemesters = async (req, res, next) => {
  try {
    const subjects = await Subject.find().sort({ semesterNumber: 1, code: 1 });
    
    // Group subjects into semesters (1 to 6)
    const semesterNumbers = [1, 2, 3, 4, 5, 6];
    const semesters = semesterNumbers.map((num) => {
      const semSubjects = subjects.filter((s) => s.semesterNumber === num);
      const yearNum = Math.ceil(num / 2);
      const totalCredits = semSubjects.reduce((acc, s) => acc + (s.totalCredits || 0), 0);
      const totalMarks = semSubjects.reduce((acc, s) => acc + (s.totalMarks || 0), 0);
      const totalHours = semSubjects.reduce((acc, s) => acc + (s.hoursPerWeek || 0), 0);

      return {
        number: num,
        title: `Semester ${num} (${num % 2 === 1 ? 'Autumn / Odd' : 'Spring / Even'} Term)`,
        yearNumber: yearNum,
        totalCredits,
        totalMarks,
        totalHoursPerWeek: totalHours,
        subjects: semSubjects
      };
    });

    res.status(200).json({
      success: true,
      data: { semesters }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * Get single subject by code with attached topics & practicals
 */
export const getSubjectByCode = async (req, res, next) => {
  try {
    const code = req.params.code.toUpperCase();
    const subject = await Subject.findOne({ code });

    if (!subject) {
      return res.status(404).json({
        success: false,
        error: { message: `Subject ${code} not found.` }
      });
    }

    const topics = await Topic.find({ subjectCode: code }).sort({ unitNumber: 1, topicId: 1 });
    const practicals = await Practical.find({ subjectCode: code });

    // Group topics by unit number
    const unitMap = new Map();
    topics.forEach((t) => {
      if (!unitMap.has(t.unitNumber)) {
        unitMap.set(t.unitNumber, {
          unitNumber: t.unitNumber,
          title: t.unitTitle || `Unit ${t.unitNumber}`,
          description: '',
          isOfficialUnit: true,
          topics: []
        });
      }
      unitMap.get(t.unitNumber).topics.push(t);
    });

    const units = Array.from(unitMap.values()).sort((a, b) => a.unitNumber - b.unitNumber);

    const fullSubject = {
      ...subject.toObject(),
      units,
      practicals
    };

    res.status(200).json({
      success: true,
      data: { subject: fullSubject }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * Get topic content by topicId
 */
export const getTopicById = async (req, res, next) => {
  try {
    const topic = await Topic.findOne({ topicId: req.params.topicId });

    if (!topic) {
      return res.status(404).json({
        success: false,
        error: { message: `Topic ${req.params.topicId} not found.` }
      });
    }

    res.status(200).json({
      success: true,
      data: { topic }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * Get all practical activities (optional subjectCode query filter)
 */
export const getPracticals = async (req, res, next) => {
  try {
    const { subjectCode } = req.query;
    const filter = subjectCode ? { subjectCode: subjectCode.toUpperCase() } : {};
    const practicals = await Practical.find(filter);

    res.status(200).json({
      success: true,
      data: { practicals }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * Get industry career roles
 */
export const getCareers = async (req, res, next) => {
  try {
    const careers = await CareerRole.find();
    res.status(200).json({
      success: true,
      data: { careers }
    });
  } catch (error) {
    next(error);
  }
};

/**
 * Get study resources and textbooks
 */
export const getResources = async (req, res, next) => {
  try {
    const resources = await Resource.find();
    res.status(200).json({
      success: true,
      data: { resources }
    });
  } catch (error) {
    next(error);
  }
};
