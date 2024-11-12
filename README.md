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

## Arquitetura do Projeto
Este projeto segue a arquitetura MVC, com as seguintes camadas principais:

- Controller: Responsável pelos endpoints da API e por receber as requisições HTTP.
- Service: Contém a lógica de negócios e manipulação de dados.
- Repository: Camada de persistência de dados, interagindo com o banco de dados H2.

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
A API estará disponível em ``http://localhost:8080``

## Documentação da API com Swagger

A API é documentada automaticamente pelo Swagger. Para acessar e testar os endpoints, navegue até:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html` – Interface gráfica para explorar e testar os endpoints diretamente no navegador.

## Configuração do Banco de Dados

A aplicação utiliza um banco de dados em memória H2. Para acessar o console do H2, vá até:

- **Console H2**: `http://localhost:8080/h2-console`

Utilize as credenciais abaixo para o acesso:

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (deixe em branco)

> **Nota**: O banco de dados H2 é redefinido a cada reinicialização da aplicação, pois é voltado apenas para testes e desenvolvimento.

## Autor
<div align="left">
  <a href="https://github.com/gabibento">
    <img alt="Gabriella Maurea Bento" src="https://avatars.githubusercontent.com/u/143539144?v=4" width="115" style="border-radius:50%">
  </a>
  <br>
  <sub><b>Gabriella Maurea Bento</b></sub><br>
  <sub>RA: 1788213</sub><br>
</div>

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
