CREATE TABLE qkc_dispatch_type (
	dispatch_type_cd VARCHAR(255) PRIMARY KEY,
	dispatch_type_name VARCHAR(255) NOT NULL,
	is_active CHAR(1) DEFAULT 'y' NOT NULL
);
