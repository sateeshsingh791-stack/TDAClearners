import { useEffect, useState } from 'react';
import { useParams, NavLink } from 'react-router-dom';
import { getSubjectByCode } from '../api/client';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function SubjectDetail() {
  const { code } = useParams();
  const [subject, setSubject] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    setLoading(true);
    getSubjectByCode(code)
      .then((res) => setSubject(res.data.data || res.data.subject || res.data))
      .catch(() => setError('Could not load this subject.'))
      .finally(() => setLoading(false));
  }, [code]);

  if (loading) return <Loading />;
  if (error) return <ErrorBlock message={error} />;
  if (!subject) return <Empty title="Subject not found" />;

  const topics = subject.topics || [];

  return (
    <div>
      <NavLink to="/syllabus" className="text-sm text-slate hover:text-ink">
        ← Back to syllabus
      </NavLink>
      <p className="font-mono text-xs text-clay uppercase tracking-widest mt-4 mb-1">{code}</p>
      <h1 className="font-display text-3xl mb-2">{subject.name || subject.title}</h1>
      {subject.description && <p className="text-slate max-w-2xl mb-8">{subject.description}</p>}

      <h2 className="font-display text-xl mb-3">Topics</h2>
      {topics.length === 0 ? (
        <Empty title="No topics listed" hint="Topics for this subject haven't been added yet." />
      ) : (
        <div className="space-y-2">
          {topics.map((t, i) => (
            <NavLink
              key={t._id || t.id || i}
              to={`/topics/${t._id || t.id}`}
              className="flex items-center justify-between border border-ink/10 rounded-lg px-4 py-3 bg-white/30 hover:bg-ink hover:text-paper transition-colors focus-ring"
            >
              <span className="font-medium">{t.title || t.name}</span>
              <span className="font-mono text-xs opacity-60">{String(i + 1).padStart(2, '0')}</span>
            </NavLink>
          ))}
        </div>
      )}
    </div>
  );
}
