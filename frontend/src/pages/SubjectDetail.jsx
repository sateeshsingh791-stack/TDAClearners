import { useEffect, useState } from 'react';
import { useParams, NavLink } from 'react-router-dom';
import { getSubjectByCode, getPracticals, getScopedQuizzes, getResources } from '../api/client';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function SubjectDetail() {
  const { code } = useParams();
  const [subject, setSubject] = useState(null);
  const [practicals, setPracticals] = useState([]);
  const [quizzes, setQuizzes] = useState([]);
  const [resources, setResources] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [activeTab, setActiveTab] = useState('UNITS'); // UNITS | PRACTICE | RESOURCES | EXAM_PREP

  useEffect(() => {
    setLoading(true);
    const cleanCode = code ? code.toUpperCase() : '';

    Promise.all([
      getSubjectByCode(cleanCode),
      getPracticals({ subjectCode: cleanCode }),
      getScopedQuizzes({ subjectCode: cleanCode }),
      getResources()
    ])
      .then(([subjRes, pracRes, quizRes, resRes]) => {
        const s = subjRes.data.data?.subject || subjRes.data.subject || subjRes.data.data || subjRes.data;
        setSubject(s);

        const pList = pracRes.data.data?.practicals || pracRes.data.practicals || (Array.isArray(pracRes.data.data) ? pracRes.data.data : []);
        setPracticals(pList.filter((p) => p.subjectCode?.toUpperCase() === cleanCode));

        const qList = quizRes.data.data?.questions || quizRes.data.data?.quizzes || quizRes.data.quizzes || (Array.isArray(quizRes.data.data) ? quizRes.data.data : []);
        setQuizzes(qList);

        const rList = resRes.data.data?.resources || resRes.data.resources || (Array.isArray(resRes.data.data) ? resRes.data.data : []);
        setResources(rList.filter((r) => r.subjectCode?.toUpperCase() === cleanCode));
      })
      .catch(() => setError('Could not load detailed subject curriculum.'))
      .finally(() => setLoading(false));
  }, [code]);

  if (loading) return <Loading />;
  if (error) return <ErrorBlock message={error} />;
  if (!subject) return <Empty title="Subject not found" hint="Please check the subject code in your syllabus." />;

  const units = subject.units || [];
  const allTopics = subject.topics || [];

  return (
    <div className="space-y-8">
      {/* Navigation Breadcrumb */}
      <div className="flex items-center justify-between">
        <NavLink to="/syllabus" className="text-sm font-mono text-slate hover:text-ink flex items-center gap-1">
          ← Back to Syllabus Scheme
        </NavLink>

        <NavLink
          to="/ai-tutor"
          state={{
            academicContext: {
              course: 'B.Voc. Textile Design & Apparel Technology',
              yearNumber: subject.yearNumber || 1,
              semesterNumber: subject.semesterNumber || 1,
              subjectCode: subject.code,
              subjectName: subject.name
            }
          }}
          className="text-xs font-mono font-medium rounded-full px-3.5 py-1.5 border border-moss/30 bg-moss/10 text-moss hover:bg-moss hover:text-paper transition-all focus-ring inline-flex items-center gap-1.5"
        >
          🤖 Ask AI Tutor about {subject.code} →
        </NavLink>
      </div>

      {/* Subject Header & Scheme Banner */}
      <div className="border border-ink/15 rounded-2xl bg-white p-6 shadow-sm space-y-4">
        <div className="flex flex-wrap items-center justify-between gap-3 border-b border-ink/10 pb-4">
          <div>
            <div className="flex items-center gap-2 mb-1">
              <span className="font-mono text-xs font-bold text-clay bg-clay/10 px-2.5 py-0.5 rounded">
                {subject.code}
              </span>
              <span className="text-xs font-mono text-slate uppercase">
                {subject.category} • {subject.type} • Semester {subject.semesterNumber}
              </span>
            </div>
            <h1 className="font-display text-3xl text-ink">{subject.name}</h1>
          </div>
          <div className="text-right font-mono text-xs text-slate">
            <p><strong className="text-ink text-sm">{subject.totalCredits}</strong> Total Credits</p>
            <p><strong className="text-ink text-sm">{subject.totalMarks}</strong> Maximum Marks</p>
          </div>
        </div>

        {subject.overview && (
          <p className="text-slate text-sm leading-relaxed max-w-3xl">{subject.overview}</p>
        )}

        {/* Detailed Scheme Stats Badges */}
        <div className="grid grid-cols-2 sm:grid-cols-4 gap-3 text-xs font-mono pt-2">
          <div className="bg-paper p-3 rounded-lg border border-ink/5">
            <span className="text-slate block text-[10px] uppercase">Contact Hours</span>
            <strong className="text-ink text-sm">{subject.hoursPerWeek} Hrs / Week</strong>
          </div>
          <div className="bg-paper p-3 rounded-lg border border-ink/5">
            <span className="text-slate block text-[10px] uppercase">Credit Distribution</span>
            <strong className="text-ink text-sm">L:{subject.lectureCredits || 0} T:{subject.tutorialCredits || 0} P:{subject.practicalCredits || 0}</strong>
          </div>
          <div className="bg-paper p-3 rounded-lg border border-ink/5">
            <span className="text-slate block text-[10px] uppercase">Marks Division</span>
            <strong className="text-ink text-sm">
              {subject.theoryMarks ? `Th:${subject.theoryMarks} ` : ''}
              {subject.practicalMarks ? `Pr:${subject.practicalMarks} ` : ''}
              IA:{subject.internalAssessmentMarks || 0}
            </strong>
          </div>
          <div className="bg-paper p-3 rounded-lg border border-ink/5">
            <span className="text-slate block text-[10px] uppercase">Syllabus Page</span>
            <strong className="text-ink text-sm">{subject.syllabusPageRef || 'Official GNDU Scheme'}</strong>
          </div>
        </div>

        {/* Objectives & Prescribed Books */}
        <div className="grid sm:grid-cols-2 gap-4 pt-3 border-t border-ink/5 text-xs">
          {subject.courseObjectives && subject.courseObjectives.length > 0 && (
            <div>
              <h4 className="font-mono font-semibold text-clay uppercase mb-1">Course Objectives</h4>
              <ul className="list-disc list-inside space-y-1 text-slate">
                {subject.courseObjectives.map((obj, i) => (
                  <li key={i}>{obj}</li>
                ))}
              </ul>
            </div>
          )}

          {subject.booksPrescribed && subject.booksPrescribed.length > 0 && (
            <div>
              <h4 className="font-mono font-semibold text-moss uppercase mb-1">Prescribed Literature</h4>
              <ul className="list-disc list-inside space-y-1 text-slate">
                {subject.booksPrescribed.map((book, i) => (
                  <li key={i}>{book}</li>
                ))}
              </ul>
            </div>
          )}
        </div>
      </div>

      {/* Curriculum Depth Tab Controls */}
      <div className="flex border-b border-ink/15 font-mono text-xs gap-4 overflow-x-auto">
        <button
          onClick={() => setActiveTab('UNITS')}
          className={`pb-3 font-medium transition-colors border-b-2 whitespace-nowrap ${
            activeTab === 'UNITS'
              ? 'border-moss text-moss font-semibold'
              : 'border-transparent text-slate hover:text-ink'
          }`}
        >
          📚 Units & Topics ({units.length > 0 ? units.length : allTopics.length})
        </button>

        <button
          onClick={() => setActiveTab('PRACTICE')}
          className={`pb-3 font-medium transition-colors border-b-2 whitespace-nowrap ${
            activeTab === 'PRACTICE'
              ? 'border-moss text-moss font-semibold'
              : 'border-transparent text-slate hover:text-ink'
          }`}
        >
          🧪 Practice & Lab Experiments ({practicals.length + quizzes.length})
        </button>

        <button
          onClick={() => setActiveTab('RESOURCES')}
          className={`pb-3 font-medium transition-colors border-b-2 whitespace-nowrap ${
            activeTab === 'RESOURCES'
              ? 'border-moss text-moss font-semibold'
              : 'border-transparent text-slate hover:text-ink'
          }`}
        >
          📖 Resources & Textbooks ({resources.length})
        </button>

        <button
          onClick={() => setActiveTab('EXAM_PREP')}
          className={`pb-3 font-medium transition-colors border-b-2 whitespace-nowrap ${
            activeTab === 'EXAM_PREP'
              ? 'border-moss text-moss font-semibold'
              : 'border-transparent text-slate hover:text-ink'
          }`}
        >
          📝 Exam Prep & Paper Setter Rules
        </button>
      </div>

      {/* TAB CONTENT 1: UNITS & TOPICS */}
      {activeTab === 'UNITS' && (
        <div className="space-y-6">
          {units.length > 0 ? (
            units.map((unit) => (
              <div key={unit.unitNumber} className="border border-ink/10 rounded-xl bg-white p-5 space-y-3">
                <div className="flex items-center justify-between border-b border-ink/5 pb-2">
                  <h3 className="font-display text-lg text-ink font-semibold">{unit.title}</h3>
                  <span className="text-xs font-mono text-clay">{(unit.topics || []).length} Topics</span>
                </div>

                <div className="grid sm:grid-cols-2 gap-3">
                  {(unit.topics || []).map((t, idx) => (
                    <NavLink
                      key={t.topicId || t._id || idx}
                      to={`/topics/${t.topicId || t._id}`}
                      className="border border-ink/10 rounded-xl p-4 bg-paper/30 hover:border-moss hover:bg-white transition-all flex flex-col justify-between group"
                    >
                      <div>
                        <span className="text-[10px] font-mono text-clay uppercase">Topic {idx + 1}</span>
                        <h4 className="font-medium text-sm text-ink group-hover:text-moss transition-colors mt-0.5">
                          {t.title || t.name}
                        </h4>
                        {t.overview && (
                          <p className="text-xs text-slate line-clamp-2 mt-1">{t.overview}</p>
                        )}
                      </div>
                      <div className="mt-3 flex items-center justify-between text-[11px] font-mono text-moss border-t border-ink/5 pt-2">
                        <span>Read Topic & Notes</span>
                        <span>→</span>
                      </div>
                    </NavLink>
                  ))}
                </div>
              </div>
            ))
          ) : (
            <div className="space-y-2">
              {allTopics.map((t, i) => (
                <NavLink
                  key={t._id || t.id || i}
                  to={`/topics/${t.topicId || t._id || t.id}`}
                  className="flex items-center justify-between border border-ink/10 rounded-xl px-4 py-3 bg-white hover:border-moss transition-colors"
                >
                  <div>
                    <h4 className="font-medium text-sm">{t.title || t.name}</h4>
                    {t.overview && <p className="text-xs text-slate">{t.overview}</p>}
                  </div>
                  <span className="font-mono text-xs text-moss shrink-0">Study Notes →</span>
                </NavLink>
              ))}
            </div>
          )}
        </div>
      )}

      {/* TAB CONTENT 2: PRACTICE & LAB */}
      {activeTab === 'PRACTICE' && (
        <div className="space-y-6">
          <div className="grid sm:grid-cols-2 gap-4">
            <NavLink
              to={`/quiz-hub?subjectCode=${subject.code}`}
              className="border border-ink/15 rounded-xl p-5 bg-white hover:border-moss hover:shadow-md transition-all space-y-2 group"
            >
              <div className="flex items-center justify-between">
                <span className="font-mono text-xs text-moss font-semibold">Interactive Quizzes</span>
                <span className="text-xs font-mono bg-moss/10 text-moss px-2 py-0.5 rounded">{quizzes.length} Questions</span>
              </div>
              <h3 className="font-display text-lg text-ink group-hover:text-moss transition-colors">
                Practice Subject Quizzes
              </h3>
              <p className="text-xs text-slate">
                Test your conceptual understanding with multiple-choice questions aligned to {subject.code}.
              </p>
              <span className="inline-block text-xs font-mono text-moss font-medium pt-2">Start Quiz Drill →</span>
            </NavLink>

            <NavLink
              to={`/flashcards?subjectCode=${subject.code}`}
              className="border border-ink/15 rounded-xl p-5 bg-white hover:border-moss hover:shadow-md transition-all space-y-2 group"
            >
              <div className="flex items-center justify-between">
                <span className="font-mono text-xs text-clay font-semibold">Flashcard Drills</span>
                <span className="text-xs font-mono bg-clay/10 text-clay px-2 py-0.5 rounded">Recall Practice</span>
              </div>
              <h3 className="font-display text-lg text-ink group-hover:text-moss transition-colors">
                Review Flashcards
              </h3>
              <p className="text-xs text-slate">
                Fast active recall drills for terminology, machine parts, and chemical testing parameters.
              </p>
              <span className="inline-block text-xs font-mono text-clay font-medium pt-2">Review Cards →</span>
            </NavLink>
          </div>

          {/* Practical Experiments List */}
          {practicals.length > 0 && (
            <div className="border border-ink/10 rounded-xl bg-white p-5 space-y-3">
              <h3 className="font-display text-lg font-semibold text-ink">Laboratory Experiments ({practicals.length})</h3>
              <div className="space-y-3">
                {practicals.map((p, idx) => (
                  <div key={p.practicalId || idx} className="border border-ink/10 rounded-lg p-4 bg-paper/20 space-y-2">
                    <div className="flex items-center justify-between">
                      <h4 className="font-medium text-sm text-ink">{p.title}</h4>
                      <span className="text-[10px] font-mono text-clay">Lab Practical</span>
                    </div>
                    {p.objective && <p className="text-xs text-slate"><strong>Objective:</strong> {p.objective}</p>}
                    {p.theory && <p className="text-xs text-slate"><strong>Theory:</strong> {p.theory}</p>}
                  </div>
                ))}
              </div>
            </div>
          )}
        </div>
      )}

      {/* TAB CONTENT 3: RESOURCES */}
      {activeTab === 'RESOURCES' && (
        <div className="space-y-4">
          {resources.length === 0 ? (
            <Empty title="No external manuals linked" hint="Official prescribed textbooks are listed in the Subject Scheme summary above." />
          ) : (
            <div className="grid sm:grid-cols-2 gap-3">
              {resources.map((r, idx) => (
                <div key={r.resourceId || idx} className="border border-ink/10 rounded-xl p-4 bg-white space-y-2">
                  <div className="flex items-center justify-between">
                    <span className="font-mono text-xs text-moss font-semibold">{r.category || 'Reference'}</span>
                    <span className="text-[10px] font-mono text-slate">{r.format || 'PDF'}</span>
                  </div>
                  <h4 className="font-medium text-sm text-ink">{r.title}</h4>
                  <p className="text-xs text-slate">{r.description}</p>
                </div>
              ))}
            </div>
          )}
        </div>
      )}

      {/* TAB CONTENT 4: EXAM PREPARATION */}
      {activeTab === 'EXAM_PREP' && (
        <div className="border border-ink/10 rounded-2xl bg-white p-6 space-y-4">
          <h3 className="font-display text-xl text-ink">Exam Pattern & Paper Setter Instructions</h3>
          {subject.instructionsForPaperSetters ? (
            <div className="p-4 rounded-xl bg-paper/60 border border-ink/10 space-y-2 text-xs font-mono">
              <p className="text-clay font-semibold uppercase">Official Syllabus Exam Structure:</p>
              <p className="text-ink leading-relaxed">{subject.instructionsForPaperSetters}</p>
            </div>
          ) : (
            <p className="text-xs font-mono text-slate">
              Section A: Compulsory short answer questions. Sections B-E: Long answer questions from respective units.
            </p>
          )}

          <div className="grid sm:grid-cols-2 gap-4 pt-2">
            <div className="p-4 rounded-xl border border-ink/10 bg-white space-y-2">
              <h4 className="font-mono text-xs font-semibold text-moss uppercase">Internal Assessment (IA)</h4>
              <p className="text-xs text-slate">
                Weightage: <strong>{subject.internalAssessmentMarks || 0} Marks</strong> (Attendance, mid-semester tests, lab practical reports).
              </p>
            </div>
            <div className="p-4 rounded-xl border border-ink/10 bg-white space-y-2">
              <h4 className="font-mono text-xs font-semibold text-clay uppercase">End-Semester University Exam</h4>
              <p className="text-xs text-slate">
                Weightage: <strong>{(subject.theoryMarks || 0) + (subject.practicalMarks || 0)} Marks</strong> (Theory / Practical Examination).
              </p>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
