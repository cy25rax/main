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
('Milk', 80, 1, 1), ('Bread', 25, 2, 1), ('Cheese', 300, 3, 2);

create table users
(
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null
);

create table users_roles
(
    user_id    bigint not null references users (id),
    role_id    bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

--create table categories
--(
--    id                  bigserial primary key,
--    advertisement_id    bigint not null references users (id),
--    category            varchar(255),
--);

--create table order_items
--(
--    id                bigserial primary key,
--    product_id        bigint not null references products (id),
--    order_id          bigint not null references orders (id),
--    quantity          int    not null,
--    price_per_product int    not null,
--    price             int    not null,
--    created_at        timestamp default current_timestamp,
--    updated_at        timestamp default current_timestamp
--);


