CREATE TABLE qkc_raw_material_list (
	material_list_id INTEGER GENERATED ALWAYS AS IDENTITY (
    	START WITH 1
    	INCREMENT BY 1
    	CACHE 20
	) PRIMARY KEY,
	material_cd VARCHAR(255),
	quantity INTEGER NOT NULL,
	user_id INTEGER  NOT NULL,
	date_receive DATE NOT NULL,
	branch_id INTEGER  NOT NULL,
	FOREIGN KEY (material_cd) REFERENCES qkc_raw_material(material_cd),
	FOREIGN KEY (user_id) REFERENCES qkc_user(user_id),
	FOREIGN KEY (branch_id) REFERENCES qkc_branch(branch_id)
);
