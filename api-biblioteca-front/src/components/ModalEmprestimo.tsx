
import { useState } from "react";
import { format } from "date-fns";
import { ptBR } from "date-fns/locale";
import { criarEmprestimo } from "@/services/api";
import { useAuth } from "@/contexts/AuthContext";
import { DetalhesCopia } from '../services/types';
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Calendar } from "@/components/ui/calendar";
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogDescription, DialogFooter } from "@/components/ui/dialog";
import { Calendar as CalendarIcon } from "lucide-react";
import { toast } from "sonner";

interface ModalEmprestimoProps {
  copia: DetalhesCopia;
  onClose: () => void;
  onPostSucesso: () => void;
}


export function ModalEmprestimo({ copia, onClose, onPostSucesso }: ModalEmprestimoProps) {
  const { user } = useAuth();
  const [quantidade, setQuantidade] = useState(1);
  const [dataTermino, setDataTermino] = useState<Date>();

  const handleSubmit = async () => {
    if (!quantidade || !dataTermino || !user) {
      toast.warning("Por favor, preencha a quantidade e a data de devolução.");
      return;
    }
    
    if (quantidade > copia.quantidadeCopia) {
        toast.error(`Quantidade indisponível. Máximo para esta cópia: ${copia.quantidadeCopia}`);
        return;
    }

    try {
      const novoEmprestimo = {
        copiaId: copia.id, 
        quantidade,
        usuarioId: user.userId, 
        dataTermino: format(dataTermino, "yyyy-MM-dd'T'HH:mm:ss"),
      };

      await criarEmprestimo(novoEmprestimo);
      toast.success(`Empréstimo do livro "${copia.tituloLivro}" realizado com sucesso!`);
      onClose(); // Fecha o modal
      onPostSucesso();
    } catch (error) {
      toast.error("Erro ao realizar o empréstimo.");
      console.error(error);
    }
  };

  return (
    <Dialog open={true} onOpenChange={onClose}>
      <DialogContent className="sm:max-w-[425px]">
        <DialogHeader>
          <DialogTitle>Confirmar Empréstimo</DialogTitle>
          <DialogDescription>
            Você está alugando: <strong>{copia.tituloLivro}</strong>
          </DialogDescription>
        </DialogHeader>
        
        <div className="grid gap-4 py-4">
          
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="quantidade" className="text-right">Quantidade</Label>
            <Input 
              id="quantidade" 
              type="number" 
              value={quantidade} 
              onChange={e => setQuantidade(parseInt(e.target.value))} 
              min="1"
              max={copia.quantidadeCopia} 
              className="col-span-3" 
            />
          </div>

          <div className="grid grid-cols-4 items-center gap-4">
            <Label className="text-right">Devolução</Label>
            <Popover>
              <PopoverTrigger asChild>
                <Button variant={"outline"} className="col-span-3 justify-start font-normal">
                  <CalendarIcon className="mr-2 h-4 w-4" />
                  {dataTermino ? format(dataTermino, "PPP", { locale: ptBR }) : <span>Escolha uma data</span>}
                </Button>
              </PopoverTrigger>
              <PopoverContent className="w-auto p-0">
                <Calendar mode="single" selected={dataTermino} onSelect={setDataTermino} initialFocus />
              </PopoverContent>
            </Popover>
          </div>
        </div>

        <DialogFooter>
          <Button variant="outline" onClick={onClose}>Cancelar</Button>
          <Button onClick={handleSubmit}>Confirmar Empréstimo</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}