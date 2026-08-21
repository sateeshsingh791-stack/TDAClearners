import { useEffect, useState } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import { getSemesters } from '../api/client';
import { Loading, ErrorBlock } from '../components/StatusBlock';

export default function Home() {
  const navigate = useNavigate();
  const [semesters, setSemesters] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    getSemesters()
      .then((res) => {
        const list = res.data.data?.semesters || res.data.semesters || (Array.isArray(res.data.data) ? res.data.data : []);
        setSemesters(list);
      })
      .catch(() => setError('Could not load academic scheme.'))
      .finally(() => setLoading(false));
  }, []);

  const activeSemesters = semesters.filter((s) => s.status === 'AVAILABLE' || s.number === 1 || s.number === 2);
  const futureSemesters = semesters.filter((s) => s.status === 'COMING_SOON' || (s.number > 2));

  return (
    <div className="space-y-16">
      
      {/* HERO SECTION — Editorial Academic Banner */}
      <section className="relative overflow-hidden border border-ink/15 rounded-3xl bg-white p-8 sm:p-12 shadow-sm space-y-6">
        <div className="flex flex-wrap items-center justify-between gap-3 font-mono text-xs">
          <span className="bg-moss/10 text-moss border border-moss/30 px-3 py-1 rounded-full font-bold uppercase tracking-wider">
            Official Academic Curriculum Platform
          </span>
          <span className="text-clay font-semibold">
            P.G. Department of Fashion Designing • GNDU NEP Scheme
          </span>
        </div>

        <div className="max-w-3xl space-y-4">
          <h1 className="font-display text-4xl sm:text-5xl lg:text-6xl text-ink font-bold leading-[1.1] tracking-tight">
            B.Voc Textile Design & Apparel Technology
          </h1>
          <p className="font-display text-xl sm:text-2xl text-clay font-medium">
            Khalsa College, Amritsar
          </p>
          <p className="text-slate text-sm sm:text-base leading-relaxed max-w-2xl font-body">
            Empowering garment technology learners through structured 7-tier syllabus mapping, unit-wise short notes, hands-on practical lab workflows, interactive practice drills, and context-aware AI academic guidance.
          </p>
        </div>

        {/* Hero Action CTAs */}
        <div className="flex flex-wrap items-center gap-3 pt-2">
          <NavLink
            to="/syllabus"
            className="bg-moss text-paper px-7 py-3.5 rounded-full font-semibold text-sm hover:bg-ink transition-all shadow-sm focus-ring flex items-center gap-2"
          >
            <span>Explore Course Scheme</span>
            <span>→</span>
          </NavLink>

          <NavLink
            to="/practice-studio"
            className="bg-white text-ink border border-ink/20 px-7 py-3.5 rounded-full font-semibold text-sm hover:bg-paper transition-all focus-ring flex items-center gap-2"
          >
            <span>Start Practice Studio</span>
            <span>🎯</span>
          </NavLink>
        </div>

        {/* Quick Academic Key Metrics */}
        <div className="grid grid-cols-2 sm:grid-cols-4 gap-4 pt-6 border-t border-ink/10 text-xs font-mono">
          <div>
            <span className="text-slate block text-[10px] uppercase">Curriculum Years</span>
            <strong className="text-ink text-base">3 Years (6 Semesters)</strong>
          </div>
          <div>
            <span className="text-slate block text-[10px] uppercase">Currently Available</span>
            <strong className="text-moss text-base">1st Year (Sem 1 & 2)</strong>
          </div>
          <div>
            <span className="text-slate block text-[10px] uppercase">1st Year Credits</span>
            <strong className="text-ink text-base">54 Total Credits</strong>
          </div>
          <div>
            <span className="text-slate block text-[10px] uppercase">1st Year Marks</span>
            <strong className="text-ink text-base">1300 Max Marks</strong>
          </div>
        </div>
      </section>

      {/* SECTION 1: COURSE JOURNEY (Visual Timeline) */}
      <section className="space-y-6">
        <div className="flex items-center justify-between border-b border-ink/15 pb-3">
          <div>
            <p className="font-mono text-xs text-clay uppercase tracking-widest">Section 01</p>
            <h2 className="font-display text-2xl font-bold text-ink">3-Year Academic Progression</h2>
          </div>
          <span className="text-xs font-mono text-slate">NEP Vocational Framework</span>
        </div>

        <div className="grid md:grid-cols-3 gap-6">
          {/* Year 1 Card */}
          <div className="border-2 border-moss rounded-2xl bg-white p-6 shadow-sm space-y-4 relative overflow-hidden">
            <div className="flex items-center justify-between">
              <span className="font-mono text-xs font-bold bg-moss text-paper px-2.5 py-0.5 rounded">
                1st Year — ACTIVE
              </span>
              <span className="text-xs font-mono text-moss font-semibold">Semesters 1 & 2</span>
            </div>
            <h3 className="font-display text-xl font-bold text-ink">Diploma in Textile & Apparel</h3>
            <p className="text-xs text-slate leading-relaxed">
              Foundational design elements, fibre chemistry, sewing machine operations, seam construction, garment drafting, and entrepreneurship basics.
            </p>
            <div className="pt-2 border-t border-ink/5 flex items-center justify-between text-xs font-mono">
              <span className="text-moss font-semibold">16 Subjects Implemented</span>
              <NavLink to="/syllabus" className="text-moss hover:underline font-bold">
                View Syllabi →
              </NavLink>
            </div>
          </div>

          {/* Year 2 Card — COMING SOON */}
          <div className="border border-ink/15 rounded-2xl bg-paper/60 p-6 space-y-4 opacity-80">
            <div className="flex items-center justify-between">
              <span className="font-mono text-xs font-semibold bg-clay/10 text-clay px-2.5 py-0.5 rounded">
                2nd Year — COMING SOON
              </span>
              <span className="text-xs font-mono text-slate">Semesters 3 & 4</span>
            </div>
            <h3 className="font-display text-xl font-bold text-ink">Advanced Diploma</h3>
            <p className="text-xs text-slate leading-relaxed">
              Advanced CAD pattern grading, apparel quality standards (AQL), wet processing, and industrial production planning.
            </p>
            <div className="pt-2 border-t border-ink/5 text-xs font-mono text-clay">
              Future Academic Content • Coming Soon
            </div>
          </div>

          {/* Year 3 Card — COMING SOON */}
          <div className="border border-ink/15 rounded-2xl bg-paper/60 p-6 space-y-4 opacity-80">
            <div className="flex items-center justify-between">
              <span className="font-mono text-xs font-semibold bg-clay/10 text-clay px-2.5 py-0.5 rounded">
                3rd Year — COMING SOON
              </span>
              <span className="text-xs font-mono text-slate">Semesters 5 & 6</span>
            </div>
            <h3 className="font-display text-xl font-bold text-ink">B.Voc Degree Graduation</h3>
            <p className="text-xs text-slate leading-relaxed">
              Industrial internship, export merchandising, enterprise DPR management, portfolio design, and final thesis project.
            </p>
            <div className="pt-2 border-t border-ink/5 text-xs font-mono text-clay">
              Future Academic Content • Coming Soon
            </div>
          </div>
        </div>
      </section>

      {/* SECTION 2: AVAILABLE SEMESTERS */}
      <section className="space-y-6">
        <div className="flex items-center justify-between border-b border-ink/15 pb-3">
          <div>
            <p className="font-mono text-xs text-clay uppercase tracking-widest">Section 02</p>
            <h2 className="font-display text-2xl font-bold text-ink">Available Semesters</h2>
          </div>
          <NavLink to="/syllabus" className="text-xs font-mono text-moss font-semibold hover:underline">
            View All Semesters →
          </NavLink>
        </div>

        {loading && <Loading />}
        {error && <ErrorBlock message={error} />}

        {!loading && !error && (
          <div className="grid md:grid-cols-2 gap-6">
            {activeSemesters.map((sem) => (
              <div key={sem.number} className="border border-ink/15 rounded-2xl bg-white p-6 space-y-4 shadow-sm">
                <div className="flex items-center justify-between border-b border-ink/10 pb-3">
                  <div>
                    <span className="text-[10px] font-mono font-bold text-moss uppercase bg-moss/10 px-2 py-0.5 rounded">
                      Official Syllabus Scheme
                    </span>
                    <h3 className="font-display text-2xl font-bold text-ink mt-1">Semester {sem.number}</h3>
                  </div>
                  <div className="text-right font-mono text-xs text-slate">
                    <p><strong className="text-ink">{sem.totalCredits}</strong> Credits</p>
                    <p><strong className="text-ink">{sem.totalMarks}</strong> Max Marks</p>
                  </div>
                </div>

                <div className="space-y-2">
                  <h4 className="font-mono text-xs font-semibold text-clay uppercase">Subjects in Semester {sem.number}:</h4>
                  <div className="grid sm:grid-cols-2 gap-2 text-xs">
                    {(sem.subjects || []).map((sub) => (
                      <NavLink
                        key={sub.code}
                        to={`/subjects/${sub.code}`}
                        className="p-2.5 rounded-xl border border-ink/10 bg-paper/30 hover:border-moss hover:bg-white transition-all flex flex-col justify-between"
                      >
                        <span className="font-mono font-bold text-clay text-[10px]">{sub.code}</span>
                        <span className="font-medium text-ink truncate mt-0.5">{sub.name}</span>
                      </NavLink>
                    ))}
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </section>

      {/* SECTION 3: SUBJECT DIRECTORY (FEATURED SUBJECTS) */}
      <section className="space-y-6">
        <div className="flex items-center justify-between border-b border-ink/15 pb-3">
          <div>
            <p className="font-mono text-xs text-clay uppercase tracking-widest">Section 03</p>
            <h2 className="font-display text-2xl font-bold text-ink">Core Vocational Subjects</h2>
          </div>
          <span className="text-xs font-mono text-slate">7-Tier Mapped Curriculum</span>
        </div>

        <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-4">
          {[
            { code: 'BVTD113', name: 'Sewing Techniques (Practical)', sem: 1, credits: 4, type: 'PRACTICAL', desc: 'Lockstitch machines, seam finishes, fullness controls, plackets, sleeves, collars.' },
            { code: 'BVTD111', name: 'Design Foundation & Basics of Textile', sem: 1, credits: 2, type: 'THEORY', desc: 'Elements of design, fibre classification, polymer chemistry, yarn spinning.' },
            { code: 'BVTD114', name: 'Introduction to Enterprenurship', sem: 1, credits: 4, type: 'THEORY', desc: 'Schumpeterian innovation, MSME support, DIC clearance, DPR project reports.' },
            { code: 'BVTD121', name: 'Introduction to Fashion', sem: 2, credits: 4, type: 'THEORY', desc: '5-stage fashion life cycle, trickle-down/across theories, trend forecasting.' },
            { code: 'CS-BVTD111', name: 'Computer Application-I', sem: 1, credits: 4, type: 'THEORY & PRAC', desc: 'PC anatomy, operating systems, MS Office, digital studio layout.' },
            { code: 'BVTD122', name: 'Garment Sewing (Practical)', sem: 2, credits: 4, type: 'PRACTICAL', desc: 'Adult bodice block drafting, 8-head croquis, dart suppression, garment assembly.' }
          ].map((sub) => (
            <NavLink
              key={sub.code}
              to={`/subjects/${sub.code}`}
              className="border border-ink/15 rounded-2xl bg-white p-5 shadow-xs hover:border-moss hover:shadow-md transition-all flex flex-col justify-between space-y-3 group"
            >
              <div>
                <div className="flex items-center justify-between mb-2">
                  <span className="font-mono text-xs font-bold text-clay bg-clay/10 px-2.5 py-0.5 rounded">
                    {sub.code}
                  </span>
                  <span className="text-[10px] font-mono text-slate uppercase">
                    Sem {sub.sem} • {sub.type}
                  </span>
                </div>
                <h3 className="font-display font-bold text-base text-ink group-hover:text-moss transition-colors">
                  {sub.name}
                </h3>
                <p className="text-xs text-slate mt-1 line-clamp-2 leading-relaxed">{sub.desc}</p>
              </div>

              <div className="pt-3 border-t border-ink/5 flex items-center justify-between text-xs font-mono text-moss font-medium">
                <span>View Units & Notes</span>
                <span>→</span>
              </div>
            </NavLink>
          ))}
        </div>
      </section>

      {/* SECTION 4: PRACTICE STUDIO PROMO BANNER */}
      <section className="border-2 border-moss rounded-3xl bg-moss/5 p-8 shadow-xs space-y-4">
        <div className="flex flex-wrap items-center justify-between gap-4">
          <div className="space-y-2 max-w-xl">
            <span className="font-mono text-xs font-bold text-moss uppercase tracking-widest bg-moss/10 px-3 py-1 rounded-full">
              Section 04 — Dynamic Practice Studio
            </span>
            <h2 className="font-display text-3xl font-bold text-ink">Targeted Scope Practice Drills</h2>
            <p className="text-xs text-slate leading-relaxed">
              Filter quizzes, flashcards, quick revision notes, and exam questions strictly by <strong>Subject</strong>, <strong>Unit</strong>, or <strong>Topic</strong>.
            </p>
          </div>

          <button
            onClick={() => navigate('/practice-studio')}
            className="bg-moss text-paper px-8 py-4 rounded-full font-semibold text-sm hover:bg-ink transition-all shadow-sm focus-ring shrink-0 flex items-center gap-2"
          >
            <span>Open Practice Studio</span>
            <span>🎯</span>
          </button>
        </div>

        <div className="grid grid-cols-2 sm:grid-cols-4 gap-3 pt-2 text-xs font-mono">
          <div className="p-3 rounded-xl bg-white border border-moss/20">
            <span className="text-moss block font-bold">🎯 MCQ Quizzes</span>
            <span className="text-slate text-[11px]">Scoped feedback & scoring</span>
          </div>
          <div className="p-3 rounded-xl bg-white border border-moss/20">
            <span className="text-moss block font-bold">🎴 Flashcard Drills</span>
            <span className="text-slate text-[11px]">3D active recall flips</span>
          </div>
          <div className="p-3 rounded-xl bg-white border border-moss/20">
            <span className="text-moss block font-bold">⚡ Quick Revision</span>
            <span className="text-slate text-[11px]">High-yield summaries</span>
          </div>
          <div className="p-3 rounded-xl bg-white border border-moss/20">
            <span className="text-moss block font-bold">📝 Exam Questions</span>
            <span className="text-slate text-[11px]">Paper setter layouts</span>
          </div>
        </div>
      </section>

      {/* SECTION 5: AI TUTOR BANNER */}
      <section className="border border-ink/15 rounded-3xl bg-white p-8 shadow-xs space-y-4">
        <div className="flex flex-wrap items-center justify-between gap-4">
          <div className="space-y-2 max-w-xl">
            <span className="font-mono text-xs font-bold text-clay uppercase tracking-widest bg-clay/10 px-3 py-1 rounded-full">
              Section 05 — Academic AI Tutor
            </span>
            <h2 className="font-display text-3xl font-bold text-ink">Context-Aware AI Tutor</h2>
            <p className="text-xs text-slate leading-relaxed">
              Ask questions directly in the context of your specific B.Voc subject or topic. Powered by our secure backend service with zero client credentials.
            </p>
          </div>

          <NavLink
            to="/ai-tutor"
            className="bg-ink text-paper px-8 py-4 rounded-full font-semibold text-sm hover:bg-moss transition-all shadow-sm focus-ring shrink-0 flex items-center gap-2"
          >
            <span>Ask AI Tutor</span>
            <span>🤖</span>
          </NavLink>
        </div>
      </section>
    </div>
  );
}
