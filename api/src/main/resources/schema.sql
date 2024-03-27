CREATE TABLE product
(
    product_id              INT PRIMARY KEY,
    name            VARCHAR (200) NOT NULL,
    unit_price       REAL NOT NULL,
    description     VARCHAR (500) NOT NULL,
    stock           INT NOT NULL,
    active_from     VARCHAR (500),
    deactivate_from VARCHAR (500),
    deactivated     INT ,
    weight          REAL NOT NULL,
    sizes           REAL NOT NULL,
    for_adults       INT NOT NULL,
    shop_id INT
);
