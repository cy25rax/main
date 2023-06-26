create table advertisement (
    id              bigserial primary key,
    title           varchar(255),
    price           int,
    category_id     int,
    user_id         int,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into advertisement (title, price, category_id, user_id) values
('Milk', 80, 1, 1), ('Bread', 25, 2, 1), ('Cheese', 300, 2, 2);

create table categories (
    id              bigserial primary key,
    title           varchar(255) unique,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into categories (title) values ('Food'), ('Others');