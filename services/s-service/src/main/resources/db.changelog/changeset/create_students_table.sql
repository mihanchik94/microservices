create table students(
    id serial primary key,
    name varchar(32) not null,
    surname varchar(32) not null,
    patronymic varchar(32),
    faculty varchar(128) not null,
    grade_book_number varchar(16) not null unique
);