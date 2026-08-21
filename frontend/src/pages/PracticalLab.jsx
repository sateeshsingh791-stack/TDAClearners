import { useEffect, useState } from 'react';
import { getPracticals } from '../api/client';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function PracticalLab() {
  const [practicals, setPracticals] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [openIndex, setOpenIndex] = useState(null);

  useEffect(() => {
    getPracticals()
      .then((res) => setPracticals(res.data.data?.practicals || res.data.practicals || (Array.isArray(res.data.data) ? res.data.data : [])))
      .catch(() => setError('Could not load the practical lab list.'))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <Loading />;
  if (error) return <ErrorBlock message={error} />;
  if (practicals.length === 0)
    return <Empty title="No practicals yet" hint="Lab exercises will appear here once added." />;

  return (
    <div>
      <p className="font-mono text-xs text-clay uppercase tracking-widest mb-2">05 — Practical Lab</p>
      <h1 className="font-display text-3xl mb-6">Hands-on exercises</h1>

      <div className="space-y-3">
        {practicals.map((p, i) => (
          <div key={p._id || p.id || i} className="border border-ink/10 rounded-xl bg-white/40">
            <button
              onClick={() => setOpenIndex(openIndex === i ? null : i)}
              className="w-full text-left px-5 py-4 flex items-center justify-between focus-ring"
            >
              <span className="font-display text-lg">{p.title || p.name}</span>
              <span className="font-mono text-xs text-clay">{openIndex === i ? '−' : '+'}</span>
            </button>
            {openIndex === i && (
              <div className="px-5 pb-5 text-sm text-ink/80 whitespace-pre-wrap leading-relaxed">
                {p.description || p.instructions || 'No details added yet.'}
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}
