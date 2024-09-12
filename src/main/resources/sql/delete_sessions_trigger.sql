CREATE OR REPLACE TRIGGER delete_sessions_trigger
AFTER INSERT ON some_table  -- Choose a table that fits your use case
FOR EACH ROW
BEGIN
    IF (SYSDATE - (SELECT MAX(created_at) FROM qkc_sessions)) > INTERVAL '1' HOUR THEN
        delete_old_sessions;
    END IF;
END;
/
