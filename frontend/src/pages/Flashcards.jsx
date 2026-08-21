import { useEffect, useState } from 'react';
import { getScopedFlashcards, updateFlashcardMastery } from '../api/client';
import { useAuth } from '../context/AuthContext';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function Flashcards() {
  const { user } = useAuth();
  const [cards, setCards] = useState([]);
  const [index, setIndex] = useState(0);
  const [flipped, setFlipped] = useState(false);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    getScopedFlashcards()
      .then((res) => setCards(res.data.data?.flashcards || res.data.flashcards || (Array.isArray(res.data.data) ? res.data.data : [])))
      .catch(() => setError('Could not load flashcards.'))
      .finally(() => setLoading(false));
  }, []);

  const mark = async (level) => {
    const card = cards[index];
    if (user && card) {
      try {
        await updateFlashcardMastery({ flashcardId: card._id || card.id, mastery: level });
      } catch {
        /* non-blocking */
      }
    }
    setFlipped(false);
    setIndex((i) => (i + 1 < cards.length ? i + 1 : 0));
  };

  if (loading) return <Loading />;
  if (error) return <ErrorBlock message={error} />;
  if (cards.length === 0) return <Empty title="No flashcards yet" hint="Cards will appear here once added." />;

  const card = cards[index];

  return (
    <div>
      <p className="font-mono text-xs text-clay uppercase tracking-widest mb-2">04 — Flashcards</p>
      <h1 className="font-display text-3xl mb-2">Quick recall</h1>
      <p className="text-slate mb-8">
        Card {index + 1} of {cards.length}
      </p>

      <button
        onClick={() => setFlipped((f) => !f)}
        className="w-full max-w-xl mx-auto block border border-ink/10 rounded-2xl bg-white/50 px-8 py-16 text-center focus-ring hover:bg-white/70 transition-colors"
      >
        <p className="font-mono text-xs text-slate uppercase tracking-widest mb-4">
          {flipped ? 'Answer' : 'Question — tap to flip'}
        </p>
        <p className="font-display text-2xl leading-snug">
          {flipped ? card.back || card.answer : card.front || card.question}
        </p>
      </button>

      {flipped && (
        <div className="flex justify-center gap-3 mt-6">
          <button
            onClick={() => mark('again')}
            className="border border-clay/40 text-clay rounded-full px-5 py-2 text-sm font-medium focus-ring hover:bg-clay/10"
          >
            Still learning
          </button>
          <button
            onClick={() => mark('mastered')}
            className="bg-moss text-paper rounded-full px-5 py-2 text-sm font-medium focus-ring hover:bg-ink"
          >
            Got it
          </button>
        </div>
      )}

      {!user && <p className="text-sm text-slate text-center mt-6">Log in to track mastery across sessions.</p>}
    </div>
  );
}
