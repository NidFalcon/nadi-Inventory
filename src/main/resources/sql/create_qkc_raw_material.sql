CREATE TABLE qkc_raw_material (
	material_cd VARCHAR(255) PRIMARY KEY,
	material_name VARCHAR(255) NOT NULL,
	unit_of_measurement VARCHAR(255) NOT NULL,
	is_active CHAR(1) DEFAULT 'y' NOT NULL
);
