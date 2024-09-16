CREATE OR REPLACE TRIGGER update_finished_product_list_on_insert
AFTER INSERT ON qkc_daily_planned_production
FOR EACH ROW
BEGIN
	IF
    	:NEW.status = 'Finished'
	THEN
    	INSERT INTO qkc_finished_product_list (sku_cd, quantity, branch_id, dpp_id, date_finished)
    	VALUES (:NEW.sku_cd, :NEW.quantity, :NEW.branch_id, :NEW.dpp_id, :NEW.production_date);
	END IF;
END;
/
