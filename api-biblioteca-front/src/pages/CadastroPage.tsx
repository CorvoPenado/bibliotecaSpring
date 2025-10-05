
import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom'; 
import { cadastrarUsuario } from '@/services/api';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { toast } from 'sonner';

function CadastroPage() {
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [idade, setIdade] = useState('');
    const navigate = useNavigate(); // Hook para navegação

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        try {
            const idadeNumerica = parseInt(idade, 10);
            if (isNaN(idadeNumerica) || idadeNumerica < 1) {
                alert('Por favor, insira uma idade válida.');
                return;
            }

            const novoUsuario = { nome, email, senha, idade: idadeNumerica };
            await cadastrarUsuario(novoUsuario);
            
            toast.success(`Usuário "${novoUsuario.nome}" criado com sucesso! Você será redirecionado para o login.`);
            
            // redirect page login
            navigate('/login');

        } catch (error) {
            console.error("Erro ao criar usuário:", error);
            toast.success('Erro ao criar usuário. Verifique os dados ou tente um email diferente.');
        }
    }

    return (
        <div className='flex justify-center items-center min-h-screen bg-gray-50 p-4'>
            <Card className="w-full max-w-md">
                <CardHeader className="text-center">
                    <CardTitle className="text-2xl">Cadastro de Novo Usuário</CardTitle>
                    <CardDescription>Crie sua conta para acessar a biblioteca.</CardDescription>
                </CardHeader>
                <CardContent>
                    <form onSubmit={handleSubmit}>
                        <div className="grid gap-4">
                            <div className="grid gap-2">
                                <Label htmlFor="nome">Nome Completo</Label>
                                <Input 
                                    id="nome"
                                    type="text"
                                    value={nome} 
                                    onChange={(e) => setNome(e.target.value)}
                                    placeholder="Seu nome"
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

                            <div className="grid gap-2">
                                <Label htmlFor="senha">Senha</Label>
                                <Input 
                                    id="senha"
                                    type="password"
                                    value={senha} 
                                    onChange={(e) => setSenha(e.target.value)}
                                    placeholder="Mínimo 3 caracteres"
                                    required
                                />
                            </div>

                            <div className="grid gap-2">
                                <Label htmlFor="idade">Idade</Label>
                                <Input 
                                    id="idade"
                                    type="number"
                                    value={idade} 
                                    onChange={(e) => setIdade(e.target.value)}
                                    placeholder="Sua Idade"
                                    min="1"
                                    required
                                />
                            </div>
                            
                            <Button type="submit" className="w-full mt-2">
                                Cadastrar
                            </Button>
                        </div>
                    </form>
                    <div className="mt-4 text-center text-sm">
                        Já tem uma conta?{' '}
                        <Link to="/login" className="underline">
                            Faça login
                        </Link>
                    </div>
                </CardContent>
            </Card>
        </div>
    );
}

export default CadastroPage;