# Desafio 3 Java Spring Professional - DevSuperior

Esse projeto consiste no segundo desafio da formação Java Spring Professional da plataforma Devsuperior.

## Tecnologias

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- Banco de dados H2
- Bean Validation
- Maven

## Objetivo

O objetivo é construir um projeto Spring Boot contendo um CRUD completo de web services REST para acessar um recurso de clientes, contendo cinco operações básicas

- Busca paginada de recursos
- Busca de recursos por id
- Inserir novo recurso
- Atualizar recurso
- Deletar recurso

## Diagrama de classe

<img width="195" height="203" alt="Image" src="https://github.com/user-attachments/assets/4cd3f469-50bd-4995-9195-91882d44dbdf" />

Com base nas especificações da entidade `Client` acima, o banco de dados H2 já possui um seed de 10 clientes

## Tratamento de exceções

- ID não encontrado (para GET por id, PUT e DELETE), deve retornar código 404
- Erro de validação, deve retornar código 422 e mensagem customizada para cada campo inválido. As regras de validação são:
    - `Nome` não pode ser vazio
    - `Data de nascimento` não pode ser data futura

## Exemplo de entrada de dados

<img width="316" height="561" alt="Image" src="https://github.com/user-attachments/assets/ebd21893-0d29-428e-8039-d4d9cd7ef680" />

## Critérios de correção

1. Busca por ID retorna cliente existente
2. Busca por ID retorna 404 para cliente inexistente
3. Busca paginada retorna listagem paginada corretamente
4. Inserção de cliente, insere cliente com dados válidos
5. Inserção de cliente retorna 422 e mensagens customizadas com dados inválidos
6. Atualização de cliente, atualiza cliente com dados válidos
7. Atualização de cliente, retorna 404 para cliente inexistente
8. Atualização de cliente, retorna 422 e mensagens customizadas com dados inválidos
9. Deleção de cliente, deleta cliente existente
10. Deleção de cliente, retorna 404 para cliente inexistente

## Competências avaliadas

- Implementação de operações de CRUD
- Tratamento de exceções
- Customização de respostas HTTP
- Validação de dados com Bean Validation