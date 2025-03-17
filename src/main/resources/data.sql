-- Verifica se existem produtos antes de limpar as tabelas
SET @product_count = (SELECT COUNT(*) FROM products);

-- Apenas limpar as tabelas se não houver produtos cadastrados
SET @do_insert = IF(@product_count = 0, 1, 0);

-- Condiciona a limpeza e inserção com base na verificação
SET SQL_SAFE_UPDATES = 0;
DELETE FROM products WHERE @do_insert = 1;
DELETE FROM users WHERE @do_insert = 1;

-- Limpar tabelas para garantir inserção limpa
SET SQL_SAFE_UPDATES = 0;
DELETE FROM products;
DELETE FROM users;

-- Inserir usuários individualmente
INSERT INTO users (id, name, username, email, password, role, created_at, updated_at)
VALUES (1, 'Admin User', 'admin', 'admin@byteshop.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi', 'ADMIN', NOW(), NOW());

INSERT INTO users (id, name, username, email, password, role, created_at, updated_at)
VALUES (2, 'Normal User', 'user', 'user@byteshop.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi', 'USER', NOW(), NOW());

INSERT INTO users (id, name, username, email, password, role, created_at, updated_at)
VALUES (3, 'Arrascaeta', 'arrascaeta', 'arrascaeta@flamengo.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi', 'USER', NOW(), NOW());

INSERT INTO users (id, name, username, email, password, role, created_at, updated_at)
VALUES (4, 'Pedro', 'pedro', 'pedro@flamengo.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi', 'USER', NOW(), NOW());

INSERT INTO users (id, name, username, email, password, role, created_at, updated_at)
VALUES (5, 'Neymar', 'manager', 'manager@byteshop.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi', 'ADMIN', NOW(), NOW());

-- Inserir produtos individualmente (com textos sem acentos para evitar problemas de codificação)
INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
VALUES (1, 'Smartphone XYZ', 'Smartphone de ultima geracao com camera de alta resolucao', 1999.90, 'Eletronicos', 50, NOW(), NOW());

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
VALUES (2, 'Notebook Pro', 'Notebook para uso profissional com processador de alta performance', 4599.90, 'Eletronicos', 25, NOW(), NOW());

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
VALUES (3, 'Mouse Gamer RGB', 'Mouse com iluminacao RGB e alta precisao para jogos', 159.90, 'Perifericos', 100, NOW(), NOW());

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
VALUES (4, 'Teclado Mecanico', 'Teclado mecanico com switches blue', 249.90, 'Perifericos', 75, NOW(), NOW());

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
VALUES (5, 'Monitor 27 polegadas', 'Monitor Full HD com taxa de atualizacao de 144Hz', 1299.90, 'Monitores', 30, NOW(), NOW());

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
VALUES (6, 'SSD 500GB', 'Disco de estado solido para armazenamento rapido', 399.90, 'Armazenamento', 80, NOW(), NOW());

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
VALUES (7, 'Headset Gamer', 'Headset com microfone e cancelamento de ruido', 199.90, 'Audio', 60, NOW(), NOW());

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
VALUES (8, 'Webcam HD', 'Webcam com resolucao Full HD e microfone integrado', 149.90, 'Perifericos', 40, NOW(), NOW());

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
VALUES (9, 'Roteador Wi-Fi', 'Roteador dual band com alta velocidade', 249.90, 'Redes', 35, NOW(), NOW());

INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at)
VALUES (10, 'Cadeira Gamer', 'Cadeira ergonomica para gamers profissionais', 899.90, 'Moveis', 20, NOW(), NOW());