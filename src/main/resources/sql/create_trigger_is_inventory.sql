CREATE OR REPLACE TRIGGER is_inventory
BEFORE INSERT ON is_inventory FOR EACH ROW
BEGIN
    SELECT NVL(MAX(inventory_id),0) + 1
      INTO :NEW.inventory_id
      FROM is_inventory;
END;