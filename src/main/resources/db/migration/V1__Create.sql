CREATE TABLE school
(
    id            SERIAL,
    name          VARCHAR(50)  NOT NULL,
    official_name varchar(100) NOT NUll,
    PRIMARY KEY (id)
);

CREATE TABLE "user_"
(
    id           SERIAL,
    first_name   VARCHAR(100) NOT NULL,
    second_name  VARCHAR(100) NOT NULL,
    email        VARCHAR(100) NOT NULL,
    phone_number VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email),
    UNIQUE (phone_number)
);

CREATE TABLE student
(
    id                   SERIAL,
    school_id            BIGINT REFERENCES school (id),
    user_id              BIGINT REFERENCES user_ (id),
    start_education_date DATE NOT NULL,
    end_education_date   DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE instructor
(
    id        SERIAL,
    school_id BIGINT REFERENCES school (id),
    user_id   BIGINT REFERENCES user_ (id),
    PRIMARY KEY (id)
);



CREATE TABLE car
(
    id                  SERIAL,
    school_id           BIGINT REFERENCES school (id),
    model               VARCHAR(50) NOT NUll,
    brand               VARCHAR(50) NOT NULL,
    registration_number VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (registration_number)
);

INSERT INTO school (name, official_name)
VALUES ('Szkoła Podstawowa', 'Szkoła Podstawowa nr 9 im. Króla Jana III Sobieskiego');
INSERT INTO school (name, official_name)
VALUES ('Technikum', 'Zespół Szkół Mechaniczno-Elektronicznych');

INSERT INTO user_(first_name, second_name, email, phone_number)
VALUES ('Marek', 'Unknown', 'pkvs@er.pl', '333666959');
INSERT INTO user_(first_name, second_name, email, phone_number)
VALUES ('Tom', 'MrRe', 'sdf@er.pl', '234234');
INSERT INTO user_(first_name, second_name, email, phone_number)
VALUES ('Mark', 'Sre', 'sgr@ewr.pl', '213234');
INSERT INTO user_(first_name, second_name, email, phone_number)
VALUES ('Hans', 'brook', 'mys@ewr.pl', '432234432');

INSERT INTO instructor (school_id, user_id)
VALUES (1, 1);
INSERT INTO instructor (school_id, user_id)
VALUES (2, 2);

INSERT INTO student (school_id, user_id, start_education_date, end_education_date)
VALUES (1, 3, '2013-10-24', '2019-10-24');
INSERT INTO student (school_id, user_id, start_education_date, end_education_date)
VALUES (2, 4, '2013-10-24', '2019-10-24');

INSERT INTO car (school_id, model, brand, registration_number)
VALUES (1, '500', 'FIAT', 'ABC123');



