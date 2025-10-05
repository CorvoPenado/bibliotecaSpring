// Em: src/pages/CriacaoGenero.tsx

import { useState } from 'react';
import { criarGenero } from '@/services/api';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { toast } from 'sonner'; 

function CriacaoGenero() {
    const [nome, setNome] = useState('');

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        try {
            await criarGenero({ nome });
            
            toast.success(`Gênero "${nome}" criado com sucesso!`); // Feedback mais elegante
            setNome(''); 

        } catch (error) {
            console.error("Erro ao criar gênero:", error);
            toast.error("Erro ao criar gênero. Verifique os dados.");
        }
    }

    return (
        <div className='container mx-auto px-4 py-8'>
            <Card className="w-full max-w-lg mx-auto">
                <CardHeader className="text-center">
                    <CardTitle className="text-2xl">Cadastro de Novo Gênero</CardTitle>
                    <CardDescription>Adicione um novo gênero literário ao sistema.</CardDescription>
                </CardHeader>
                <CardContent>
                    <form onSubmit={handleSubmit}>
                        <div className="grid gap-4">
                            <div className="grid gap-2">
                                <Label htmlFor="nome">Nome do Gênero</Label>
                                <Input 
                                    id="nome"
                                    type="text"
                                    value={nome} 
                                    onChange={(e) => setNome(e.target.value)}
                                    placeholder="Ex: Ficção Científica"
                                    required
                                />
                            </div>
                            
                            <Button type="submit" className="w-full mt-2">
                                Salvar Gênero
                            </Button>
                        </div>
                    </form>
                </CardContent>
            </Card>
        </div>
    );
}

export default CriacaoGenero;