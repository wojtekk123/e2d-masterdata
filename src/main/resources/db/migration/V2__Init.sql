
INSERT INTO school (name,official_name)
 VALUES ('Szkoła Podstawowa','Szkoła Podstawowa nr 9 im. Króla Jana III Sobieskiego');

 INSERT INTO school (name,official_name)
 VALUES ('Technikum','Zespół Szkół Mechaniczno-Elektronicznych');

INSERT INTO instructor ( school_id)
 VALUES (1);

INSERT INTO student ( school_id, start_education_date, end_education_date)
 VALUES (1,'2013-10-24','2019-10-24');
INSERT INTO student ( school_id, start_education_date, end_education_date)
 VALUES (1,'2013-10-24','2019-10-24');

INSERT INTO user_( first_name, second_name, email, phone_number, student_id)
 VALUES ('Marek','Unknown','pkvs@er.pl','333666959',1);
INSERT INTO user_( first_name, second_name, email, phone_number, student_id)
 VALUES ('Tom','MrRe','sdf@er.pl','234234',2);
 INSERT INTO user_(first_name, second_name, email, phone_number, instructor_id)
 VALUES ('Mark','Sre','sgr@ewr.pl','213234',1);

INSERT INTO school (name,official_name)
 VALUES ('Szkoła Podstawowa','Szkoła Podstawowa nr 9 im. Króla Jana III Sobieskiego');

 INSERT INTO school (name,official_name)
 VALUES ('Technikum','Zespół Szkół Mechaniczno-Elektronicznych');

INSERT INTO car (school_id, model, brand, registration_number)
 VALUES (1,'500','FIAT','ABC123');
