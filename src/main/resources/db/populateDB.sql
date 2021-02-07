DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM packs;
DELETE FROM dataFiles;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (login, first_name, last_name, middle_name, phone_number, password)
VALUES ('admsam5', 'Шаманин', 'Михаил', 'Владимирович', '+78312969500', 'password'),
       ('ruauso2', 'Ушакова', 'Ольга', 'Сергеевна', '+786133333333', 'password');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100001),
       ('ADMIN', 100000);

INSERT INTO packs (name, user_id, processed, loaded)
VALUES ('223121903.001', 100000, false, '2020-12-19 15:17:00'),
       ('223122001.001', 100001, true, '2020-12-20 16:02:00'),
       ('223122102.001', 100001, false, '2020-12-22 10:34:00');

INSERT INTO dataFiles (pack_id, type, format_version, year, reg_num_to_pfr, district_code, package_number,
    document_code, branch_number, out_numb , body)
VALUES (100002, 'PFR', '700', '2020', '070-000-121212', '000', '00006-000', 'OPPF', '6789',NULL,'BODY'),
       (100002, 'PFR', '700', '2020', '070-000-121212', '000', '00006-001', 'SPIS', '6789',NULL,'BODY'),
       (100003, 'PFR', '700', '2020', '070-000-121212', '000', '00010-000', 'OPPF', '6789',NULL,'BODY'),
       (100003, 'PFR', '700', '2020', '070-000-121212', '000', '00010-001', 'SPIS', '6789',NULL,'BODY'),
       (100004, 'PFR', '700', '2020', '070-000-121212', '000', '00123-000', 'OPPF', '6789',NULL,'BODY'),
       (100004, 'PFR', '700', '2020', '070-000-121212', '000', '00123-001', 'SPIS', '6789',NULL,'BODY');