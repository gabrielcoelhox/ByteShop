-- Populando tabela de usuários com diferentes perfis
-- A senha para todos os usuários é '123456'
INSERT INTO users (id, name, username, email, password, role, created_at, updated_at) VALUES
('8d43e1c5-8edc-4f00-a028-47cfb126621a', 'Admin User', 'admin', 'admin@byteshop.com', '$2a$10$CwTycUXWue0Thq9StjUM0uG8BnUn.DMZqwbis3QFb1aWOUe15FnUO', 'ADMIN', NOW(), NOW()),
('7ca7c782-8710-42c0-ad81-4ba50f74dd96', 'Normal User', 'user', 'user@byteshop.com', '$2a$10$CwTycUXWue0Thq9StjUM0uG8BnUn.DMZqwbis3QFb1aWOUe15FnUO', 'USER', NOW(), NOW()),
('9d5a6082-6902-4a3f-9682-ddb7a1cee7c7', 'Arrascaeta', 'arrascaeta', 'arrascaeta@example.com', '$2a$10$CwTycUXWue0Thq9StjUM0uG8BnUn.DMZqwbis3QFb1aWOUe15FnUO', 'USER', NOW(), NOW()),
('c77f3d05-7c95-4b2c-88b7-3b8e7f3b12c8', 'Bruno Henrique', 'brunohenrique', 'brunohenrique@example.com', '$2a$10$CwTycUXWue0Thq9StjUM0uG8BnUn.DMZqwbis3QFb1aWOUe15FnUO', 'USER', NOW(), NOW()),
('d51ec86b-6c9d-4e94-a5c7-842f13205a7d', 'Neymar', 'neymar', 'neymar@byteshop.com', '$2a$10$CwTycUXWue0Thq9StjUM0uG8BnUn.DMZqwbis3QFb1aWOUe15FnUO', 'ADMIN', NOW(), NOW());

-- Populando tabela de produtos com diferentes categorias
INSERT INTO products (id, name, description, price, category, stock_quantity, created_at, updated_at) VALUES
('9878a9c9-fc69-4a65-8d9e-2bcd8a437e52', 'Smartphone XYZ', 'Smartphone de última geração com câmera de alta resolução', 1999.90, 'Eletrônicos', 50, NOW(), NOW()),
('3c976294-fc14-4a26-aec8-3f41e1f3cbce', 'Notebook Pro', 'Notebook para uso profissional com processador de alta performance', 4599.90, 'Eletrônicos', 25, NOW(), NOW()),
('9a8e3f5d-1c2b-4a0e-b9c7-8d6f5a4e3c2b', 'Mouse Gamer RGB', 'Mouse com iluminação RGB e alta precisão para jogos', 159.90, 'Periféricos', 100, NOW(), NOW()),
('7b6e9d8c-5a4e-3c2b-1a0f-9e8d7c6b5a4e', 'Teclado Mecânico', 'Teclado mecânico com switches blue', 249.90, 'Periféricos', 75, NOW(), NOW()),
('5e4d3c2b-1a0f-9e8d-7c6b-5a4e3c2b1a0f', 'Monitor 27 polegadas', 'Monitor Full HD com taxa de atualização de 144Hz', 1299.90, 'Monitores', 30, NOW(), NOW()),
('1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', 'SSD 500GB', 'Disco de estado sólido para armazenamento rápido', 399.90, 'Armazenamento', 80, NOW(), NOW()),
('f1e2d3c4-b5a6-7c8d-9e0f-1a2b3c4d5e6f', 'Headset Gamer', 'Headset com microfone e cancelamento de ruído', 199.90, 'Áudio', 60, NOW(), NOW()),
('a1b2c3d4-e5f6-a7b8-c9d0-e1f2a3b4c5d6', 'Webcam HD', 'Webcam com resolução Full HD e microfone integrado', 149.90, 'Periféricos', 40, NOW(), NOW()),
('b5c6d7e8-f9a0-b1c2-d3e4-f5a6b7c8d9e0', 'Roteador Wi-Fi', 'Roteador dual band com alta velocidade', 249.90, 'Redes', 35, NOW(), NOW()),
('c9d0e1f2-a3b4-c5d6-e7f8-a9b0c1d2e3f4', 'Cadeira Gamer', 'Cadeira ergonômica para gamers profissionais', 899.90, 'Móveis', 20, NOW(), NOW());

-- Populando tabela de pedidos
INSERT INTO orders (id, user_id, total_value, status, created_at, updated_at) VALUES
('1e2f3a4b-5c6d-7e8f-9a0b-1c2d3e4f5a6b', '7ca7c782-8710-42c0-ad81-4ba50f74dd96', 2159.80, 'COMPLETED', NOW() - INTERVAL 15 DAY, NOW() - INTERVAL 14 DAY),
('2f3e4d5c-6b7a-8e9d-0f1e-2d3c4b5a6e7d', '9d5a6082-6902-4a3f-9682-ddb7a1cee7c7', 4599.90, 'COMPLETED', NOW() - INTERVAL 10 DAY, NOW() - INTERVAL 9 DAY),
('3e4d5c6b-7a8e-9f0d-1e2f-3a4b5c6d7e8f', 'c77f3d05-7c95-4b2c-88b7-3b8e7f3b12c8', 449.80, 'COMPLETED', NOW() - INTERVAL 7 DAY, NOW() - INTERVAL 6 DAY),
('4d5c6b7a-8e9f-0d1c-2e3b-4a5c6b7d8e9f', '7ca7c782-8710-42c0-ad81-4ba50f74dd96', 899.90, 'COMPLETED', NOW() - INTERVAL 5 DAY, NOW() - INTERVAL 4 DAY),
('5c6b7a8e-9f0d-1c2e-3b4a-5c6b7d8e9f0d', '9d5a6082-6902-4a3f-9682-ddb7a1cee7c7', 1299.90, 'PENDING', NOW() - INTERVAL 2 DAY, NOW() - INTERVAL 2 DAY),
('6b7a8e9f-0d1c-2e3b-4a5c-6b7d8e9f0d1c', 'c77f3d05-7c95-4b2c-88b7-3b8e7f3b12c8', 1999.90, 'PENDING', NOW() - INTERVAL 1 DAY, NOW() - INTERVAL 1 DAY);

-- Populando tabela de itens de pedido
INSERT INTO order_items (id, order_id, product_id, quantity, unit_price) VALUES
('a7b8c9d0-e1f2-a3b4-c5d6-e7f8a9b0c1d2', '1e2f3a4b-5c6d-7e8f-9a0b-1c2d3e4f5a6b', '9878a9c9-fc69-4a65-8d9e-2bcd8a437e52', 1, 1999.90),
('b8c9d0e1-f2a3-b4c5-d6e7-f8a9b0c1d2e3', '1e2f3a4b-5c6d-7e8f-9a0b-1c2d3e4f5a6b', '9a8e3f5d-1c2b-4a0e-b9c7-8d6f5a4e3c2b', 1, 159.90),
('c9d0e1f2-a3b4-c5d6-e7f8-a9b0c1d2e3f4', '2f3e4d5c-6b7a-8e9d-0f1e-2d3c4b5a6e7d', '3c976294-fc14-4a26-aec8-3f41e1f3cbce', 1, 4599.90),
('d0e1f2a3-b4c5-d6e7-f8a9-b0c1d2e3f4a5', '3e4d5c6b-7a8e-9f0d-1e2f-3a4b5c6d7e8f', '7b6e9d8c-5a4e-3c2b-1a0f-9e8d7c6b5a4e', 1, 249.90),
('e1f2a3b4-c5d6-e7f8-a9b0-c1d2e3f4a5b6', '3e4d5c6b-7a8e-9f0d-1e2f-3a4b5c6d7e8f', 'f1e2d3c4-b5a6-7c8d-9e0f-1a2b3c4d5e6f', 1, 199.90),
('f2a3b4c5-d6e7-f8a9-b0c1-d2e3f4a5b6c7', '4d5c6b7a-8e9f-0d1c-2e3b-4a5c6b7d8e9f', 'c9d0e1f2-a3b4-c5d6-e7f8-a9b0c1d2e3f4', 1, 899.90),
('a3b4c5d6-e7f8-a9b0-c1d2-e3f4a5b6c7d8', '5c6b7a8e-9f0d-1c2e-3b4a-5c6b7d8e9f0d', '5e4d3c2b-1a0f-9e8d-7c6b-5a4e3c2b1a0f', 1, 1299.90),
('b4c5d6e7-f8a9-b0c1-d2e3-f4a5b6c7d8e9', '6b7a8e9f-0d1c-2e3b-4a5c-6b7d8e9f0d1c', '9878a9c9-fc69-4a65-8d9e-2bcd8a437e52', 1, 1999.90); 