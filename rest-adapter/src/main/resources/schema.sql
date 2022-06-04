create table ingredients(
    id primary key,
    name varchar not null,
    canonical varchar unique not null
);