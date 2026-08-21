import mongoose from 'mongoose';
import { Subject } from '../models/Subject.js';
import { Topic } from '../models/Topic.js';
import { Practical } from '../models/Practical.js';
import { CareerRole } from '../models/CareerRole.js';
import { Resource } from '../models/Resource.js';
import {
  INITIAL_SUBJECTS,
  INITIAL_TOPICS,
  INITIAL_PRACTICALS,
  INITIAL_CAREERS,
  INITIAL_RESOURCES
} from '../data/initialData.js';

const isDbConnected = () => mongoose.connection.readyState === 1;

/**
 * Get all subjects categorized by semester/year.
 */
export const getSemesters = async (req, res, next) => {
  try {
    let subjects = [];
    if (isDbConnected()) {
      try {
        subjects = await Subject.find().sort({ semesterNumber: 1, code: 1 });
      } catch (e) {
        subjects = [];
      }
    }

    if (!subjects || subjects.length === 0) {
      subjects = INITIAL_SUBJECTS;
    }

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
    let subject = null;
    let topics = [];
    let practicals = [];

    if (isDbConnected()) {
      try {
        subject = await Subject.findOne({ code });
        if (subject) {
          topics = await Topic.find({ subjectCode: code }).sort({ unitNumber: 1, topicId: 1 });
          practicals = await Practical.find({ subjectCode: code });
          subject = subject.toObject();
        }
      } catch (e) {
        subject = null;
      }
    }

    if (!subject) {
      subject = INITIAL_SUBJECTS.find((s) => s.code.toUpperCase() === code);
      if (!subject) {
        return res.status(404).json({
          success: false,
          error: { message: `Subject ${code} not found.` }
        });
      }
      topics = INITIAL_TOPICS.filter((t) => t.subjectCode.toUpperCase() === code);
      practicals = INITIAL_PRACTICALS.filter((p) => p.subjectCode.toUpperCase() === code);
    }

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
      ...subject,
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
    let topic = null;
    if (isDbConnected()) {
      try {
        topic = await Topic.findOne({ topicId: req.params.topicId });
      } catch (e) {
        topic = null;
      }
    }

    if (!topic) {
      topic = INITIAL_TOPICS.find((t) => t.topicId === req.params.topicId);
    }

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
    let practicals = [];
    if (isDbConnected()) {
      try {
        const filter = subjectCode ? { subjectCode: subjectCode.toUpperCase() } : {};
        practicals = await Practical.find(filter);
      } catch (e) {
        practicals = [];
      }
    }

    if (!practicals || practicals.length === 0) {
      practicals = subjectCode
        ? INITIAL_PRACTICALS.filter((p) => p.subjectCode.toUpperCase() === subjectCode.toUpperCase())
        : INITIAL_PRACTICALS;
    }

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
    let careers = [];
    if (isDbConnected()) {
      try {
        careers = await CareerRole.find();
      } catch (e) {
        careers = [];
      }
    }

    if (!careers || careers.length === 0) {
      careers = INITIAL_CAREERS;
    }

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
    let resources = [];
    if (isDbConnected()) {
      try {
        resources = await Resource.find();
      } catch (e) {
        resources = [];
      }
    }

    if (!resources || resources.length === 0) {
      resources = INITIAL_RESOURCES;
    }

    res.status(200).json({
      success: true,
      data: { resources }
    });
  } catch (error) {
    next(error);
  }
};
