// Em: src/pages/HomePage.tsx

import React from 'react';
import { Link } from 'react-router-dom';
import { Button } from '@/components/ui/button';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { Code, BookCheck, Database, Server, ShieldCheck } from 'lucide-react';

function HomePage() {
  const techFeatures = [
    "Autenticação e Autorização com Spring Security", 
    "Geração e Validação de Tokens JWT", 
    "Armazenamento Seguro de Senhas com BCrypt",
    "Tratamento de Exceções Global com @RestControllerAdvice",
    "Validação de Entrada de Dados com Bean Validation (@Valid)",
    "Consultas Customizadas com JPQL (@Query)",
    "Tarefas Agendadas (@Scheduled) para multas",
    "Gerenciamento Transacional (@Transactional)",
  ];

  const businessRules = [
    "Livro só pode ser cadastrado se o autor já existir.",
    "Criação automática de cópias ao cadastrar um livro.",
    "Empréstimo diminui a quantidade de cópias disponíveis.",
    "Devolução aumenta a quantidade e finaliza o empréstimo.",
    "Usuário com empréstimos ativos não pode ser deletado.",
  ];

  const securityRules = [ 
    "Acesso a endpoints separado por perfis (ROLE_ADMIN, ROLE_USER).",
    "Endpoints de Login e Cadastro são públicos.",
    "Visualização de livros é permitida para o perfil USER.",
    "Deleção de usuários é uma rota restrita ao perfil ADMIN.",
  ];

  return (
    <div className="container mx-auto px-4 py-8 md:py-12 ">
      <main className="flex flex-col items-center text-center ">
        
        <section className="w-full max-w-4xl space-y-4">
          <Badge variant="outline">Backend Portfolio Project</Badge>
          <h1 className="text-4xl md:text-5xl font-bold tracking-tight">
            API de Gerenciamento de Biblioteca
          </h1>
          <p className="text-lg text-muted-foreground">
            Uma API RESTful construída com Spring Boot para gerenciar o acervo, usuários e empréstimos de uma biblioteca, demonstrando as melhores práticas do ecossistema Spring.
          </p>
          <div className="flex justify-center gap-4">
            <Button asChild>
              <Link to="/dados">Ver Dados da API</Link>
            </Button>
            <Button variant="outline" asChild>
              <a href="http://localhost:8080/swagger-ui.html" target="_blank" rel="noopener noreferrer">
                Documentação Swagger
              </a>
            </Button>
          </div>
        </section>

        <section className="w-full max-w-6xl mt-16 grid grid-cols-1 md:grid-cols-2 gap-8 text-left">
          <Card>
            <CardHeader className="flex flex-row items-center gap-4">
              <Code className="w-8 h-8 text-primary" />
              <CardTitle>Features Técnicas</CardTitle>
            </CardHeader>
            <CardContent>
              <ul className="space-y-2">
                {techFeatures.map((feature, index) => (
                  <li key={index} className="flex items-start gap-2">
                    <Server className="w-4 h-4 mt-1 flex-shrink-0 text-green-500" />
                    <span>{feature}</span>
                  </li>
                ))}
              </ul>
            </CardContent>
          </Card>

          <Card>
            <CardHeader className="flex flex-row items-center gap-4">
              <BookCheck className="w-8 h-8 text-primary" />
              <CardTitle>Regras de Negócio Implementadas</CardTitle>
            </CardHeader>
            <CardContent>
              <ul className="space-y-2">
                {businessRules.map((rule, index) => (
                  <li key={index} className="flex items-start gap-2">
                    <Database className="w-4 h-4 mt-1 flex-shrink-0 text-green-500" />
                    <span>{rule}</span>
                  </li>
                ))}
              </ul>
            </CardContent>
          </Card>
        </section>

        {/* NOVA SEÇÃO DE SEGURANÇA */}
        <section className="w-full max-w-6xl mt-8 text-left">
            <Card>
                <CardHeader className="flex flex-row items-center gap-4">
                    <ShieldCheck className="w-8 h-8 text-primary" />
                    <CardTitle>Segurança e Autorização</CardTitle>
                </CardHeader>
                <CardContent>
                    <ul className="space-y-2">
                        {securityRules.map((rule, index) => (
                        <li key={index} className="flex items-start gap-2">
                            <ShieldCheck className="w-4 h-4 mt-1 flex-shrink-0 text-green-500" />
                            <span>{rule}</span>
                        </li>
                        ))}
                    </ul>
                </CardContent>
            </Card>
        </section>

        <section className="w-full max-w-4xl mt-16">
          <h2 className="text-3xl font-bold tracking-tight">🛠️ Stack de Tecnologias</h2>
          <div className="flex flex-wrap justify-center gap-4 mt-6">
            <Badge>Java 17</Badge>
            <Badge>Spring Boot 3</Badge>
            <Badge>Spring Security</Badge> 
            <Badge>Spring Data JPA</Badge>
            <Badge>PostgreSQL</Badge>
            <Badge>Maven</Badge>
            <Badge>JWT</Badge> 
            <Badge>Springdoc OpenAPI</Badge>
          </div>
        </section>

      </main>
    </div>
  );
}

export default HomePage;