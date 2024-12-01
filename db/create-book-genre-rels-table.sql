create table genres_of_books (
	id serial primary key,
	book bigint references books(id) not null,
	genre bigint references genres(id) not null
);
