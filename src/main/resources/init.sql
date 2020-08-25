CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
create table IF NOT EXISTS role
(
    id uuid default uuid_generate_v4(),
    name varchar(250) not null UNIQUE,
    constraint ROLE_PK primary key (id)
);
create table IF NOT EXISTS market
(
    id uuid default uuid_generate_v4(),
    name varchar(250) not null UNIQUE,
    user_id uuid not null,
    constraint MARKET_PK primary key (id)
);
create table IF NOT EXISTS users
(
    id uuid default uuid_generate_v4(),
    name varchar(250) not null UNIQUE,
    role_id uuid not null,
    constraint USERS_PK primary key (id)
);
create table IF NOT EXISTS items
(
    id uuid default uuid_generate_v4(),
    name varchar(250) not null UNIQUE,
    description varchar(250) not null,
    market_id uuid not null,
    price decimal not null,
    constraint ITEMS_PK primary key (id)
);
create table IF NOT EXISTS user_transaction
(
    id uuid default uuid_generate_v4(),
    user_id uuid not null,
    item_id uuid not null,
    count int not null,
    price decimal not null,
    total decimal not null,
    constraint USER_TRANSACTION_PK primary key (id)
);
