CREATE OR REPLACE TRIGGER update_quantity_raw_material_list_on_delete
AFTER DELETE ON qkc_production_materials
FOR EACH ROW
BEGIN
  UPDATE qkc_raw_material_list
  SET quantity = quantity + :OLD.quantity_to_use
  WHERE material_list_id = :OLD.material_list_id;
END;
/
