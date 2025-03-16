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

## 📌 Endpoints da API

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

## 🛠️ Tecnologias Utilizadas

- Spring Boot
- Spring Security
- JWT
- MySQL
- JPA/Hibernate
- Maven
- Lombok