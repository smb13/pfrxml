DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS packs CASCADE;
DROP TABLE IF EXISTS files;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    login           VARCHAR  NOT NULL,
    first_name      VARCHAR  NOT NULL,
    last_name       VARCHAR  NOT NULL,
    middle_name     VARCHAR  NOT NULL,
    phone_number    VARCHAR  NOT NULL,
    password        VARCHAR  NOT NULL,
    registered      TIMESTAMP DEFAULT now() NOT NULL,
    enabled         BOOLEAN DEFAULT TRUE  NOT NULL
);

CREATE TABLE packs
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR  NOT NULL,
    user_id	         INTEGER  NOT NULL,
    loaded           TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE files
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    pack_id	     INTEGER  NOT NULL,
    type             VARCHAR  NOT NULL,
    format_version	 VARCHAR  NOT NULL,
    year             VARCHAR  NOT NULL,
    reg_num_to_pfr	 VARCHAR  NOT NULL,
    district_code	 VARCHAR  NOT NULL,
    package_number	 VARCHAR  NOT NULL,
    document_code	 VARCHAR  NOT NULL,
    branch_number	 VARCHAR  NOT NULL,
    out_numb         VARCHAR,
    body             TEXT  NOT NULL,
    FOREIGN KEY (pack_id) REFERENCES packs (id) ON DELETE CASCADE
);


CREATE UNIQUE INDEX users_unique_login_idx ON users (login);

CREATE TABLE user_roles
(
    user_id         INTEGER NOT NULL,
    role            VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

