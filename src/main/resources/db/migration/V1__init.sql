create table products (id bigint auto_increment primary key, title varchar(255), price int);
insert into products (title, price)
values
('Chocolate', 90),
('Cookies', 150),
('Candies', 120);