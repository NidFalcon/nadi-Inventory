CREATE TABLE qkc_sessions (
    session_id   VARCHAR2(40) NOT NULL,
    username     VARCHAR2(20) NOT NULL,
    CONSTRAINT qkc_sessions_pk PRIMARY KEY (session_id)
);
