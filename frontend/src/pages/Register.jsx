import { useState } from 'react';
import { useNavigate, NavLink } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function Register() {
  const { register } = useAuth();
  const navigate = useNavigate();
  const [form, setForm] = useState({ name: '', email: '', password: '' });
  const [error, setError] = useState('');
  const [busy, setBusy] = useState(false);

  const submit = async (e) => {
    e.preventDefault();
    setError('');
    setBusy(true);
    try {
      await register(form.name, form.email, form.password);
      navigate('/login');
    } catch (err) {
      setError(err.response?.data?.error?.message || 'Registration failed.');
    } finally {
      setBusy(false);
    }
  };

  return (
    <div className="min-h-screen bg-paper flex items-center justify-center px-5">
      <div className="w-full max-w-sm">
        <NavLink to="/" className="font-display text-3xl block text-center mb-8">
          TDA<span className="text-clay">Clearners</span>
        </NavLink>
        <div className="border border-ink/10 rounded-2xl p-8 bg-white/40">
          <h1 className="font-display text-2xl mb-1">Create your account</h1>
          <p className="text-sm text-slate mb-6">Start tracking your syllabus progress.</p>

          {error && <p className="text-sm text-clay mb-4">{error}</p>}

          <form onSubmit={submit} className="space-y-4">
            <div>
              <label className="text-xs font-medium text-slate">Name</label>
              <input
                required
                value={form.name}
                onChange={(e) => setForm({ ...form, name: e.target.value })}
                className="mt-1 w-full border border-ink/15 rounded-lg px-3 py-2 text-sm focus-ring bg-white"
              />
            </div>
            <div>
              <label className="text-xs font-medium text-slate">Email</label>
              <input
                type="email"
                required
                value={form.email}
                onChange={(e) => setForm({ ...form, email: e.target.value })}
                className="mt-1 w-full border border-ink/15 rounded-lg px-3 py-2 text-sm focus-ring bg-white"
              />
            </div>
            <div>
              <label className="text-xs font-medium text-slate">Password</label>
              <input
                type="password"
                required
                minLength={6}
                value={form.password}
                onChange={(e) => setForm({ ...form, password: e.target.value })}
                className="mt-1 w-full border border-ink/15 rounded-lg px-3 py-2 text-sm focus-ring bg-white"
              />
              <p className="text-xs text-slate mt-1">At least 6 characters.</p>
            </div>
            <button
              disabled={busy}
              className="w-full bg-moss text-paper rounded-lg py-2.5 text-sm font-semibold hover:bg-ink transition-colors focus-ring disabled:opacity-60"
            >
              {busy ? 'Creating account…' : 'Create account'}
            </button>
          </form>

          <p className="text-sm text-slate text-center mt-6">
            Already registered?{' '}
            <NavLink to="/login" className="text-moss font-medium">
              Log in
            </NavLink>
          </p>
        </div>
      </div>
    </div>
  );
}
