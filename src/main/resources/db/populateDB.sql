DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM packs;
DELETE FROM files;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (login, first_name, last_name, middle_name, phone_number, password)
VALUES ('admsam5', 'Шаманин', 'Михаил', 'Владимирович', '+78312969500', 'password'),
       ('ruauso2', 'Ушакова', 'Ольга', 'Сергеевна', '+786133333333', 'password');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100001),
       ('ADMIN', 100000);
