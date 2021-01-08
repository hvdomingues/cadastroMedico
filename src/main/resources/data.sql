INSERT INTO medico (nome_completo, crm, telefone, celular, cep) VALUES
  ('Aliko', '1234569', '119958645','11845454','03568999');


INSERT INTO especialidade (id, nome_especialidade) VALUES
  (1, 'ALERGOLOGIA'),
  (2, 'ANGIOLOGIA'),
  (3, 'BUCO MAXILO'),
  (4, 'CARDIOLOGIA CLÍNICA'),
  (5, 'CARDIOLOGIA INFANTIL'),
  (6, 'CIRURGIA CABEÇA E PESCOÇO'),
  (7, 'CIRURGIA CARDÍACA'),
  (8, 'CIRURGIA DE TÓRAX');
	
INSERT INTO MEDICO_ESPECIALIDADE ( CRM , especialidade_ID) VALUES
	 ('1234569','2'),
	 ('1234569','4');
	 