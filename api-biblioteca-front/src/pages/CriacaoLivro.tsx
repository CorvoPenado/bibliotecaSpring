
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { criarLivro, buscarAutores, buscarGeneros} from '@/services/api';
import {Autor, Genero  } from '@/services/types';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { toast } from 'sonner';

function CriacaoLivro() {
    const [titulo, setTitulo] = useState('');
    const [sinopse, setSinopse] = useState('');
    const [autorId, setAutorId] = useState<number | null>(null);
    const [generoId, setGeneroId] = useState<number | null>(null);

    const [autores, setAutores] = useState<Autor[]>([]);
    const [generos, setGeneros] = useState<Genero[]>([]);
    
    const navigate = useNavigate();

    useEffect(() => {
        async function carregarOpcoes() {
            try {
                const [autoresData, generosData] = await Promise.all([
                    buscarAutores(),
                    buscarGeneros()
                ]);
                setAutores(autoresData);
                setGeneros(generosData);
            } catch (error) {
                console.error("Erro ao carregar autores e gêneros", error);
                alert("Não foi possível carregar as opções de autor e gênero.");
            }
        }
        carregarOpcoes();
    }, []);

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        if (!titulo || !sinopse || !autorId || !generoId) {
            alert("Por favor, preencha todos os campos.");
            return;
        }

        try {
            const novoLivro = { titulo, sinopse, autorId, generoId };
            await criarLivro(novoLivro);
            toast.success(`Livro "${novoLivro.titulo}" criado com sucesso!`);
            navigate('/dados'); 
        } catch (error) {
            console.error("Erro ao criar livro:", error);
            toast.success('Erro ao criar livro. Verifique os dados.');
        }
    }

    return (
        <div className='container mx-auto px-4 py-8'>
            <Card className="w-full max-w-2xl mx-auto">
                <CardHeader className="text-center">
                    <CardTitle className="text-2xl">Cadastro de Novo Livro</CardTitle>
                    <CardDescription>Preencha os dados para adicionar um novo livro ao acervo.</CardDescription>
                </CardHeader>
                <CardContent>
                    <form onSubmit={handleSubmit}>
                        <div className="grid gap-6">
                            <div className="grid gap-2">
                                <Label htmlFor="titulo">Título do Livro</Label>
                                <Input id="titulo" value={titulo} onChange={(e) => setTitulo(e.target.value)} required />
                            </div>

                            <div className="grid gap-2">
                                <Label htmlFor="sinopse">Sinopse</Label>
                                <Textarea id="sinopse" value={sinopse} onChange={(e) => setSinopse(e.target.value)} required />
                            </div>

                            <div className="grid gap-2">
                                <Label htmlFor="autor">Autor</Label>
                                <Select onValueChange={(value) => setAutorId(parseInt(value))} required>
                                    <SelectTrigger id="autor">
                                        <SelectValue placeholder="Selecione um autor" />
                                    </SelectTrigger>
                                    <SelectContent>
                                        {autores.map(autor => (
                                            <SelectItem key={autor.id} value={String(autor.id)}>
                                                {autor.nome}
                                            </SelectItem>
                                        ))}
                                    </SelectContent>
                                </Select>
                            </div>

                            <div className="grid gap-2">
                                <Label htmlFor="genero">Gênero</Label>
                                <Select onValueChange={(value) => setGeneroId(parseInt(value))} required>
                                    <SelectTrigger id="genero">
                                        <SelectValue placeholder="Selecione um gênero" />
                                    </SelectTrigger>
                                    <SelectContent>
                                        {generos.map(genero => (
                                            <SelectItem key={genero.id} value={String(genero.id)}>
                                                {genero.nome}
                                            </SelectItem>
                                        ))}
                                    </SelectContent>
                                </Select>
                            </div>
                            
                            <Button type="submit" className="w-full mt-4">
                                Salvar Livro
                            </Button>
                        </div>
                    </form>
                </CardContent>
            </Card>
        </div>
    );
}

export default CriacaoLivro;