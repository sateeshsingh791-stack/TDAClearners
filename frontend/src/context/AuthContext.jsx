import { createContext, useContext, useState } from 'react';

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  // Always active student session without any authentication barrier
  const [user] = useState({
    id: 'student-learner',
    name: 'Student Learner',
    role: 'student',
    course: 'B.Voc Textile Design & Apparel Technology'
  });
  const [loading] = useState(false);

  const login = async () => user;
  const register = async () => user;
  const logout = () => {};

  return (
    <AuthContext.Provider value={{ user, loading, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export const useAuth = () => useContext(AuthContext);
