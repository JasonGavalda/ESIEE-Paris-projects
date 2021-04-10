create table RDV(
	rdv_id INT PRIMARY KEY AUTOINCREMENT NOT NULL,
	title VARCHAR(256) NOT NULL,
	date DATE NOT NULL,
	time TIME NOT NULL,
	address VARCHAR(256),
	phone_number INT
)