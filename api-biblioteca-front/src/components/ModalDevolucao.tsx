
import { useState } from 'react';
import { Button } from "@/components/ui/button";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter, DialogDescription } from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { DetalhesEmprestimoUsuario } from '@/services/types';

interface ModalDevolucaoProps {
  emprestimo: DetalhesEmprestimoUsuario | null;
  onClose: () => void;
  onConfirm: (quantidade: number) => void;
}

export function ModalDevolucao({ emprestimo, onClose, onConfirm }: ModalDevolucaoProps) {
  const [quantidade, setQuantidade] = useState(1);

  if (!emprestimo) return null;

  const handleConfirmar = () => {
    if (quantidade > 0) {
      onConfirm(quantidade);
    } else {
      alert("A quantidade deve ser pelo menos 1.");
    }
  };

  return (
    <Dialog open={!!emprestimo} onOpenChange={onClose}>
      <DialogContent className="sm:max-w-[425px]">
        <DialogHeader>
          <DialogTitle>Devolver Livro</DialogTitle>
          <DialogDescription>
            Você está devolvendo: <strong>{emprestimo.tituloLivro}</strong>.
          </DialogDescription>
        </DialogHeader>
        <div className="grid gap-4 py-4">
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="quantidade" className="text-right">
              Quantidade
            </Label>
            <Input id="quantidade" type="number" value={quantidade} onChange={(e) => setQuantidade(Number(e.target.value))} className="col-span-3" min={1} />
          </div>
        </div>
        <DialogFooter>
          <Button variant="outline" onClick={onClose}>Cancelar</Button>
          <Button onClick={handleConfirmar}>Confirmar Devolução</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}