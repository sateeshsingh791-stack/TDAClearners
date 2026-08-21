import { Router } from 'express';
import {
  getSemesters,
  getSubjectByCode,
  getTopicById,
  getPracticals,
  getCareers,
  getResources
} from '../controllers/curriculum.controller.js';

const router = Router();

router.get('/semesters', getSemesters);
router.get('/subjects/:code', getSubjectByCode);
router.get('/topics/:topicId', getTopicById);
router.get('/practicals', getPracticals);
router.get('/careers', getCareers);
router.get('/resources', getResources);

export default router;
