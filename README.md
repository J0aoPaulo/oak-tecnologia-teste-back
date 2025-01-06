# Oak Tecnologia Teste Backend

Este é o repositório do backend do projeto de teste da Oak Tecnologia, que implementa um sistema para a gestão de produtos e clientes.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.x**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **JPA (Java Persistence API) com Hibernate**
- **PostgreSQL**
- **Docker**
- **Swagger UI** para documentação da API

## Objetivo do Projeto

Este projeto tem como objetivo implementar uma API RESTful que permite realizar operações de CRUD (Create, Read, Update, Delete) para gerenciamento de **clientes** e **produtos**.

## Estrutura do Projeto

- **/src/main/java/com/api/oak_store**: Contém o código-fonte do projeto.
    - **config**: Configurações da aplicação, como segurança e JWT.
    - **controller**: Contém os controladores da API.
    - **controller/dto**: Contém os objetos de transferência de dados (DTOs).
    - **service**: Contém a lógica de negócios.
    - **repository**: Contém os repositórios JPA para acesso ao banco de dados.

- **/src/main/resources**: Contém arquivos de configuração e recursos da aplicação.
    - **application.properties**: Configurações do Spring Boot, como conexão com o banco de dados, JWT e outras propriedades da aplicação.

## Funcionalidades

### Endpoints da API

#### Clientes

- **POST** `/api/v1/costumers`: Criação de um novo cliente.
- **PUT** `/api/v1/costumers/{costumerId}`: Atualização de um cliente existente.
- **GET** `/api/v1/costumers`: Listagem de todos os clientes.
- **GET** `/api/v1/costumers/{costumerId}`: Obter um cliente pelo ID.
- **DELETE** `/api/v1/costumers/{costumerId}`: Exclusão de um cliente pelo ID.

#### Produtos

- **POST** `/api/v1/products`: Criação de um novo produto.
- **PUT** `/api/v1/products/{productId}`: Atualização de um produto existente.
- **GET** `/api/v1/products`: Listagem de todos os produtos.
- **DELETE** `/api/v1/products/{productId}`: Exclusão de um produto pelo ID.

#### Autenticação

- **POST** `/login`: Endpoint para login e geração de token JWT.

### Documentação da API

A documentação da API é gerada automaticamente pelo Swagger UI. Acesse a interface do Swagger UI em:

## Como Rodar o Projeto Localmente

### 1. Clone o Repositório

Clone este repositório para o seu ambiente local:

```bash
git clone https://github.com/J0aoPaulo/oak-tecnologia-teste-back.git
cd oak-tecnologia-teste-back