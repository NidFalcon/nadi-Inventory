CREATE TABLE is_inventory(
    inventory_id NUMBER(12)     NOT NULL,
    description  VARCHAR2(200)  NOT NULL,
    quantity     NUMBER(12)     NOT NULL,
    active_tag   VARCHAR2(1),
    CONSTRAINT is_inventory_pk PRIMARY KEY (inventory_id)
);