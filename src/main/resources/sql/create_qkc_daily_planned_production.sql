CREATE TABLE qkc_daily_planned_production (
	dpp_id INTEGER GENERATED ALWAYS AS IDENTITY (
    	START WITH 1
    	INCREMENT BY 1
    	CACHE 20
	) PRIMARY KEY,
	production_date DATE NOT NULL,
	branch_id INTEGER NOT NULL,
	sku_cd VARCHAR(255) NOT NULL,
	quantity INTEGER NOT NULL,
	status VARCHAR(255) NOT NULL,
	FOREIGN KEY (branch_id) REFERENCES qkc_branch(branch_id),
	FOREIGN KEY (sku_cd) REFERENCES qkc_sku(sku_cd)
);
