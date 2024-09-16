CREATE OR REPLACE PROCEDURE delete_old_sessions AS
BEGIN
    DELETE FROM qkc_sessions
    WHERE created_at < SYSTIMESTAMP - INTERVAL '1' DAY;
END;
/
