import { useState, useRef, useEffect } from 'react';
import { NavLink, useLocation } from 'react-router-dom';
import { chatWithAiTutor, getSemesters } from '../api/client';
import { useAuth } from '../context/AuthContext';

const QUICK_ACTIONS = [
  { label: '💡 Explain this topic', prompt: 'Please explain this topic in detail with key concepts and real-world examples.' },
  { label: '🧠 Simplify this', prompt: 'Please simplify the concepts in this topic into plain, easy-to-understand terms for a beginner.' },
  { label: '🔬 Give an example', prompt: 'Can you provide a practical textile or garment industry example for this topic?' },
  { label: '📋 Step-by-step', prompt: 'Break down the step-by-step procedure or technical workflow for this topic.' },
  { label: '❓ Create MCQs', prompt: 'Generate 5 multiple-choice questions (MCQs) with options and explanations based on this topic.' },
  { label: '🎴 Create flashcards', prompt: 'Create 4 flashcards (Front Question -> Back Answer) for key terms in this topic.' },
  { label: '📝 Revision questions', prompt: 'Provide 3 important university exam revision questions with answer outlines for this topic.' },
  { label: '⚖️ Compare concepts', prompt: 'Compare and contrast the main techniques or concepts in this subject area.' },
  { label: '🚀 Quick summary', prompt: 'Give me a quick 2-minute revision summary of the most critical points.' }
];

export default function AiTutor() {
  const { user } = useAuth();
  const location = useLocation();
  
  // Passed context from Topic, Subject, or Practical page
  const routeContext = location.state?.academicContext || {};

  const [academicContext, setAcademicContext] = useState({
    course: routeContext.course || 'B.Voc. Textile Design & Apparel Technology',
    yearNumber: routeContext.yearNumber || 1,
    semesterNumber: routeContext.semesterNumber || 1,
    subjectCode: routeContext.subjectCode || '',
    subjectName: routeContext.subjectName || '',
    unitTitle: routeContext.unitTitle || '',
    topicTitle: routeContext.topicTitle || ''
  });

  const [showSelector, setShowSelector] = useState(false);
  const [semestersList, setSemestersList] = useState([]);
  const [availableSubjects, setAvailableSubjects] = useState([]);

  const [messages, setMessages] = useState([
    {
      id: 'init-1',
      role: 'assistant',
      text: routeContext.subjectCode
        ? `Welcome! I am your AI Tutor for **${routeContext.subjectCode}** (${routeContext.topicTitle || routeContext.subjectName || 'Syllabus'}). How can I assist your study today?`
        : 'Welcome! I am your Senior Academic AI Tutor for **B.Voc. Textile Design & Apparel Technology**. Select your subject or ask any question to begin!',
      timestamp: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
      badges: ['OFFICIAL_SYLLABUS']
    }
  ]);

  const [input, setInput] = useState('');
  const [busy, setBusy] = useState(false);
  const [errorBanner, setErrorBanner] = useState('');
  const endRef = useRef(null);
  const textareaRef = useRef(null);

  // Fetch available subjects for context selector if needed
  useEffect(() => {
    getSemesters()
      .then((res) => {
        const list = res.data.data?.semesters || res.data.semesters || (Array.isArray(res.data.data) ? res.data.data : []);
        setSemestersList(list);
        const allSubjects = [];
        list.forEach((sem) => {
          (sem.subjects || []).forEach((sub) => allSubjects.push(sub));
        });
        setAvailableSubjects(allSubjects);
      })
      .catch(() => {});
  }, []);

  // Auto-scroll timeline
  useEffect(() => {
    endRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [messages, busy]);

  const handleSend = async (customPrompt) => {
    const question = (customPrompt || input).trim();
    if (!question || busy) return;

    setErrorBanner('');
    const timeStr = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

    const newMsg = { id: String(Date.now()), role: 'user', text: question, timestamp: timeStr };
    setMessages((m) => [...m, newMsg]);
    if (!customPrompt) setInput('');
    setBusy(true);

    try {
      const res = await chatWithAiTutor({
        userMessage: question,
        academicContext,
        history: messages.map((m) => ({ role: m.role, text: m.text }))
      });

      const turn = res.data.data?.chatTurn || {};
      const replyText = turn.text || res.data.data?.reply || res.data.reply || res.data.message || 'No response received.';

      // Determine visual badges from response content
      const badges = ['AI_EXPLANATION'];
      if (replyText.toLowerCase().includes('syllabus') || replyText.toLowerCase().includes('bvtd')) {
        badges.unshift('OFFICIAL_SYLLABUS');
      }
      if (replyText.toLowerCase().includes('industry') || replyText.toLowerCase().includes('aql') || replyText.toLowerCase().includes('sam')) {
        badges.push('INDUSTRY_KNOWLEDGE');
      }

      setMessages((m) => [
        ...m,
        {
          id: turn.id || String(Date.now()),
          role: 'assistant',
          text: replyText,
          timestamp: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
          badges
        }
      ]);
    } catch (err) {
      const status = err.response?.status;
      if (status === 429) {
        setErrorBanner('Too many queries in a short time. Please wait a minute before asking another question.');
      } else {
        setErrorBanner('Unable to reach the AI Tutor backend service. Please check your network and try again.');
      }
    } finally {
      setBusy(false);
    }
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Enter' && !e.shiftKey) {
      e.preventDefault();
      handleSend();
    }
  };

  const clearChat = () => {
    setMessages([
      {
        id: String(Date.now()),
        role: 'assistant',
        text: 'Conversation cleared. What topic would you like to explore next?',
        timestamp: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
        badges: ['AI_EXPLANATION']
      }
    ]);
    setErrorBanner('');
  };

  const selectSubjectContext = (subjCode) => {
    const found = availableSubjects.find((s) => s.code === subjCode);
    if (found) {
      setAcademicContext({
        course: 'B.Voc. Textile Design & Apparel Technology',
        yearNumber: Math.ceil((found.semesterNumber || 1) / 2),
        semesterNumber: found.semesterNumber || 1,
        subjectCode: found.code,
        subjectName: found.name,
        unitTitle: '',
        topicTitle: ''
      });
      setShowSelector(false);
      setMessages((m) => [
        ...m,
        {
          id: String(Date.now()),
          role: 'assistant',
          text: `Context updated to **${found.code} - ${found.name}** (Semester ${found.semesterNumber}). What would you like to study in this course?`,
          timestamp: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
          badges: ['OFFICIAL_SYLLABUS']
        }
      ]);
    }
  };

  if (!user) {
    return (
      <div className="max-w-xl mx-auto text-center py-12 space-y-4">
        <p className="font-mono text-xs text-clay uppercase tracking-widest">06 — AI Tutor</p>
        <h1 className="font-display text-3xl">Log in to Access AI Tutor</h1>
        <p className="text-slate text-sm">
          Please log in to your student account to ask context-aware questions about your B.Voc Textile Design & Apparel Technology subjects.
        </p>
        <NavLink
          to="/login"
          className="inline-block bg-moss text-paper rounded-full px-6 py-2.5 text-sm font-semibold hover:bg-ink transition-colors focus-ring"
        >
          Log in to Account →
        </NavLink>
      </div>
    );
  }

  return (
    <div className="max-w-4xl mx-auto flex flex-col h-[calc(100vh-140px)]">
      {/* Header Bar */}
      <div className="flex items-center justify-between pb-3 border-b border-ink/10">
        <div>
          <div className="flex items-center gap-2">
            <p className="font-mono text-xs text-clay uppercase tracking-widest">06 — AI Academic Tutor</p>
            <span className="text-[10px] font-mono bg-moss/10 text-moss px-2 py-0.5 rounded font-semibold">
              B.Voc. Textile Design
            </span>
          </div>
          <h1 className="font-display text-2xl text-ink">Interactive AI Learning Assistant</h1>
        </div>

        <div className="flex items-center gap-2">
          <button
            onClick={() => setShowSelector(!showSelector)}
            className="text-xs font-mono px-3 py-1.5 rounded-lg border border-ink/15 bg-white/70 hover:bg-white transition-colors focus-ring flex items-center gap-1.5"
          >
            <span>🎯 Context:</span>
            <strong className="text-moss">
              {academicContext.subjectCode || 'General Syllabus'}
            </strong>
            <span>{showSelector ? '▲' : '▼'}</span>
          </button>

          <button
            onClick={clearChat}
            title="Clear conversation"
            className="text-xs font-mono px-3 py-1.5 rounded-lg border border-ink/15 bg-white/50 hover:bg-clay/10 hover:text-clay transition-colors focus-ring"
          >
            Clear Chat
          </button>
        </div>
      </div>

      {/* Context Selector Dropdown */}
      {showSelector && (
        <div className="bg-paper border border-ink/15 rounded-xl p-4 my-3 shadow-md space-y-3 z-10">
          <div className="flex items-center justify-between">
            <h3 className="font-display text-sm font-semibold">Select Academic Subject Context</h3>
            <button onClick={() => setShowSelector(false)} className="text-xs text-slate hover:text-ink">
              ✕ Close
            </button>
          </div>
          <div className="grid sm:grid-cols-2 md:grid-cols-3 gap-2 max-h-48 overflow-y-auto">
            {availableSubjects.map((s) => (
              <button
                key={s.code}
                onClick={() => selectSubjectContext(s.code)}
                className={`text-left p-2.5 rounded-lg border text-xs transition-all ${
                  academicContext.subjectCode === s.code
                    ? 'border-moss bg-moss/10 font-semibold text-moss'
                    : 'border-ink/10 bg-white hover:border-ink/30'
                }`}
              >
                <div className="font-mono text-[10px] text-clay">{s.code} • Sem {s.semesterNumber}</div>
                <div className="truncate">{s.name}</div>
              </button>
            ))}
          </div>
        </div>
      )}

      {/* Context Information Strip */}
      {academicContext.subjectCode && (
        <div className="bg-moss/5 border border-moss/15 rounded-lg px-3 py-1.5 my-2 flex flex-wrap items-center justify-between text-xs font-mono text-slate">
          <div className="flex items-center gap-2 truncate">
            <span className="text-moss font-semibold">Active Context:</span>
            <span>{academicContext.subjectCode} - {academicContext.subjectName}</span>
            {academicContext.topicTitle && (
              <span className="text-ink truncate">› {academicContext.topicTitle}</span>
            )}
          </div>
          <button
            onClick={() =>
              setAcademicContext({
                course: 'B.Voc. Textile Design & Apparel Technology',
                yearNumber: 1,
                semesterNumber: 1,
                subjectCode: '',
                subjectName: '',
                unitTitle: '',
                topicTitle: ''
              })
            }
            className="text-[10px] text-clay hover:underline shrink-0"
          >
            Clear Context
          </button>
        </div>
      )}

      {/* Error Alert */}
      {errorBanner && (
        <div className="my-2 p-3 rounded-xl bg-clay/10 border border-clay/30 text-clay text-xs flex items-center justify-between">
          <span>⚠️ {errorBanner}</span>
          <button onClick={() => setErrorBanner('')} className="font-mono text-xs hover:underline">
            Dismiss
          </button>
        </div>
      )}

      {/* Messages Timeline */}
      <div className="flex-1 overflow-y-auto space-y-4 border border-ink/10 rounded-2xl bg-white/40 p-4 my-3 shadow-inner">
        {messages.map((m) => (
          <div
            key={m.id}
            className={`flex flex-col max-w-[88%] ${m.role === 'user' ? 'ml-auto items-end' : 'mr-auto items-start'}`}
          >
            {/* Badges bar for Assistant */}
            {m.role === 'assistant' && (
              <div className="flex flex-wrap gap-1.5 mb-1 text-[10px] font-mono">
                {(m.badges || []).includes('OFFICIAL_SYLLABUS') && (
                  <span className="bg-moss/15 text-moss px-2 py-0.5 rounded-full font-medium border border-moss/20">
                    📌 According to Official Syllabus
                  </span>
                )}
                {(m.badges || []).includes('INDUSTRY_KNOWLEDGE') && (
                  <span className="bg-gold/20 text-ink px-2 py-0.5 rounded-full font-medium border border-gold/30">
                    🏭 Additional Industry Knowledge
                  </span>
                )}
                {(m.badges || []).includes('AI_EXPLANATION') && (
                  <span className="bg-ink/5 text-slate px-2 py-0.5 rounded-full font-medium border border-ink/10">
                    🤖 AI Generated Explanation
                  </span>
                )}
              </div>
            )}

            {/* Bubble */}
            <div
              className={`rounded-2xl px-4 py-3 text-sm leading-relaxed whitespace-pre-wrap ${
                m.role === 'user'
                  ? 'bg-moss text-paper shadow-sm rounded-tr-none font-medium'
                  : 'bg-white text-ink border border-ink/10 shadow-sm rounded-tl-none'
              }`}
            >
              {m.text}
            </div>

            {/* Timestamp */}
            <span className="text-[10px] font-mono text-slate/70 mt-1 px-1">{m.timestamp}</span>
          </div>
        ))}

        {busy && (
          <div className="flex items-center gap-2 text-xs font-mono text-moss bg-moss/5 border border-moss/10 rounded-xl px-4 py-2.5 max-w-xs">
            <span className="animate-spin">🌀</span>
            <span>Tutor is retrieving curriculum data…</span>
          </div>
        )}
        <div ref={endRef} />
      </div>

      {/* Quick Action Pills */}
      <div className="flex items-center gap-2 overflow-x-auto pb-2 scrollbar-none">
        {QUICK_ACTIONS.map((qa, i) => (
          <button
            key={i}
            disabled={busy}
            onClick={() => handleSend(qa.prompt)}
            className="text-xs font-mono whitespace-nowrap bg-white/70 hover:bg-moss hover:text-paper text-ink border border-ink/15 rounded-full px-3 py-1.5 transition-all shadow-xs focus-ring disabled:opacity-50 shrink-0"
          >
            {qa.label}
          </button>
        ))}
      </div>

      {/* Input Area */}
      <div className="flex items-end gap-2 pt-1">
        <div className="flex-1 relative">
          <textarea
            ref={textareaRef}
            rows={2}
            value={input}
            onChange={(e) => setInput(e.target.value)}
            onKeyDown={handleKeyDown}
            placeholder="Ask a question about your syllabus (Press Enter to send, Shift+Enter for new line)…"
            className="w-full border border-ink/15 rounded-2xl px-4 py-3 text-sm focus-ring bg-white shadow-xs resize-none max-h-32"
          />
        </div>
        <button
          onClick={() => handleSend()}
          disabled={busy || !input.trim()}
          className="bg-moss text-paper rounded-2xl px-6 py-3.5 text-sm font-semibold hover:bg-ink transition-all focus-ring disabled:opacity-50 shrink-0 shadow-sm flex items-center gap-1.5"
        >
          <span>Send</span>
          <span>→</span>
        </button>
      </div>
    </div>
  );
}
