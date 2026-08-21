import { useEffect, useState } from 'react';
import { useParams, NavLink } from 'react-router-dom';
import { getTopicById, toggleTopicCompletion, toggleBookmark } from '../api/client';
import { useAuth } from '../context/AuthContext';
import { Loading, ErrorBlock } from '../components/StatusBlock';

export default function TopicLearning() {
  const { topicId } = useParams();
  const { user } = useAuth();
  const [topic, setTopic] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [done, setDone] = useState(false);
  const [bookmarked, setBookmarked] = useState(false);
  const [busy, setBusy] = useState(false);

  useEffect(() => {
    setLoading(true);
    getTopicById(topicId)
      .then((res) => {
        const t = res.data.data?.topic || res.data.topic || res.data.data || res.data;
        setTopic(t);
        setDone(!!t.completed);
        setBookmarked(!!t.bookmarked);
      })
      .catch(() => setError('Could not load this topic.'))
      .finally(() => setLoading(false));
  }, [topicId]);

  const markComplete = async () => {
    if (!user) return;
    setBusy(true);
    try {
      await toggleTopicCompletion({ topicId });
      setDone((d) => !d);
    } finally {
      setBusy(false);
    }
  };

  const markBookmark = async () => {
    if (!user) return;
    setBusy(true);
    try {
      await toggleBookmark({ topicId });
      setBookmarked((b) => !b);
    } finally {
      setBusy(false);
    }
  };

  if (loading) return <Loading />;
  if (error) return <ErrorBlock message={error} />;
  if (!topic) return null;

  return (
    <div className="max-w-3xl">
      <NavLink to="/syllabus" className="text-sm text-slate hover:text-ink">
        ← Back to syllabus
      </NavLink>

      <div className="flex items-start justify-between gap-4 mt-4 mb-6">
        <h1 className="font-display text-3xl">{topic.title || topic.name}</h1>
        <div className="flex gap-2 shrink-0">
          <button
            onClick={markBookmark}
            disabled={!user || busy}
            title={user ? 'Toggle bookmark' : 'Log in to bookmark'}
            className={`w-9 h-9 rounded-full border border-ink/15 flex items-center justify-center focus-ring disabled:opacity-40 ${
              bookmarked ? 'bg-gold text-ink' : 'bg-white/40'
            }`}
          >
            ★
          </button>
          <button
            onClick={markComplete}
            disabled={!user || busy}
            className={`text-sm font-medium rounded-full px-4 py-2 border border-ink/15 focus-ring disabled:opacity-40 ${
              done ? 'bg-moss text-paper' : 'bg-white/40'
            }`}
          >
            {done ? 'Completed ✓' : 'Mark complete'}
          </button>
        </div>
      </div>

      {!user && (
        <p className="text-sm text-slate mb-6 border border-ink/10 rounded-lg px-4 py-3 bg-white/30">
          <NavLink to="/login" className="text-moss font-medium">
            Log in
          </NavLink>{' '}
          to track completion and bookmarks.
        </p>
      )}

      <article className="prose-sm max-w-none whitespace-pre-wrap leading-relaxed text-ink/90">
        {topic.content || topic.description || 'No content added for this topic yet.'}
      </article>
    </div>
  );
}
