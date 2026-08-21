import { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';
import { getSemesters } from '../api/client';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function SyllabusBrowser() {
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
      .catch(() => setError('Could not load the syllabus.'))
      .finally(() => setLoading(false));
  }, []);

  // Group semesters by year number
  const yearGroups = [
    { yearNumber: 1, title: '1st Year', status: 'AVAILABLE' },
    { yearNumber: 2, title: '2nd Year', status: 'COMING_SOON' },
    { yearNumber: 3, title: '3rd Year', status: 'COMING_SOON' }
  ];

  return (
    <div className="space-y-8">
      <div>
        <p className="font-mono text-xs text-clay uppercase tracking-widest mb-2">02 — Syllabus & Academic Scheme</p>
        <h1 className="font-display text-3xl md:text-4xl mb-2">
          {academicScheme?.degree || 'B.Voc. Textile Design & Apparel Technology'}
        </h1>
        <p className="text-slate text-sm font-mono">
          {academicScheme?.department || 'P.G. Department of Fashion Designing'}
        </p>
      </div>

      {loading && <Loading />}
      {error && <ErrorBlock message={error} />}

      {!loading && !error && (
        <div className="space-y-10">
          {yearGroups.map((group) => {
            const semList = semesters.filter(
              (s) => Math.ceil((s.number || s.semesterNumber) / 2) === group.yearNumber
            );
            const isYearAvailable = group.status === 'AVAILABLE' || semList.some((s) => s.status === 'AVAILABLE');

            return (
              <section key={group.yearNumber} className="border border-ink/10 rounded-2xl bg-white/60 p-6 space-y-4">
                <div className="flex items-center justify-between border-b border-ink/10 pb-3">
                  <div className="flex items-center gap-3">
                    <h2 className="font-display text-2xl">{group.title}</h2>
                    <span
                      className={`text-xs px-2.5 py-0.5 rounded-full font-mono font-medium ${
                        isYearAvailable
                          ? 'bg-moss/10 text-moss border border-moss/20'
                          : 'bg-clay/10 text-clay border border-clay/20'
                      }`}
                    >
                      {isYearAvailable ? 'Syllabus Available' : 'Coming Soon'}
                    </span>
                  </div>
                  <span className="text-xs font-mono text-slate">
                    {isYearAvailable ? '2 Semesters Active' : 'Future Expansion'}
                  </span>
                </div>

                {!isYearAvailable ? (
                  <div className="p-6 rounded-xl border border-dashed border-ink/20 bg-paper/50 text-center space-y-2">
                    <p className="font-display text-lg text-ink/70">Detailed syllabus will be added when available.</p>
                    <p className="text-xs font-mono text-slate max-w-md mx-auto">
                      Year {group.yearNumber} course scheme & official unit breakdown will appear here once officially published.
                    </p>
                  </div>
                ) : (
                  <div className="space-y-4">
                    {semList.map((sem, i) => {
                      const isAvailable = sem.status === 'AVAILABLE' || sem.number === 1 || sem.number === 2;

                      return (
                        <details
                          key={sem._id || sem.id || i}
                          open={isAvailable && sem.number === 1}
                          className={`border rounded-xl transition-all ${
                            isAvailable
                              ? 'border-ink/15 bg-white shadow-sm group'
                              : 'border-ink/10 bg-paper/40 opacity-75'
                          }`}
                        >
                          <summary className="cursor-pointer list-none px-5 py-4 font-display text-xl flex items-center justify-between select-none">
                            <div className="flex items-center gap-3">
                              <span>{sem.title || `Semester ${sem.number}`}</span>
                              <span
                                className={`text-xs px-2 py-0.5 rounded font-mono ${
                                  isAvailable ? 'bg-moss/10 text-moss font-semibold' : 'bg-clay/10 text-clay'
                                }`}
                              >
                                {isAvailable ? 'Available' : 'Coming Soon'}
                              </span>
                            </div>
                            {isAvailable ? (
                              <div className="flex items-center gap-4 text-xs font-mono text-slate">
                                <span>{sem.totalCredits || 0} Credits</span>
                                <span>{sem.totalMarks || 0} Marks</span>
                                <span className="text-clay text-sm font-mono group-open:rotate-45 transition-transform">
                                  +
                                </span>
                              </div>
                            ) : (
                              <span className="text-xs font-mono text-clay">Coming Soon</span>
                            )}
                          </summary>

                          {isAvailable ? (
                            <div className="px-5 pb-5 pt-2 border-t border-ink/5 space-y-4">
                              <div className="flex flex-wrap gap-4 text-xs font-mono text-slate bg-paper/60 p-3 rounded-lg border border-ink/5">
                                <div>
                                  <strong className="text-ink">Contact Hours:</strong> {sem.totalHoursPerWeek || 0} hrs/wk
                                </div>
                                <div>
                                  <strong className="text-ink">Total Credits:</strong> {sem.totalCredits || 0}
                                </div>
                                <div>
                                  <strong className="text-ink">Max Marks:</strong> {sem.totalMarks || 0}
                                </div>
                              </div>

                              <div className="grid sm:grid-cols-2 gap-3">
                                {(sem.subjects || []).length === 0 ? (
                                  <p className="text-sm text-slate">No subjects listed for this semester.</p>
                                ) : (
                                  (sem.subjects || []).map((sub, j) => (
                                    <NavLink
                                      key={sub.code || sub._id || j}
                                      to={`/subjects/${sub.code}`}
                                      className="border border-ink/10 rounded-xl p-3 bg-white hover:border-moss hover:shadow-md transition-all flex flex-col justify-between group/card"
                                    >
                                      <div>
                                        <div className="flex items-center justify-between mb-1">
                                          <span className="font-mono text-xs font-semibold text-clay bg-clay/10 px-2 py-0.5 rounded">
                                            {sub.code}
                                          </span>
                                          <span className="text-[10px] font-mono text-slate uppercase">
                                            {sub.category} • {sub.type}
                                          </span>
                                        </div>
                                        <h3 className="font-medium text-sm text-ink group-hover/card:text-moss transition-colors">
                                          {sub.name}
                                        </h3>
                                      </div>
                                      <div className="mt-3 flex items-center justify-between text-[11px] font-mono text-slate border-t border-ink/5 pt-2">
                                        <span>{sub.totalCredits} Credits</span>
                                        <span className="text-moss font-medium">Explore Subject →</span>
                                      </div>
                                    </NavLink>
                                  ))
                                )}
                              </div>
                            </div>
                          ) : (
                            <div className="px-5 pb-4 text-sm text-slate font-mono">
                              Detailed syllabus will be added when available.
                            </div>
                          )}
                        </details>
                      );
                    })}
                  </div>
                )}
              </section>
            );
          })}
        </div>
      )}
    </div>
  );
}
