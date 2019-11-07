
CREATE TABLE school (
    id SERIAL ,
    name varchar(50) NOT NULL,
    official_name varchar(100)  NOT NUll,
    PRIMARY KEY (id)
);

CREATE TABLE student (
    id SERIAL,
    school_id INT REFERENCES school(id),
    start_education_date DATE NOT NULL,
    end_education_date DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE instructor(
    id SERIAL,
    school_id INT REFERENCES school(id),
    PRIMARY KEY (id)
);

CREATE TABLE "user_account" (
    id SERIAL,
    first_name varchar (100) NOT NULL,
    second_name varchar (100) NOT NULL,
    email varchar (100) NOT NULL,
    phone_number varchar (100) NOT NULL,
    student_id INT REFERENCES student(id),
    instructor_id INT REFERENCES instructor(id),
    PRIMARY KEY (id),
    UNIQUE (email),
    UNIQUE (phone_number)

);

CREATE TABLE car(
    id SERIAL,
    school_id INT REFERENCES school(id),
    model VARCHAR (50) NOT NUll,
    brand VARCHAR (50) NOT NULL,
    registration_number VARCHAR (50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (registration_number)
);

