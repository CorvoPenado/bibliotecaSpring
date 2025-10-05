
import { DetalhesEmprestimoUsuario } from '../services/types'; 
import { Button } from './ui/button';

interface CardEmprestimoProps {
  emprestimo: DetalhesEmprestimoUsuario;
  onAbrirModalDevolucao: () => void; 
}

function CardEmprestimo({ emprestimo, onAbrirModalDevolucao }: CardEmprestimoProps) {

  const formatarData = (data: string | Date | null) => {
    if (!data) return 'N/A';
    return new Date(data).toLocaleDateString('pt-BR', {
      day: '2-digit', month: '2-digit', year: 'numeric'
    });
  };

  return (
    <div className="border rounded-lg p-4 mb-4 shadow-sm bg-white">
      
      <h3 className="font-bold text-lg text-primary">{emprestimo.tituloLivro}</h3>
      <hr className="my-2" />

      <div className="space-y-2 text-sm text-gray-700">
        
        <div className="flex justify-between items-center">
          <span className="font-semibold">Status:</span>
          {emprestimo.finalizadoEmprestimo ? (
            <span className="font-semibold text-gray-500">Finalizado</span>
          ) : emprestimo.emDiaEmprestimo ? (
            <span className="font-semibold text-green-600">Em Dia</span>
          ) : (
            <span className="font-semibold text-red-600">Atrasado</span>
          )}
        </div>

        <div className='flex justify-between items-center'>
            <span>Quantidade: </span>
            <span>{emprestimo.quantidadeCopias}</span>
        </div>

        <div className="flex justify-between">
          <span>Devolver até:</span>
          <span>{formatarData(emprestimo.dataTerminoEmprestimo)}</span>
        </div>

        {emprestimo.finalizadoEmprestimo && (
           <div className="flex justify-between">
             <span>Devolvido em:</span>
             <span>{formatarData(emprestimo.dataFinalizadoEmprestimo)}</span>
           </div>
        )}

        {emprestimo.multaEmprestimo > 0 && (
          <div className="flex justify-between font-semibold text-destructive">
            <span>Multa:</span>
            <span>{emprestimo.multaEmprestimo.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</span>
          </div>
        )}
      </div>
      
      {!emprestimo.finalizadoEmprestimo && (
        <div className="mt-4 pt-4 border-t">
          <Button 
            className="w-full" 
            onClick={onAbrirModalDevolucao} 
          >
            Devolver Livro
          </Button>
        </div>
      )}
    </div>
  );
}

export default CardEmprestimo;