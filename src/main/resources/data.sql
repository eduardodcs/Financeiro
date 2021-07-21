INSERT INTO CATEGORIA (descricao, data_criacao, status) VALUES ('Alimentação', '2021-07-21 00:44:49.313483', 'ATIVO');
INSERT INTO CATEGORIA (descricao, data_criacao, status) VALUES ('Educação', '2021-07-21 00:44:49.313483', 'ATIVO');
INSERT INTO CATEGORIA (descricao, data_criacao, status) VALUES ('Moradia', '2021-07-21 00:44:49.313483', 'ATIVO');
INSERT INTO CATEGORIA (descricao, data_criacao, status) VALUES ('Lazer', '2021-07-21 00:44:49.313483', 'ATIVO');

INSERT INTO LANCAMENTO (descricao, valor, situacao, vencimento, categoria_id) VALUES ('Compras no supermercado', 523.25, 'PAGO', '2021-07-05', 1);
INSERT INTO LANCAMENTO (descricao, valor, situacao, vencimento, categoria_id) VALUES ('Faculdade', 468.00, 'PAGO', '2021-07-07', 2);
INSERT INTO LANCAMENTO (descricao, valor, situacao, vencimento, categoria_id) VALUES ('Passeio no shopping', 75.25, 'ABERTO', '2021-07-10', 4);
INSERT INTO LANCAMENTO (descricao, valor, situacao, vencimento, categoria_id) VALUES ('Aluguel', 800.00, 'ABERTO', '2021-07-15', 3);
INSERT INTO LANCAMENTO (descricao, valor, situacao, vencimento, categoria_id) VALUES ('Conta de luz', 23.25, 'ABERTO', '2021-07-05', 3);
INSERT INTO LANCAMENTO (descricao, valor, situacao, vencimento, categoria_id) VALUES ('Almoço em restaurante', 15.00, 'ABERTO', '2021-07-12', 1);
INSERT INTO LANCAMENTO (descricao, valor, situacao, vencimento, categoria_id) VALUES ('Cursos', 66.00, 'ABERTO', '2021-07-20', 2);