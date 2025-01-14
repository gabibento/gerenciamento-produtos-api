# Gerenciador de Produtos API

Uma API simples de gerenciamento de produtos, construída com Spring Boot e configurada com Swagger para documentação. A API permite criar, buscar, atualizar e deletar produtos de uma base de dados H2 em memória. Agora com **autenticação e autorização utilizando Spring Security e JWT**, garantindo maior segurança para as operações.

> A implementação de autenticação e autorização com JWT foi desenvolvida com base na videoaula '[Segurança JWT com Spring Boot - Autenticação e Autorização](https://youtu.be/5w-YCcOjPD0?si=fcRJsZa07ei1KkFG)', com adaptações para atender aos requisitos do projeto.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.5**
- **H2 Database**: Banco de dados em memória para ambiente de desenvolvimento.
- **Spring Data JPA**: Para persistência de dados.
- **Springdoc OpenAPI**: Integração com Swagger para documentação da API.
- **Spring Security**: Para autenticação e autorização.
- **JWT (JSON Web Token)**: Utilizado para autenticação stateless.
- **BCrypt**: Para criptografia segura de senhas.
  
## Controle de Acesso

A API possui diferentes níveis de acesso, dependendo da autenticação e do papel do usuário:

- **Endpoints públicos** (acessíveis sem autenticação):
    - `POST /auth/register`: Registro de novos usuários.
    - `POST /auth/login`: Login para geração de token JWT.
- **Endpoints protegidos por autenticação** (qualquer usuário autenticado pode acessar):
    - `GET /products`: Lista todos os produtos.
    - `GET /products/{id}`: Busca produto pelo ID.
    - `GET /products/search?name={name}`: Busca produtos pelo nome.
    - `GET /products/category?category={category}`: Busca produtos pela categoria.
- **Endpoints restritos a administradores (ADMIN)**:
    - `POST /products`: Criação de novos produtos.
    - `PUT /products/{id}`: Atualização de produtos existentes.
    - `DELETE /products/{id}`: Exclusão de produtos.


## Arquitetura do Projeto

Este projeto segue a arquitetura MVC, com as seguintes camadas principais:

- **Controller**: Responsável pelos endpoints da API e por receber as requisições HTTP.
- **Service**: Contém a lógica de negócios e manipulação de dados.
- **Repository**: Camada de persistência de dados, interagindo com o banco de dados H2.
- **Segurança**:
    - Implementação de autenticação com JWT.
    - Uso de filtros personalizados para validação de tokens em cada requisição.
    - Configuração de controle de acesso baseada em roles (usuários `ADMIN` ou `USER`).

## Instalação

### Pré-requisitos

- Java 17+
- Maven
- IDE (IntelliJ IDEA, Eclipse, etc.)

### Passos para executar

1. **Clone o repositório**:
   
```bash
   git clone https://github.com/gabibento/gerenciador-produtos-api.git
```
2. **Importe o projeto:**
   - Abra o projeto na IDE e importe o projeto como um projeto Maven, caso não seja reconhecido automaticamente pela IDE

3. **Execute a aplicação:**
   - Navegue até a classe principal do projeto, chamada GerenciadorApplication, clique com o botão direito na classe e selecione Run 'GerenciadorApplication'.

4. **Acesse a API:**
   - Após a execução, a API estará disponível em http://localhost:8080.
     
## Documentação da API com Swagger

A API é documentada automaticamente pelo Swagger. Para acessar e testar os endpoints, navegue até:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html` – Interface gráfica para explorar e testar os endpoints diretamente no navegador.
  Use o botão "Authorize" para autenticar-se inserindo o token JWT.

## Configuração de Segurança

- **Registro**: Para registrar usuários, use o endpoint `/auth/register`. A senha será criptografada com BCrypt.
- **Login**: Para autenticar, use o endpoint `/auth/login`. Um token JWT será retornado.
- **Acesso Protegido**: Adicione o token JWT no cabeçalho `Authorization` no formato `Bearer <seu_token>` para acessar os endpoints protegidos.

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
</div>

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
