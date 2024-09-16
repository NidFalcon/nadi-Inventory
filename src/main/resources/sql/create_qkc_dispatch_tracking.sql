CREATE TABLE qkc_dispatch_tracking (
	dispatch_track_id INTEGER GENERATED ALWAYS AS IDENTITY (
    	START WITH 1
    	INCREMENT BY 1
    	CACHE 20
	) PRIMARY KEY,
	dispatch_type_cd VARCHAR(255) NOT NULL,
	fpl_id INTEGER NOT NULL,
	quantity INTEGER NOT NULL,
	branch_id INTEGER NOT NULL,
	destination VARCHAR(255),
	dispatch_date DATE NOT NULL,
	FOREIGN KEY (dispatch_type_cd) REFERENCES qkc_dispatch_type(dispatch_type_cd),
	FOREIGN KEY (fpl_id) REFERENCES qkc_finished_product_list(fpl_id),
	FOREIGN KEY (branch_id) REFERENCES qkc_branch(branch_id)
);
