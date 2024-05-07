CREATE TABLE Category(
     id   INT AUTO_INCREMENT PRIMARY KEY,
     name Varchar(50) NOT NULL
);

CREATE TABLE Address(
        id          BIGINT AUTO_INCREMENT PRIMARY KEY,
        number      Varchar (10) NOT NULL,
        road        Varchar (50) NOT NULL,
        city        Varchar (50) NOT NULL,
        add_info    Varchar(30)
);

CREATE TABLE Shop(
        id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
        name                Varchar (50) NOT NULL,
        description         Varchar (500) NOT NULL,
        active_since        DATETIME,
        disable_since       DATETIME,
        siret               Varchar (50) NOT NULL,
        address_id          BIGINT NOT NULL UNIQUE,
        FOREIGN KEY (address_id) REFERENCES Address(id)
);

CREATE TABLE Delivery_Person(
        id              BIGINT AUTO_INCREMENT PRIMARY KEY,
        firstname       Varchar (50) NOT NULL,
        lastname        Varchar (50) NOT NULL,
        vehicle         Varchar (50) NOT NULL,
        date_of_birth   DATETIME NOT NULL,
        active_since    DATETIME,
        disable_since   DATETIME,
        address_id      BIGINT,
        FOREIGN KEY (address_id) REFERENCES Address(id)
);

CREATE TABLE Admin(
        id          BIGINT AUTO_INCREMENT PRIMARY KEY,
        description Varchar (200) NOT NULL
);

CREATE TABLE Client(
    id              BIGINT PRIMARY KEY,
    firstname       VARCHAR (50) NOT NULL,
    lastname        VARCHAR (50) NOT NULL,
    active_since    DATETIME,
    disable_since   DATETIME,
    date_of_birth   DATETIME NOT NULL
);

CREATE TABLE client_address(
        address_id  BIGINT NOT NULL,
        client_id   BIGINT NOT NULL,
        FOREIGN KEY (address_id) REFERENCES Address(id),
        FOREIGN KEY (client_id) REFERENCES Client(id)
);

CREATE TABLE category_shop(
    shop_id         BIGINT NOT NULL,
    category_id     BIGINT NOT NULL,
    FOREIGN KEY (shop_id) REFERENCES Shop(id),
    FOREIGN KEY (category_id) REFERENCES Category(id)
);

CREATE TABLE _User (
     id                     BIGINT AUTO_INCREMENT PRIMARY KEY,
     mail                   Varchar (30) NOT NULL,
     password               Varchar (20) NOT NULL,
     client_id              BIGINT NULL UNIQUE,
     shop_id                BIGINT NULL UNIQUE,
     delivery_person_id     BIGINT NULL UNIQUE,
     admin_id               BIGINT NULL UNIQUE,
     FOREIGN KEY (client_id)    REFERENCES Client(id),
     FOREIGN KEY (shop_id)      REFERENCES Shop(id),
     FOREIGN KEY (delivery_person_id) REFERENCES Delivery_Person(id),
     FOREIGN KEY (admin_id)     REFERENCES Admin(id)
);

CREATE TABLE Delivery(
        id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
        fee                 REAL NOT NULL,
        arrive_at           DATETIME,
        got                 Bool,
        prepared            Bool,
        accepted            Bool,
        delivered           Bool,
        disable             Bool,
        delivery_person_id  BIGINT,
        FOREIGN KEY (delivery_person_id) REFERENCES Delivery_Person(id)
);

CREATE TABLE Product(
    id              BIGINT  AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR (20) NOT NULL,
    unit_price      REAL NOT NULL,
    description     VARCHAR (200) NOT NULL,
    stock           INT NOT NULL,
    active_since    DATETIME,
    disable_since   DATETIME,
    weight          REAL NOT NULL,
    sizes           REAL NOT NULL,
    for_adults      INT NOT NULL,
    shop_id         BIGINT NOT NULL,
    FOREIGN KEY (shop_id) REFERENCES Shop(id)
);

CREATE TABLE _Order (
        id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
        order_at            DATETIME,
        payed               Bool,
        client_id           BIGINT NOT NULL,
        delivery_id         BIGINT,
        ship_to             BIGINT,
        charge_to           BIGINT,
        FOREIGN KEY (client_id) REFERENCES Client(id),
        FOREIGN KEY (delivery_id) REFERENCES Delivery(id),
        FOREIGN KEY (ship_to) REFERENCES Address(id),
        FOREIGN KEY (charge_to) REFERENCES Address(id)
);

CREATE TABLE product_order(
      quantity      INT NOT NULL,
      order_id      BIGINT NOT NULL,
      product_id    BIGINT NOT NULL,
      FOREIGN KEY (order_id) REFERENCES _Order(id),
      FOREIGN KEY (product_id) REFERENCES Product(id)
);
