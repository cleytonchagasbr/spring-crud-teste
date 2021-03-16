DROP TABLE exams;
DROP TABLE healthcare_institution;

CREATE TABLE healthcare_institution (
	id INTEGER PRIMARY KEY AUTO_INCREMENT, 
	name VARCHAR(64) NOT NULL, 
	cnpj VARCHAR(64) NOT NULL UNIQUE,
	coins int
);
	
CREATE TABLE exams(
	id INTEGER PRIMARY KEY AUTO_INCREMENT, 
    healthcareInstitution VARCHAR(64) NOT NULL,
	patientName VARCHAR(64) NOT NULL,
	patientAge VARCHAR(64) NOT NULL,
	patientGender VARCHAR(64) NOT NULL,
	physicianName VARCHAR(64) NOT NULL,
	physicianCRM VARCHAR(64) NOT NULL,
	procedureName VARCHAR(64) NOT NULL,
	foreign key (healthcareInstitution) references healthcare_institution(id)
);

INSERT INTO healthcare_institution(name, cnpj, coins) VALUES
('Teste', '123456789', 20);



