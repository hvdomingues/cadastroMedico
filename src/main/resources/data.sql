INSERT INTO medico ( medico_id, nome_completo, crm, telefone, celular, is_deleted) VALUES
  (1, 'Aliko', '1234569', '119958645','11845454', 0),
  (2, 'Henrique', '12434569', '1159958645','115845454', 0),
  (3, 'Victor', '12434569', '1159958645','115845454', 1);

INSERT INTO endereco (endereco_id, rua, bairro, estado, numero, cidade, medico_id, cep) VALUES
  (1, 'Embira','Jd Cleusa', 'SP', '269', 'São Paulo', 1, '03546000');


INSERT INTO especialidade (especialidade_id, nome_especialidade) VALUES
  (1, 'ALERGOLOGIA'),
  (2, 'ANGIOLOGIA'),
  (3, 'BUCO MAXILO'),
  (4, 'CARDIOLOGIA CLÍNICA'),
  (5, 'CARDIOLOGIA INFANTIL'),
  (6, 'CIRURGIA CABEÇA E PESCOÇO'),
  (7, 'CIRURGIA CARDÍACA'),
  (8, 'CIRURGIA DE TÓRAX');
	
INSERT INTO MEDICO_ESPECIALIDADE ( medico_especialidade_id, medico_id , especialidade_ID) VALUES
	 ('1','1','2'),
	 ('2','1','4');
	 