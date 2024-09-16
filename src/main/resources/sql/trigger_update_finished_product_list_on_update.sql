CREATE OR REPLACE TRIGGER update_finished_product_list_on_update
BEFORE UPDATE ON qkc_daily_planned_production
FOR EACH ROW
DECLARE
	fplQuantity NUMBER;
BEGIN
	IF
    	:NEW.status = 'Finished' AND :OLD.status != 'Finished'
	THEN
    	INSERT INTO qkc_finished_product_list (sku_cd, quantity, branch_id, dpp_id, date_finished)
    	VALUES (:NEW.sku_cd, :NEW.quantity, :NEW.branch_id, :NEW.dpp_id, :NEW.production_date);
	ELSIF
    	:OLD.status = 'Finished' AND :NEW.status != 'Finished'
	THEN
    	SELECT quantity
        	INTO fplQuantity
        	FROM qkc_finished_product_list
        	WHERE dpp_id = :NEW.dpp_id
        	AND dpp_id = :OLD.dpp_id;
    	IF
        	fplQuantity = :OLD.quantity
    	THEN
        	DELETE FROM qkc_finished_product_list
        	WHERE dpp_id = :OLD.dpp_id;
    	ELSE
        	RAISE_APPLICATION_ERROR(-20002, 'FPL quantity already modified' || SQLERRM);
    	END IF;
	ELSIF
    	:OLD.status = 'Finished' AND :NEW.status = 'Finished'
	THEN
    	SELECT quantity
        	INTO fplQuantity
        	FROM qkc_finished_product_list
        	WHERE dpp_id = :NEW.dpp_id
        	AND dpp_id = :OLD.dpp_id;
    	IF
        	(:NEW.quantity + fplQuantity - :OLD.quantity) >= 0
    	THEN
        	UPDATE qkc_finished_product_list
            	SET quantity = :NEW.quantity + fplQuantity - :OLD.quantity
            	WHERE dpp_id = :OLD.dpp_id
            	AND dpp_id = :NEW.dpp_id;
    	ELSE
        	RAISE_APPLICATION_ERROR(-20003, 'FPL quantity not enough' || SQLERRM);
    	END IF;
	END IF;
END;
/
