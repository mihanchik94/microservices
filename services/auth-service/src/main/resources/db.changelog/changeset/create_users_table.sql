create table users(
    id bigint primary key generated always as identity unique,
    username varchar(128) not null unique,
    password varchar(32) not null unique
);