
import { useState, useEffect } from 'react';
import { perfilUsuario, buscarEmprestimos, devolverEmprestimo } from '../services/api';
import { PerfilUsuarioDTO, DetalhesEmprestimoUsuario } from '../services/types';
import { useAuth } from '@/contexts/AuthContext';
import { toast } from 'sonner';

import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Loader2, AlertTriangle } from 'lucide-react';
import CardEmprestimo from '@/components/CardEmprestimo';
import { ModalDevolucao } from '@/components/ModalDevolucao';

function PerfilPage() {
    const { user } = useAuth(); 

    const [dados, setDados] = useState<PerfilUsuarioDTO | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);
    const [emprestimos, setEmprestimos] = useState<DetalhesEmprestimoUsuario[]>([]); // Iniciar com array vazio é mais seguro
    const [emprestimoParaDevolver, setEmprestimoParaDevolver] = useState<DetalhesEmprestimoUsuario | null>(null);

    async function carregarDados() {
        try {
            const [usuarioApi, emprestimosUser] = await Promise.all([
                perfilUsuario(),
                buscarEmprestimos()
            ]);
            setDados(usuarioApi);
            setEmprestimos(emprestimosUser);
        } catch (err) {
            console.error("Erro ao buscar dados da página de perfil:", err);
            setError("Não foi possível carregar os dados. Tente novamente mais tarde.");
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        carregarDados();
    }, []);

    const handleConfirmarDevolucao = async (quantidadeDevolvida: number) => {
        if (!emprestimoParaDevolver || !user) return;

        try {
            await devolverEmprestimo(emprestimoParaDevolver.id, {
                usuarioId: user.userId,
                quantidadeDevolvida
            });
            toast.success("Livro devolvido com sucesso!");
            setEmprestimoParaDevolver(null);
            await new Promise(r => setTimeout(r, 300));
            // Fecha o modal
            await carregarDados(); // Recarrega os dados para atualizar a lista
        } catch (error) {
            toast.error("Erro ao devolver o livro. Tente novamente.");
            console.error(error);
        }
    };

    if (loading) {
        return (
            <div className="flex justify-center items-center h-[50vh]">
                <div className="flex flex-col items-center gap-4">
                    <Loader2 className="h-12 w-12 animate-spin text-primary" />
                    <p className="text-muted-foreground">Carregando perfil...</p>
                </div>
            </div>
        );
    }

    if (error) {
        return (
            <div className="flex justify-center items-center h-[50vh]">
                <div className="flex flex-col items-center gap-4 text-center text-destructive">
                    <AlertTriangle className="h-12 w-12" />
                    <h2 className="text-2xl font-semibold">Ocorreu um Erro</h2>
                    <p>{error}</p>
                </div>
            </div>
        );
    }

    if (!dados) {
        return null;
    }

    return (
        <div className='container mx-auto px-4 py-8 '>
            <div className='text-center mb-12'>
                <h1 className='text-4xl font-bold tracking-tight'>Meu Perfil</h1>
                <p className='text-lg text-muted-foreground mt-2'>Suas informações de usuário.</p>
            </div>

            <Card className="max-w-2xl mx-auto ">
                <CardHeader className="flex flex-row items-start gap-8 p-6">
                    <Avatar className="h-24 w-24">
                        <AvatarImage src="" alt={dados.nome} />
                        <AvatarFallback className="text-4xl">
                            {dados.nome.split(' ').map(n => n[0]).join('')}
                        </AvatarFallback>
                    </Avatar>
                    <div className="grid gap-1">
                        <CardTitle className="text-3xl">{dados.nome}</CardTitle>
                        <CardDescription className="text-base">{dados.email}</CardDescription>
                    </div>
                </CardHeader>
                <CardContent>
                    <div className="text-sm flex justify-center gap-8">
                        <div>
                            <strong className="text-muted-foreground">ID do Usuário: </strong>
                            <span> <strong>{dados.id}</strong></span>
                        </div>
                        <div>
                            <strong className="text-muted-foreground">Idade: </strong>
                            <span> <strong>{dados.idade} anos</strong></span>
                        </div>
                    </div>
                </CardContent>
            </Card>

            <div className="max-w-2xl mx-auto mt-12">
                <div className='text-center mb-8'>
                    <h2 className='text-3xl font-bold tracking-tight'>Meus Empréstimos</h2>
                    <p className='text-md text-muted-foreground mt-1'>Histórico de seus empréstimos ativos e finalizados.</p>
                </div>

                {emprestimos.length > 0 ? (
                    <div>
                        {emprestimos.map((item) => (
                            <CardEmprestimo
                                key={item.id}
                                emprestimo={item}
                                onAbrirModalDevolucao={() => setEmprestimoParaDevolver(item)}
                            />
                        ))}
                    </div>
                ) : (
                    <div className="text-center py-8 px-4 border rounded-lg bg-gray-50">
                        <p className="text-muted-foreground">Você não possui nenhum empréstimo no momento.</p>
                    </div>
                )}
            </div>

            <ModalDevolucao
                emprestimo={emprestimoParaDevolver}
                onClose={() => setEmprestimoParaDevolver(null)}
                onConfirm={handleConfirmarDevolucao}
            />
        </div>
    );
}

export default PerfilPage;