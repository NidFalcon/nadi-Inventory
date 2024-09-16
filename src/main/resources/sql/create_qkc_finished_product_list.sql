CREATE TABLE qkc_finished_product_list (
	fpl_id INTEGER GENERATED ALWAYS AS IDENTITY (
    	START WITH 1
    	INCREMENT BY 1
    	CACHE 20
	) PRIMARY KEY,
            dpp_id INTEGER NOT NULL,
	sku_cd VARCHAR(255) NOT NULL,
	quantity INTEGER NOT NULL,
	branch_id INTEGER NOT NULL,
	date_finished DATE NOT NULL,
            FOREIGN KEY (dpp_id) REFERENCES qkc_daily_planned_production(dpp_id),
	FOREIGN KEY (sku_cd) REFERENCES qkc_sku(sku_cd),
	FOREIGN KEY (branch_id) REFERENCES qkc_branch(branch_id)
);
