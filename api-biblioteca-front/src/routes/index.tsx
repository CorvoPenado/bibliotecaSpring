import { createBrowserRouter } from 'react-router-dom';
import App from '@/App';
import HomePage from '../pages/HomePage'
import DadosPage from '../pages/DadosPage';
import { LoginPage } from '../pages/LoginPage';

import { ProtectedRoute } from './ProtectedRoute';
import PerfilPage from '@/pages/PerfilPage';
import CadastroPage from '@/pages/CadastroPage';
import CriacaoLivro from '@/pages/CriacaoLivro';
import { AdminRoute } from './AdminRoute';
import CriacaoAutor from '@/pages/CriacaoAutor';
import CriacaoGenero from '@/pages/CriacaoGenero';

export const router = createBrowserRouter([
  // Rotas públicas que não precisam de login
  //Aqui definimos rotas publicas onde não terão o que o Layout APP TEM
  /*
  {
    path: '/login',
    element: <LoginPage />,
  },
  { 
    path: '/cadastro', 
    element: <CadastroPage />,
  },*/

  // Rotas protegidas
  {
    path: '/',
    element: <App />, // O layout principal com menu, etc.
    children: [
      {
        path: '/login',
        element: <LoginPage />,
      },
      {
        path: '/cadastro',
        element: <CadastroPage />,
      },
      {
        path: '/',
        element: <HomePage />,
      },
      {
        element: <ProtectedRoute />, // "Envolvemos" as rotas privadas com o protetor
        children: [
          {
            path: '/dados',
            element: <DadosPage />,
          },
          {
            path: '/perfil',
            element: <PerfilPage />,
          },
          {
            //Exclusivo para rotas de ADMINISTRADORES
            element: <AdminRoute />,
            children: [
              { path: '/livros/criar', element: <CriacaoLivro /> },
              { path: '/autores/criar', element: <CriacaoAutor /> },
              { path: '/generos/criar', element: <CriacaoGenero /> }
            ]
          }
          // Adicione outras rotas que precisam de login aqui dentro
          // { path: '/perfil', element: <PerfilPage /> },
        ],
      },
    ],
  },
]);