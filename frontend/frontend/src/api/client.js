import axios from 'axios';

const baseURL = import.meta.env.VITE_API_URL || 'http://localhost:5000';

const client = axios.create({ baseURL });

client.interceptors.request.use((config) => {
  const token = localStorage.getItem('tda_token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

client.interceptors.response.use(
  (res) => res,
  (err) => {
    if (err.response?.status === 401) {
      localStorage.removeItem('tda_token');
      localStorage.removeItem('tda_user');
      if (!window.location.pathname.startsWith('/login')) {
        window.location.href = '/login';
      }
    }
    return Promise.reject(err);
  }
);

export default client;

// ---- Auth ----
export const registerUser = (data) => client.post('/api/auth/register', data);
export const loginUser = (data) => client.post('/api/auth/login', data);
export const getMe = () => client.get('/api/auth/me');

// ---- Curriculum ----
export const getSemesters = () => client.get('/api/curriculum/semesters');
export const getSubjectByCode = (code) => client.get(`/api/curriculum/subjects/${code}`);
export const getTopicById = (topicId) => client.get(`/api/curriculum/topics/${topicId}`);
export const getPracticals = () => client.get('/api/curriculum/practicals');
export const getCareers = () => client.get('/api/curriculum/careers');
export const getResources = () => client.get('/api/curriculum/resources');

// ---- Learning ----
export const getScopedQuizzes = (params) => client.get('/api/learning/quizzes', { params });
export const getScopedFlashcards = (params) => client.get('/api/learning/flashcards', { params });

// ---- Student ----
export const getStudentProgress = () => client.get('/api/student/progress');
export const toggleTopicCompletion = (data) => client.post('/api/student/progress/topic-toggle', data);
export const toggleBookmark = (data) => client.post('/api/student/progress/bookmark-toggle', data);
export const updateFlashcardMastery = (data) => client.post('/api/student/progress/flashcard-mastery', data);
export const getQuizAttempts = () => client.get('/api/student/attempts');
export const recordQuizAttempt = (data) => client.post('/api/student/attempts', data);

// ---- AI ----
export const chatWithAiTutor = (data) => client.post('/api/ai/chat', data);
