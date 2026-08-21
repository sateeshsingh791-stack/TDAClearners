import { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';
import { getSemesters } from '../api/client';
import { useAuth } from '../context/AuthContext';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

const TILES = [
  { to: '/syllabus', title: 'Syllabus Browser', desc: 'Walk through every semester, subject and topic.' },
  { to: '/quiz-hub', title: 'Quiz Hub', desc: 'Test what you know, subject by subject.' },
  { to: '/flashcards', title: 'Flashcards', desc: 'Fast recall drills for the topics you flag.' },
  { to: '/practical-lab', title: 'Practical Lab', desc: 'Hands-on lab exercises tied to the syllabus.' },
  { to: '/ai-tutor', title: 'AI Tutor', desc: 'Ask a question, get an explanation in context.' },
  { to: '/career', title: 'Career & Industry', desc: 'Where each subject leads, in practice.' },
  { to: '/resources', title: 'Resources', desc: 'Reference material curated per topic.' },
  { to: '/progress', title: 'Bookmarks & Progress', desc: 'Everything you have saved or completed.' }
];

export default function Home() {
  const { user } = useAuth();
  const [semesters, setSemesters] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    getSemesters()
      .then((res) => setSemesters(res.data.data || res.data.semesters || res.data || []))
      .catch(() => setError('Could not load the semester list.'))
      .finally(() => setLoading(false));
  }, []);

  return (
    <div className="space-y-12">
      <section>
        <p className="font-mono text-xs text-clay uppercase tracking-widest mb-3">B.Tech CSE Companion</p>
        <h1 className="font-display text-4xl md:text-5xl leading-tight max-w-2xl">
          {user ? `Back to it, ${user.name?.split(' ')[0]}.` : 'Your syllabus, made walkable.'}
        </h1>
        <p className="text-slate mt-4 max-w-xl">
          Browse every semester, drill topics with quizzes and flashcards, and ask the AI tutor when something
          doesn't click.
        </p>
      </section>

      <section className="grid sm:grid-cols-2 lg:grid-cols-4 gap-4">
        {TILES.map((t) => (
          <NavLink
            key={t.to}
            to={t.to}
            className="group border border-ink/10 rounded-xl p-5 bg-white/40 hover:bg-ink hover:text-paper transition-colors focus-ring"
          >
            <h3 className="font-display text-lg mb-1">{t.title}</h3>
            <p className="text-sm text-slate group-hover:text-paper/70">{t.desc}</p>
          </NavLink>
        ))}
      </section>

      <section>
        <h2 className="font-display text-2xl mb-4">Semesters</h2>
        {loading && <Loading />}
        {error && <ErrorBlock message={error} />}
        {!loading && !error && semesters.length === 0 && (
          <Empty title="No semesters published yet" hint="Check back once the curriculum is added." />
        )}
        {!loading && semesters.length > 0 && (
          <div className="grid sm:grid-cols-2 md:grid-cols-3 gap-3">
            {semesters.map((s, i) => (
              <div key={s._id || s.id || i} className="border border-ink/10 rounded-lg px-4 py-3 bg-white/30">
                <p className="font-medium">{s.name || s.title || `Semester ${s.number || i + 1}`}</p>
                {s.subjectsCount != null && (
                  <p className="text-xs text-slate mt-0.5">{s.subjectsCount} subjects</p>
                )}
              </div>
            ))}
          </div>
        )}
      </section>
    </div>
  );
}
