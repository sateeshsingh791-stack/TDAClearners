import { useEffect, useState } from 'react';
import { useSearchParams, NavLink } from 'react-router-dom';
import { getPracticals } from '../api/client';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function PracticalLab() {
  const [searchParams] = useSearchParams();
  const initialSubject = searchParams.get('subjectCode') || 'ALL';

  const [subjectFilter, setSubjectFilter] = useState(initialSubject);
  const [practicals, setPracticals] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [activePractical, setActivePractical] = useState(null);

  useEffect(() => {
    setLoading(true);
    const query = subjectFilter !== 'ALL' ? { subjectCode: subjectFilter } : {};
    getPracticals(query)
      .then((res) => {
        const list = res.data.data?.practicals || res.data.practicals || (Array.isArray(res.data.data) ? res.data.data : []);
        setPracticals(list);
        if (list.length > 0) setActivePractical(list[0]);
      })
      .catch(() => setError('Could not load laboratory practicals.'))
      .finally(() => setLoading(false));
  }, [subjectFilter]);

  return (
    <div className="space-y-6">
      {/* Header */}
      <div>
        <p className="font-mono text-xs text-clay uppercase tracking-widest mb-1">05 — Practical Laboratory Workflows</p>
        <h1 className="font-display text-3xl font-bold text-ink">Textile & Garment Practical Workflows</h1>
        <p className="text-slate text-sm">
          Hands-on lab procedures, equipment requirements, safety precautions, and viva voce defense.
        </p>
      </div>

      {/* Subject Filter Bar */}
      <div className="border border-ink/15 rounded-2xl bg-white p-4 flex flex-wrap items-center justify-between gap-3 shadow-xs">
        <div className="flex items-center gap-2 text-xs font-mono">
          <span className="text-moss font-semibold">Filter Practical Subject:</span>
          <select
            value={subjectFilter}
            onChange={(e) => setSubjectFilter(e.target.value)}
            className="border border-ink/15 rounded-xl px-3 py-1.5 bg-paper/50 focus-ring font-medium"
          >
            <option value="ALL">All Practical Subjects</option>
            <option value="BVTD113">BVTD113 — Sewing Techniques (Practical)</option>
            <option value="BVTD112">BVTD112 — Design Foundation & Basics of Textile (Practical)</option>
            <option value="BVTD122">BVTD122 — Garment Sewing (Practical)</option>
            <option value="BVTD123">BVTD123 — Design Foundation II (Practical)</option>
            <option value="CS-BVTD121">CS-BVTD121 — Computer Applications II (Practical)</option>
          </select>
        </div>

        <span className="text-xs font-mono text-slate">
          {practicals.length} Experiments Loaded
        </span>
      </div>

      {loading && <Loading />}
      {error && <ErrorBlock message={error} />}

      {!loading && !error && practicals.length === 0 && (
        <Empty title="No practical lab workflows found" hint="Select another subject filter or clear filters." />
      )}

      {!loading && !error && practicals.length > 0 && (
        <div className="grid md:grid-cols-3 gap-6">
          
          {/* Practicals Sidebar Navigation */}
          <div className="space-y-2">
            <h3 className="font-mono text-xs font-semibold text-clay uppercase tracking-wider mb-2">
              Select Experiment:
            </h3>
            {practicals.map((p, idx) => {
              const isSelected = activePractical?.practicalId === p.practicalId || activePractical?._id === p._id;
              return (
                <button
                  key={p.practicalId || idx}
                  onClick={() => setActivePractical(p)}
                  className={`w-full text-left p-4 rounded-xl border transition-all text-xs ${
                    isSelected
                      ? 'border-moss bg-moss text-paper font-semibold shadow-xs'
                      : 'border-ink/10 bg-white hover:border-moss text-ink'
                  }`}
                >
                  <div className="font-mono text-[10px] opacity-80 uppercase">{p.subjectCode} • Practical {idx + 1}</div>
                  <div className="font-medium mt-0.5 truncate">{p.title}</div>
                </button>
              );
            })}
          </div>

          {/* Practical Full Workflow View */}
          {activePractical && (
            <div className="md:col-span-2 border border-ink/15 rounded-2xl bg-white p-6 shadow-sm space-y-6">
              {/* Header & Badges */}
              <div className="border-b border-ink/10 pb-4 space-y-2">
                <div className="flex flex-wrap items-center gap-2 font-mono text-[11px]">
                  <span className="bg-moss/15 text-moss px-2.5 py-0.5 rounded-full font-semibold border border-moss/20">
                    📌 Official Syllabus Component ({activePractical.subjectCode})
                  </span>
                  <span className="bg-clay/10 text-clay px-2.5 py-0.5 rounded-full font-medium border border-clay/20">
                    Suggested Practice — Faculty Verification Recommended
                  </span>
                </div>

                <h2 className="font-display text-2xl text-ink font-bold">{activePractical.title}</h2>
              </div>

              {/* 1. Objective */}
              {activePractical.objective && (
                <div className="space-y-1">
                  <h4 className="font-mono text-xs font-bold text-clay uppercase">1. Objective:</h4>
                  <p className="text-xs text-ink leading-relaxed bg-paper/50 p-3 rounded-xl border border-ink/5">
                    {activePractical.objective}
                  </p>
                </div>
              )}

              {/* 2. Materials & Equipment */}
              {activePractical.materialsRequired && activePractical.materialsRequired.length > 0 && (
                <div className="space-y-1">
                  <h4 className="font-mono text-xs font-bold text-moss uppercase">2. Materials & Equipment Required:</h4>
                  <ul className="list-disc list-inside space-y-1 text-xs text-slate bg-moss/5 p-3 rounded-xl border border-moss/10">
                    {activePractical.materialsRequired.map((mat, i) => (
                      <li key={i}>{mat}</li>
                    ))}
                  </ul>
                </div>
              )}

              {/* 3. Theory & Procedure */}
              <div className="space-y-3">
                {activePractical.theory && (
                  <div>
                    <h4 className="font-mono text-xs font-bold text-ink uppercase mb-1">3. Underlying Practical Theory:</h4>
                    <p className="text-xs text-slate leading-relaxed">{activePractical.theory}</p>
                  </div>
                )}

                {activePractical.stepByStepProcedure && activePractical.stepByStepProcedure.length > 0 && (
                  <div>
                    <h4 className="font-mono text-xs font-bold text-ink uppercase mb-1">4. Step-by-Step Procedure:</h4>
                    <ol className="list-decimal list-inside space-y-1.5 text-xs text-slate bg-paper/30 p-3.5 rounded-xl border border-ink/10">
                      {activePractical.stepByStepProcedure.map((step, i) => (
                        <li key={i} className="leading-relaxed">{step}</li>
                      ))}
                    </ol>
                  </div>
                )}
              </div>

              {/* 5. Observations & Precautions */}
              <div className="grid sm:grid-cols-2 gap-4">
                {activePractical.expectedObservations && (
                  <div className="p-3.5 rounded-xl border border-ink/10 bg-white space-y-1 text-xs">
                    <h5 className="font-mono font-bold text-clay uppercase">Expected Observation:</h5>
                    <p className="text-slate">{activePractical.expectedObservations}</p>
                  </div>
                )}

                {activePractical.precautions && activePractical.precautions.length > 0 && (
                  <div className="p-3.5 rounded-xl border border-ink/10 bg-white space-y-1 text-xs">
                    <h5 className="font-mono font-bold text-moss uppercase">Precautions:</h5>
                    <ul className="list-disc list-inside text-slate space-y-0.5">
                      {activePractical.precautions.map((prec, i) => (
                        <li key={i}>{prec}</li>
                      ))}
                    </ul>
                  </div>
                )}
              </div>

              {/* 6. Viva Voce Defense */}
              {activePractical.vivaQuestions && activePractical.vivaQuestions.length > 0 && (
                <div className="space-y-2 border-t border-ink/10 pt-4">
                  <h4 className="font-mono text-xs font-bold text-ink uppercase">6. Viva Voce Questions & Guidance:</h4>
                  <div className="space-y-2">
                    {activePractical.vivaQuestions.map((viva, i) => (
                      <div key={i} className="p-3 rounded-xl border border-ink/10 bg-paper/30 text-xs space-y-1">
                        <p className="font-semibold text-ink">Q{i + 1}: {viva.question}</p>
                        <p className="text-moss font-mono">A: {viva.answer}</p>
                      </div>
                    ))}
                  </div>
                </div>
              )}

              {/* Ask AI Tutor CTA */}
              <div className="pt-2">
                <NavLink
                  to="/ai-tutor"
                  state={{
                    academicContext: {
                      course: 'B.Voc. Textile Design & Apparel Technology',
                      subjectCode: activePractical.subjectCode,
                      topicTitle: activePractical.title
                    }
                  }}
                  className="text-xs font-mono font-semibold bg-moss/10 text-moss border border-moss/30 px-4 py-2 rounded-full hover:bg-moss hover:text-paper transition-all inline-flex items-center gap-1.5"
                >
                  🤖 Ask AI Tutor about this practical experiment →
                </NavLink>
              </div>
            </div>
          )}
        </div>
      )}
    </div>
  );
}
