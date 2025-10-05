
import { useState, useEffect } from 'react';
import { buscarDetalhesCopias, } from '../services/api';
import { DetalhesCopia } from '../services/types';
import { ModalEmprestimo } from '@/components/ModalEmprestimo';

// Importando todos os componentes de UI necessários
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card";
import { Badge } from '@/components/ui/badge';
import { Button } from '@/components/ui/button';
import { Loader2, BookX } from 'lucide-react';

function DadosPage() {
    const [dados, setDados] = useState<DetalhesCopia[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [copiaParaEmprestimo, setCopiaParaEmprestimo] = useState<DetalhesCopia | null>(null);


    async function carregarDados() {
        console.log("ATUALIZANDO DADOS!");
        try {
            const copiasDaApi = await buscarDetalhesCopias();
            setDados(copiasDaApi);
        } catch (error) {
            console.error("Erro ao buscar dados das cópias:", error);
        } finally {
            setLoading(false);
        }
    }
    
    useEffect(() => {
        carregarDados();
    }, []);

    if (loading) {
        return (
            <div className="flex justify-center items-center h-[70vh]">
                <div className="flex flex-col items-center gap-4">
                    <Loader2 className="h-12 w-12 animate-spin text-primary" />
                    <p className="text-muted-foreground">Carregando acervo...</p>
                </div>
            </div>
        );
    }

    if (dados.length === 0) {
        return (
            <div className="flex justify-center items-center h-[70vh]">
                <div className="flex flex-col items-center gap-4 text-center">
                    <BookX className="h-12 w-12 text-muted-foreground" />
                    <h2 className="text-2xl font-semibold">Nenhuma Cópia Encontrada</h2>
                    <p className="text-muted-foreground max-w-md">Parece que não há livros ou cópias cadastradas no momento. Itens cadastrados aparecerão aqui.</p>
                </div>
            </div>
        );
    }

    return (
        <div className='container mx-auto px-4 py-8'>
            <div className='text-center mb-12'>
                <h1 className='text-4xl font-bold tracking-tight'>Livros disponíveis</h1>
                <p className='text-lg text-muted-foreground mt-2'>Explore nosso acervo completo.</p>
            </div>

            <div className='grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6'>
                {dados.map((copia) => (
                    <Card key={copia.id} className="flex flex-col">
                        <CardHeader>
                            <CardTitle className="truncate py-1">{copia.tituloLivro}</CardTitle>
                            <CardDescription>por {copia.nomeAutor}</CardDescription>
                        </CardHeader>
                        <CardContent className="flex-grow space-y-4">
                            <Badge variant="outline">{copia.nomeGenero}</Badge>
                            <div className='flex justify-between text-sm'>
                                <span className='font-semibold'>Status:</span>
                                <span className={copia.statusCopia === 'DISPONÍVEL' ? 'text-green-600' : 'text-red-600'}>
                                    {copia.statusCopia}
                                </span>
                            </div>
                            <div className='flex justify-between text-sm'>
                                <span className='font-semibold'>Quantidade em estoque:</span>
                                <span>{copia.quantidadeCopia}</span>
                            </div>
                        </CardContent>
                        <CardFooter>
                            <Button
                                className="w-full"
                                size="sm"
                                disabled={copia.statusCopia !== 'DISPONÍVEL' || copia.quantidadeCopia === 0}
                                onClick={() => setCopiaParaEmprestimo(copia)}
                            >
                                Alugar
                            </Button>
                        </CardFooter>
                    </Card>
                ))}
            </div>

            {copiaParaEmprestimo && (
                <ModalEmprestimo
                    onPostSucesso={carregarDados}
                    copia={copiaParaEmprestimo}
                    onClose={() => setCopiaParaEmprestimo(null)}
                />
            )}
        </div>
    );
}

export default DadosPage;