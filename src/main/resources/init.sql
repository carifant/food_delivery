create table IF NOT EXISTS role
(
    id uuid default random_uuid(),
    name varchar_ignorecase(250) not null UNIQUE,
    constraint ROLE_PK primary key (id)
);

create table IF NOT EXISTS market
(
    id uuid default random_uuid(),
    name varchar_ignorecase(250) not null UNIQUE,
    user_id uuid not null,
    constraint MARKET_PK primary key (id)
 );

 create table IF NOT EXISTS users
(
    id uuid default random_uuid(),
    name varchar_ignorecase(250) not null UNIQUE,
    role_id uuid not null,
    constraint USERS_PK primary key (id)
 );

 create table IF NOT EXISTS items
(
    id uuid default random_uuid(),
    name varchar_ignorecase(250) not null UNIQUE,
    description varchar_ignorecase(250) not null,
    market_id uuid not null,
    price decimal not null,
    constraint ITEMS_PK primary key (id)
 );

 create table IF NOT EXISTS user_transaction
(
    id uuid default random_uuid(),
    user_id uuid not null,
    item_id uuid not null,
    count int not null,
    price decimal not null,
    total decimal not null,
    constraint USER_TRANSACTION_PK primary key (id)
 );
