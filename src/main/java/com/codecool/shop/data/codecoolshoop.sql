

create table customer
(
	id bigserial not null,
	first_name varchar not null,
	last_name varchar not null,
	email_address varchar not null,
	password varchar not null,
	billing_address varchar,
	shiping varchar
);

create unique index customer_customer_id_uindex
	on customer (id);

-- create unique index customer_email_address_uindex
-- 	on customer (email_address);

alter table customer
	add constraint customer_pk
		primary key (id);


-- create category table
create table category
(
    id        bigserial not null,
    category_name        varchar   not null,
    category_department  varchar   not null,
    category_description varchar   not null
);

create unique index category_category_id_uindex
    on category (id);

-- create unique index category_category_name_uindex
--     on category (category_name);

alter table category
    add constraint category_pk
        primary key (id);

-- create supplier table
create table supplier
(
	id bigserial not null,
	supplier_name varchar not null,
	supplier_description varchar not null
);

create unique index supplier_supplier_id_uindex
	on supplier (id);

-- create unique index supplier_supplier_name_uindex
-- 	on supplier (supplier_name);

alter table supplier
	add constraint supplier_pk
		primary key (id);


-- create product table
create table product
(
	id bigserial not null,
	category_id int
		constraint product_category_id_fk
			references category(id),
	supplier_id int
		constraint product_supplier_id_fk
			references supplier(id),
	product_name varchar,
	product_description varchar,
	product_price float4,
	product_currency varchar,
	quantity int,
	image varchar
);

create unique index product_product_id_uindex
	on product (id);

alter table product
	add constraint product_pk
		primary key (id);

-- insert categories values
INSERT INTO category (id, category_name, category_department, category_description) VALUES (DEFAULT, 'Tablet', 'Hardware', 'A tablet computer');
INSERT INTO category (id, category_name, category_department, category_description) VALUES (DEFAULT, 'Laptop', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO category (id, category_name, category_department, category_description) VALUES (DEFAULT, 'TV', 'Hardware', 'Display');
INSERT INTO category (id, category_name, category_department, category_description) VALUES (DEFAULT, 'Smartphone', 'Hardware', 'Phone');
INSERT INTO category (id, category_name, category_department, category_description) VALUES (DEFAULT, 'Antivirus', 'Software', 'Antivirus software');

-- suppliers
INSERT INTO supplier (id, supplier_name, supplier_description) VALUES (DEFAULT, 'Amazon', 'Digital content and services');
INSERT INTO supplier (id, supplier_name, supplier_description) VALUES (DEFAULT, 'Lenovo', 'Computers');
INSERT INTO supplier (id, supplier_name, supplier_description) VALUES (DEFAULT, 'Apple', 'Technology');
INSERT INTO supplier (id, supplier_name, supplier_description) VALUES (DEFAULT, 'Microsoft', 'Software');
INSERT INTO supplier (id, supplier_name, supplier_description) VALUES (DEFAULT, 'Avast', 'Software');
INSERT INTO supplier (id, supplier_name, supplier_description) VALUES (DEFAULT, 'LG', 'Technology');
INSERT INTO supplier (id, supplier_name, supplier_description) VALUES (DEFAULT, 'Samsung', 'Technology');

-- products
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 1, 1, 'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 'USD', 10000, 'src/main/webapp/static/img/product_1.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 1, 2, 'Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 'USD', 5000, 'src/main/webapp/static/img/product_2.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 1, 1, 'Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.",', 89, 'USD', 10000, 'src/main/webapp/static/img/product_3.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 1, 2, 'Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 'USD', 5000, 'src/main/webapp/static/img/product_2.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 1, 1, 'Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.",', 89, 'USD', 10000, 'src/main/webapp/static/img/product_3.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 2, 1, 'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 'USD', 10000, 'src/main/webapp/static/img/product_1.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 2, 1, 'Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.",', 89, 'USD', 10000, 'src/main/webapp/static/img/product_3.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 2, 1, 'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 'USD', 10000, 'src/main/webapp/static/img/product_1.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 2, 3, 'Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 'USD', 5000, 'src/main/webapp/static/img/product_2.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 3, 3, 'Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.",', 89, 'USD', 10000, 'src/main/webapp/static/img/product_3.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 3, 3, 'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 'USD', 10000, 'src/main/webapp/static/img/product_1.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 3, 3, 'Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 'USD', 5000, 'src/main/webapp/static/img/product_2.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 3, 4, 'Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.",', 89, 'USD', 10000, 'src/main/webapp/static/img/product_3.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 4, 4, 'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 'USD', 10000, 'src/main/webapp/static/img/product_1.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 4, 4, 'Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 'USD', 5000, 'src/main/webapp/static/img/product_2.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 4, 4, 'Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.",', 89, 'USD', 10000, 'src/main/webapp/static/img/product_3.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 4, 5, 'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 'USD', 10000, 'src/main/webapp/static/img/product_1.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 4, 5, 'Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 'USD', 5000, 'src/main/webapp/static/img/product_2.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 4, 5, 'Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.",', 89, 'USD', 10000, 'src/main/webapp/static/img/product_3.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 5, 5, 'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 'USD', 10000, 'src/main/webapp/static/img/product_1.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 5, 6, 'Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 'USD', 5000, 'src/main/webapp/static/img/product_2.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 5, 6, 'Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.",', 89, 'USD', 10000, 'src/main/webapp/static/img/product_3.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 5, 6, 'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 'USD', 10000, 'src/main/webapp/static/img/product_1.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 5, 7, 'Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 'USD', 5000, 'src/main/webapp/static/img/product_2.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 5, 7, 'Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.",', 89, 'USD', 10000, 'src/main/webapp/static/img/product_3.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 5, 7, 'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 'USD', 10000, 'src/main/webapp/static/img/product_1.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 5, 7, 'Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 'USD', 5000, 'src/main/webapp/static/img/product_2.jpg');
INSERT INTO product (id, category_id, supplier_id, product_name, product_description, product_price, product_currency, quantity, image) VALUES (DEFAULT, 5, 7, 'Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.",', 89, 'USD', 10000, 'src/main/webapp/static/img/product_3.jpg');

-- users
INSERT INTO customer (id, first_name, last_name, email_address, password, billing_address, shiping) VALUES (DEFAULT, 'John', 'Doe', 'john@doe.com', '1234', 'Bucuresti', 'Pitesti')
