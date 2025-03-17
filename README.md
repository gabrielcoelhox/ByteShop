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
<summary><strong>:exclamation: Clique aqui para abrir o tutorial!</strong></summary>

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

## 🗄️ Tutorial: Como Testar o Projeto com MySQL
<details>

<summary><strong>:exclamation: Clique aqui para abrir o tutorial!</strong></summary>
Este tutorial mostra como configurar, monitorar e testar o projeto ByteShop usando um banco de dados MySQL.

### 1. Configuração do MySQL

1. **Instale o MySQL** (caso ainda não tenha):
   - Windows: Baixe em https://dev.mysql.com/downloads/installer/ e siga o assistente
   - Linux (Ubuntu): `sudo apt install mysql-server`
   - macOS: `brew install mysql` (necessário Homebrew)

2. **Inicie o serviço MySQL**:
   - Windows: Verifique se o serviço está rodando pelo Gerenciador de Serviços
   - Linux: `sudo systemctl start mysql`
   - macOS: `brew services start mysql`

3. **Acesse o MySQL**:
   ```bash
   mysql -u root -p
   # Digite sua senha quando solicitado
   ```

4. **Crie um banco de dados para o projeto**:
   ```sql
   CREATE DATABASE byteshop;
   ```

5. **Crie um usuário dedicado para o projeto** (opcional, mas recomendado):
   ```sql
   CREATE USER 'byteshop_user'@'localhost' IDENTIFIED BY 'sua_senha';
   GRANT ALL PRIVILEGES ON byteshop.* TO 'byteshop_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

### 2. Configure o Projeto para Usar MySQL

1. **Edite o arquivo de configuração** em `src/main/resources/application.properties`:
   ```properties
   # Conexão com MySQL
   spring.datasource.url=jdbc:mysql://localhost:3306/byteshop?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=UTF-8
   spring.datasource.username=byteshop_user
   spring.datasource.password=sua_senha
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

   # JPA/Hibernate
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

   # Desabilitar o script data.sql
   spring.sql.init.mode=never
   ```

2. **Execute o projeto**:
   ```bash
   mvn clean spring-boot:run
   ```
   Durante a inicialização, o Hibernate criará as tabelas e os dados de exemplo serão carregados automaticamente pelo `DataInitializer`.

### 3. Verifique as Tabelas Criadas

1. **Conecte-se ao MySQL**:
   ```bash
   mysql -u byteshop_user -p byteshop
   ```

2. **Liste as tabelas**:
   ```sql
   SHOW TABLES;
   ```
   Você deverá ver as tabelas: `users`, `products`, `orders`, `order_items`

3. **Explore a estrutura das tabelas**:
   ```sql
   DESCRIBE users;
   DESCRIBE products;
   DESCRIBE orders;
   DESCRIBE order_items;
   ```

4. **Consulte e Analise os Dados

1. **Verifique os usuários**:
   ```sql
   SELECT id, username, name, email, role FROM users;
   ```

2. **Verifique os produtos**:
   ```sql
   SELECT id, name, price, category, stock_quantity FROM products;
   ```

3. **Verifique os pedidos**:
   ```sql
   SELECT o.id, o.user_id, u.username, o.status, o.total_amount, o.created_at, o.paid_at
   FROM orders o
   JOIN users u ON o.user_id = u.id;
   ```

4. **Verifique os itens de um pedido específico**:
   ```sql
   -- Substitua 'ID_DO_PEDIDO' pelo ID real de um pedido
   SELECT oi.order_id, oi.product_id, p.name, oi.quantity, oi.price, (oi.quantity * oi.price) as subtotal
   FROM order_items oi
   JOIN products p ON oi.product_id = p.id
   WHERE oi.order_id = 'ID_DO_PEDIDO';
   ```

5. *Teste as Consultas Otimizadas*

Teste as mesmas consultas otimizadas implementadas na aplicação:

1. **Top 5 usuários que mais gastaram**:

Se quiser visualizar outro status, altere o campo PAID para `PENDING` ou `CANCELED`
   ```sql
   SELECT u.id, u.name, u.email, COUNT(o.id) as order_count, SUM(o.total_amount) as total_spent 
   FROM users u 
   JOIN orders o ON u.id = o.user_id 
   WHERE o.status = 'PAID' 
   GROUP BY u.id, u.name, u.email 
   ORDER BY total_spent DESC 
   LIMIT 5;
   ```

2. **Valor médio dos pedidos por usuário**:
Se quiser visualizar outro status, altere o campo PAID para `PENDING` ou `CANCELED`
   ```sql
   SELECT u.id, u.name, u.email, AVG(o.total_amount) as average_order_value 
   FROM users u 
   JOIN orders o ON u.id = o.user_id 
   WHERE o.status = 'PAID' 
   GROUP BY u.id, u.name, u.email;
   ```

3. **Faturamento mensal** (substitua ANO e MÊS pelos valores desejados):
Se quiser visualizar outro status, altere o campo PAID para `PENDING` ou `CANCELED`
   ```sql
   SELECT SUM(total_amount) as revenue
   FROM orders
   WHERE status = 'PAID'
   AND YEAR(paid_at) = ANO AND MONTH(paid_at) = MÊS;
   # Substitua o campo ANO e MÊS pelos valores que deseja realizar a pesquisa
   ```

4. **Monitore Operações em Tempo Real

5. **Ative o log de consultas** (opcional):
   ```sql
   SET GLOBAL general_log = 'ON';
   SET GLOBAL log_output = 'TABLE';
   ```

6. **Visualize as consultas recentes**:
   ```sql
   SELECT event_time, command_type, argument 
   FROM mysql.general_log 
   ORDER BY event_time DESC 
   LIMIT 20;
   ```

7. **Execute operações na API** usando o Swagger ou outras ferramentas e observe as consultas correspondentes no log.

8. *Teste Transações e Integridade de Dados*

9.  **Crie um novo pedido** via Swagger (veja o tutorial anterior)

10. **Verifique se o pedido foi criado no banco**:
   ```sql
   SELECT * FROM orders ORDER BY created_at DESC LIMIT 1;
   SELECT * FROM order_items WHERE order_id = 'ID_DO_PEDIDO_CRIADO';
   ```

11. *Processe o pagamento do pedido** via Swagger

12. **Verifique se o estoque foi atualizado**:
   ```sql
   SELECT name, stock_quantity FROM products WHERE id IN 
   (SELECT product_id FROM order_items WHERE order_id = 'ID_DO_PEDIDO_PAGO');
   ```

13. *Dicas e Solução de Problemas*

- **Falha na conexão**: Verifique se o serviço MySQL está rodando (`sudo systemctl status mysql`)
- **Erro de acesso**: Confirme nome de usuário e senha no `application.properties`
- **Não carrega dados iniciais**: Verifique logs para possíveis erros durante a execução
- **Consulta lenta**: Adicione índices às colunas frequentemente consultadas:
  ```sql
  CREATE INDEX idx_orders_user_id ON orders(user_id);
  CREATE INDEX idx_order_items_order_id ON order_items(order_id);
  CREATE INDEX idx_orders_status ON orders(status);
  ```
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