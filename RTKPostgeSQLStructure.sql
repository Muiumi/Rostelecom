CREATE TABLE classrooms (
    year_of_study INTEGER NOT NULL,
	subjects TEXT[] NOT NULL,
	CONSTRAINT classroom_pkey PRIMARY KEY (year_of_study)
);

CREATE TABLE persons (
    id SERIAL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    age INTEGER NOT NULL,
	classroom_id INTEGER NOT NULL,
	CONSTRAINT fk_classroom_id FOREIGN KEY (classroom_id)
		REFERENCES classrooms (year_of_study),
	CONSTRAINT person_pkey PRIMARY KEY (id)
);

CREATE TABLE grades (
	person_grade_id SERIAL NOT NULL,
	subject VARCHAR(50) NOT NULL,
    person_id INTEGER NOT NULL,
    grade INTEGER NOT NULL,
	CONSTRAINT fk_person_id FOREIGN KEY (person_id)
		REFERENCES persons (id),
	CONSTRAINT grades_pkey PRIMARY KEY (person_grade_id)
);