import { useEffect, useState } from 'react';
import { getCareers } from '../api/client';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function CareerIndustry() {
  const [careers, setCareers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    getCareers()
      .then((res) => setCareers(res.data.data?.careers || res.data.careers || (Array.isArray(res.data.data) ? res.data.data : [])))
      .catch(() => setError('Could not load career paths.'))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <Loading />;
  if (error) return <ErrorBlock message={error} />;
  if (careers.length === 0)
    return <Empty title="Nothing here yet" hint="Career paths will appear once added." />;

  return (
    <div>
      <p className="font-mono text-xs text-clay uppercase tracking-widest mb-2">07 — Career & Industry</p>
      <h1 className="font-display text-3xl mb-6">Where this leads</h1>

      <div className="grid sm:grid-cols-2 gap-4">
        {careers.map((c, i) => (
          <div key={c._id || c.id || i} className="border border-ink/10 rounded-xl p-5 bg-white/40">
            <h3 className="font-display text-lg mb-1">{c.title || c.role || c.name}</h3>
            <p className="text-sm text-slate leading-relaxed">{c.description}</p>
            {c.relatedSubject && (
              <p className="text-xs font-mono text-clay mt-3 uppercase">{c.relatedSubject}</p>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}
