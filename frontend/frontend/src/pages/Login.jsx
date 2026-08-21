import { useState } from 'react';
import { useNavigate, NavLink } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function Login() {
  const { login } = useAuth();
  const navigate = useNavigate();
  const [form, setForm] = useState({ email: '', password: '' });
  const [error, setError] = useState('');
  const [busy, setBusy] = useState(false);

  const submit = async (e) => {
    e.preventDefault();
    setError('');
    setBusy(true);
    try {
      await login(form.email, form.password);
      navigate('/');
    } catch (err) {
      setError(err.response?.data?.error?.message || 'Login failed. Check your details.');
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
          <h1 className="font-display text-2xl mb-1">Welcome back</h1>
          <p className="text-sm text-slate mb-6">Log in to continue your syllabus.</p>

          {error && <p className="text-sm text-clay mb-4">{error}</p>}

          <form onSubmit={submit} className="space-y-4">
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
                value={form.password}
                onChange={(e) => setForm({ ...form, password: e.target.value })}
                className="mt-1 w-full border border-ink/15 rounded-lg px-3 py-2 text-sm focus-ring bg-white"
              />
            </div>
            <button
              disabled={busy}
              className="w-full bg-moss text-paper rounded-lg py-2.5 text-sm font-semibold hover:bg-ink transition-colors focus-ring disabled:opacity-60"
            >
              {busy ? 'Logging in…' : 'Log in'}
            </button>
          </form>

          <p className="text-sm text-slate text-center mt-6">
            New here?{' '}
            <NavLink to="/register" className="text-moss font-medium">
              Create an account
            </NavLink>
          </p>
        </div>
      </div>
    </div>
  );
}
