insert into Director (FIRST_NAME,LAST_NAME) values ('Christopher', 'Nolan');
insert into Director (FIRST_NAME,LAST_NAME) values ('Quentin', 'Tarantino');
insert into Director (FIRST_NAME,LAST_NAME) values ('George', 'Miller');
insert into Director (FIRST_NAME,LAST_NAME) values ('Stephen', 'Spielberg');

insert into Genre (GENRE_NAME) values ('Action');
insert into Genre (GENRE_NAME) values ('Adventure');
insert into Genre (GENRE_NAME) values ('Horror');
insert into Genre (GENRE_NAME) values ('Thriller');
insert into Genre (GENRE_NAME) values ('Comedy');

insert into Movie (TITLE,RELEASE_YEAR,DESCRIPTION,ID_DIRECTOR) values ('Interstellar', 2014, 'Astronauts travel through space to save humanity', 1);
insert into Movie (TITLE,RELEASE_YEAR,DESCRIPTION,ID_DIRECTOR) values ('Inception', 2010,'Agents go into dreams', 1);
insert into Movie (TITLE,RELEASE_YEAR,DESCRIPTION,ID_DIRECTOR) values ('Inglorious Basterds',2009, 'Americans and French plan to end WWII', 2);
insert into Movie (TITLE,RELEASE_YEAR,DESCRIPTION,ID_DIRECTOR) values ('Django Unchained',2013, 'Freed slave looks for his wive', 2);
insert into Movie (TITLE,RELEASE_YEAR,DESCRIPTION,ID_DIRECTOR) values ('Indiana Jones 1',1981, 'Arqueologist looks for the Ark of the Covenant', 4);

insert into MOVIE_GENRES (ID_MOVIE,ID_GENRE) values (1,1);
insert into MOVIE_GENRES (ID_MOVIE,ID_GENRE) values (1,2);
insert into MOVIE_GENRES (ID_MOVIE,ID_GENRE) values (2,1);
insert into MOVIE_GENRES (ID_MOVIE,ID_GENRE) values (2,3);
insert into MOVIE_GENRES (ID_MOVIE,ID_GENRE) values (3,1);
insert into MOVIE_GENRES (ID_MOVIE,ID_GENRE) values (3,2);
insert into MOVIE_GENRES (ID_MOVIE,ID_GENRE) values (4,1);
insert into MOVIE_GENRES (ID_MOVIE,ID_GENRE) values (4,2);
insert into MOVIE_GENRES (ID_MOVIE,ID_GENRE) values (5,1);
insert into MOVIE_GENRES (ID_MOVIE,ID_GENRE) values (5,2);