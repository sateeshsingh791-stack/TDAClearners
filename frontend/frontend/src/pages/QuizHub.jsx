import { useEffect, useState } from 'react';
import { getScopedQuizzes, recordQuizAttempt } from '../api/client';
import { useAuth } from '../context/AuthContext';
import { Loading, ErrorBlock, Empty } from '../components/StatusBlock';

export default function QuizHub() {
  const { user } = useAuth();
  const [quizzes, setQuizzes] = useState([]);
  const [active, setActive] = useState(null);
  const [answers, setAnswers] = useState({});
  const [submitted, setSubmitted] = useState(false);
  const [score, setScore] = useState(0);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    getScopedQuizzes()
      .then((res) => setQuizzes(res.data.data || res.data.quizzes || res.data || []))
      .catch(() => setError('Could not load quizzes.'))
      .finally(() => setLoading(false));
  }, []);

  const startQuiz = (quiz) => {
    setActive(quiz);
    setAnswers({});
    setSubmitted(false);
    setScore(0);
  };

  const submit = async () => {
    const questions = active.questions || [];
    let correct = 0;
    questions.forEach((q, i) => {
      if (answers[i] === q.correctAnswer || answers[i] === q.answer) correct += 1;
    });
    setScore(correct);
    setSubmitted(true);
    if (user) {
      try {
        await recordQuizAttempt({ quizId: active._id || active.id, score: correct, total: questions.length });
      } catch {
        /* non-blocking */
      }
    }
  };

  if (active) {
    const questions = active.questions || [];
    return (
      <div className="max-w-2xl">
        <button onClick={() => setActive(null)} className="text-sm text-slate hover:text-ink mb-4">
          ← All quizzes
        </button>
        <h1 className="font-display text-2xl mb-6">{active.title || active.name}</h1>

        {questions.map((q, i) => (
          <div key={i} className="mb-6">
            <p className="font-medium mb-2">
              {i + 1}. {q.question || q.text}
            </p>
            <div className="space-y-2">
              {(q.options || []).map((opt, j) => {
                const isSelected = answers[i] === opt;
                const isCorrect = submitted && (opt === q.correctAnswer || opt === q.answer);
                const isWrongPick = submitted && isSelected && !isCorrect;
                return (
                  <button
                    key={j}
                    disabled={submitted}
                    onClick={() => setAnswers({ ...answers, [i]: opt })}
                    className={`w-full text-left border rounded-lg px-4 py-2 text-sm focus-ring transition-colors ${
                      isCorrect
                        ? 'border-moss bg-moss/10'
                        : isWrongPick
                        ? 'border-clay bg-clay/10'
                        : isSelected
                        ? 'border-ink bg-ink/5'
                        : 'border-ink/10 bg-white/30'
                    }`}
                  >
                    {opt}
                  </button>
                );
              })}
            </div>
          </div>
        ))}

        {!submitted ? (
          <button
            onClick={submit}
            className="bg-moss text-paper rounded-full px-6 py-2.5 text-sm font-semibold hover:bg-ink transition-colors focus-ring"
          >
            Submit answers
          </button>
        ) : (
          <div className="border border-ink/10 rounded-xl px-5 py-4 bg-white/40">
            <p className="font-display text-xl">
              {score} / {questions.length} correct
            </p>
            {!user && <p className="text-sm text-slate mt-1">Log in to save your quiz attempts.</p>}
          </div>
        )}
      </div>
    );
  }

  return (
    <div>
      <p className="font-mono text-xs text-clay uppercase tracking-widest mb-2">03 — Quiz Hub</p>
      <h1 className="font-display text-3xl mb-6">Test yourself</h1>

      {loading && <Loading />}
      {error && <ErrorBlock message={error} />}
      {!loading && !error && quizzes.length === 0 && (
        <Empty title="No quizzes yet" hint="Quizzes will show up here once added." />
      )}

      <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-4">
        {quizzes.map((q, i) => (
          <button
            key={q._id || q.id || i}
            onClick={() => startQuiz(q)}
            className="text-left border border-ink/10 rounded-xl p-5 bg-white/40 hover:bg-ink hover:text-paper transition-colors focus-ring"
          >
            <h3 className="font-display text-lg mb-1">{q.title || q.name}</h3>
            <p className="text-sm text-slate group-hover:text-paper/70">
              {(q.questions || []).length} questions
            </p>
          </button>
        ))}
      </div>
    </div>
  );
}
