create table authors (
	id serial primary key,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	patronymic varchar(50),
	birth_date date,
	email varchar(100)
)