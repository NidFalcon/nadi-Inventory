CREATE TABLE is_users(
    user_id     NUMBER(8)     NOT NULL,
    username    VARCHAR2(20)  NOT NULL,
    password    VARCHAR2(256) NOT NULL,
    CONSTRAINT is_users_pk PRIMARY KEY (user_id),
    CONSTRAINT is_users_unique UNIQUE (username)
);