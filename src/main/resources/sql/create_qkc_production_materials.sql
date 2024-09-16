CREATE TABLE qkc_production_materials (
	pm_id INTEGER GENERATED ALWAYS AS IDENTITY (
    	START WITH 1
    	INCREMENT BY 1
    	CACHE 20
	) PRIMARY KEY,
	dpp_id INTEGER NOT NULL,
	material_cd VARCHAR(255) NOT NULL,
	material_list_id INTEGER NOT NULL,
	quantity_to_use INTEGER NOT NULL,
	FOREIGN KEY (dpp_id) REFERENCES qkc_daily_planned_production(dpp_id) ON DELETE CASCADE,
	FOREIGN KEY (material_cd) REFERENCES qkc_raw_material(material_cd),
	FOREIGN KEY (material_list_id) REFERENCES qkc_raw_material_list(material_list_id)
);
