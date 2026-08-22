import { useEffect, useState, useMemo } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { getSemesters, getScopedQuizzes, getScopedFlashcards, recordQuizAttempt, updateFlashcardMastery } from '../api/client';
import { useAuth } from '../context/AuthContext';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function PracticeStudio() {
  const { user } = useAuth();
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();

  // Scope selection state from URL or defaults
  const [selectedSemester, setSelectedSemester] = useState(searchParams.get('semester') || 'ALL');
  const [selectedSubject, setSelectedSubject] = useState(searchParams.get('subjectCode') || 'ALL');
  const [selectedUnit, setSelectedUnit] = useState(searchParams.get('unitNumber') || 'ALL');
  const [selectedTopic, setSelectedTopic] = useState(searchParams.get('topicId') || 'ALL');
  const [activityMode, setActivityMode] = useState(searchParams.get('activity') || 'QUIZ'); // QUIZ | FLASHCARDS | REVISION | PRACTICE_QUESTIONS | EXAM_PREP

  // Curriculum tree loaded from API
  const [semesters, setSemesters] = useState([]);
  const [loadingCurriculum, setLoadingCurriculum] = useState(true);
  const [curriculumError, setCurriculumError] = useState('');

  // Activity content state
  const [apiQuizzes, setApiQuizzes] = useState([]);
  const [apiFlashcards, setApiFlashcards] = useState([]);
  const [loadingActivity, setLoadingActivity] = useState(false);
  const [activityError, setActivityError] = useState('');

  // Quiz execution state
  const [userAnswers, setUserAnswers] = useState({});
  const [quizSubmitted, setQuizSubmitted] = useState(false);
  const [quizScore, setQuizScore] = useState(0);

  // Flashcard execution state
  const [cardIndex, setCardIndex] = useState(0);
  const [cardFlipped, setCardFlipped] = useState(false);

  // 1. Fetch initial curriculum structure (semesters -> subjects -> units -> topics)
  useEffect(() => {
    setLoadingCurriculum(true);
    getSemesters()
      .then((res) => {
        const semList = res.data.data?.semesters || res.data.semesters || (Array.isArray(res.data.data) ? res.data.data : []);
        setSemesters(semList);
      })
      .catch(() => setCurriculumError('Could not load academic curriculum structure.'))
      .finally(() => setLoadingCurriculum(false));
  }, []);

  // 2. Derive available subjects based on selectedSemester
  const availableSubjects = useMemo(() => {
    const list = [];
    semesters.forEach((sem) => {
      if (sem.status === 'AVAILABLE' || sem.number === 1 || sem.number === 2) {
        if (selectedSemester === 'ALL' || String(sem.number) === String(selectedSemester)) {
          (sem.subjects || []).forEach((sub) => {
            list.push({ ...sub, semesterNumber: sem.number });
          });
        }
      }
    });
    return list;
  }, [semesters, selectedSemester]);

  // 3. Find selected subject object
  const selectedSubjectObj = useMemo(() => {
    if (selectedSubject === 'ALL') return null;
    return availableSubjects.find((s) => s.code.toUpperCase() === selectedSubject.toUpperCase()) || null;
  }, [availableSubjects, selectedSubject]);

  // 4. Derive available units for selectedSubjectObj (reads syllabus unit names dynamically)
  const availableUnits = useMemo(() => {
    if (!selectedSubjectObj || !selectedSubjectObj.units) return [];
    return selectedSubjectObj.units;
  }, [selectedSubjectObj]);

  // 5. Find selected unit object
  const selectedUnitObj = useMemo(() => {
    if (selectedUnit === 'ALL' || !availableUnits.length) return null;
    return availableUnits.find((u) => String(u.unitNumber) === String(selectedUnit)) || null;
  }, [availableUnits, selectedUnit]);

  // 6. Derive available topics based on selectedSubjectObj and selectedUnitObj
  const availableTopics = useMemo(() => {
    if (!selectedSubjectObj) {
      // If no subject selected, return all available topics across active subjects
      const allT = [];
      availableSubjects.forEach((sub) => {
        (sub.units || []).forEach((u) => {
          (u.topics || []).forEach((t) => {
            allT.push({ ...t, subjectCode: sub.code, subjectName: sub.name, unitNumber: u.unitNumber, unitTitle: u.title });
          });
        });
      });
      return allT;
    }

    if (selectedUnitObj) {
      return (selectedUnitObj.topics || []).map((t) => ({
        ...t,
        subjectCode: selectedSubjectObj.code,
        subjectName: selectedSubjectObj.name,
        unitNumber: selectedUnitObj.unitNumber,
        unitTitle: selectedUnitObj.title
      }));
    }

    // If subject is selected but Unit is ALL, return all topics from all units of selectedSubjectObj
    const allT = [];
    (selectedSubjectObj.units || []).forEach((u) => {
      (u.topics || []).forEach((t) => {
        allT.push({
          ...t,
          subjectCode: selectedSubjectObj.code,
          subjectName: selectedSubjectObj.name,
          unitNumber: u.unitNumber,
          unitTitle: u.title
        });
      });
    });
    return allT;
  }, [availableSubjects, selectedSubjectObj, selectedUnitObj]);

  // 7. Find selected topic object
  const selectedTopicObj = useMemo(() => {
    if (selectedTopic === 'ALL' || !availableTopics.length) return null;
    return availableTopics.find((t) => String(t.topicId) === String(selectedTopic)) || null;
  }, [availableTopics, selectedTopic]);

  // 8. Scope-filtered topics (Level 1: Subject, Level 2: Subject+Unit, Level 3: Subject+Unit+Topic)
  const scopedTopics = useMemo(() => {
    if (selectedTopicObj) return [selectedTopicObj];
    return availableTopics;
  }, [selectedTopicObj, availableTopics]);

  // 9. Sync state changes to URL search params
  useEffect(() => {
    const params = {};
    if (selectedSemester !== 'ALL') params.semester = selectedSemester;
    if (selectedSubject !== 'ALL') params.subjectCode = selectedSubject;
    if (selectedUnit !== 'ALL') params.unitNumber = selectedUnit;
    if (selectedTopic !== 'ALL') params.topicId = selectedTopic;
    params.activity = activityMode;
    setSearchParams(params, { replace: true });
  }, [selectedSemester, selectedSubject, selectedUnit, selectedTopic, activityMode]);

  // 10. Cascading Selection Reset Handlers
  const handleSemesterChange = (newSem) => {
    setSelectedSemester(newSem);

    // Compute newly available subjects
    const newAvailSubjects = [];
    semesters.forEach((sem) => {
      if (sem.status === 'AVAILABLE' || sem.number === 1 || sem.number === 2) {
        if (newSem === 'ALL' || String(sem.number) === String(newSem)) {
          (sem.subjects || []).forEach((sub) => newAvailSubjects.push(sub));
        }
      }
    });

    const isSubjectValid = newAvailSubjects.some((s) => s.code.toUpperCase() === selectedSubject.toUpperCase());
    if (!isSubjectValid) {
      setSelectedSubject('ALL');
      setSelectedUnit('ALL');
      setSelectedTopic('ALL');
    }
  };

  const handleSubjectChange = (newSubj) => {
    setSelectedSubject(newSubj);
    setSelectedUnit('ALL');
    setSelectedTopic('ALL');
  };

  const handleUnitChange = (newUnit) => {
    setSelectedUnit(newUnit);
    setSelectedTopic('ALL');
  };

  const handleTopicChange = (newTopic) => {
    setSelectedTopic(newTopic);
  };

  // 11. Fetch activity content from API when scope or mode changes
  useEffect(() => {
    if (loadingCurriculum) return;
    setLoadingActivity(true);
    setActivityError('');
    setQuizSubmitted(false);
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
          setApiQuizzes(list);
        })
        .catch(() => setActivityError('Could not fetch quiz questions.'))
        .finally(() => setLoadingActivity(false));
    } else if (activityMode === 'FLASHCARDS') {
      getScopedFlashcards(queryParams)
        .then((res) => {
          const list = res.data.data?.flashcards || res.data.flashcards || (Array.isArray(res.data.data) ? res.data.data : []);
          setApiFlashcards(list);
        })
        .catch(() => setActivityError('Could not fetch flashcards.'))
        .finally(() => setLoadingActivity(false));
    } else {
      setLoadingActivity(false);
    }
  }, [selectedSubject, selectedUnit, selectedTopic, activityMode, loadingCurriculum]);

  // 12. Dynamic Scope-Grounded Quiz Questions Fallback Generator
  const activeQuizQuestions = useMemo(() => {
    if (apiQuizzes && apiQuizzes.length > 0) {
      return apiQuizzes;
    }

    // Generate fallback MCQs from scopedTopics if API has 0 pre-stored questions
    const generated = [];
    scopedTopics.forEach((t, i) => {
      if (t.importantTerms && Object.keys(t.importantTerms).length > 0) {
        Object.entries(t.importantTerms).forEach(([term, def], termIdx) => {
          generated.push({
            questionId: `gen_q_${t.topicId}_${termIdx}`,
            subjectCode: t.subjectCode || selectedSubject,
            unitNumber: t.unitNumber || selectedUnit,
            topicId: t.topicId,
            question: `In ${t.subjectCode} (${t.title}), what is defined as: "${def}"?`,
            options: [
              term,
              `Secondary ${term} Process`,
              `Auxiliary ${term} Standard`,
              `Non-standard ${term} Allowance`
            ].sort(() => 0.5 - Math.random()),
            correctAnswer: term,
            explanation: `${term} is defined as: "${def}" under ${t.title} (${t.subjectCode}).`,
            difficulty: 'MEDIUM'
          });
        });
      }

      if (t.keyPoints && t.keyPoints.length > 0) {
        t.keyPoints.slice(0, 2).forEach((kp, kpIdx) => {
          generated.push({
            questionId: `gen_kp_${t.topicId}_${kpIdx}`,
            subjectCode: t.subjectCode || selectedSubject,
            unitNumber: t.unitNumber || selectedUnit,
            topicId: t.topicId,
            question: `Which statement accurately describes ${t.title}?`,
            options: [
              kp,
              `Incorrect assertion regarding ${t.title} workflow.`,
              `Unrelated parameter not applicable to ${t.subjectCode}.`,
              `Obsolete textile specification replaced in GNDU NEP scheme.`
            ].sort(() => 0.5 - Math.random()),
            correctAnswer: kp,
            explanation: `According to official syllabus notes: ${kp}`,
            difficulty: 'EASY'
          });
        });
      }
    });

    return generated;
  }, [apiQuizzes, scopedTopics, selectedSubject, selectedUnit]);

  // 13. Dynamic Scope-Grounded Flashcards Fallback Generator
  const activeFlashcards = useMemo(() => {
    if (apiFlashcards && apiFlashcards.length > 0) {
      return apiFlashcards;
    }

    const generated = [];
    scopedTopics.forEach((t) => {
      if (t.importantTerms) {
        Object.entries(t.importantTerms).forEach(([term, def], idx) => {
          generated.push({
            cardId: `gen_fc_${t.topicId}_${idx}`,
            subjectCode: t.subjectCode || selectedSubject,
            unitNumber: t.unitNumber || selectedUnit,
            topicId: t.topicId,
            type: 'DEFINITION',
            front: `What is ${term} in ${t.subjectCode}?`,
            back: `${def}`,
            categoryHint: `${t.subjectCode} • Unit ${t.unitNumber || 1}`
          });
        });
      }
    });

    return generated;
  }, [apiFlashcards, scopedTopics, selectedSubject, selectedUnit]);

  const handleQuizSubmit = async () => {
    let score = 0;
    activeQuizQuestions.forEach((q, i) => {
      const picked = userAnswers[i];
      if (picked !== undefined) {
        const pickedVal = typeof picked === 'number' ? q.options[picked] : picked;
        if (pickedVal === q.correctAnswer || picked === q.correctIndex || picked === q.answer) {
          score += 1;
        }
      }
    });
    setQuizScore(score);
    setQuizSubmitted(true);

    if (user && activeQuizQuestions[0]) {
      try {
        await recordQuizAttempt({
          quizId: activeQuizQuestions[0].questionId || String(Date.now()),
          score,
          total: activeQuizQuestions.length
        });
      } catch (e) {
        /* non-blocking */
      }
    }
  };

  const handleCardMastery = async (masteryLevel) => {
    const card = activeFlashcards[cardIndex];
    if (user && card) {
      try {
        await updateFlashcardMastery({ flashcardId: card.cardId || card._id, mastery: masteryLevel });
      } catch (e) {
        /* non-blocking */
      }
    }
    setCardFlipped(false);
    if (cardIndex < activeFlashcards.length - 1) {
      setCardIndex((idx) => idx + 1);
    }
  };

  const shuffleCards = () => {
    setCardFlipped(false);
    setCardIndex(0);
  };

  if (loadingCurriculum) return <Loading />;
  if (curriculumError) return <ErrorBlock message={curriculumError} />;

  return (
    <div className="space-y-6">
      {/* Page Header */}
      <div>
        <p className="font-mono text-xs text-clay uppercase tracking-widest mb-1">04 — Practice Studio</p>
        <h1 className="font-display text-3xl font-bold text-ink">Dynamic Academic Practice Studio</h1>
        <p className="text-slate text-sm">
          Filter study activities dynamically by <strong>Semester</strong>, <strong>Subject</strong>, <strong>Unit</strong>, or <strong>Topic</strong>.
        </p>
      </div>

      {/* Scope Selection Control Panel */}
      <div className="border border-ink/15 rounded-2xl bg-white p-5 shadow-xs space-y-4">
        <div className="flex items-center justify-between border-b border-ink/10 pb-3">
          <h2 className="font-mono text-xs font-semibold text-clay uppercase tracking-wider flex items-center gap-1.5">
            <span>🎯 Cascading Scope Controls</span>
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
            Reset Scope
          </button>
        </div>

        {/* 4 Cascading Dropdowns */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-3">
          
          {/* 1. Semester Selector */}
          <div>
            <label className="block text-[11px] font-mono text-slate mb-1">Semester</label>
            <select
              value={selectedSemester}
              onChange={(e) => handleSemesterChange(e.target.value)}
              className="w-full border border-ink/15 rounded-xl px-3 py-2 text-xs font-medium bg-paper/50 focus-ring"
            >
              <option value="ALL">All Semesters (Sem 1 & 2)</option>
              <option value="1">Semester 1 (Autumn Term)</option>
              <option value="2">Semester 2 (Spring Term)</option>
            </select>
          </div>

          {/* 2. Subject Selector */}
          <div>
            <label className="block text-[11px] font-mono text-slate mb-1">Subject</label>
            <select
              value={selectedSubject}
              onChange={(e) => handleSubjectChange(e.target.value)}
              className="w-full border border-ink/15 rounded-xl px-3 py-2 text-xs font-medium bg-paper/50 focus-ring font-semibold text-moss"
            >
              <option value="ALL">All Subjects</option>
              {availableSubjects.map((s) => (
                <option key={s.code} value={s.code}>
                  {s.code} — {s.name}
                </option>
              ))}
            </select>
          </div>

          {/* 3. Unit Selector (Reads Syllabus Unit Names Dynamically) */}
          <div>
            <label className="block text-[11px] font-mono text-slate mb-1">Unit / Section</label>
            <select
              value={selectedUnit}
              onChange={(e) => handleUnitChange(e.target.value)}
              disabled={selectedSubject === 'ALL'}
              className="w-full border border-ink/15 rounded-xl px-3 py-2 text-xs font-medium bg-paper/50 focus-ring disabled:opacity-50"
            >
              <option value="ALL">
                {selectedSubject === 'ALL' ? 'All Units (Select Subject First)' : 'All Units in Subject'}
              </option>
              {availableUnits.map((u) => (
                <option key={u.unitNumber} value={u.unitNumber}>
                  {u.title || `Unit ${u.unitNumber}`}
                </option>
              ))}
            </select>
          </div>

          {/* 4. Topic Selector */}
          <div>
            <label className="block text-[11px] font-mono text-slate mb-1">Topic</label>
            <select
              value={selectedTopic}
              onChange={(e) => handleTopicChange(e.target.value)}
              disabled={selectedSubject === 'ALL'}
              className="w-full border border-ink/15 rounded-xl px-3 py-2 text-xs font-medium bg-paper/50 focus-ring disabled:opacity-50"
            >
              <option value="ALL">
                {selectedSubject === 'ALL' ? 'All Topics (Select Subject First)' : 'All Topics in Scope'}
              </option>
              {availableTopics.map((t) => (
                <option key={t.topicId} value={t.topicId}>
                  {t.title || t.name}
                </option>
              ))}
            </select>
          </div>
        </div>

        {/* Active Scope Summary Banner */}
        <div className="bg-paper/80 border border-ink/10 rounded-xl px-4 py-2.5 flex flex-wrap items-center justify-between text-xs font-mono">
          <div className="flex items-center gap-2 truncate">
            <span className="text-moss font-semibold">Active Scope Level:</span>
            <span className="bg-moss/10 text-moss px-2.5 py-0.5 rounded font-bold">
              {selectedSubjectObj ? selectedSubjectObj.code : 'All Subjects'}
            </span>
            {selectedUnitObj && (
              <span className="bg-clay/10 text-clay px-2.5 py-0.5 rounded truncate max-w-xs">
                {selectedUnitObj.title}
              </span>
            )}
            {selectedTopicObj && (
              <span className="text-ink font-bold truncate max-w-xs">
                › {selectedTopicObj.title}
              </span>
            )}
          </div>
          <span className="text-slate text-[11px] shrink-0">
            Level {selectedTopicObj ? '3 (Topic)' : selectedUnitObj ? '2 (Unit)' : selectedSubjectObj ? '1 (Subject)' : '0 (General)'}
          </span>
        </div>
      </div>

      {/* Activity Mode Selector Tabs */}
      <div className="grid grid-cols-2 sm:grid-cols-5 gap-2.5">
        {[
          { mode: 'QUIZ', label: '🎯 MCQ Quiz', desc: 'Instant feedback' },
          { mode: 'FLASHCARDS', label: '🎴 Flashcards', desc: '3D active recall' },
          { mode: 'REVISION', label: '⚡ Quick Revision', desc: 'High-yield notes' },
          { mode: 'PRACTICE_QUESTIONS', label: '📝 Practice Exam', desc: 'Model Q&A' },
          { mode: 'EXAM_PREP', label: '🎓 Exam Prep', desc: 'Paper setter rules' }
        ].map((item) => (
          <button
            key={item.mode}
            onClick={() => setActivityMode(item.mode)}
            className={`p-3 rounded-xl border text-left transition-all ${
              activityMode === item.mode
                ? 'border-moss bg-moss text-paper shadow-sm font-semibold'
                : 'border-ink/15 bg-white text-ink hover:border-moss'
            }`}
          >
            <div className="text-xs font-bold font-mono">{item.label}</div>
            <div className="text-[10px] opacity-80 mt-0.5">{item.desc}</div>
          </button>
        ))}
      </div>

      {loadingActivity && <Loading />}
      {activityError && <ErrorBlock message={activityError} />}

      {/* ACTIVITY MODE 1: MCQ QUIZ */}
      {!loadingActivity && !activityError && activityMode === 'QUIZ' && (
        <div>
          {activeQuizQuestions.length === 0 ? (
            <Empty
              title="No Quiz Content Available for This Selection"
              hint={
                selectedSubject === 'ALL'
                  ? 'Please select a subject to start a targeted quiz.'
                  : `No quiz questions found for ${selectedSubject}${selectedUnit !== 'ALL' ? ` • Unit ${selectedUnit}` : ''}. Try selecting another unit or subject.`
              }
            />
          ) : (
            <div className="border border-ink/15 rounded-2xl bg-white p-6 space-y-6 shadow-sm">
              <div className="flex items-center justify-between border-b border-ink/10 pb-3 font-mono text-xs">
                <span className="text-moss font-semibold">
                  Targeted MCQ Quiz ({activeQuizQuestions.length} Questions)
                </span>
                <span className="text-slate">
                  {selectedSubjectObj ? `${selectedSubjectObj.code} ${selectedUnitObj ? `(${selectedUnitObj.title})` : ''}` : 'Syllabus Quiz'}
                </span>
              </div>

              <div className="space-y-6">
                {activeQuizQuestions.map((q, idx) => (
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
                        const isCorrectOpt = quizSubmitted && (opt === q.correctAnswer || optIdx === q.correctIndex);
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
                      Score: {quizScore} / {activeQuizQuestions.length} Correct
                    </h3>
                    <p className="text-xs text-slate mt-0.5">
                      {quizScore === activeQuizQuestions.length ? 'Perfect score! You mastered this scope.' : 'Great effort! Review explanations above.'}
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

      {/* ACTIVITY MODE 2: FLASHCARDS */}
      {!loadingActivity && !activityError && activityMode === 'FLASHCARDS' && (
        <div>
          {activeFlashcards.length === 0 ? (
            <Empty
              title="No Flashcards Available for This Selection"
              hint={
                selectedSubject === 'ALL'
                  ? 'Please select a subject to start active recall flashcard drills.'
                  : `No flashcards found for ${selectedSubject}. Try selecting another unit or topic.`
              }
            />
          ) : (
            <div className="max-w-xl mx-auto space-y-4">
              <div className="flex items-center justify-between text-xs font-mono text-slate">
                <span>Card {cardIndex + 1} of {activeFlashcards.length}</span>
                <span>{activeFlashcards[cardIndex]?.categoryHint || 'Active Recall'}</span>
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
                    {cardFlipped ? activeFlashcards[cardIndex]?.back : activeFlashcards[cardIndex]?.front}
                  </h3>
                </div>

                <div className="pt-4 border-t border-ink/5 text-xs font-mono text-slate flex justify-between">
                  <span>{activeFlashcards[cardIndex]?.subjectCode || selectedSubject}</span>
                  <span className="text-moss font-semibold">{cardFlipped ? 'Tap to view question' : 'Tap to reveal answer'}</span>
                </div>
              </div>

              {/* Card Navigation Controls */}
              <div className="flex items-center justify-between text-xs font-mono pt-1">
                <button
                  disabled={cardIndex === 0}
                  onClick={() => {
                    setCardFlipped(false);
                    setCardIndex((i) => Math.max(0, i - 1));
                  }}
                  className="border border-ink/15 rounded-full px-4 py-2 bg-white hover:bg-paper disabled:opacity-40"
                >
                  ← Previous
                </button>

                <button
                  onClick={shuffleCards}
                  className="border border-ink/15 rounded-full px-3 py-2 bg-white text-slate hover:text-ink"
                >
                  Shuffle 🔀
                </button>

                <button
                  disabled={cardIndex === activeFlashcards.length - 1}
                  onClick={() => {
                    setCardFlipped(false);
                    setCardIndex((i) => Math.min(activeFlashcards.length - 1, i + 1));
                  }}
                  className="border border-ink/15 rounded-full px-4 py-2 bg-white hover:bg-paper disabled:opacity-40"
                >
                  Next →
                </button>
              </div>

              {/* Self-Evaluation Mastery Buttons */}
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

      {/* ACTIVITY MODE 3: QUICK REVISION */}
      {!loadingActivity && !activityError && activityMode === 'REVISION' && (
        <div className="space-y-4">
          <div className="border border-ink/15 rounded-2xl bg-white p-6 space-y-4 shadow-sm">
            <div className="flex items-center justify-between border-b border-ink/10 pb-3 font-mono text-xs">
              <h3 className="font-display text-xl text-ink font-semibold">
                Quick Revision Summary
              </h3>
              <span className="text-moss font-bold">
                {selectedSubjectObj ? selectedSubjectObj.code : 'All Subjects'}
              </span>
            </div>

            {scopedTopics.length === 0 ? (
              <Empty
                title="No Quick Revision Content Available"
                hint={
                  selectedSubject === 'ALL'
                    ? 'Select a subject to load revision summaries.'
                    : 'No topics found for this selection.'
                }
              />
            ) : (
              <div className="space-y-4">
                {scopedTopics.map((t, idx) => (
                  <div key={t.topicId || idx} className="border border-ink/10 rounded-xl p-4 bg-paper/30 space-y-3">
                    <div className="flex items-center justify-between border-b border-ink/5 pb-2">
                      <h4 className="font-display text-base text-ink font-semibold">{t.title || t.name}</h4>
                      <span className="text-[10px] font-mono text-clay">{t.subjectCode} • Unit {t.unitNumber || 1}</span>
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
                        <strong className="text-xs font-mono text-clay uppercase block mb-1">Academic Terminology:</strong>
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
                ))}
              </div>
            )}
          </div>
        </div>
      )}

      {/* ACTIVITY MODE 4: PRACTICE EXAM QUESTIONS */}
      {!loadingActivity && !activityError && activityMode === 'PRACTICE_QUESTIONS' && (
        <div className="space-y-4">
          <div className="border border-ink/15 rounded-2xl bg-white p-6 space-y-4 shadow-sm">
            <div className="flex items-center justify-between border-b border-ink/10 pb-3 font-mono text-xs">
              <h3 className="font-display text-xl text-ink font-semibold">University Exam & Viva Practice Questions</h3>
              <span className="text-clay font-bold">{selectedSubjectObj ? selectedSubjectObj.code : 'Syllabus Scoped'}</span>
            </div>

            {scopedTopics.length === 0 ? (
              <Empty
                title="No Practice Questions Available"
                hint={
                  selectedSubject === 'ALL'
                    ? 'Select a subject to view exam practice questions.'
                    : 'No topics found for this selection.'
                }
              />
            ) : (
              <div className="space-y-4">
                {scopedTopics.map((t, idx) => (
                  <div key={t.topicId || idx} className="border border-ink/10 rounded-xl p-4 bg-white space-y-3 text-xs">
                    <div className="flex items-center justify-between border-b border-ink/5 pb-2">
                      <strong className="font-display text-sm text-ink">{t.title}</strong>
                      <span className="font-mono text-[10px] text-moss">{t.subjectCode} • Unit {t.unitNumber || 1}</span>
                    </div>

                    <div className="space-y-2">
                      <div className="p-3 rounded-lg bg-paper/50 border border-ink/5">
                        <p className="font-semibold text-ink">Q1 (Short Answer — 2 Marks):</p>
                        <p className="text-slate mt-0.5">Define the key principles of {t.title} and state its application in apparel manufacturing.</p>
                      </div>

                      <div className="p-3 rounded-lg bg-paper/50 border border-ink/5">
                        <p className="font-semibold text-ink">Q2 (Analytical — 7 Marks):</p>
                        <p className="text-slate mt-0.5">Explain in detail the step-by-step procedure, equipment requirements, and industrial relevance of {t.title}.</p>
                      </div>

                      <div className="p-3 rounded-lg bg-paper/50 border border-ink/5">
                        <p className="font-semibold text-ink">Q3 (Viva Voce Defense):</p>
                        <p className="text-slate mt-0.5">What precautions and troubleshooting steps must be observed during {t.title}?</p>
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>
      )}

      {/* ACTIVITY MODE 5: EXAM PREPARATION */}
      {!loadingActivity && !activityError && activityMode === 'EXAM_PREP' && (
        <div className="space-y-4">
          <div className="border border-ink/15 rounded-2xl bg-white p-6 space-y-4 shadow-sm">
            <div className="flex items-center justify-between border-b border-ink/10 pb-3 font-mono text-xs">
              <h3 className="font-display text-xl text-ink font-semibold">Scope-Specific Exam Preparation</h3>
              <span className="text-moss font-bold">{selectedSubjectObj ? selectedSubjectObj.code : 'Syllabus Prep'}</span>
            </div>

            {selectedSubjectObj ? (
              <div className="space-y-4 text-xs font-mono">
                {/* 1. Syllabus Focus */}
                <div className="p-4 rounded-xl bg-paper/60 border border-ink/10 space-y-2">
                  <h4 className="text-moss font-bold uppercase">1. SYLLABUS FOCUS (Official Paper Setter Rules):</h4>
                  <p className="text-ink leading-relaxed">
                    {selectedSubjectObj.instructionsForPaperSetters ||
                      'The question paper consists of five sections: Section A (compulsory short questions) and Sections B, C, D, E corresponding to Units I to IV.'}
                  </p>
                </div>

                {/* 2. AI Study Recommendation */}
                <div className="p-4 rounded-xl bg-moss/5 border border-moss/20 space-y-2">
                  <h4 className="text-moss font-bold uppercase">2. AI Study Recommendation — Based on Syllabus:</h4>
                  <ul className="list-disc list-inside text-slate space-y-1">
                    {scopedTopics.map((t, idx) => (
                      <li key={idx}>Prioritize high-yield notes for <strong>{t.title}</strong> (Unit {t.unitNumber || 1}).</li>
                    ))}
                  </ul>
                </div>

                {/* 3. Past Paper Trend Disclaimer */}
                <div className="p-4 rounded-xl bg-paper/40 border border-ink/10 space-y-1 text-slate">
                  <h4 className="text-clay font-bold uppercase">3. PAST PAPER TREND:</h4>
                  <p>Past-paper analysis will be available when verified question papers are added.</p>
                </div>
              </div>
            ) : (
              <Empty
                title="Select a Subject for Exam Preparation"
                hint="Please select a specific subject from the controls above to view paper setter rules and exam preparation checklists."
              />
            )}
          </div>
        </div>
      )}
    </div>
  );
}
