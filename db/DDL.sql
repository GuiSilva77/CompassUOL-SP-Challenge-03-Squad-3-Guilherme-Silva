CREATE DATABASE IF NOT EXISTS challenge;

CREATE TABLE tb_user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(70) NOT NULL,

    CONSTRAINT PK_USER PRIMARY KEY(id),
    CONSTRAINT UQ_USER_EMAIL UNIQUE(email)
);

CREATE TABLE tb_category (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,

    CONSTRAINT PK_CATEGORY PRIMARY KEY(id)
);

CREATE TABLE tb_product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date DATETIME NOT NULL,
    description VARCHAR(128) NOT NULL,
    name VARCHAR(50) NOT NULL,
    img_url VARCHAR(128) NOT NULL,
    price DECIMAL NOT NULL,

    CONSTRAINT PK_PRODUCT PRIMARY KEY(id)
);

CREATE TABLE tb_role (
    id BIGINT NOT NULL AUTO_INCREMENT,
    role VARCHAR(30) NOT NULL,

    CONSTRAINT PK_ROLE PRIMARY KEY(id)
);

CREATE TABLE tb_product_category (
    id_product BIGINT NOT NULL,
    id_category BIGINT NOT NULL,

    CONSTRAINT PK_PRODUCT_CATEGORY PRIMARY KEY(id_product, id_category),
    CONSTRAINT FK_PRODUCT_PRODUCT_CATEGORY FOREIGN KEY(id_product) REFERENCES tb_product(id),
    CONSTRAINT FK_CATEGORY_PRODUCT_CATEGORY FOREIGN KEY(id_category) REFERENCES tb_category(id)
);

CREATE TABLE tb_user_role (
    id_user BIGINT NOT NULL,
    id_role BIGINT NOT NULL,

    CONSTRAINT PK_USER_ROLE PRIMARY KEY(id_user, id_role),
    CONSTRAINT FK_USER_USER_ROLE FOREIGN KEY(id_user) REFERENCES tb_user(id),
    CONSTRAINT FK_ROLE_USER_ROLE FOREIGN KEY(id_role) REFERENCES tb_role(id)
);

CREATE TABLE challenge.tb_email
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    from_email   VARCHAR(128)          NOT NULL,
    from_name    VARCHAR(128)          NOT NULL,
    reply_to     VARCHAR(128)          NOT NULL,
    to_email         VARCHAR(128)          NOT NULL,
    subject      VARCHAR(128)          NOT NULL,
    body         TEXT                  NOT NULL,
    content_type VARCHAR(128)          NOT NULL,
    CONSTRAINT pk_tb_email PRIMARY KEY (id)
);


