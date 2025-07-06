create table student_pictures(
    id serial primary key,
    name varchar(64) not null,
    s3key varchar(128) not null unique,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    student_id int references students(id)
);