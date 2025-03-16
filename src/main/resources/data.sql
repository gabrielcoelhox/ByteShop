-- Verifica se existem produtos antes de limpar as tabelas
SET @product_count = (SELECT COUNT(*) FROM products);

-- Apenas limpar as tabelas se não houver produtos cadastrados
SET @do_insert = IF(@product_count = 0, 1, 0);

-- Condiciona a limpeza e inserção com base na verificação
SET SQL_SAFE_UPDATES = 0;
DELETE FROM products WHERE @do_insert = 1;
DELETE FROM users WHERE @do_insert = 1;

-- Populando tabela de usuários com diferentes perfis
-- A senha para todos os usuários é '123456', código hash: '$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi'
INSERT INTO users (id, name, username, email, password, role, created_at, updated_at)
SELECT '8d43e1c5-8edc-4f00-a028-47cfb126621a', 'Admin User', 'admin', 'admin@byteshop.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi', 'ADMIN', NOW(), NOW()
WHERE @do_insert = 1;

INSERT INTO users (id, name, username, email, password, role, created_at, updated_at)
SELECT '7ca7c782-8710-42c0-ad81-4ba50f74dd96', 'Normal User', 'user', 'user@byteshop.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi', 'USER', NOW(), NOW()
WHERE @do_insert = 1;

INSERT INTO users (id, name, username, email, password, role, created_at, updated_at)
SELECT '9d5a6082-6902-4a3f-9682-ddb7a1cee7c7', 'Arrascaeta', 'johndoe', 'john@example.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi', 'USER', NOW(), NOW()
WHERE @do_insert = 1;

INSERT INTO users (id, name, username, email, password, role, created_at, updated_at)
SELECT 'c77f3d05-7c95-4b2c-88b7-3b8e7f3b12c8', 'Bruno Henrique', 'janesmith', 'jane@example.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi', 'USER', NOW(), NOW()
WHERE @do_insert = 1;

INSERT INTO users (id, name, username, email, password, role, created_at, updated_at)
SELECT 'd51ec86b-6c9d-4e94-a5c7-842f13205a7d', 'Neymar', 'manager', 'manager@byteshop.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi', 'ADMIN', NOW(), NOW()
WHERE @do_insert = 1;

-- Populando tabela de produtos com diferentes categorias
INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
SELECT '9878a9c9-fc69-4a65-8d9e-2bcd8a437e52', 'Smartphone XYZ', 'Smartphone de última geração com câmera de alta resolução', 1999.90, 'Eletrônicos', 50, NOW(), NOW()
WHERE @do_insert = 1;

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
SELECT '3c976294-fc14-4a26-aec8-3f41e1f3cbce', 'Notebook Pro', 'Notebook para uso profissional com processador de alta performance', 4599.90, 'Eletrônicos', 25, NOW(), NOW()
WHERE @do_insert = 1;

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
SELECT '9a8e3f5d-1c2b-4a0e-b9c7-8d6f5a4e3c2b', 'Mouse Gamer RGB', 'Mouse com iluminação RGB e alta precisão para jogos', 159.90, 'Periféricos', 100, NOW(), NOW()
WHERE @do_insert = 1;

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
SELECT '7b6e9d8c-5a4e-3c2b-1a0f-9e8d7c6b5a4e', 'Teclado Mecânico', 'Teclado mecânico com switches blue', 249.90, 'Periféricos', 75, NOW(), NOW()
WHERE @do_insert = 1;

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
SELECT '5e4d3c2b-1a0f-9e8d-7c6b-5a4e3c2b1a0f', 'Monitor 27 polegadas', 'Monitor Full HD com taxa de atualização de 144Hz', 1299.90, 'Monitores', 30, NOW(), NOW()
WHERE @do_insert = 1;

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
SELECT '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', 'SSD 500GB', 'Disco de estado sólido para armazenamento rápido', 399.90, 'Armazenamento', 80, NOW(), NOW()
WHERE @do_insert = 1;

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
SELECT 'f1e2d3c4-b5a6-7c8d-9e0f-1a2b3c4d5e6f', 'Headset Gamer', 'Headset com microfone e cancelamento de ruído', 199.90, 'Áudio', 60, NOW(), NOW()
WHERE @do_insert = 1;

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
SELECT 'a1b2c3d4-e5f6-a7b8-c9d0-e1f2a3b4c5d6', 'Webcam HD', 'Webcam com resolução Full HD e microfone integrado', 149.90, 'Periféricos', 40, NOW(), NOW()
WHERE @do_insert = 1;

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
SELECT 'b5c6d7e8-f9a0-b1c2-d3e4-f5a6b7c8d9e0', 'Roteador Wi-Fi', 'Roteador dual band com alta velocidade', 249.90, 'Redes', 35, NOW(), NOW()
WHERE @do_insert = 1;

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
SELECT 'c9d0e1f2-a3b4-c5d6-e7f8-a9b0c1d2e3f4', 'Cadeira Gamer', 'Cadeira ergonômica para gamers profissionais', 899.90, 'Móveis', 20, NOW(), NOW()
WHERE @do_insert = 1;