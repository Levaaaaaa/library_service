create table if not exists authors (
	id bigint unique auto_increment,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	patronymic varchar(50),
	birth_date date,
	email varchar(100),
	primary key(id)
);

CREATE TABLE if not exists books (
	id bigint unique auto_increment,
    isbn varchar(13) unique not null,
    title varchar(200) not null,
    description varchar(1000),
    author bigint references authors(id),
    primary key(id)
);

create table if not exists genres (
	id bigint unique auto_increment,
	genre varchar(50) not null unique,
	primary key(id)
);

create table if not exists genres_of_books (
	id bigint not null auto_increment,
	book bigint not null,
	genre bigint references genres(id) not null,
	primary key(id),
	constraint genres_of_books_book_fk foreign key(book) references books(id) on delete cascade
);

create table if not exists users (
    id bigint not null auto_increment,
    username varchar(50) not null unique,
    password varchar(50) not null,
    primary key(id)
);

