
import { JSX } from "react";
import { Link, Outlet } from 'react-router-dom';
import { useAuth } from './contexts/AuthContext';
import { Button } from "./components/ui/button";
import { BookMarked } from "lucide-react";
import { Toaster } from "./components/ui/sonner";

function App(): JSX.Element {
  const { isAuthenticated, logout,user} = useAuth();
  
  return (
    <div className="flex flex-col min-h-screen bg-gray-50">
      
      <header className="sticky top-0 z-40 w-full border-b bg-white">
        <nav className="container mx-auto px-4 flex justify-between items-center h-16">
          
          {/* Logo visivel */}
          <Link to="/" className="flex items-center gap-2 text-lg font-semibold hover:text-primary">
            <BookMarked className="h-6 w-6" />
            <span>Biblioteca</span>
          </Link>
          
          {/* Links e bot~~oes */}
          <div className="flex items-center gap-4">
            {isAuthenticated ? (
              // -- Links para usuários LOGADOS --
              <>
                {/*Só verifica, se for admin libera o LINK, caso contrário não aparece*/}
                {user?.roles.includes('ROLE_ADMIN') &&(
                  <>
                    <Link to="/livros/criar" className="text-sm font-medium hover:text-primary transition-colors">
                      Criar Livro
                    </Link>
                    
                    <Link to="/autores/criar" className="text-sm font-medium hover:text-primary transition-colors">
                      Criar Autor
                    </Link>

                    <Link to="/generos/criar" className="text-sm font-medium hover:text-primary transition-colors">
                      Criar Genero
                    </Link>
                  </>
                )}
                <Link to="/dados" className="text-sm font-medium text-muted-foreground hover:text-primary transition-colors">
                  Ver Livros
                </Link>
                <Link to="/perfil" className="text-sm font-medium text-muted-foreground hover:text-primary transition-colors">
                  Acessar Perfil
                </Link>
                <Button onClick={logout} variant="outline" size="sm">
                  Sair
                </Button>
              </>
            ) : (
              // -- Links para usuários DESLOGADOS --
              <>
                <Link to="/login" className="text-sm font-medium text-muted-foreground hover:text-primary transition-colors">
                  Login
                </Link>
                <Button asChild size="sm">
                  <Link to="/cadastro">Cadastrar</Link>
                </Button>
              </>
            )}
          </div>
        </nav>
      </header>

      <main className="flex-grow w-full">
        <Outlet />
      </main>
      <Toaster richColors />
    </div>
  );
}

export default App;