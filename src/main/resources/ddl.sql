CREATE TABLE genre
(
   genreid char(36) PRIMARY KEY NOT NULL,
   externalid int NOT NULL,
   name varchar(25) NOT NULL
);

CREATE TABLE movie
(
   movieid char(36) PRIMARY KEY NOT NULL,
   externalid int NOT NULL,
   name varchar(100) NOT NULL,
   genreid char(36) NOT NULL
);

CREATE TABLE movie_rating
(
   id char(36) PRIMARY KEY NOT NULL,
   rating decimal(10,0) NOT NULL,
   movieid char(36) NOT NULL,
   userid char(36) NOT NULL
);

CREATE TABLE user
(
   userid char(36) PRIMARY KEY NOT NULL,
   name varchar(25) NOT NULL,
   age int NOT NULL
);

CREATE UNIQUE INDEX unique_gr_genreid ON genre(genreid);

ALTER TABLE movie
ADD CONSTRAINT fk_m_genreid
FOREIGN KEY (genreid)
REFERENCES genre(genreid);

CREATE UNIQUE INDEX unique_mv_movieid ON movie(movieid);

CREATE INDEX genreid ON movie(genreid);
ALTER TABLE movie_rating
ADD CONSTRAINT fk_mr_userid
FOREIGN KEY (userid)
REFERENCES user(userid);

ALTER TABLE movie_rating
ADD CONSTRAINT fk_mr_movieid
FOREIGN KEY (movieid)
REFERENCES movie(movieid);

CREATE INDEX movieid ON movie_rating(movieid);

CREATE INDEX userid ON movie_rating(userid);

CREATE UNIQUE INDEX unique_mr_id ON movie_rating(id);

CREATE UNIQUE INDEX unique_ur_userid ON user(userid);

CREATE UNIQUE INDEX unique_mr_rating ON movie_rating(movieid,userid);
