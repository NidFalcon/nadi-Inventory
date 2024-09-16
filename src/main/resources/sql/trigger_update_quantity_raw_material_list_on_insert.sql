CREATE OR REPLACE TRIGGER update_quantity_raw_material_list_on_insert
AFTER INSERT ON qkc_production_materials
FOR EACH ROW
DECLARE
	current_quantity NUMBER;
BEGIN
	SELECT quantity
	INTO current_quantity
	FROM qkc_raw_material_list
	WHERE material_list_id = :NEW.material_list_id;

	IF current_quantity >= :NEW.quantity_to_use THEN
    	UPDATE qkc_raw_material_list
    	SET quantity = quantity - :NEW.quantity_to_use
    	WHERE material_list_id = :NEW.material_list_id;
	ELSE
    	RAISE_APPLICATION_ERROR(-20001, 'Not Enough Stocks');
	END IF;
EXCEPTION
	WHEN OTHERS THEN
    	RAISE_APPLICATION_ERROR(-20001, 'An error occurred: ' || SQLERRM);
END;
/
