import { useEffect, useState } from 'react';
import { getResources } from '../api/client';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function Resources() {
  const [resources, setResources] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    getResources()
      .then((res) => setResources(res.data.data || res.data.resources || res.data || []))
      .catch(() => setError('Could not load resources.'))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <Loading />;
  if (error) return <ErrorBlock message={error} />;
  if (resources.length === 0)
    return <Empty title="No resources yet" hint="Reference material will appear here once added." />;

  return (
    <div>
      <p className="font-mono text-xs text-clay uppercase tracking-widest mb-2">08 — Resources</p>
      <h1 className="font-display text-3xl mb-6">Reference material</h1>

      <div className="space-y-2">
        {resources.map((r, i) => (
          <a
            key={r._id || r.id || i}
            href={r.url || r.link || '#'}
            target="_blank"
            rel="noreferrer"
            className="flex items-center justify-between border border-ink/10 rounded-lg px-4 py-3 bg-white/30 hover:bg-ink hover:text-paper transition-colors focus-ring"
          >
            <div>
              <p className="font-medium">{r.title || r.name}</p>
              {r.type && <p className="text-xs text-slate">{r.type}</p>}
            </div>
            <span className="font-mono text-xs">↗</span>
          </a>
        ))}
      </div>
    </div>
  );
}
