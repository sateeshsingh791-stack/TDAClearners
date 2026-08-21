import { useEffect, useState } from 'react';
import { useSearchParams, NavLink } from 'react-router-dom';
import { getSemesters, getResources } from '../api/client';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function GlobalSearch() {
  const [searchParams] = useSearchParams();
  const query = searchParams.get('q') || '';

  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    if (!query.trim()) {
      setResults([]);
      setLoading(false);
      return;
    }

    setLoading(true);
    setError('');

    Promise.all([getSemesters(), getResources()])
      .then(([semRes, resRes]) => {
        const semList = semRes.data.data?.semesters || semRes.data.semesters || (Array.isArray(semRes.data.data) ? semRes.data.data : []);
        const resourceList = resRes.data.data?.resources || resRes.data.resources || (Array.isArray(resRes.data.data) ? resRes.data.data : []);

        const q = query.toLowerCase().trim();
        const matches = [];

        semList.forEach((sem) => {
          (sem.subjects || []).forEach((sub) => {
            // Subject match
            if (
              sub.code.toLowerCase().includes(q) ||
              sub.name.toLowerCase().includes(q) ||
              (sub.overview && sub.overview.toLowerCase().includes(q))
            ) {
              matches.push({
                type: 'SUBJECT',
                title: `${sub.code} — ${sub.name}`,
                context: `Semester ${sem.number} • ${sub.category} • ${sub.type}`,
                description: sub.overview || 'Subject in B.Voc Textile Design curriculum.',
                link: `/subjects/${sub.code}`
              });
            }

            // Units & Topics match
            (sub.units || []).forEach((unit) => {
              (unit.topics || []).forEach((topic) => {
                if (
                  topic.title.toLowerCase().includes(q) ||
                  (topic.overview && topic.overview.toLowerCase().includes(q)) ||
                  (topic.keyPoints && topic.keyPoints.some((kp) => kp.toLowerCase().includes(q)))
                ) {
                  matches.push({
                    type: 'TOPIC',
                    title: topic.title,
                    context: `${sub.code} (${sub.name}) › Unit ${unit.unitNumber} › Topic`,
                    description: topic.overview || 'Academic study topic & quick revision notes.',
                    link: `/topics/${topic.topicId}`
                  });
                }

                // Terminology match
                if (topic.importantTerms) {
                  Object.entries(topic.importantTerms).forEach(([term, def]) => {
                    if (term.toLowerCase().includes(q) || def.toLowerCase().includes(q)) {
                      matches.push({
                        type: 'DEFINITION',
                        title: `Term: ${term}`,
                        context: `${sub.code} › Unit ${unit.unitNumber} › ${topic.title}`,
                        description: def,
                        link: `/topics/${topic.topicId}`
                      });
                    }
                  });
                }
              });
            });
          });
        });

        // Resources match
        resourceList.forEach((res) => {
          if (
            res.title.toLowerCase().includes(q) ||
            (res.description && res.description.toLowerCase().includes(q)) ||
            (res.subjectCode && res.subjectCode.toLowerCase().includes(q))
          ) {
            matches.push({
              type: 'RESOURCE',
              title: res.title,
              context: `Resource Manual › ${res.subjectCode || 'General Syllabus'}`,
              description: res.description || 'Academic reference document.',
              link: `/resources`
            });
          }
        });

        setResults(matches);
      })
      .catch(() => setError('Failed to execute search across academic database.'))
      .finally(() => setLoading(false));
  }, [query]);

  return (
    <div className="space-y-6">
      <div>
        <p className="font-mono text-xs text-clay uppercase tracking-widest mb-1">Global Academic Search</p>
        <h1 className="font-display text-3xl font-bold text-ink">
          Search Results for "{query}"
        </h1>
        <p className="text-slate text-sm font-mono mt-1">
          {results.length} matches found across Subjects, Units, Topics, Definitions, and Resources.
        </p>
      </div>

      {loading && <Loading />}
      {error && <ErrorBlock message={error} />}

      {!loading && !error && results.length === 0 && (
        <Empty
          title="No matching academic content found"
          hint={`Try searching for terms like 'sewing', 'french seam', 'BVTD113', 'fibre', 'entrepreneurship', or 'fashion'.`}
        />
      )}

      {!loading && !error && results.length > 0 && (
        <div className="space-y-3">
          {results.map((r, idx) => (
            <NavLink
              key={idx}
              to={r.link}
              className="block border border-ink/15 rounded-2xl bg-white p-5 hover:border-moss hover:shadow-md transition-all space-y-1.5 group"
            >
              <div className="flex items-center justify-between font-mono text-xs">
                <span className="text-moss font-semibold">{r.context}</span>
                <span className="bg-clay/10 text-clay px-2 py-0.5 rounded text-[10px] uppercase font-bold">
                  {r.type}
                </span>
              </div>
              <h3 className="font-display text-lg font-bold text-ink group-hover:text-moss transition-colors">
                {r.title}
              </h3>
              <p className="text-xs text-slate line-clamp-2 leading-relaxed">{r.description}</p>
            </NavLink>
          ))}
        </div>
      )}
    </div>
  );
}
