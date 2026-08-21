import { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';
import { getSemesters } from '../api/client';
import { useAuth } from '../context/AuthContext';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

const TILES = [
  { to: '/syllabus', title: 'Syllabus Browser', desc: 'Walk through official semesters, subjects, and units.' },
  { to: '/quiz-hub', title: 'Quiz Hub', desc: 'Test what you know, subject by subject.' },
  { to: '/flashcards', title: 'Flashcards', desc: 'Fast recall drills for the topics you flag.' },
  { to: '/practical-lab', title: 'Practical Lab', desc: 'Hands-on lab exercises tied to the syllabus.' },
  { to: '/ai-tutor', title: 'AI Tutor', desc: 'Ask a question, get an explanation in context.' },
  { to: '/career', title: 'Career & Industry', desc: 'Where each subject leads in apparel & fashion industry.' },
  { to: '/resources', title: 'Resources', desc: 'Reference books and lab manuals.' },
  { to: '/progress', title: 'Bookmarks & Progress', desc: 'Everything you have saved or completed.' }
];

export default function Home() {
  const { user } = useAuth();
  const [semesters, setSemesters] = useState([]);
  const [academicScheme, setAcademicScheme] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    getSemesters()
      .then((res) => {
        const dataObj = res.data.data || res.data;
        const list = dataObj?.semesters || (Array.isArray(dataObj) ? dataObj : []);
        setSemesters(list);
        if (dataObj?.academicScheme) {
          setAcademicScheme(dataObj.academicScheme);
        }
      })
      .catch(() => setError('Could not load the semester list.'))
      .finally(() => setLoading(false));
  }, []);

  return (
    <div className="space-y-12">
      <section>
        <p className="font-mono text-xs text-clay uppercase tracking-widest mb-3">
          {academicScheme?.degree || 'B.Voc. Textile Design & Apparel Technology'}
        </p>
        <h1 className="font-display text-4xl md:text-5xl leading-tight max-w-2xl">
          {user ? `Back to it, ${user.name?.split(' ')[0]}.` : 'Your official syllabus, made walkable.'}
        </h1>
        <p className="text-slate mt-4 max-w-xl">
          Explore Semester 1 & Semester 2 course schemes, drill practical sewing & textile units, and test your knowledge with interactive quizzes.
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

      <section className="space-y-6">
        <div className="flex items-center justify-between">
          <div>
            <h2 className="font-display text-2xl">Academic Structure</h2>
            <p className="text-xs font-mono text-slate">B.Voc. Textile Design & Apparel Technology (3-Year Scheme)</p>
          </div>
          <NavLink to="/syllabus" className="text-xs font-mono text-moss hover:underline">
            View Full Scheme →
          </NavLink>
        </div>

        {loading && <Loading />}
        {error && <ErrorBlock message={error} />}

        {!loading && semesters.length > 0 && (
          <div className="grid sm:grid-cols-2 md:grid-cols-3 gap-4">
            {semesters.map((s, i) => {
              const isAvailable = s.status === 'AVAILABLE' || s.number === 1 || s.number === 2;

              return (
                <div
                  key={s._id || s.id || i}
                  className={`border rounded-xl p-4 transition-all ${
                    isAvailable
                      ? 'border-ink/15 bg-white shadow-sm hover:border-moss'
                      : 'border-ink/10 bg-paper/40 opacity-80'
                  }`}
                >
                  <div className="flex items-center justify-between mb-2">
                    <span className="font-mono text-xs text-clay font-medium">
                      {Math.ceil((s.number || i + 1) / 2)}st/2nd/3rd Year
                    </span>
                    <span
                      className={`text-[10px] font-mono px-2 py-0.5 rounded ${
                        isAvailable ? 'bg-moss/10 text-moss font-semibold' : 'bg-clay/10 text-clay'
                      }`}
                    >
                      {isAvailable ? 'AVAILABLE' : 'COMING SOON'}
                    </span>
                  </div>

                  <h3 className="font-display text-lg mb-1">{s.title || `Semester ${s.number || i + 1}`}</h3>

                  {isAvailable ? (
                    <div className="mt-3 pt-2 border-t border-ink/5 flex items-center justify-between text-xs font-mono">
                      <span className="text-slate">{(s.subjects || []).length} Official Subjects</span>
                      <NavLink to="/syllabus" className="text-moss font-medium hover:underline">
                        Explore →
                      </NavLink>
                    </div>
                  ) : (
                    <p className="text-xs font-mono text-slate mt-3 pt-2 border-t border-ink/5">
                      Detailed syllabus will be added when available.
                    </p>
                  )}
                </div>
              );
            })}
          </div>
        )}
      </section>
    </div>
  );
}
