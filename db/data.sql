insert into authors (first_name, last_name)
values
('John', 'Tolkien'),
('Alexandr', 'Pushkin'),
('Maxim', 'Bogdanovich')

insert into genres (genre)
values
('fantasy'),
('horror'),
('sci-fi'),
('poetry'),
('lyrics'),
('epos');

insert into books (isbn, title, author)
values
(
	'1111111111111',
	'The Lord of the Rings',
	(select id from authors where last_name like 'Tolkien')
),
(
	'2222222222222',
	'Eugene Onegin',
	(select id from authors where last_name like 'Pushkin')
),
(
	'3333333333333',
	'The star Venus',
	(select id from authors where last_name like 'Bogdanovich')
);

insert into genres_of_books (book, genre)
values
(
	(select id from books where title like 'The Lord of the Rings'),
	(select id from genres where genre like 'fantasy')
),
(
	(select id from books where title like 'The Lord of the Rings'),
	(select id from genres where genre like 'epos')
),
(
	(select id from books where title like 'Eugene Onegin'),
	(select id from genres where genre like 'poetry')
),
(
	(select id from books where title like 'The star Venus'),
	(select id from genres where genre like 'poetry')
),
(
	(select id from books where title like 'The star Venus'),
	(select id from genres where genre like 'lyrics')
);