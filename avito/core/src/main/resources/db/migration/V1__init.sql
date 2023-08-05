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
('Pants', 80, 1, 1), ('Shirt', 25, 2, 1), ('Cap', 300, 2, 1),
('Chair', 120, 3, 2), ('Lada 2101', 500, 4, 2), ('Mirror', 20, 3, 1);

create table categories (
    id              bigserial primary key,
    title           varchar(255) unique,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into categories (title) values ('Clothes'), ('Others'), ('Furniture'), ('Car');

create table advertisement_users (
    id              bigserial primary key,
    username        varchar(255) unique
);

insert into advertisement_users (username) values ('bob'), ('john');

create table users_feedback (
    id              bigserial primary key,
    user_id         bigserial,
    text            varchar(255)
);