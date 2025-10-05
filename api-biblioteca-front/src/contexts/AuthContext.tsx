import { createContext, useState, useEffect, useContext, ReactNode } from 'react';
import { jwtDecode } from 'jwt-decode';
import { login as apiLogin, setAuthToken } from '../services/api';
import { LoginDTO } from '../services/types';

interface User {
  sub: string;
  roles: string[];// Espera um array como ['ROLE_ADMIN'] ou ['ROLE_USER']
  userId:number; 
}

interface AuthContextType {
  user: User | null;
  login: (credenciais: LoginDTO) => Promise<void>;
  logout: () => void;
  isAuthenticated: boolean;
  isLoading: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<User | null>(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const token = localStorage.getItem('authToken');
    if (token) {
      try {
        const decodedUser = jwtDecode<User>(token);
        setUser(decodedUser);
        setAuthToken(token);
      } catch (error) {
        localStorage.removeItem('authToken');
      }
    }
    setIsLoading(false);
  }, []);

  const login = async (credenciais: LoginDTO) => {
    const response = await apiLogin(credenciais);
    const { token } = response;
    
    localStorage.setItem('authToken', token);
    const decodedUser = jwtDecode<User>(token);

    setUser(decodedUser);
    setAuthToken(token);
  };

  const logout = () => {
    localStorage.removeItem('authToken');
    setUser(null);
    setAuthToken(null);
  };
  
  const isAuthenticated = !!user;

  return (
    <AuthContext.Provider value={{ user, login, logout, isAuthenticated,isLoading}}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
}