import { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';
import { getSemesters } from '../api/client';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function SyllabusBrowser() {
  const [semesters, setSemesters] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    getSemesters()
      .then((res) => {
        const list = res.data.data?.semesters || res.data.semesters || (Array.isArray(res.data.data) ? res.data.data : []);
        setSemesters(list);
      })
      .catch(() => setError('Could not load the syllabus.'))
      .finally(() => setLoading(false));
  }, []);

  return (
    <div>
      <p className="font-mono text-xs text-clay uppercase tracking-widest mb-2">02 — Syllabus</p>
      <h1 className="font-display text-3xl mb-6">Browse by semester</h1>

      {loading && <Loading />}
      {error && <ErrorBlock message={error} />}
      {!loading && !error && semesters.length === 0 && (
        <Empty title="Nothing published yet" hint="Semesters will appear here once added." />
      )}

      <div className="space-y-3">
        {semesters.map((sem, i) => (
          <details key={sem._id || sem.id || i} className="border border-ink/10 rounded-xl bg-white/40 group">
            <summary className="cursor-pointer list-none px-5 py-4 font-display text-xl flex items-center justify-between">
              {sem.name || sem.title || `Semester ${sem.number || i + 1}`}
              <span className="text-clay text-sm font-mono group-open:rotate-45 transition-transform">+</span>
            </summary>
            <div className="px-5 pb-4 grid sm:grid-cols-2 gap-2">
              {(sem.subjects || []).length === 0 && (
                <p className="text-sm text-slate">No subjects listed for this semester.</p>
              )}
              {(sem.subjects || []).map((sub, j) => (
                <NavLink
                  key={sub.code || sub._id || j}
                  to={`/subjects/${sub.code}`}
                  className="border border-ink/10 rounded-lg px-3 py-2 text-sm hover:bg-ink hover:text-paper transition-colors focus-ring"
                >
                  {sub.name || sub.title}
                </NavLink>
              ))}
            </div>
          </details>
        ))}
      </div>
    </div>
  );
}
