<p align="center"> 💻 Atualizado em 16 de Março de 2025 💻</p>

<h1 align="center"> 🛒 Desafio ByteShop 🛒</h1>

<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/gabrielcoelhox/ByteShop">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/gabrielcoelhox/ByteShop">

  <a href="https://github.com/gabrielcoelhox/ByteShop/commits/main">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/gabrielcoelhox/ByteShop">
  </a>
</p>

## <a id="id1"> 💻 O Projeto </a>

Este projeto foi desenvolvido como desafio de avaliação para a vaga de desenvolvedor da empresa __*FourSales*__. A proposta do projeto foi construir um sistema de gerenciamento de pedidos e produtos para um e-commerce, garantindo:
- Autenticação segura com JWT.
- CRUD completo de produtos.
- Gerenciamento de pedidos com regras específicas.
- Otimização de queries SQL para melhor performance.
- Dois perfis de usuário: ADMIN e USER
- Documentação interativa com Swagger UI

## <a id="id2">📌 Regras de negócio </a>

<details>
<summary><strong>🛍️ Produtos</strong></summary>
Criar um CRUD completo para produtos com os seguintes campos:

- ID (UUID)
- Nome
- Descrição
- Preço
- Categoria
- Quantidade em estoque
- Data de criação
- Data de atualização
</details>

<details>
<summary><strong>📝 Pedidos</strong></summary>
1. Um USER pode criar um pedido contendo múltiplos produtos.</br>
2. O pedido deve iniciar com o status PENDENTE.</br>
3. Criar uma rota para realizar o pagamento do pedido.</br>
4. Atualizar o estoque dos produtos apenas após o pagamento do pedido.</br>
5. Se algum produto do pedido não tiver estoque disponível, o pedido deve ser cancelado automaticamente, e o usuário informado.</br>
6. O valor total do pedido deve ser calculado dinamicamente com base no preço atual dos produtos.</br>
7. Criar um endpoint para listar pedidos do usuário autenticado.
</details>

<details>
<summary><strong>📊 Consultas SQL Otimizadas</strong></summary>
Criar endpoints que executem consultas otimizadas no MySQL:

- Top 5 usuários que mais compraram.
- Ticket médio dos pedidos de cada usuário.
- Valor total faturado no mês.
</details>

## 📋Pré-requisitos
- Java JDK 17 ou superior
  - Baixe em: https://www.oracle.com/java/technologies/downloads/#java17
  - Instale e configure a variável JAVA_HOME

- Maven
  - Baixe em: https://maven.apache.org/download.cgi
  - Instale e adicione ao PATH do sistema

- MySQL
  - Baixe em: https://dev.mysql.com/downloads/installer/
  - Instale e inicie o serviço MySQL
  - Anote o usuário e senha definidos durante a instalação
 
## 🔧 Instalação e Execução

1. **Clone o repositório**
```bash
git clone https://github.com/gabrielcoelhox/ByteShop.git
```

2. **Configure o banco de dados**
- Abra o arquivo `src/main/resources/application.properties`
- Altere as configurações do banco de dados:
```properties
spring.datasource.username=seu_usuario_mysql
spring.datasource.password=sua_senha_mysql
```

3. **Compile o projeto**
```bash
mvn clean install
```

4. **Execute o projeto**
```bash
mvn spring-boot:run
```

5. **Acesse a documentação Swagger**
- Após iniciar o projeto, acesse:
  - Swagger UI: http://localhost:8080/swagger-ui/index.html
  - OpenAPI JSON: http://localhost:8080/v3/api-docs

## <code><img width="25" src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/swagger.png" alt="Swagger" title="Swagger"/></code> Tutorial: Como Testar o Projeto com Swagger
<details>

### 1. Acessando o Swagger UI

1. Execute o projeto Spring Boot (`mvn clean spring-boot:run`)
2. Abra seu navegador e acesse: http://localhost:8080/swagger-ui/index.html
3. Você verá a interface do Swagger organizada com os seguintes grupos de endpoints:
   - **Autenticação** - Login e registro
   - **Produtos** - Gerenciamento de produtos
   - **Pedidos** - Gerenciamento de pedidos
   - **Consultas Otimizadas** - Análises e relatórios

### 2. Autenticação

Para acessar endpoints protegidos, primeiro você precisa se autenticar:

1. Expanda a seção **Autenticação**
2. Clique no endpoint `POST /api/auth/login`
3. Clique em "Try it out"
4. No campo "Request body", insira as credenciais:
   ```json
   {
     "username": "exemplo",
     "password": "12345678"
   }
   ```
   *(Ou use as credenciais: user/123456, johndoe/123456, janesmith/123456)*
5. Clique em "Execute"
6. Na resposta, copie o token JWT (sem as aspas) da seção "Response body":
   ```json
   {
     "token": "eyJhbGciOiJIUzI1NiJ9..."
   }
   ```
7. Clique no botão "Authorize" (cadeado) no topo da página
8. No campo "Value", digite o token copiado
9. Clique em "Authorize" e depois em "Close"

Agora você está autenticado e pode acessar endpoints protegidos!

### 3. Testando Endpoints de Produtos

#### 3.1 Listar Todos os Produtos
1. Expanda a seção **Produtos**
2. Clique no endpoint `GET /api/products`
3. Clique em "Try it out" e depois em "Execute"
4. Observe a lista de produtos retornada

#### 3.2 Buscar Produto por ID
1. Na seção **Produtos**, localize `GET /api/products/{id}`
2. Clique em "Try it out"
3. No campo "id", insira o UUID de um produto (você pode obter isso da lista anterior)
4. Clique em "Execute"
5. Observe os detalhes do produto específico

#### 3.3 Buscar por Categoria
1. Localize `GET /api/products/category/{category}`
2. Clique em "Try it out"
3. Digite uma categoria (ex: "Eletrônicos")
4. Clique em "Execute"
5. Veja a lista de produtos da categoria especificada

#### 3.4 Criar Novo Produto (requer Admin)
1. Localize `POST /api/products`
2. Clique em "Try it out"
3. No campo Request body, insira um novo produto:
   ```json
   {
     "name": "Novo Produto Teste",
     "description": "Descrição do produto teste",
     "price": 199.99,
     "category": "Testes",
     "stockQuantity": 50
   }
   ```
4. Clique em "Execute"
5. Confirme que o produto foi criado com sucesso (código 201)

### 4. Testando Endpoints de Pedidos

#### 4.1 Listar Meus Pedidos
1. Expanda a seção **Pedidos**
2. Clique no endpoint `GET /api/orders`
3. Clique em "Try it out" e depois em "Execute"
4. Observe seus pedidos atuais

#### 4.2 Criar Novo Pedido
1. Localize `POST /api/orders`
2. Clique em "Try it out"
3. Insira os dados do pedido:
   ```json
   {
     "items": [
       {
         "productId": "ID-DO-PRODUTO-AQUI",
         "quantity": 2
       }
     ]
   }
   ```
   (Substitua "ID-DO-PRODUTO-AQUI" por um ID real de produto)
4. Clique em "Execute"
5. Verifique se o pedido foi criado com sucesso

#### 4.3 Processar Pagamento
1. Localize `POST /api/orders/{id}/payment`
2. Clique em "Try it out"
3. Insira o ID do pedido que acabou de criar
4. Clique em "Execute"
5. Confirme que o pagamento foi processado (o status deve mudar para "PAID")

#### 4.4 Ver Todos os Pedidos (Admin)
1. Localize `GET /api/orders/admin/all`
2. Clique em "Try it out" e depois em "Execute"
3. Como administrador, você poderá ver todos os pedidos do sistema

### 5. Testando Análises (Admin)

#### 5.1 Top 5 Usuários
1. Expanda a seção **Consultas Otimizadas**
2. Clique no endpoint `GET /api/analytics/top-users`
3. Clique em "Try it out" e depois em "Execute"
4. Veja os 5 usuários que mais gastaram na plataforma

#### 5.2 Valor Médio de Pedidos
1. Localize `GET /api/analytics/average-order-value`
2. Clique em "Try it out" e depois em "Execute"
3. Observe o valor médio dos pedidos por usuário

#### 5.3 Faturamento Mensal
1. Localize `GET /api/analytics/monthly-revenue`
2. Clique em "Try it out"
3. Insira o ano e mês desejados (ex: ano=2023, mês=3)
4. Clique em "Execute"
5. Veja o faturamento total para o período especificado

### Dicas Adicionais

- **Códigos de resposta**: Observe sempre os códigos HTTP retornados:
  - 200/201: Sucesso
  - 400: Erro nos dados enviados
  - 401/403: Erro de autenticação/autorização 
  - 404: Recurso não encontrado

- **Token expirado**: Se receber erro 401, seu token pode ter expirado. Faça login novamente.

- **Usuários de teste**:
  - admin/123456 (ADMIN): Acesso a todos os endpoints
  - user/123456 (USER): Acesso limitado
  - johndoe/123456 (USER): Acesso limitado
  - janesmith/123456 (USER): Acesso limitado
</details>

## 📊 Dados de exemplo

O sistema é automaticamente populado com dados de exemplo para testes:

### Usuários
| Username | Senha | Perfil | Nome |
|----------|-------|--------|-------|
| admin | 123456 | ADMIN | Admin User |
| user | 123456 | USER | Normal User |
| arrascaeta | 123456 | USER | Arrascaeta |
| brunohenrique | 123456 | USER | Bruno Henrique |
| neymar | 123456 | ADMIN | Neymar |

### Produtos
O sistema inclui 10 produtos de exemplo em diversas categorias:
- Eletrônicos (Smartphone, Notebook)
- Periféricos (Mouse, Teclado, Webcam)
- Monitores
- Armazenamento (SSD)
- Áudio (Headset)
- Redes (Roteador)
- Móveis (Cadeira Gamer)

### Pedidos
Há alguns pedidos de exemplo com status "COMPLETED" e "PENDING" para demonstrar o fluxo de trabalho.

## 📌 Endpoints da API
Você pode explorar todos os endpoints através da interface do Swagger UI, mas aqui estão os principais:

### Autenticação
```
POST /api/auth/register
{
    "username": "admin",
    "password": "123456",
    "name": "Administrador",
    "email": "admin@byteshop.com",
    "role": "ADMIN"
}

POST /api/auth/login
{
    "username": "admin",
    "password": "123456"
}
```

### Produtos (requer token JWT)
```
GET /api/products - Lista todos os produtos
POST /api/products - Cria um novo produto (ADMIN)
{
    "name": "Produto Teste",
    "description": "Descrição do produto",
    "price": 99.90,
    "category": "Eletrônicos",
    "stockQuantity": 10
}
```

### Pedidos (requer token JWT)
```
POST /api/orders - Cria um novo pedido
{
    "items": [
        {
            "productId": "id-do-produto",
            "quantity": 2
        }
    ]
}

POST /api/orders/{id}/payment - Processa o pagamento do pedido
```

### Analytics (requer token JWT de ADMIN)
```
GET /api/analytics/top-users - Top 5 usuários que mais compraram
GET /api/analytics/average-order-value - Ticket médio por usuário
GET /api/analytics/monthly-revenue?year=2024&month=3 - Faturamento do mês
```

## 🔒 Autenticação

- Todos os endpoints (exceto registro e login) requerem autenticação
- Use o token JWT retornado no login no header: `Authorization: Bearer seu-token-aqui`
- Apenas usuários ADMIN podem gerenciar produtos e acessar analytics
- Usuários normais podem apenas criar pedidos e visualizar produtos

## 📊 Monitoramento

- Os logs SQL são exibidos no console
- Monitore as tabelas no MySQL:
```sql
USE byteshop;
SHOW TABLES;
```

## ❗ Solução de Problemas Comuns

- **Maven não encontra Java 17**: Verifique a variável JAVA_HOME
- **Erro de conexão MySQL**: Verifique se o serviço está rodando
- **Erro de porta**: Verifique se a porta 8080 está livre
- **Erro de autenticação**: Verifique as credenciais do MySQL no application.properties
- **Swagger não abre**: Verifique se a aplicação está rodando e se a URL está correta

## 🛠️ Tecnologias Utilizadas

- Spring Boot
- Spring Security
- JWT
- MySQL
- JPA/Hibernate
- Maven
- Lombok
- Swagger/OpenAPI

## 📄 Licença

Este projeto está sob a licença MIT - veja o arquivo [LICENSE.md](LICENSE.md) para detalhes.