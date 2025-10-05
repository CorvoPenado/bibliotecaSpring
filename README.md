# 📚 API de Gerenciamento de Biblioteca (Biblioteca-Spring)

API RESTful construída com Spring Boot para gerenciar o acervo, usuários e empréstimos de uma biblioteca. Este projeto foi desenvolvido como uma peça de portfólio para demonstrar habilidades no ecossistema Spring e na construção de backends.

![Java](https://img.shields.io/badge/Java-17%2B-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green?logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue?logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-4-red?logo=apachemaven)

---

## 🚀 Demonstração em Ação

As animações abaixo demonstram os principais fluxos da API sendo testados.

| **Endpoints de Usuários (CRUD)** | **Endpoints de Livros (com DTO)** |
| :---: | :---: |
| ![Users](assets/usuarios_points.gif) | ![Books](assets/livros_points.gif) |
| **Endpoints de Empréstimos (Regras de Negócio)** | **Estrutura do Banco e DTOs** |
| ![Emprestimos](assets/emprestimos_points.gif) | ![Schemes](assets/schemes.gif) |

---

## ✨ Features e Regras de Negócio

Este projeto implementa uma série de funcionalidades e conceitos importantes no desenvolvimento de APIs:

#### Features Técnicas
- **Tratamento de Exceções Global:** Uso de `@RestControllerAdvice` para capturar exceções e retornar respostas de erro JSON padronizadas e limpas.
- **Validação de Entrada de Dados:** Utilização de Bean Validation (`@Valid`) nos DTOs para garantir a integridade dos dados na entrada da API.
- **Consultas Customizadas com JPQL:** Queries otimizadas com `@Query` para buscar dados complexos e projetá-los em DTOs específicos para relatórios.
- **Métodos de Consulta Derivados:** Uso do poder do Spring Data JPA para criar consultas complexas diretamente a partir da assinatura dos métodos no repositório.
- **Tarefas Agendadas (`@Scheduled`):** Um processo automático que verifica empréstimos atrasados e aplica multas, simulando um "cron job" no backend.
- **Gerenciamento Transacional (`@Transactional`):** Garante a consistência dos dados em operações de negócio que envolvem múltiplas alterações no banco.

#### Regras de Negócio Implementadas
- Um livro só pode ser cadastrado se o autor já existir no sistema.
- Ao cadastrar um livro, uma cópia com quantidade padrão é criada automaticamente.
- A criação de um empréstimo diminui a quantidade disponível da cópia correspondente. Se a quantidade chegar a zero, o status da cópia muda para `INDISPONÍVEL`.
- A devolução de um item de empréstimo aumenta a quantidade disponível da cópia e, se for o caso, finaliza o empréstimo.
- Um usuário não pode ser deletado se possuir empréstimos ativos (não finalizados), garantindo a integridade referencial dos dados.

---

## 🛠️ Stack de Tecnologias

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL
* **Build Tool:** Maven
* **Documentação:** Springdoc OpenAPI (Swagger)

---

## ⚙️ Configuração e Execução

Siga os passos abaixo para executar o projeto localmente.

#### Pré-requisitos
- Java JDK 17 ou superior
- Apache Maven 3.8 ou superior
- PostgreSQL
- Um cliente de API como [Postman](https://www.postman.com/) ou [Insomnia](https://insomnia.rest/).

#### Passos
1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/bibliotecaSpring.git](https://github.com/seu-usuario/bibliotecaSpring.git)
    cd bibliotecaSpring
    ```

2.  **Configure o Banco de Dados:**
    - Crie um banco de dados no PostgreSQL com o nome `bibliotecaDB`.
    - Verifique o arquivo `src/main/resources/application.properties` e ajuste as propriedades `spring.datasource.username` e `spring.datasource.password` de acordo com a sua configuração local do PostgreSQL.

3.  **Execute a Aplicação:**
    - Pelo terminal, na raiz do projeto, execute:
    ```bash
    mvn spring-boot:run
    ```
    - Alternativamente, importe o projeto em sua IDE (IntelliJ, Eclipse, etc.) e execute a classe principal `BibliotecaApplication`.

4.  **Acesse a Aplicação:** A API estará disponível em `http://localhost:8080`.

---

## 📄 Documentação da API (Swagger)

Toda a API está documentada e pode ser testada interativamente através do Swagger UI.

Após iniciar a aplicação, acesse a seguinte URL no seu navegador:
[**http://localhost:8080/swagger-ui.html**](http://localhost:8080/swagger-ui.html)

---

## 🔄 Próximos Passos e Melhorias

- [ ] Adicionar **testes de unidade e integração** para garantir a qualidade e a estabilidade do código.
- [ ] Implementar **paginação** (`Pageable`) nos endpoints de listagem para lidar com grandes volumes de dados.
