import { useEffect, useState } from 'react';
import { useParams, NavLink } from 'react-router-dom';
import { getTopicById } from '../api/client';
import { Loading, ErrorBlock } from '../components/StatusBlock';

export default function TopicLearning() {
  const { topicId } = useParams();
  const [topic, setTopic] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const [done, setDone] = useState(() => {
    try {
      const list = JSON.parse(localStorage.getItem('tdac_completed_topics') || '[]');
      return list.includes(topicId);
    } catch {
      return false;
    }
  });

  const [bookmarked, setBookmarked] = useState(() => {
    try {
      const list = JSON.parse(localStorage.getItem('tdac_bookmarked_topics') || '[]');
      return list.includes(topicId);
    } catch {
      return false;
    }
  });

  useEffect(() => {
    setLoading(true);
    getTopicById(topicId)
      .then((res) => {
        const t = res.data.data?.topic || res.data.topic || res.data.data || res.data;
        setTopic(t);
      })
      .catch(() => setError('Could not load this topic.'))
      .finally(() => setLoading(false));
  }, [topicId]);

  const toggleComplete = () => {
    try {
      const list = JSON.parse(localStorage.getItem('tdac_completed_topics') || '[]');
      let updated;
      if (list.includes(topicId)) {
        updated = list.filter((id) => id !== topicId);
        setDone(false);
      } else {
        updated = [...list, topicId];
        setDone(true);
      }
      localStorage.setItem('tdac_completed_topics', JSON.stringify(updated));
    } catch (e) {
      /* non-blocking */
    }
  };

  const toggleBookmarkAction = () => {
    try {
      const list = JSON.parse(localStorage.getItem('tdac_bookmarked_topics') || '[]');
      let updated;
      if (list.includes(topicId)) {
        updated = list.filter((id) => id !== topicId);
        setBookmarked(false);
      } else {
        updated = [...list, topicId];
        setBookmarked(true);
      }
      localStorage.setItem('tdac_bookmarked_topics', JSON.stringify(updated));
    } catch (e) {
      /* non-blocking */
    }
  };

  if (loading) return <Loading />;
  if (error) return <ErrorBlock message={error} />;
  if (!topic) return null;

  return (
    <div className="max-w-4xl space-y-6">
      <NavLink to="/syllabus" className="text-xs font-mono text-slate hover:text-ink flex items-center gap-1">
        ← Back to Syllabus Scheme
      </NavLink>

      {/* Topic Header Card */}
      <div className="border border-ink/15 rounded-2xl bg-white p-6 shadow-xs space-y-4">
        <div className="flex items-start justify-between gap-4">
          <div>
            <div className="flex items-center gap-2 font-mono text-[11px] mb-1">
              <span className="bg-moss/10 text-moss px-2 py-0.5 rounded font-bold">{topic.subjectCode}</span>
              <span className="text-slate">Unit {topic.unitNumber} • {topic.unitTitle}</span>
            </div>
            <h1 className="font-display text-3xl font-bold text-ink">{topic.title || topic.name}</h1>
          </div>

          <div className="flex gap-2 shrink-0">
            <button
              onClick={toggleBookmarkAction}
              title={bookmarked ? 'Remove bookmark' : 'Bookmark topic'}
              className={`w-9 h-9 rounded-full border border-ink/15 flex items-center justify-center text-sm focus-ring transition-all ${
                bookmarked ? 'bg-gold text-ink font-bold shadow-xs' : 'bg-white text-slate hover:bg-paper'
              }`}
            >
              ★
            </button>
            <button
              onClick={toggleComplete}
              className={`text-xs font-mono font-medium rounded-full px-4 py-2 border border-ink/15 focus-ring transition-all ${
                done ? 'bg-moss text-paper font-semibold shadow-xs' : 'bg-white text-ink hover:bg-paper'
              }`}
            >
              {done ? 'Completed ✓' : 'Mark Complete'}
            </button>
          </div>
        </div>

        {/* AI Tutor Quick Action */}
        <div>
          <NavLink
            to="/ai-tutor"
            state={{
              academicContext: {
                course: 'B.Voc. Textile Design & Apparel Technology',
                yearNumber: Math.ceil((topic.semesterNumber || 1) / 2),
                semesterNumber: topic.semesterNumber || 1,
                subjectCode: topic.subjectCode,
                unitTitle: topic.unitTitle,
                topicTitle: topic.title || topic.name
              }
            }}
            className="text-xs font-mono font-medium rounded-full px-3.5 py-1.5 border border-moss/30 bg-moss/10 text-moss hover:bg-moss hover:text-paper transition-all focus-ring inline-flex items-center gap-1.5"
          >
            🤖 Ask AI Tutor about this topic →
          </NavLink>
        </div>
      </div>

      {/* High-Yield Study Notes */}
      <div className="border border-ink/15 rounded-2xl bg-white p-6 shadow-xs space-y-5 text-sm text-ink leading-relaxed">
        {topic.overview && (
          <div className="p-4 rounded-xl bg-paper/50 border border-ink/5 space-y-1">
            <h3 className="font-mono text-xs font-bold text-clay uppercase">Topic Overview & Context:</h3>
            <p className="text-slate">{topic.overview}</p>
          </div>
        )}

        {topic.keyPoints && topic.keyPoints.length > 0 && (
          <div className="space-y-2">
            <h3 className="font-mono text-xs font-bold text-moss uppercase">Key Academic Concepts:</h3>
            <ul className="list-disc list-inside space-y-1 text-slate bg-moss/5 p-4 rounded-xl border border-moss/10">
              {topic.keyPoints.map((kp, i) => (
                <li key={i} className="leading-relaxed">{kp}</li>
              ))}
            </ul>
          </div>
        )}

        {topic.importantTerms && Object.keys(topic.importantTerms).length > 0 && (
          <div className="space-y-2">
            <h3 className="font-mono text-xs font-bold text-clay uppercase">Academic Terminology & Definitions:</h3>
            <div className="grid sm:grid-cols-2 gap-3 text-xs">
              {Object.entries(topic.importantTerms).map(([term, def], i) => (
                <div key={i} className="p-3 rounded-xl border border-ink/10 bg-white space-y-1">
                  <strong className="text-ink font-semibold block">{term}</strong>
                  <span className="text-slate block">{def}</span>
                </div>
              ))}
            </div>
          </div>
        )}

        {topic.visualExplanation && (
          <div className="p-4 rounded-xl border border-ink/10 bg-paper/30 space-y-1 text-xs">
            <h3 className="font-mono text-xs font-bold text-ink uppercase">Visual & Microscopic Analysis:</h3>
            <p className="text-slate">{topic.visualExplanation}</p>
          </div>
        )}

        {topic.industrialRelevance && (
          <div className="p-4 rounded-xl border border-ink/10 bg-paper/30 space-y-1 text-xs">
            <h3 className="font-mono text-xs font-bold text-clay uppercase">Apparel Industry Relevance:</h3>
            <p className="text-slate">{topic.industrialRelevance}</p>
          </div>
        )}
      </div>
    </div>
  );
}
