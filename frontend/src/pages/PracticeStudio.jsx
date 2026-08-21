import { useEffect, useState } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { getSemesters, getScopedQuizzes, getScopedFlashcards, recordQuizAttempt, updateFlashcardMastery } from '../api/client';
import { useAuth } from '../context/AuthContext';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function PracticeStudio() {
  const { user } = useAuth();
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();

  // Scope selection state
  const [selectedSemester, setSelectedSemester] = useState(searchParams.get('semester') || 'ALL');
  const [selectedSubject, setSelectedSubject] = useState(searchParams.get('subjectCode') || 'ALL');
  const [selectedUnit, setSelectedUnit] = useState(searchParams.get('unitNumber') || 'ALL');
  const [selectedTopic, setSelectedTopic] = useState(searchParams.get('topicId') || 'ALL');
  const [activityMode, setActivityMode] = useState(searchParams.get('activity') || 'QUIZ'); // QUIZ | FLASHCARDS | REVISION | PRACTICE_QUESTIONS

  // Data state
  const [semesters, setSemesters] = useState([]);
  const [allSubjects, setAllSubjects] = useState([]);
  const [allTopics, setAllTopics] = useState([]);

  // Activity content state
  const [quizzes, setQuizzes] = useState([]);
  const [flashcards, setFlashcards] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  // Quiz state
  const [quizIndex, setQuizIndex] = useState(0);
  const [userAnswers, setUserAnswers] = useState({});
  const [quizSubmitted, setQuizSubmitted] = useState(false);
  const [quizScore, setQuizScore] = useState(0);

  // Flashcard state
  const [cardIndex, setCardIndex] = useState(0);
  const [cardFlipped, setCardFlipped] = useState(false);

  // Load initial curriculum data
  useEffect(() => {
    getSemesters()
      .then((res) => {
        const semList = res.data.data?.semesters || res.data.semesters || (Array.isArray(res.data.data) ? res.data.data : []);
        setSemesters(semList);

        const subList = [];
        const topList = [];
        semList.forEach((sem) => {
          (sem.subjects || []).forEach((sub) => {
            subList.push({ ...sub, semesterNumber: sem.number || sem.semesterNumber });
            (sub.units || []).forEach((u) => {
              (u.topics || []).forEach((t) => {
                topList.push({ ...t, subjectCode: sub.code, unitNumber: u.unitNumber });
              });
            });
          });
        });
        setAllSubjects(subList);
        setAllTopics(topList);
      })
      .catch(() => setError('Could not load curriculum scheme.'))
      .finally(() => setLoading(false));
  }, []);

  // Update query params when scope changes
  useEffect(() => {
    const params = {};
    if (selectedSemester !== 'ALL') params.semester = selectedSemester;
    if (selectedSubject !== 'ALL') params.subjectCode = selectedSubject;
    if (selectedUnit !== 'ALL') params.unitNumber = selectedUnit;
    if (selectedTopic !== 'ALL') params.topicId = selectedTopic;
    params.activity = activityMode;
    setSearchParams(params, { replace: true });
  }, [selectedSemester, selectedSubject, selectedUnit, selectedTopic, activityMode]);

  // Fetch scoped activity content
  useEffect(() => {
    setLoading(true);
    setError('');
    setQuizSubmitted(false);
    setQuizIndex(0);
    setUserAnswers({});
    setCardIndex(0);
    setCardFlipped(false);

    const queryParams = {};
    if (selectedSubject !== 'ALL') queryParams.subjectCode = selectedSubject;
    if (selectedUnit !== 'ALL') queryParams.unitNumber = selectedUnit;
    if (selectedTopic !== 'ALL') queryParams.topicId = selectedTopic;

    if (activityMode === 'QUIZ') {
      getScopedQuizzes(queryParams)
        .then((res) => {
          const list = res.data.data?.questions || res.data.data?.quizzes || res.data.quizzes || (Array.isArray(res.data.data) ? res.data.data : []);
          setQuizzes(list);
        })
        .catch(() => setError('Could not load scoped quizzes.'))
        .finally(() => setLoading(false));
    } else if (activityMode === 'FLASHCARDS') {
      getScopedFlashcards(queryParams)
        .then((res) => {
          const list = res.data.data?.flashcards || res.data.flashcards || (Array.isArray(res.data.data) ? res.data.data : []);
          setFlashcards(list);
        })
        .catch(() => setError('Could not load scoped flashcards.'))
        .finally(() => setLoading(false));
    } else {
      setLoading(false);
    }
  }, [selectedSubject, selectedUnit, selectedTopic, activityMode]);

  // Filter subjects by selected semester
  const availableSubjects = selectedSemester === 'ALL'
    ? allSubjects
    : allSubjects.filter((s) => String(s.semesterNumber) === String(selectedSemester));

  // Filter topics by selected subject & unit
  const filteredTopics = allTopics.filter((t) => {
    if (selectedSubject !== 'ALL' && t.subjectCode !== selectedSubject) return false;
    if (selectedUnit !== 'ALL' && String(t.unitNumber) !== String(selectedUnit)) return false;
    return true;
  });

  const handleQuizSubmit = async () => {
    let score = 0;
    quizzes.forEach((q, i) => {
      const picked = userAnswers[i];
      if (picked !== undefined && (picked === q.correctIndex || picked === q.correctAnswer || picked === q.answer)) {
        score += 1;
      }
    });
    setQuizScore(score);
    setQuizSubmitted(true);

    if (user && quizzes[0]) {
      try {
        await recordQuizAttempt({
          quizId: quizzes[0]._id || quizzes[0].id || String(Date.now()),
          score,
          total: quizzes.length
        });
      } catch (e) {
        /* non-blocking */
      }
    }
  };

  const handleCardMastery = async (masteryLevel) => {
    const card = flashcards[cardIndex];
    if (user && card) {
      try {
        await updateFlashcardMastery({ flashcardId: card.cardId || card._id, mastery: masteryLevel });
      } catch (e) {
        /* non-blocking */
      }
    }
    setCardFlipped(false);
    if (cardIndex < flashcards.length - 1) {
      setCardIndex((idx) => idx + 1);
    }
  };

  return (
    <div className="space-y-6">
      {/* Header */}
      <div>
        <p className="font-mono text-xs text-clay uppercase tracking-widest mb-1">04 — Practice Studio</p>
        <h1 className="font-display text-3xl font-bold text-ink">Dynamic Academic Practice Studio</h1>
        <p className="text-slate text-sm">
          Select your academic scope (Semester, Subject, Unit, Topic) and practice mode to generate targeted study items.
        </p>
      </div>

      {/* Scope Selector Control Panel */}
      <div className="border border-ink/15 rounded-2xl bg-white p-5 shadow-xs space-y-4">
        <div className="flex items-center justify-between border-b border-ink/10 pb-3">
          <h2 className="font-mono text-xs font-semibold text-clay uppercase tracking-wider">
            🎯 Scope Selection Controls
          </h2>
          <button
            onClick={() => {
              setSelectedSemester('ALL');
              setSelectedSubject('ALL');
              setSelectedUnit('ALL');
              setSelectedTopic('ALL');
            }}
            className="text-xs font-mono text-slate hover:text-ink underline"
          >
            Reset All Filters
          </button>
        </div>

        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-3">
          {/* Semester Selector */}
          <div>
            <label className="block text-[11px] font-mono text-slate mb-1">Semester</label>
            <select
              value={selectedSemester}
              onChange={(e) => {
                setSelectedSemester(e.target.value);
                setSelectedSubject('ALL');
                setSelectedUnit('ALL');
                setSelectedTopic('ALL');
              }}
              className="w-full border border-ink/15 rounded-xl px-3 py-2 text-xs font-medium bg-paper/50 focus-ring"
            >
              <option value="ALL">All Semesters (Sem 1 & 2)</option>
              <option value="1">Semester 1 (Autumn Term)</option>
              <option value="2">Semester 2 (Spring Term)</option>
            </select>
          </div>

          {/* Subject Selector */}
          <div>
            <label className="block text-[11px] font-mono text-slate mb-1">Subject</label>
            <select
              value={selectedSubject}
              onChange={(e) => {
                setSelectedSubject(e.target.value);
                setSelectedUnit('ALL');
                setSelectedTopic('ALL');
              }}
              className="w-full border border-ink/15 rounded-xl px-3 py-2 text-xs font-medium bg-paper/50 focus-ring"
            >
              <option value="ALL">All Subjects</option>
              {availableSubjects.map((s) => (
                <option key={s.code} value={s.code}>
                  {s.code} - {s.name}
                </option>
              ))}
            </select>
          </div>

          {/* Unit Selector */}
          <div>
            <label className="block text-[11px] font-mono text-slate mb-1">Unit / Section</label>
            <select
              value={selectedUnit}
              onChange={(e) => {
                setSelectedUnit(e.target.value);
                setSelectedTopic('ALL');
              }}
              className="w-full border border-ink/15 rounded-xl px-3 py-2 text-xs font-medium bg-paper/50 focus-ring"
            >
              <option value="ALL">All Units & Sections</option>
              <option value="1">Unit 1 / Section-I</option>
              <option value="2">Unit 2 / Section-II</option>
              <option value="3">Unit 3</option>
              <option value="4">Unit 4</option>
            </select>
          </div>

          {/* Topic Selector */}
          <div>
            <label className="block text-[11px] font-mono text-slate mb-1">Topic</label>
            <select
              value={selectedTopic}
              onChange={(e) => setSelectedTopic(e.target.value)}
              className="w-full border border-ink/15 rounded-xl px-3 py-2 text-xs font-medium bg-paper/50 focus-ring"
            >
              <option value="ALL">All Topics in Scope</option>
              {filteredTopics.map((t) => (
                <option key={t.topicId} value={t.topicId}>
                  {t.title || t.name}
                </option>
              ))}
            </select>
          </div>
        </div>

        {/* Active Scope Summary Banner */}
        <div className="bg-paper/80 border border-ink/10 rounded-xl px-4 py-2.5 flex flex-wrap items-center justify-between text-xs font-mono">
          <div className="flex items-center gap-2">
            <span className="text-moss font-semibold">Current Scope:</span>
            <span className="bg-moss/10 text-moss px-2 py-0.5 rounded font-bold">
              {selectedSubject !== 'ALL' ? selectedSubject : 'All Subjects'}
            </span>
            {selectedUnit !== 'ALL' && (
              <span className="bg-clay/10 text-clay px-2 py-0.5 rounded">Unit {selectedUnit}</span>
            )}
            {selectedTopic !== 'ALL' && (
              <span className="text-ink truncate max-w-xs">Topic: {selectedTopic}</span>
            )}
          </div>
          <span className="text-slate text-[11px]">Strict Filtering Active</span>
        </div>
      </div>

      {/* Activity Mode Selector Tabs */}
      <div className="grid grid-cols-2 sm:grid-cols-4 gap-3">
        <button
          onClick={() => setActivityMode('QUIZ')}
          className={`p-4 rounded-xl border text-left transition-all ${
            activityMode === 'QUIZ'
              ? 'border-moss bg-moss text-paper shadow-sm font-semibold'
              : 'border-ink/15 bg-white text-ink hover:border-moss'
          }`}
        >
          <div className="text-lg mb-1">🎯 MCQ Quiz</div>
          <div className="text-xs opacity-90">Test knowledge with instant feedback & scoring</div>
        </button>

        <button
          onClick={() => setActivityMode('FLASHCARDS')}
          className={`p-4 rounded-xl border text-left transition-all ${
            activityMode === 'FLASHCARDS'
              ? 'border-moss bg-moss text-paper shadow-sm font-semibold'
              : 'border-ink/15 bg-white text-ink hover:border-moss'
          }`}
        >
          <div className="text-lg mb-1">🎴 Flashcards</div>
          <div className="text-xs opacity-90">Active recall drills & 3D flip card review</div>
        </button>

        <button
          onClick={() => setActivityMode('REVISION')}
          className={`p-4 rounded-xl border text-left transition-all ${
            activityMode === 'REVISION'
              ? 'border-moss bg-moss text-paper shadow-sm font-semibold'
              : 'border-ink/15 bg-white text-ink hover:border-moss'
          }`}
        >
          <div className="text-lg mb-1">⚡ Quick Revision</div>
          <div className="text-xs opacity-90">Key concepts, definitions & visual summaries</div>
        </button>

        <button
          onClick={() => setActivityMode('PRACTICE_QUESTIONS')}
          className={`p-4 rounded-xl border text-left transition-all ${
            activityMode === 'PRACTICE_QUESTIONS'
              ? 'border-moss bg-moss text-paper shadow-sm font-semibold'
              : 'border-ink/15 bg-white text-ink hover:border-moss'
          }`}
        >
          <div className="text-lg mb-1">📝 Practice Exam Prep</div>
          <div className="text-xs opacity-90">Viva questions, paper setter layouts & procedures</div>
        </button>
      </div>

      {loading && <Loading />}
      {error && <ErrorBlock message={error} />}

      {/* ACTIVITY 1: QUIZ MODE */}
      {!loading && !error && activityMode === 'QUIZ' && (
        <div>
          {quizzes.length === 0 ? (
            <Empty
              title="No Quizzes Available in Selected Scope"
              hint={`No quiz questions found for ${selectedSubject !== 'ALL' ? selectedSubject : 'current selection'}. Try selecting 'All Units' or another subject.`}
            />
          ) : (
            <div className="border border-ink/15 rounded-2xl bg-white p-6 space-y-6 shadow-sm">
              <div className="flex items-center justify-between border-b border-ink/10 pb-3">
                <span className="font-mono text-xs text-moss font-semibold">
                  Targeted MCQ Quiz ({quizzes.length} Questions)
                </span>
                <span className="font-mono text-xs text-slate">
                  {selectedSubject !== 'ALL' ? selectedSubject : 'Syllabus Quiz'}
                </span>
              </div>

              <div className="space-y-6">
                {quizzes.map((q, idx) => (
                  <div key={q.questionId || idx} className="border border-ink/10 rounded-xl p-4 bg-paper/30 space-y-3">
                    <div className="flex items-start justify-between gap-2">
                      <p className="font-medium text-sm text-ink">
                        {idx + 1}. {q.question}
                      </p>
                      {q.difficulty && (
                        <span className="text-[10px] font-mono bg-clay/10 text-clay px-2 py-0.5 rounded shrink-0">
                          {q.difficulty}
                        </span>
                      )}
                    </div>

                    <div className="space-y-2">
                      {(q.options || []).map((opt, optIdx) => {
                        const isSelected = userAnswers[idx] === optIdx;
                        const isCorrectOpt = quizSubmitted && (optIdx === q.correctIndex || opt === q.correctAnswer);
                        const isWrongPick = quizSubmitted && isSelected && !isCorrectOpt;

                        return (
                          <button
                            key={optIdx}
                            disabled={quizSubmitted}
                            onClick={() => setUserAnswers({ ...userAnswers, [idx]: optIdx })}
                            className={`w-full text-left border rounded-xl px-4 py-2.5 text-xs font-medium transition-all ${
                              isCorrectOpt
                                ? 'border-moss bg-moss/15 text-moss font-bold'
                                : isWrongPick
                                ? 'border-clay bg-clay/15 text-clay'
                                : isSelected
                                ? 'border-ink bg-ink text-paper'
                                : 'border-ink/15 bg-white hover:border-ink/40'
                            }`}
                          >
                            {opt}
                          </button>
                        );
                      })}
                    </div>

                    {quizSubmitted && q.explanation && (
                      <div className="p-3 rounded-lg bg-moss/5 border border-moss/20 text-xs text-slate space-y-1">
                        <strong className="text-moss block font-mono">Explanation:</strong>
                        <p>{q.explanation}</p>
                      </div>
                    )}
                  </div>
                ))}
              </div>

              {!quizSubmitted ? (
                <button
                  onClick={handleQuizSubmit}
                  className="bg-moss text-paper rounded-full px-6 py-2.5 text-sm font-semibold hover:bg-ink transition-colors focus-ring"
                >
                  Submit Quiz Answers →
                </button>
              ) : (
                <div className="border border-moss/30 rounded-xl p-5 bg-moss/5 flex items-center justify-between">
                  <div>
                    <h3 className="font-display text-xl font-bold text-moss">
                      Score: {quizScore} / {quizzes.length} Correct
                    </h3>
                    <p className="text-xs text-slate mt-0.5">
                      {quizScore === quizzes.length ? 'Perfect score! You mastered this scope.' : 'Great effort! Review the explanations above.'}
                    </p>
                  </div>
                  <button
                    onClick={() => {
                      setQuizSubmitted(false);
                      setUserAnswers({});
                    }}
                    className="text-xs font-mono font-medium border border-moss rounded-full px-4 py-2 bg-white text-moss hover:bg-moss hover:text-paper transition-colors"
                  >
                    Retake Quiz ↻
                  </button>
                </div>
              )}
            </div>
          )}
        </div>
      )}

      {/* ACTIVITY 2: FLASHCARDS MODE */}
      {!loading && !error && activityMode === 'FLASHCARDS' && (
        <div>
          {flashcards.length === 0 ? (
            <Empty
              title="No Flashcards Available in Selected Scope"
              hint={`No flashcards found for ${selectedSubject !== 'ALL' ? selectedSubject : 'current selection'}. Try selecting another unit or subject.`}
            />
          ) : (
            <div className="max-w-xl mx-auto space-y-4">
              <div className="flex items-center justify-between text-xs font-mono text-slate">
                <span>Card {cardIndex + 1} of {flashcards.length}</span>
                <span>{flashcards[cardIndex]?.categoryHint || 'Active Recall'}</span>
              </div>

              {/* 3D Flip Card */}
              <div
                onClick={() => setCardFlipped(!cardFlipped)}
                className="cursor-pointer min-h-[220px] border border-ink/15 rounded-2xl bg-white p-6 shadow-sm hover:border-moss transition-all flex flex-col justify-between"
              >
                <div>
                  <div className="flex items-center justify-between mb-3">
                    <span className="font-mono text-[10px] bg-clay/10 text-clay px-2 py-0.5 rounded uppercase">
                      {cardFlipped ? 'Answer (Back)' : 'Question (Front)'}
                    </span>
                    <span className="text-xs text-slate">Click to flip 🔄</span>
                  </div>

                  <h3 className="font-display text-lg text-ink font-medium leading-relaxed">
                    {cardFlipped ? flashcards[cardIndex]?.back : flashcards[cardIndex]?.front}
                  </h3>
                </div>

                <div className="pt-4 border-t border-ink/5 text-xs font-mono text-slate flex justify-between">
                  <span>Subject: {flashcards[cardIndex]?.subjectCode || selectedSubject}</span>
                  <span className="text-moss font-semibold">{cardFlipped ? 'Tap to view question' : 'Tap to reveal answer'}</span>
                </div>
              </div>

              {/* Self-Evaluation Buttons */}
              {cardFlipped && (
                <div className="grid grid-cols-3 gap-2 pt-2">
                  <button
                    onClick={() => handleCardMastery('HARD')}
                    className="p-2.5 rounded-xl border border-clay/30 bg-clay/10 text-clay font-mono text-xs font-semibold hover:bg-clay hover:text-paper transition-colors"
                  >
                    ❌ Hard
                  </button>
                  <button
                    onClick={() => handleCardMastery('REVIEW')}
                    className="p-2.5 rounded-xl border border-gold/30 bg-gold/10 text-ink font-mono text-xs font-semibold hover:bg-gold transition-colors"
                  >
                    🤔 Needs Review
                  </button>
                  <button
                    onClick={() => handleCardMastery('MASTERED')}
                    className="p-2.5 rounded-xl border border-moss/30 bg-moss/10 text-moss font-mono text-xs font-semibold hover:bg-moss hover:text-paper transition-colors"
                  >
                    ✅ Mastered
                  </button>
                </div>
              )}
            </div>
          )}
        </div>
      )}

      {/* ACTIVITY 3: QUICK REVISION MODE */}
      {!loading && !error && activityMode === 'REVISION' && (
        <div className="space-y-4">
          <div className="border border-ink/15 rounded-2xl bg-white p-6 space-y-4 shadow-sm">
            <h3 className="font-display text-xl text-ink">Quick Revision Summary ({selectedSubject !== 'ALL' ? selectedSubject : 'All Subjects'})</h3>
            <p className="text-xs text-slate">
              Essential bullet points, key terms, and visual explanations strictly for your selected scope.
            </p>

            <div className="space-y-4">
              {filteredTopics.length === 0 ? (
                <Empty title="No revision notes found" hint="Select another subject or unit to view quick revision notes." />
              ) : (
                filteredTopics.map((t, idx) => (
                  <div key={t.topicId || idx} className="border border-ink/10 rounded-xl p-4 bg-paper/30 space-y-3">
                    <div className="flex items-center justify-between border-b border-ink/5 pb-2">
                      <h4 className="font-display text-base text-ink">{t.title || t.name}</h4>
                      <span className="text-[10px] font-mono text-clay">{t.subjectCode}</span>
                    </div>

                    {t.overview && <p className="text-xs text-slate">{t.overview}</p>}

                    {t.keyPoints && t.keyPoints.length > 0 && (
                      <div>
                        <strong className="text-xs font-mono text-moss uppercase block mb-1">Key Revision Points:</strong>
                        <ul className="list-disc list-inside space-y-1 text-xs text-slate">
                          {t.keyPoints.map((kp, i) => (
                            <li key={i}>{kp}</li>
                          ))}
                        </ul>
                      </div>
                    )}

                    {t.importantTerms && Object.keys(t.importantTerms).length > 0 && (
                      <div className="pt-2">
                        <strong className="text-xs font-mono text-clay uppercase block mb-1">Important Terminology:</strong>
                        <div className="grid sm:grid-cols-2 gap-2 text-xs">
                          {Object.entries(t.importantTerms).map(([term, def], i) => (
                            <div key={i} className="p-2 rounded bg-white border border-ink/5">
                              <strong className="text-ink">{term}:</strong> <span className="text-slate">{def}</span>
                            </div>
                          ))}
                        </div>
                      </div>
                    )}
                  </div>
                ))
              )}
            </div>
          </div>
        </div>
      )}

      {/* ACTIVITY 4: PRACTICE QUESTIONS (EXAM & VIVA) */}
      {!loading && !error && activityMode === 'PRACTICE_QUESTIONS' && (
        <div className="space-y-4">
          <div className="border border-ink/15 rounded-2xl bg-white p-6 space-y-4 shadow-sm">
            <h3 className="font-display text-xl text-ink">University Exam & Viva Practice Questions</h3>
            <p className="text-xs text-slate">
              Sectional exam practice questions, viva defense outlines, and paper setter instructions.
            </p>

            <div className="space-y-4">
              <div className="border border-ink/10 rounded-xl p-4 bg-paper/50 space-y-2 text-xs font-mono">
                <h4 className="text-clay font-bold uppercase">Exam Paper Setter Guidelines:</h4>
                <p className="text-slate">
                  Section A: Compulsory short-answer questions (1.5 - 2 marks each). Sections B-E: Unit-wise analytical questions (7 - 12 marks each).
                </p>
              </div>

              {filteredTopics.map((t, idx) => (
                <div key={t.topicId || idx} className="border border-ink/10 rounded-xl p-4 bg-white space-y-2 text-xs">
                  <div className="flex items-center justify-between">
                    <strong className="font-display text-sm text-ink">{t.title}</strong>
                    <span className="font-mono text-[10px] text-moss">Practicals & Theory</span>
                  </div>
                  <p className="text-slate">1. Explain in detail the construction, procedure, and industrial application of {t.title}. (7 Marks)</p>
                  <p className="text-slate">2. List key safety precautions and troubleshooting steps for {t.title}. (3 Marks)</p>
                </div>
              ))}
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
