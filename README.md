# user-movie-demo

Data Focused Java Developer - Programming Test
·         Create an in-memory database
o    Credentials cfa_admin / p@$$w0rd!
o    Create a data model that fulfills the following requirements
·         Track users, movies, and movie ratings by user
o    Think through the modeling exercise and decide what tables fields will be useful/required
o    Save the DDL for the database to a file called "ddl.sql".
o    Populate each of the tables with some sample data, save these inserts in a file called "sample_data.sql". Read the queries below as they will require certain sample data to be populated.
·         Using Java/Spring, write RESTful API methods that return the following. We will evaluate based on correctness of results and coding style.
o    Given a userId, return all movies that user has watched and the total count, as well as the rating for that movie.
o    Given a parameter of "movieId", return its average rating.
o    Given a genre such as "action" and a userId, return the top 5 movies for that genre by average rating, where the rating was made by other users within 5 years (older and younger) of the user's age.
·         AFTER running your queries, we will attempt to insert some additional data, perhaps for a movie that already exists. Ensure the behavior of the database will support this properly (referential integrity, proper primary keys, etc). Give consideration to where indexes may be needed (and include in ddl.sql if any are created).
·         Gzip the completed database, your API code, plus the ddl.sql and sample_data.sql and submit them.
·         We will unzip the package and run:
o    mvn clean package
o    mvn jetty:run-war
·         Unless otherwise specified, we'll open a browser to http://localhost:8080 to view the app

