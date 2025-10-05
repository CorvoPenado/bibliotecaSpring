// Em: src/routes/AdminRoute.tsx
import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { Loader2 } from 'lucide-react';

export function AdminRoute() {
  const { isAuthenticated, user,isLoading } = useAuth();

  if (isLoading) {
        return (
            <div className="flex justify-center items-center h-[70vh]">
                <div className="flex flex-col items-center gap-4">
                    <Loader2 className="h-12 w-12 animate-spin text-primary" />
                    <p className="text-muted-foreground">Carregando acervo...</p>
                </div>
            </div>
        );
    }

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  // Se o usuário não for ADMIN, redireciona para a página inicial
  if (!user?.roles.includes('ROLE_ADMIN')) {
    alert("Acesso negado. Você não tem permissão de Administrador.");
    return <Navigate to="/" replace />;
  }

  return <Outlet />; // Se for admin, permite o acesso
}