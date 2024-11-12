# Gerenciador de Produtos API

Uma API simples de gerenciamento de produtos, construída com Spring Boot e configurada com Swagger para documentação. A API permite criar, buscar, atualizar e deletar produtos de uma base de dados H2 em memória.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.5**
- **H2 Database**: Banco de dados em memória para ambiente de desenvolvimento.
- **Spring Data JPA**: Para persistência de dados.
- **Springdoc OpenAPI**: Integração com Swagger para documentação da API.

## Funcionalidades

A API permite realizar operações CRUD básicas com a entidade `Product`:
- **Criar Produto**: `POST /products`
- **Buscar Todos os Produtos**: `GET /products`
- **Buscar Produto por ID**: `GET /products/{id}`
- **Buscar Produtos por Nome**: `GET /products/search?name={name}`
- **Buscar Produtos por Categoria**: `GET /products/category?category={category}`
- **Atualizar Produto**: `PUT /products/{id}`
- **Deletar Produto**: `DELETE /products/{id}`

## Instalação

### Pré-requisitos

- Java 17+
- Maven

### Passos para executar

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/gabibento/gerenciador-produtos-api.git
   ```
2. **Entre na pasta do projeto**:
   ```bash
   cd gerenciador-produtos-api
   ```
3. Compile o projeto:
   ```bash
   mvn clean install
   ```
4. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```
A API estará disponível em http://localhost:8080.

**Documentação da API com Swagger**
A documentação da API é gerada automaticamente com o Swagger e pode ser acessada em:
Swagger UI: http://localhost:8080/swagger-ui.html

Através da interface do Swagger UI, é possível explorar e testar os endpoints da API diretamente no navegador.

**Configurações do Banco de Dados**
O projeto utiliza o banco de dados em memória H2. Para acessar o console do H2, vá para: http://localhost:8080/h2-console
Utilize as seguintes credenciais para acesso:

JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (deixe em branco)
