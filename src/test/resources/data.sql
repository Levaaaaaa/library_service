delete genres_of_books;
delete genres;
delete books;
delete authors;

insert into authors (first_name, last_name)
values
('John', 'Test author 1'),
('Alexandr', 'Test author 2'),
('Maxim', 'Test author 3');

insert into genres (genre)
values
('fantasy'),
('horror'),
('sci-fi'),
('poetry'),
('lyrics'),
('epos');

insert into books (isbn, title, author)
select
	'1111111111111',
	'Test title 1',
	authors.id
from authors where authors.last_name = 'Test author 1';

insert into books (isbn, title, author)
select
	'2222222222222',
	'Test title 2',
	authors.id
from authors where authors.last_name = 'Test author 2';

insert into books (isbn, title, author)
select
	'3333333333333',
	'Test title 3',
    authors.id
from authors where authors.last_name = 'Test author 3';

insert into genres_of_books (book, genre)
values
(
	(select id from books where title like 'Test title 1'),
	(select id from genres where genre like 'fantasy')
),
(
	(select id from books where title like 'Test title 1'),
	(select id from genres where genre like 'epos')
),
(
	(select id from books where title like 'Test title 2'),
	(select id from genres where genre like 'poetry')
),
(
	(select id from books where title like 'Test title 3'),
	(select id from genres where genre like 'poetry')
),
(
	(select id from books where title like 'Test title 3'),
	(select id from genres where genre like 'lyrics')
);