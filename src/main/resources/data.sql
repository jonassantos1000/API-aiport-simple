INSERT INTO CLIENT(nome, cpf, logradouro, telefone, email) VALUES ('maria','01234567891', 'rua marechal', '20554455', 'maria@gmail.com');

INSERT INTO CLIENT(nome, cpf, logradouro, telefone, email) VALUES ('joao','01234567891', 'rua fulano', '23515448', 'joao@gmail.com');

INSERT INTO FLIGHT(arrival_time, departure_time, destiny, origin, price) VALUES ('2021-03-12 10:00:00', '2021-03-11 10:00:00', 'bahiaa', 'goias', 450.0);

INSERT INTO FLIGHT(arrival_time, departure_time, destiny, origin, price) VALUES ('2021-03-12 10:00:00', '2021-03-11 10:00:00', 'rio de janeiro', 'sao paulo', 500.0);

INSERT INTO TICKET(data_compra, client_id, flight_id) values ('2021-03-10 11:00:00', 1,1);    
