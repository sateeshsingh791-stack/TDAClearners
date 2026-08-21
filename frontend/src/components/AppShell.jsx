import { NavLink, useNavigate, useLocation } from 'react-router-dom';
import { useState } from 'react';

const NAV_ITEMS = [
  { to: '/', label: 'Home', icon: '🏛️' },
  { to: '/syllabus', label: 'Course Journey', icon: '🗺️' },
  { to: '/practice-studio', label: 'Practice Studio', icon: '🎯' },
  { to: '/ai-tutor', label: 'AI Tutor', icon: '🤖' },
  { to: '/resources', label: 'Resources & Notes', icon: '📖' },
  { to: '/practical-lab', label: 'Practical Lab', icon: '🧪' },
  { to: '/career', label: 'Career & Industry', icon: '💼' },
  { to: '/progress', label: 'Bookmarks & Progress', icon: '⭐' }
];

export default function AppShell({ children }) {
  const navigate = useNavigate();
  const location = useLocation();
  const [searchQuery, setSearchQuery] = useState('');
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  const handleSearchSubmit = (e) => {
    e.preventDefault();
    if (searchQuery.trim()) {
      navigate(`/search?q=${encodeURIComponent(searchQuery.trim())}`);
      setMobileMenuOpen(false);
    }
  };

  return (
    <div className="min-h-screen bg-[#FDFBF7] text-[#1A1A1A] font-body selection:bg-moss/20">
      {/* Top Editorial Header */}
      <header className="border-b border-ink/10 bg-[#FDFBF7]/95 backdrop-blur sticky top-0 z-40">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 py-3.5 flex items-center justify-between gap-4">
          
          {/* Brand Logo & Academic Identity */}
          <div className="flex items-center gap-3">
            <button
              onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
              className="md:hidden text-ink p-1 focus-ring rounded"
              aria-label="Toggle Navigation"
            >
              <span className="block w-5 h-0.5 bg-ink mb-1" />
              <span className="block w-5 h-0.5 bg-ink mb-1" />
              <span className="block w-5 h-0.5 bg-ink" />
            </button>

            <NavLink to="/" className="flex items-center gap-2 group">
              <div className="w-8 h-8 rounded-lg bg-moss text-paper flex items-center justify-center font-display font-bold text-sm shadow-xs group-hover:bg-ink transition-colors">
                TD
              </div>
              <div>
                <span className="font-display font-bold text-xl tracking-tight text-ink block leading-none">
                  Fabri<span className="text-clay">Learn</span>
                </span>
                <span className="text-[10px] font-mono text-slate uppercase tracking-wider block mt-0.5">
                  Khalsa College Amritsar
                </span>
              </div>
            </NavLink>
          </div>

          {/* Global Search Bar */}
          <form onSubmit={handleSearchSubmit} className="hidden md:flex flex-1 max-w-md">
            <div className="relative w-full">
              <input
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                placeholder="Search topics, subjects, units, definitions…"
                className="w-full bg-white border border-ink/15 rounded-full pl-9 pr-4 py-2 text-xs focus-ring shadow-xs placeholder:text-slate/60"
              />
              <span className="absolute left-3 top-2.5 text-xs opacity-50">🔍</span>
            </div>
          </form>

          {/* Quick Header Actions */}
          <div className="flex items-center gap-2">
            <NavLink
              to="/ai-tutor"
              className="text-xs font-mono font-semibold bg-moss/10 text-moss border border-moss/30 px-3.5 py-1.5 rounded-full hover:bg-moss hover:text-paper transition-all focus-ring hidden sm:inline-flex items-center gap-1.5"
            >
              <span>🤖 AI Tutor</span>
            </NavLink>

            <NavLink
              to="/practice-studio"
              className="text-xs font-mono font-semibold bg-ink text-paper px-3.5 py-1.5 rounded-full hover:bg-moss transition-all focus-ring flex items-center gap-1"
            >
              <span>Practice Studio →</span>
            </NavLink>
          </div>
        </div>

        {/* Desktop Primary Navigation Bar */}
        <nav className="hidden md:block border-t border-ink/5 bg-white/60">
          <div className="max-w-7xl mx-auto px-6 flex items-center gap-1 overflow-x-auto py-1">
            {NAV_ITEMS.map((item) => (
              <NavLink
                key={item.to}
                to={item.to}
                className={({ isActive }) =>
                  `px-3.5 py-2 text-xs font-mono font-medium rounded-lg transition-colors whitespace-nowrap flex items-center gap-1.5 ${
                    isActive
                      ? 'bg-moss/10 text-moss font-bold border border-moss/20'
                      : 'text-slate hover:text-ink hover:bg-paper'
                  }`
                }
              >
                <span className="text-xs">{item.icon}</span>
                <span>{item.label}</span>
              </NavLink>
            ))}
          </div>
        </nav>
      </header>

      {/* Mobile Drawer Menu */}
      {mobileMenuOpen && (
        <div className="md:hidden fixed inset-0 bg-ink/50 backdrop-blur-xs z-50" onClick={() => setMobileMenuOpen(false)}>
          <div
            className="w-4/5 max-w-xs bg-paper h-full shadow-2xl p-5 flex flex-col justify-between"
            onClick={(e) => e.stopPropagation()}
          >
            <div className="space-y-4">
              <div className="flex items-center justify-between border-b border-ink/10 pb-3">
                <div className="font-display font-bold text-lg text-ink">FabriLearn Menu</div>
                <button onClick={() => setMobileMenuOpen(false)} className="text-slate text-sm font-mono">
                  ✕ Close
                </button>
              </div>

              {/* Mobile Search */}
              <form onSubmit={handleSearchSubmit}>
                <input
                  value={searchQuery}
                  onChange={(e) => setSearchQuery(e.target.value)}
                  placeholder="Search syllabus & notes…"
                  className="w-full bg-white border border-ink/15 rounded-xl px-3 py-2 text-xs focus-ring mb-2"
                />
              </form>

              <nav className="space-y-1">
                {NAV_ITEMS.map((item) => (
                  <NavLink
                    key={item.to}
                    to={item.to}
                    onClick={() => setMobileMenuOpen(false)}
                    className={({ isActive }) =>
                      `flex items-center gap-2.5 px-3 py-2.5 rounded-xl text-xs font-mono font-medium transition-colors ${
                        isActive ? 'bg-moss text-paper font-semibold' : 'text-slate hover:bg-white hover:text-ink'
                      }`
                    }
                  >
                    <span>{item.icon}</span>
                    <span>{item.label}</span>
                  </NavLink>
                ))}
              </nav>
            </div>

            <div className="border-t border-ink/10 pt-3 text-[10px] font-mono text-slate">
              B.Voc. Textile Design & Apparel Technology • Khalsa College, Amritsar
            </div>
          </div>
        </div>
      )}

      {/* Main Content Area */}
      <main className="max-w-7xl mx-auto px-4 sm:px-6 py-8 min-h-[calc(100vh-140px)]">
        {children}
      </main>

      {/* Footer */}
      <footer className="border-t border-ink/10 bg-white/70 py-8 text-xs font-mono text-slate">
        <div className="max-w-7xl mx-auto px-6 flex flex-wrap items-center justify-between gap-4">
          <div>
            <strong className="text-ink font-display text-sm">FabriLearn Academic Platform</strong>
            <p className="mt-0.5 text-[11px]">
              Bachelor of Vocation (B.Voc.) in Textile Design & Apparel Technology — Khalsa College, Amritsar
            </p>
          </div>
          <div className="flex items-center gap-4 text-[11px]">
            <NavLink to="/syllabus" className="hover:underline">Syllabus Scheme</NavLink>
            <NavLink to="/practice-studio" className="hover:underline">Practice Studio</NavLink>
            <NavLink to="/ai-tutor" className="hover:underline">AI Tutor</NavLink>
            <NavLink to="/resources" className="hover:underline">Unit Notes</NavLink>
          </div>
        </div>
      </footer>
    </div>
  );
}
