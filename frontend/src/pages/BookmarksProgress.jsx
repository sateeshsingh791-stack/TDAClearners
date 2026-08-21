import { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';
import { getSemesters } from '../api/client';
import { Loading, ErrorBlock } from '../components/StatusBlock';

export default function BookmarksProgress() {
  const [completedTopicIds, setCompletedTopicIds] = useState(() => {
    try {
      return JSON.parse(localStorage.getItem('tdac_completed_topics') || '[]');
    } catch {
      return [];
    }
  });

  const [bookmarkedTopicIds, setBookmarkedTopicIds] = useState(() => {
    try {
      return JSON.parse(localStorage.getItem('tdac_bookmarked_topics') || '[]');
    } catch {
      return [];
    }
  });

  const [semesters, setSemesters] = useState([]);
  const [allTopics, setAllTopics] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    getSemesters()
      .then((res) => {
        const semList = res.data.data?.semesters || res.data.semesters || (Array.isArray(res.data.data) ? res.data.data : []);
        setSemesters(semList);

        const topicsList = [];
        semList.forEach((sem) => {
          (sem.subjects || []).forEach((sub) => {
            (sub.units || []).forEach((u) => {
              (u.topics || []).forEach((t) => {
                topicsList.push({ ...t, subjectCode: sub.code, subjectName: sub.name, semesterNumber: sem.number });
              });
            });
          });
        });
        setAllTopics(topicsList);
      })
      .catch(() => setError('Could not load curriculum progress.'))
      .finally(() => setLoading(false));
  }, []);

  const totalTopicsCount = allTopics.length || 1;
  const completedCount = completedTopicIds.length;
  const progressPercent = Math.round((completedCount / totalTopicsCount) * 100);

  const bookmarkedTopics = allTopics.filter((t) => bookmarkedTopicIds.includes(t.topicId));

  const removeBookmark = (id) => {
    const updated = bookmarkedTopicIds.filter((bId) => bId !== id);
    setBookmarkedTopicIds(updated);
    localStorage.setItem('tdac_bookmarked_topics', JSON.stringify(updated));
  };

  return (
    <div className="space-y-8">
      {/* Header */}
      <div>
        <p className="font-mono text-xs text-clay uppercase tracking-widest mb-1">09 — Student Learning Dashboard</p>
        <h1 className="font-display text-3xl font-bold text-ink">Bookmarks & Learning Progress</h1>
        <p className="text-slate text-sm">
          Track your progress through the B.Voc Textile Design syllabus and access saved study topics (persisted locally).
        </p>
      </div>

      {loading && <Loading />}
      {error && <ErrorBlock message={error} />}

      {!loading && !error && (
        <>
          {/* Progress Summary Cards */}
          <div className="grid sm:grid-cols-3 gap-4">
            <div className="border border-moss/30 rounded-2xl bg-white p-5 shadow-xs space-y-2">
              <span className="font-mono text-xs text-moss font-semibold uppercase">Overall Completion</span>
              <div className="flex items-baseline justify-between">
                <h2 className="font-display text-3xl font-bold text-moss">{progressPercent}%</h2>
                <span className="font-mono text-xs text-slate">{completedCount} of {totalTopicsCount} Topics</span>
              </div>
              <div className="w-full bg-paper rounded-full h-2 overflow-hidden border border-ink/5">
                <div className="bg-moss h-full transition-all duration-500" style={{ width: `${progressPercent}%` }} />
              </div>
            </div>

            <div className="border border-clay/30 rounded-2xl bg-white p-5 shadow-xs space-y-2">
              <span className="font-mono text-xs text-clay font-semibold uppercase">Bookmarked Items</span>
              <div className="flex items-baseline justify-between">
                <h2 className="font-display text-3xl font-bold text-clay">{bookmarkedTopicIds.length}</h2>
                <span className="font-mono text-xs text-slate">Saved for Review</span>
              </div>
              <p className="text-[11px] text-slate font-mono">Quick reference study list</p>
            </div>

            <div className="border border-ink/15 rounded-2xl bg-white p-5 shadow-xs space-y-2">
              <span className="font-mono text-xs text-ink font-semibold uppercase">Active Scheme</span>
              <h2 className="font-display text-xl font-bold text-ink">1st Year (Sem 1 & 2)</h2>
              <p className="text-[11px] text-slate font-mono">16 Subjects • 54 Credits Implemented</p>
            </div>
          </div>

          {/* Bookmarked Topics List */}
          <div className="border border-ink/15 rounded-2xl bg-white p-6 space-y-4 shadow-sm">
            <div className="flex items-center justify-between border-b border-ink/10 pb-3">
              <h3 className="font-display text-xl font-bold text-ink">Bookmarked Topics ({bookmarkedTopics.length})</h3>
              <span className="font-mono text-xs text-slate">Local Memory Persistence</span>
            </div>

            {bookmarkedTopics.length === 0 ? (
              <p className="text-xs text-slate font-mono py-4 text-center">
                No topics bookmarked yet. Click the star icon (★) on any topic page to save it here for fast review.
              </p>
            ) : (
              <div className="grid sm:grid-cols-2 gap-3">
                {bookmarkedTopics.map((t) => (
                  <div key={t.topicId} className="border border-ink/10 rounded-xl p-4 bg-paper/30 space-y-2 flex flex-col justify-between">
                    <div>
                      <div className="flex items-center justify-between font-mono text-[10px] text-clay">
                        <span>{t.subjectCode} • Sem {t.semesterNumber}</span>
                        <button onClick={() => removeBookmark(t.topicId)} className="hover:underline text-clay">
                          Remove ✕
                        </button>
                      </div>
                      <h4 className="font-display font-semibold text-sm text-ink mt-1">{t.title}</h4>
                      {t.overview && <p className="text-xs text-slate line-clamp-2 mt-0.5">{t.overview}</p>}
                    </div>

                    <NavLink
                      to={`/topics/${t.topicId}`}
                      className="text-xs font-mono text-moss font-semibold pt-2 border-t border-ink/5 flex items-center justify-between hover:underline"
                    >
                      <span>Study Notes</span>
                      <span>→</span>
                    </NavLink>
                  </div>
                ))}
              </div>
            )}
          </div>
        </>
      )}
    </div>
  );
}
