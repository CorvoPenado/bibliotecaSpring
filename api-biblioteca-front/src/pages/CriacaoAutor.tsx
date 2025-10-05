
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { criarAutor } from '@/services/api';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { toast } from 'sonner';

function CriacaoAutor() {
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        try {
            const novoAutor = { nome, email };
            await criarAutor(novoAutor);
            
            toast.success(`Autor "${novoAutor.nome}" criado com sucesso!`);
            
            // navigate('/pagina'); 
            setNome('');
            setEmail('');

        } catch (error) {
            console.error("Erro ao criar autor:", error);
            toast.success('Erro ao criar autor. Verifique os dados.');
        }
    }

    return (
        <div className='container mx-auto px-4 py-8'>
            <Card className="w-full max-w-lg mx-auto">
                <CardHeader className="text-center">
                    <CardTitle className="text-2xl">Cadastro de Novo Autor</CardTitle>
                    <CardDescription>Preencha os dados para adicionar um novo autor ao sistema.</CardDescription>
                </CardHeader>
                <CardContent>
                    <form onSubmit={handleSubmit}>
                        <div className="grid gap-4">
                            <div className="grid gap-2">
                                <Label htmlFor="nome">Nome do Autor</Label>
                                <Input 
                                    id="nome"
                                    type="text"
                                    value={nome} 
                                    onChange={(e) => setNome(e.target.value)}
                                    placeholder="Ex: Machado de Assis"
                                    required
                                />
                            </div>

                            <div className="grid gap-2">
                                <Label htmlFor="email">Email</Label>
                                <Input 
                                    id="email"
                                    type="email"
                                    value={email} 
                                    onChange={(e) => setEmail(e.target.value)}
                                    placeholder="email@exemplo.com"
                                    required
                                />
                            </div>
                            
                            <Button type="submit" className="w-full mt-2">
                                Salvar Autor
                            </Button>
                        </div>
                    </form>
                </CardContent>
            </Card>
        </div>
    );
}

export default CriacaoAutor;