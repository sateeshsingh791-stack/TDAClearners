import { createContext, useContext, useEffect, useState } from 'react';
import { loginUser, registerUser, getMe } from '../api/client';

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const stored = localStorage.getItem('tda_user');
    return stored ? JSON.parse(stored) : null;
  });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const token = localStorage.getItem('tda_token');
    if (!token) {
      setLoading(false);
      return;
    }
    getMe()
      .then((res) => {
        const me = res.data.data || res.data.user || res.data;
        setUser(me);
        localStorage.setItem('tda_user', JSON.stringify(me));
      })
      .catch(() => {
        localStorage.removeItem('tda_token');
        localStorage.removeItem('tda_user');
        setUser(null);
      })
      .finally(() => setLoading(false));
  }, []);

  const login = async (email, password) => {
    const res = await loginUser({ email, password });
    const token = res.data.token || res.data.data?.token;
    const me = res.data.user || res.data.data?.user || res.data.data;
    localStorage.setItem('tda_token', token);
    localStorage.setItem('tda_user', JSON.stringify(me));
    setUser(me);
    return me;
  };

  const register = async (name, email, password) => {
    const res = await registerUser({ name, email, password });
    const token = res.data.token || res.data.data?.token;
    const me = res.data.user || res.data.data?.user || res.data.data;
    if (token) {
      localStorage.setItem('tda_token', token);
      localStorage.setItem('tda_user', JSON.stringify(me));
      setUser(me);
    }
    return me;
  };

  const logout = () => {
    localStorage.removeItem('tda_token');
    localStorage.removeItem('tda_user');
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, loading, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export const useAuth = () => useContext(AuthContext);
