

INSERT INTO "user"(first_name, second_name,type, email, phone_number)
VALUES ('Marek', 'Nowak','ADMIN', 'pkvs@o2.pl', '+12 123123322');
INSERT INTO "user"(first_name, second_name,type, email, phone_number)
VALUES ('Tomek', 'Nowak','STUDENT',  'mbaadf@eo2.pl', '+12 123123323');
INSERT INTO "user"(first_name, second_name,type, email, phone_number)
VALUES ('Wojtek', 'Nowak', 'SCHOOL','pk4vs@o1.pl', '+12 123123321');
INSERT INTO "user"(first_name, second_name,type, email, phone_number)
VALUES ('Lukasz', 'Nowak','SCHOOL', 'pkx4vs@22.pl', '+12 122143324');
INSERT INTO "user"(first_name, second_name,type, email, phone_number)
VALUES ('Adam', 'Nowak','STUDENT', 'akx4vs@22.pl', '+12 142143324');
INSERT INTO "user"(first_name, second_name,type, email, phone_number)
VALUES ('instrucotr', 'Nowak','INSTRUCTOR', 'akx4vs@22.pl', '+12 142143324');

insert INTO auth(user_id, username, password)
VALUES (1,'Marek','1234');
insert INTO auth(user_id, username, password)
VALUES (2,'Tomek','1234');
insert INTO auth(user_id, username, password)
VALUES (3,'Wojtek','1234');
insert INTO auth(user_id, username, password)
VALUES (4,'Lukasz','1234');
insert INTO auth(user_id, username, password)
VALUES (5,'Adam','1234');

INSERT INTO school (user_id,name, official_name)
VALUES (3,'Szkoła Podstawowa', 'Szkoła Podstawowa nr 9 im. Króla Jana III Sobieskiego');
INSERT INTO school (user_id,name, official_name)
VALUES (4,'Technikum', 'Zespół Szkół Mechaniczno-Elektronicznych');

INSERT INTO student (school_id, user_id, start_education_date, end_education_date)
VALUES (1, 2, '2013-10-24', '2019-10-24');
INSERT INTO student (school_id, user_id, start_education_date, end_education_date)
VALUES (2, 5, '2013-10-24', '2019-10-24');

INSERT INTO instructor (school_id, user_id)
VALUES (1, 6);

INSERT INTO car (school_id, model, brand, registration_number)
VALUES (1, '500', 'FIAT', 'ABC123');
INSERT INTO car (school_id, model, brand, registration_number)
VALUES (1, '500', 'FIAT', 'ABC126');
INSERT INTO car (school_id, model, brand, registration_number)
VALUES (2, 'golf', 'Vw', 'ABC125');
INSERT INTO car (school_id, model, brand, registration_number)
VALUES (2, 'golf', 'Vw', 'ABC124');

