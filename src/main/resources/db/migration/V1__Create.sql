CREATE TABLE school
(
    id            SERIAL,
    name          VARCHAR(50)  NOT NULL,
    official_name varchar(100) NOT NUll,
    PRIMARY KEY (id)
);

CREATE TABLE "user"
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
    user_id              BIGINT REFERENCES "user" (id),
    start_education_date DATE NOT NULL,
    end_education_date   DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE instructor
(
    id        SERIAL,
    school_id BIGINT REFERENCES school (id),
    user_id   BIGINT REFERENCES "user" (id),
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


