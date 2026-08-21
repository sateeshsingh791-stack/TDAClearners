import { Routes, Route } from 'react-router-dom';
import AppShell from './components/AppShell';
import Login from './pages/Login';
import Register from './pages/Register';
import Home from './pages/Home';
import SyllabusBrowser from './pages/SyllabusBrowser';
import SubjectDetail from './pages/SubjectDetail';
import TopicLearning from './pages/TopicLearning';
import QuizHub from './pages/QuizHub';
import Flashcards from './pages/Flashcards';
import PracticalLab from './pages/PracticalLab';
import AiTutor from './pages/AiTutor';
import CareerIndustry from './pages/CareerIndustry';
import Resources from './pages/Resources';
import BookmarksProgress from './pages/BookmarksProgress';
import GlobalSearch from './pages/GlobalSearch';

export default function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />

      <Route path="/" element={<AppShell><Home /></AppShell>} />
      <Route path="/syllabus" element={<AppShell><SyllabusBrowser /></AppShell>} />
      <Route path="/subjects/:code" element={<AppShell><SubjectDetail /></AppShell>} />
      <Route path="/topics/:topicId" element={<AppShell><TopicLearning /></AppShell>} />
      <Route path="/quiz-hub" element={<AppShell><QuizHub /></AppShell>} />
      <Route path="/flashcards" element={<AppShell><Flashcards /></AppShell>} />
      <Route path="/practical-lab" element={<AppShell><PracticalLab /></AppShell>} />
      <Route
        path="/ai-tutor"
        element={
          <AppShell>
            <AiTutor />
          </AppShell>
        }
      />
      <Route path="/career" element={<AppShell><CareerIndustry /></AppShell>} />
      <Route path="/resources" element={<AppShell><Resources /></AppShell>} />
      <Route path="/progress" element={<AppShell><BookmarksProgress /></AppShell>} />
      <Route path="/search" element={<AppShell><GlobalSearch /></AppShell>} />
    </Routes>
  );
}
