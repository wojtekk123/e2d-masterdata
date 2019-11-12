INSERT INTO school (name, official_name)
VALUES ('Szkoła Podstawowa', 'Szkoła Podstawowa nr 9 im. Króla Jana III Sobieskiego');
INSERT INTO school (name, official_name)
VALUES ('Technikum', 'Zespół Szkół Mechaniczno-Elektronicznych');

INSERT INTO "user"(first_name, second_name, email, phone_number)
VALUES ('Marek', 'Nowak', 'pkvs@o2.pl', '+12 123123322');
INSERT INTO "user"(first_name, second_name, email, phone_number)
VALUES ('Tom', 'Kowalski', 'mbaadf@eo2.pl', '+12 123123323');
INSERT INTO "user"(first_name, second_name, email, phone_number)
VALUES ('Luis', 'Mart', 'sgr@o2.pl', '+12 123123324');
INSERT INTO "user"(first_name, second_name, email, phone_number)
VALUES ('Hans', 'Bond', 'makk@o2.pl', '+12 123123325');

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


