INSERT INTO monetary_units (id, code, name) VALUES
    (nextval('monetary_units_seq'), 'RUB', 'rubles');

INSERT INTO call_types (id, code, name) VALUES
    (nextval('call_types_seq'), '01', 'OUTGOING'),
    (nextval('call_types_seq'), '02', 'INCOMING');

INSERT INTO tariffs (id, code, name) VALUES
     (nextval('tariffs_seq'), '06', 'unlimitedTariff'),
     (nextval('tariffs_seq'), '03', 'minuteTariff'),
     (nextval('tariffs_seq'), '11', 'regularTariff');

INSERT INTO clients (id, balance, phone_number, tariff_id) VALUES
    (nextval('clients_seq'), 1000, '70000000000', (SELECT t.id FROM tariffs t WHERE t.code = '06'));

INSERT INTO users (id, password, username, client_id) VALUES
    (nextval('users_seq'), '{bcrypt}$2a$10$nVgeyg60L8OSIzmHUN8RMOXFs5sedApZJ4WORb..OodNEb745MVGK', 'manager', null),
    (nextval('users_seq'), '{bcrypt}$2a$10$gqZfeeTEibaJwkipp0XFXO8wVogxY0H1Gqmkenf.cwFynAZuhmgrK', 'test', (
        SELECT c.id FROM clients c WHERE phone_number = '70000000000'
    ));

INSERT INTO  user_roles (user_id, role) VALUES
    ((SELECT u.id from users u WHERE username = 'manager'), 'MANAGER'),
    ((SELECT u.id from users u WHERE username = 'manager'), 'ABONENT'),
    ((SELECT u.id from users u WHERE username = 'test'), 'ABONENT');