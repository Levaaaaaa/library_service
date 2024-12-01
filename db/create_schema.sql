create table authors (
	id serial primary key,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	patronymic varchar(50),
	birth_date date,
	email varchar(100)
);

create table genres (
	id serial primary key,
	genre varchar(50) not null unique
);

CREATE TABLE books (
	id bigint primary key auto_increment,
    isbn varchar(13) unique not null,
    title varchar(200) not null,
    description varchar(1000),
    author bigint references authors(id) not null
);

create table genres_of_books (
	id serial primary key,
	book bigint references books(id) not null,
	genre bigint references genres(id) not null
);
