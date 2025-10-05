import axios from 'axios';

import { DetalhesEmprestimoUsuario,DetalhesCopia,EmprestimoParaCriar,AutorParaCriar,LoginDTO, LoginResponseDTO, UsuarioParaCriar,PerfilUsuarioDTO, LivroDTO,Genero,Autor, GeneroParaCriar, EmprestimoDevolucaoDTO } from './types';

export interface Livro{
    id: number;
    titulo:string;
    sinopse:string;
    nomeAutor:string;
    nomeGenero:string;
}

const apiClient = axios.create({
    //BASE DA API, como Livros, Autores e etc...
    baseURL: 'http://localhost:8080',
    headers:{
        'Content-Type':'application/json',
    }
});

export const devolverEmprestimo = async (emprestimoId: number, dadosDevolucao: EmprestimoDevolucaoDTO): Promise<void> => {
  await apiClient.patch(`/emprestimos/${emprestimoId}/devolver`, dadosDevolucao);
}

export const buscarEmprestimos = async():Promise<DetalhesEmprestimoUsuario[]> =>{
  const response = await apiClient.get<DetalhesEmprestimoUsuario[]>('/emprestimos/meus');
  return response.data;
}

export const criarAutor = async (novoAutor: AutorParaCriar) => {
  return apiClient.post('/autores/create', novoAutor);
};

export const buscarAutores = async (): Promise<Autor[]> => {
  const response = await apiClient.get<Autor[]>('/autores/lista');
  return response.data;
};

export const criarGenero = async (novoGenero: GeneroParaCriar) => {
  return apiClient.post('/generos/create', novoGenero);
};

export const buscarGeneros = async (): Promise<Genero[]> => {
  const response = await apiClient.get<Genero[]>('/generos/lista');
  return response.data;
};

export const criarEmprestimo = async (novoEmprestimo: EmprestimoParaCriar) => {
  return apiClient.post('/emprestimos/create', novoEmprestimo);
};

export const buscarDetalhesCopias = async (): Promise<DetalhesCopia[]> => {
  const response = await apiClient.get<DetalhesCopia[]>('/copias/detalhes');
  return response.data;
};

export const criarLivro = async (novoLivro: LivroDTO)=>{
  return apiClient.post('/livros/create',novoLivro);
}

export const buscarLivros = async (): Promise<Livro[]> =>{
    try {
        const response = await apiClient.get<Livro[]>('/livros/lista');
        return response.data;
    } catch (error) {
        console.error('Erro na chamada da API para buscar livros:', error);
        throw error;
    }
}

export const perfilUsuario = async (): Promise<PerfilUsuarioDTO> =>{
    try {
        const response = await apiClient.get<PerfilUsuarioDTO>('usuarios/me');
        return response.data;
    } catch (error) {
        console.error('Erro na chamada da API para buscar PERFIL USUARIO:', error);
        throw error;
    }
}

export const login = async (credenciais: LoginDTO): Promise<LoginResponseDTO> =>{
    const response = await apiClient.post<LoginResponseDTO>('/login',credenciais);
    return response.data;
}

//Cria novo USUARIO, SIMPLES NÃO ?
export const cadastrarUsuario = async (novoUsuario: UsuarioParaCriar)=>{
    await apiClient.post('/usuarios/create',novoUsuario);
}

export const setAuthToken = (token: string | null) => {
  if (token) {
    apiClient.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  } else {
    //Se o usuario fizer logout ele tira o acesso
    delete apiClient.defaults.headers.common['Authorization'];
  }
};

apiClient.interceptors.response.use(
  (response) => response, 
  (error) => {
    if (error.response && (error.response.status === 401 || error.response.status === 403)) {
      // desloga o user
      localStorage.removeItem('authToken');
      setAuthToken(null);
      // Redireciona para a página de login
      window.location.href = '/login'; 
    }
    return Promise.reject(error);
  }
);