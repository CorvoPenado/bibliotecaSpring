export interface LoginDTO{
    email:string;
    senha:string;
}

export interface LoginResponseDTO{
    token:string;
}

export interface UsuarioParaCriar {
  nome: string;
  email: string;
  senha: string;
  idade: number;
}

export interface PerfilUsuarioDTO{
    id:number;
    nome:string;
    email:string;
    idade:number;
}

export interface LivroDTO{
    titulo:string;
    sinopse:string;
    autorId:number;
    generoId:number;
}

export interface Autor {
  id: number;
  nome: string;
}

export interface Genero {
  id: number;
  nome: string;
}

export interface GeneroParaCriar {
  nome: string;
}

export interface AutorParaCriar {
  nome: string;
  email: string;
}

export interface EmprestimoParaCriar {
  dataTermino: string; //string no formato iso
  quantidade: number;
  usuarioId: number;
  copiaId: number;
}
export interface DetalhesCopia {
  id: number; 
  tituloLivro: string;
  nomeGenero: string;
  nomeAutor: string;
  statusCopia: string;
  quantidadeCopia: number;
}

export interface DetalhesEmprestimoUsuario {
  id:number;
  nomeUsuario: string;
  tituloLivro: string;
  quantidadeCopias: number; 
  emDiaEmprestimo: boolean; 
  finalizadoEmprestimo: boolean;
  multaEmprestimo: number;
  dataTerminoEmprestimo: string | Date; 
  dataFinalizadoEmprestimo: string | null; 
}

export interface EmprestimoDevolucaoDTO {
  usuarioId: number; 
  quantidadeDevolvida: number;
}