import { NavLink, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { useAuth } from '../context/AuthContext';

const NAV = [
  { to: '/', label: 'Home', glyph: '01' },
  { to: '/syllabus', label: 'Syllabus', glyph: '02' },
  { to: '/quiz-hub', label: 'Quiz Hub', glyph: '03' },
  { to: '/flashcards', label: 'Flashcards', glyph: '04' },
  { to: '/practical-lab', label: 'Practical Lab', glyph: '05' },
  { to: '/ai-tutor', label: 'AI Tutor', glyph: '06' },
  { to: '/career', label: 'Career & Industry', glyph: '07' },
  { to: '/resources', label: 'Resources', glyph: '08' },
  { to: '/progress', label: 'Bookmarks & Progress', glyph: '09' }
];

export default function AppShell({ children }) {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const [search, setSearch] = useState('');
  const [open, setOpen] = useState(false);

  const submitSearch = (e) => {
    e.preventDefault();
    if (search.trim()) {
      navigate(`/search?q=${encodeURIComponent(search.trim())}`);
      setOpen(false);
    }
  };

  return (
    <div className="min-h-screen bg-paper text-ink font-body">
      <header className="border-b border-ink/10 bg-paper/95 backdrop-blur sticky top-0 z-30">
        <div className="max-w-7xl mx-auto px-5 py-4 flex items-center justify-between gap-4">
          <div className="flex items-center gap-3">
            <button
              className="md:hidden focus-ring rounded p-1"
              onClick={() => setOpen((o) => !o)}
              aria-label="Toggle menu"
            >
              <span className="block w-6 h-0.5 bg-ink mb-1.5" />
              <span className="block w-6 h-0.5 bg-ink mb-1.5" />
              <span className="block w-6 h-0.5 bg-ink" />
            </button>
            <NavLink to="/" className="font-display text-2xl tracking-tight">
              TDA<span className="text-clay">Clearners</span>
            </NavLink>
          </div>

          <form onSubmit={submitSearch} className="hidden md:flex flex-1 max-w-md">
            <input
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              placeholder="Search topics, subjects, resources…"
              className="w-full bg-ink/5 border border-ink/10 rounded-full px-4 py-2 text-sm focus-ring"
            />
          </form>

          <div className="flex items-center gap-3">
            {user ? (
              <>
                <span className="hidden sm:inline text-sm text-slate">Hi, {user.name?.split(' ')[0] || 'Student'}</span>
                <button
                  onClick={logout}
                  className="text-sm font-medium border border-ink/15 rounded-full px-4 py-1.5 hover:bg-ink hover:text-paper transition-colors focus-ring"
                >
                  Log out
                </button>
              </>
            ) : (
              <NavLink
                to="/login"
                className="text-sm font-medium bg-moss text-paper rounded-full px-4 py-1.5 hover:bg-ink transition-colors focus-ring"
              >
                Log in
              </NavLink>
            )}
          </div>
        </div>
      </header>

      <div className="max-w-7xl mx-auto flex">
        <nav
          className={`${
            open ? 'block' : 'hidden'
          } md:block w-full md:w-60 shrink-0 border-r border-ink/10 md:min-h-[calc(100vh-73px)] px-5 py-6`}
        >
          <ul className="space-y-1">
            {NAV.map((item) => (
              <li key={item.to}>
                <NavLink
                  to={item.to}
                  onClick={() => setOpen(false)}
                  className={({ isActive }) =>
                    `flex items-center gap-3 rounded-lg px-3 py-2 text-sm font-medium transition-colors focus-ring ${
                      isActive ? 'bg-ink text-paper' : 'text-slate hover:bg-ink/5 hover:text-ink'
                    }`
                  }
                >
                  <span className="font-mono text-xs text-clay">{item.glyph}</span>
                  {item.label}
                </NavLink>
              </li>
            ))}
          </ul>
        </nav>

        <main className="flex-1 px-5 py-8 min-w-0">{children}</main>
      </div>
    </div>
  );
}
