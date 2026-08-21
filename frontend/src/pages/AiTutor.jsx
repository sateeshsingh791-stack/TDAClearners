import { useState, useRef, useEffect } from 'react';
import { NavLink, useLocation } from 'react-router-dom';
import { chatWithAiTutor } from '../api/client';
import { useAuth } from '../context/AuthContext';

export default function AiTutor() {
  const { user } = useAuth();
  const location = useLocation();
  const initialContext = location.state?.academicContext || {};

  const [messages, setMessages] = useState([
    {
      role: 'assistant',
      text: initialContext.subjectCode
        ? `I am ready to help you with ${initialContext.subjectCode} (${initialContext.topicTitle || initialContext.subjectName || 'Syllabus'}). Ask any question!`
        : 'Ask me anything about your B.Voc Textile Design & Apparel Technology syllabus — I\'ll explain it in context.'
    }
  ]);
  const [input, setInput] = useState('');
  const [busy, setBusy] = useState(false);
  const endRef = useRef(null);

  useEffect(() => {
    endRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [messages]);

  const send = async (e) => {
    e.preventDefault();
    if (!input.trim() || busy) return;
    const question = input.trim();
    setMessages((m) => [...m, { role: 'user', text: question }]);
    setInput('');
    setBusy(true);

    try {
      const res = await chatWithAiTutor({
        userMessage: question,
        academicContext: initialContext,
        history: messages.map((m) => ({ role: m.role, text: m.text }))
      });
      const reply =
        res.data.data?.chatTurn?.text ||
        res.data.data?.reply ||
        res.data.reply ||
        res.data.message ||
        'No response received.';
      setMessages((m) => [...m, { role: 'assistant', text: reply }]);
    } catch (err) {
      setMessages((m) => [
        ...m,
        { role: 'assistant', text: 'Something went wrong reaching the AI Tutor service. Please try again.' }
      ]);
    } finally {
      setBusy(false);
    }
  };

  if (!user) {
    return (
      <div>
        <p className="font-mono text-xs text-clay uppercase tracking-widest mb-2">06 — AI Tutor</p>
        <h1 className="font-display text-3xl mb-4">Log in to chat with the tutor</h1>
        <NavLink to="/login" className="text-moss font-medium">
          Log in →
        </NavLink>
      </div>
    );
  }

  return (
    <div className="max-w-2xl flex flex-col h-[calc(100vh-160px)]">
      <div className="flex items-center justify-between mb-2">
        <p className="font-mono text-xs text-clay uppercase tracking-widest">06 — AI Tutor</p>
        {initialContext.subjectCode && (
          <span className="text-xs font-mono bg-moss/10 text-moss px-2.5 py-0.5 rounded font-medium">
            Context: {initialContext.subjectCode}
          </span>
        )}
      </div>
      <h1 className="font-display text-3xl mb-4">Ask the AI Tutor</h1>

      <div className="flex-1 overflow-y-auto space-y-3 border border-ink/10 rounded-xl bg-white/30 p-4 mb-4">
        {messages.map((m, i) => (
          <div
            key={i}
            className={`max-w-[85%] rounded-xl px-4 py-2.5 text-sm leading-relaxed whitespace-pre-wrap ${
              m.role === 'user' ? 'ml-auto bg-moss text-paper' : 'bg-white/90 text-ink border border-ink/5 shadow-sm'
            }`}
          >
            {m.text}
          </div>
        ))}
        {busy && <div className="text-xs font-mono text-slate">Tutor is thinking…</div>}
        <div ref={endRef} />
      </div>

      <form onSubmit={send} className="flex gap-2">
        <input
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Type your question (e.g., Explain French Seam vs Plain Seam)…"
          className="flex-1 border border-ink/15 rounded-full px-4 py-2.5 text-sm focus-ring bg-white"
        />
        <button
          disabled={busy}
          className="bg-moss text-paper rounded-full px-5 py-2.5 text-sm font-semibold hover:bg-ink transition-colors focus-ring disabled:opacity-60"
        >
          Send
        </button>
      </form>
    </div>
  );
}
