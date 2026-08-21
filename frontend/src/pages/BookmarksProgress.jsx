import { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';
import { getStudentProgress, getQuizAttempts } from '../api/client';
import { useAuth } from '../context/AuthContext';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function BookmarksProgress() {
  const { user } = useAuth();
  const [progress, setProgress] = useState(null);
  const [attempts, setAttempts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    if (!user) {
      setLoading(false);
      return;
    }
    Promise.all([getStudentProgress(), getQuizAttempts()])
      .then(([p, a]) => {
        setProgress(p.data.data?.progress || p.data.progress || p.data.data || p.data);
        setAttempts(a.data.data?.attempts || a.data.attempts || (Array.isArray(a.data.data) ? a.data.data : []));
      })
      .catch(() => setError('Could not load your progress.'))
      .finally(() => setLoading(false));
  }, [user]);

  if (!user) {
    return (
      <div>
        <p className="font-mono text-xs text-clay uppercase tracking-widest mb-2">09 — Progress</p>
        <h1 className="font-display text-3xl mb-4">Log in to see your progress</h1>
        <NavLink to="/login" className="text-moss font-medium">
          Log in →
        </NavLink>
      </div>
    );
  }

  if (loading) return <Loading />;
  if (error) return <ErrorBlock message={error} />;

  const completedTopics = progress?.completedTopics || progress?.topics || [];
  const bookmarks = progress?.bookmarks || [];

  return (
    <div className="space-y-10">
      <div>
        <p className="font-mono text-xs text-clay uppercase tracking-widest mb-2">09 — Progress</p>
        <h1 className="font-display text-3xl mb-6">Your journey so far</h1>
      </div>

      <section>
        <h2 className="font-display text-xl mb-3">Bookmarked topics</h2>
        {bookmarks.length === 0 ? (
          <Empty title="No bookmarks yet" hint="Star a topic while learning to save it here." />
        ) : (
          <div className="grid sm:grid-cols-2 gap-3">
            {bookmarks.map((b, i) => (
              <NavLink
                key={b._id || b.id || i}
                to={`/topics/${b.topicId || b._id || b.id}`}
                className="border border-ink/10 rounded-lg px-4 py-3 bg-white/30 hover:bg-ink hover:text-paper transition-colors focus-ring"
              >
                {b.title || b.name || 'Untitled topic'}
              </NavLink>
            ))}
          </div>
        )}
      </section>

      <section>
        <h2 className="font-display text-xl mb-3">Completed topics</h2>
        {completedTopics.length === 0 ? (
          <Empty title="No topics completed yet" hint="Mark topics complete as you go through them." />
        ) : (
          <ul className="space-y-2">
            {completedTopics.map((t, i) => (
              <li key={t._id || t.id || i} className="border border-ink/10 rounded-lg px-4 py-2.5 bg-white/30 text-sm">
                {t.title || t.name}
              </li>
            ))}
          </ul>
        )}
      </section>

      <section>
        <h2 className="font-display text-xl mb-3">Quiz attempts</h2>
        {attempts.length === 0 ? (
          <Empty title="No quiz attempts yet" hint="Take a quiz from the Quiz Hub to see history here." />
        ) : (
          <div className="space-y-2">
            {attempts.map((a, i) => (
              <div
                key={a._id || a.id || i}
                className="flex items-center justify-between border border-ink/10 rounded-lg px-4 py-3 bg-white/30 text-sm"
              >
                <span>{a.quizTitle || a.quizId || `Attempt ${i + 1}`}</span>
                <span className="font-mono text-clay">
                  {a.score}/{a.total}
                </span>
              </div>
            ))}
          </div>
        )}
      </section>
    </div>
  );
}
