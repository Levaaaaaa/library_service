CREATE TABLE books (
	id bigint primary key auto_increment,
    isbn varchar(13) unique not null,
    title varchar(200) not null,
    description varchar(1000),
    author bigint not null
)