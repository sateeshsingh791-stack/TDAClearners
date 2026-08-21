import { useEffect, useState } from 'react';
import { useSearchParams, NavLink } from 'react-router-dom';
import { getSemesters, getResources } from '../api/client';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function GlobalSearch() {
  const [params] = useSearchParams();
  const query = params.get('q') || '';
  const [results, setResults] = useState({ subjects: [], topics: [], resources: [] });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    if (!query) {
      setLoading(false);
      return;
    }
    setLoading(true);
    const q = query.toLowerCase();
    Promise.all([getSemesters(), getResources()])
      .then(([sem, res]) => {
        const semesters = sem.data.data?.semesters || sem.data.semesters || (Array.isArray(sem.data.data) ? sem.data.data : []);
        const resources = res.data.data?.resources || res.data.resources || (Array.isArray(res.data.data) ? res.data.data : []);

        const subjects = [];
        const topics = [];
        semesters.forEach((s) => {
          (s.subjects || []).forEach((sub) => {
            if ((sub.name || '').toLowerCase().includes(q)) subjects.push(sub);
            (sub.topics || []).forEach((t) => {
              if ((t.title || t.name || '').toLowerCase().includes(q)) topics.push(t);
            });
          });
        });
        const matchedResources = resources.filter((r) => (r.title || r.name || '').toLowerCase().includes(q));

        setResults({ subjects, topics, resources: matchedResources });
      })
      .catch(() => setError('Search failed. Please try again.'))
      .finally(() => setLoading(false));
  }, [query]);

  const total = results.subjects.length + results.topics.length + results.resources.length;

  return (
    <div>
      <p className="font-mono text-xs text-clay uppercase tracking-widest mb-2">Search</p>
      <h1 className="font-display text-3xl mb-6">
        Results for <span className="text-clay">"{query}"</span>
      </h1>

      {loading && <Loading />}
      {error && <ErrorBlock message={error} />}
      {!loading && !error && total === 0 && (
        <Empty title="No matches found" hint="Try a different keyword." />
      )}

      {results.subjects.length > 0 && (
        <section className="mb-8">
          <h2 className="font-display text-lg mb-2">Subjects</h2>
          <div className="grid sm:grid-cols-2 gap-2">
            {results.subjects.map((s, i) => (
              <NavLink
                key={s.code || i}
                to={`/subjects/${s.code}`}
                className="border border-ink/10 rounded-lg px-4 py-2.5 bg-white/30 hover:bg-ink hover:text-paper transition-colors focus-ring"
              >
                {s.name}
              </NavLink>
            ))}
          </div>
        </section>
      )}

      {results.topics.length > 0 && (
        <section className="mb-8">
          <h2 className="font-display text-lg mb-2">Topics</h2>
          <div className="grid sm:grid-cols-2 gap-2">
            {results.topics.map((t, i) => (
              <NavLink
                key={t._id || t.id || i}
                to={`/topics/${t._id || t.id}`}
                className="border border-ink/10 rounded-lg px-4 py-2.5 bg-white/30 hover:bg-ink hover:text-paper transition-colors focus-ring"
              >
                {t.title || t.name}
              </NavLink>
            ))}
          </div>
        </section>
      )}

      {results.resources.length > 0 && (
        <section>
          <h2 className="font-display text-lg mb-2">Resources</h2>
          <div className="grid sm:grid-cols-2 gap-2">
            {results.resources.map((r, i) => (
              <a
                key={r._id || r.id || i}
                href={r.url || r.link || '#'}
                target="_blank"
                rel="noreferrer"
                className="border border-ink/10 rounded-lg px-4 py-2.5 bg-white/30 hover:bg-ink hover:text-paper transition-colors focus-ring"
              >
                {r.title || r.name}
              </a>
            ))}
          </div>
        </section>
      )}
    </div>
  );
}
