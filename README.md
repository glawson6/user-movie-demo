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
· 

# Highlights of app include

    Spring Data Rest
    Custom Spring Controllers for API
    JPA
    Spring JdbcTemplate
    Custom Spring Boot configuration classes
    Spring Profile Configuration
    Embedded database configuartion
    MySQL database configuration
    Flyway database migration

### To run this app

 mvn clean package && java -Dspring.profiles.active=production -jar target/user-movie-demo.jar 
 
 There is a ddl.sql and sample_data.sql in src/main/resources. The custom APIs are implemnted in MovieStatsController
 
 
# API test functionality

Given a userId, return all movies that user has watched and the total count, as well as the rating for that movie.
Request
```
http -v GET localhost:8080/api/movie/watched/f0f1efa0-9aad-4e28-ade9-c8be03acf2d1
GET /api/movie/watched/f0f1efa0-9aad-4e28-ade9-c8be03acf2d1 HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:8080
User-Agent: HTTPie/0.9.3
```

Response
```
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Date: Sun, 31 Jul 2016 23:04:21 GMT
Server: Apache-Coyote/1.1
Transfer-Encoding: chunked
X-Application-Context: application:production

{
    "moviesWatched": [
        {
            "genre": "Drama", 
            "movieId": "758d2fc1-2d15-4571-b59c-aefe82673e41", 
            "movieName": "Father and Godfather", 
            "rating": 9.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "e17c2aa2-2697-4901-85d6-7030dc8a37f9", 
            "movieName": "Star Wars: TIE Fighter", 
            "rating": 8.0
        }, 
        {
            "genre": "Action", 
            "movieId": "2d2553e7-edf2-40ba-8458-72d6a562497b", 
            "movieName": "Star Wars: The Force Awakens", 
            "rating": 3.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "ee2d17c0-9caf-434c-8543-086d264b363d", 
            "movieName": "Star Trek IV: The Voyage Home", 
            "rating": 0.0
        }, 
        {
            "genre": "Animation", 
            "movieId": "caa0731c-75dd-4333-9c75-0b8c104e4ac9", 
            "movieName": "Lego Star Wars: The Padawan Menace", 
            "rating": 9.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "eac3f728-2431-4ada-8f93-ca1561e75fdd", 
            "movieName": "Star Trek VI: The Undiscovered Country", 
            "rating": 4.0
        }, 
        {
            "genre": "Adventure", 
            "movieId": "cf77ed73-f562-4b9a-ab69-40e81eadbdd8", 
            "movieName": "Star Wars: Episode II - Attack of the Clones", 
            "rating": 1.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "36c9450b-88d0-4cce-bc0f-64849b161039", 
            "movieName": "Star Trek III: The Search for Spock", 
            "rating": 2.0
        }, 
        {
            "genre": "Documentary", 
            "movieId": "7968d53a-976c-4034-8779-6183754504dd", 
            "movieName": "The Godfather Legacy", 
            "rating": 6.0
        }, 
        {
            "genre": "Comedy", 
            "movieId": "99f5a9d3-384a-4466-9bf4-957bcbe64766", 
            "movieName": "In the Name of Godfather", 
            "rating": 5.0
        }, 
        {
            "genre": "Action", 
            "movieId": "9a15de0a-b398-4228-a8fc-8cef8562944e", 
            "movieName": "Star Trek Into Darkness", 
            "rating": 3.0
        }, 
        {
            "genre": "Drama", 
            "movieId": "e8a2f451-4d75-4bad-84eb-cff452d1ad9a", 
            "movieName": "The Godfather Squad", 
            "rating": 2.0
        }, 
        {
            "genre": "Action", 
            "movieId": "ff322589-0910-4c7f-b048-3b0070659fd6", 
            "movieName": "Hong Kong Godfather", 
            "rating": 5.0
        }, 
        {
            "genre": "Thriller", 
            "movieId": "2723fc00-9749-40a9-8081-d967cb4864f9", 
            "movieName": "Star Wars: The Clone Wars", 
            "rating": 5.0
        }, 
        {
            "genre": "Adventure", 
            "movieId": "ebb832d7-935c-468d-ad87-807aae923898", 
            "movieName": "Star Wars", 
            "rating": 9.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "ccdf25c8-1c2f-4028-b4b7-c8d0cd6fa2ef", 
            "movieName": "Han Solo: A Star Wars Story", 
            "rating": 6.0
        }, 
        {
            "genre": "Comedy", 
            "movieId": "f69c3826-e8ef-4ab6-b34e-0b43f00fe9ef", 
            "movieName": "The Godfather of Green Bay", 
            "rating": 0.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "78854f86-04f5-4e33-9e1e-0b91da4efc91", 
            "movieName": "Star Trek: First Contact", 
            "rating": 2.0
        }, 
        {
            "genre": "Drama", 
            "movieId": "491ff777-59d3-49fe-b829-4f7550e38ac8", 
            "movieName": "The Godfather: Part II", 
            "rating": 1.0
        }, 
        {
            "genre": "Action", 
            "movieId": "6d3263eb-ebfe-454f-850f-c6766692ee67", 
            "movieName": "The Last Godfather", 
            "rating": 1.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "0f8ad9fe-68c0-44bb-804d-a71cc7433be7", 
            "movieName": "Star Trek - Horizon", 
            "rating": 7.0
        }, 
        {
            "genre": "Documentary", 
            "movieId": "1592565d-ff42-438b-9ed2-bdffb24c7f8a", 
            "movieName": "Andy Warhol: A Documentary Film", 
            "rating": 5.0
        }, 
        {
            "genre": "Action", 
            "movieId": "a1c0e81d-b457-46eb-8df4-0c22ceed74b4", 
            "movieName": "Onimasa: A Japanese Godfather", 
            "rating": 1.0
        }, 
        {
            "genre": "Documentary", 
            "movieId": "2eecb8e3-296a-4fee-9fe4-b11b45891761", 
            "movieName": "Star Wars Begins", 
            "rating": 4.0
        }, 
        {
            "genre": "Adventure", 
            "movieId": "bbe986e1-ede2-4850-86fc-bf17e349e493", 
            "movieName": "The Empire Strikes Back", 
            "rating": 7.0
        }, 
        {
            "genre": "Drama", 
            "movieId": "dfc2e946-3700-4415-98f3-cfde12f96d84", 
            "movieName": "The Godfather", 
            "rating": 7.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "f8d53ad0-0757-4f8e-84eb-83330e647a7d", 
            "movieName": "Star Trek - Aurora", 
            "rating": 7.0
        }, 
        {
            "genre": "Action", 
            "movieId": "8641abc5-04b9-4003-99db-40b3b676952b", 
            "movieName": "Disco Godfather", 
            "rating": 4.0
        }, 
        {
            "genre": "Action", 
            "movieId": "507f4701-afc1-460e-ac98-eaa66fe2a1f8", 
            "movieName": "Godfather from Canton", 
            "rating": 10.0
        }, 
        {
            "genre": "Action", 
            "movieId": "155ea66d-74e9-42cc-b4dd-d13da1f3b242", 
            "movieName": "The Black Godfather", 
            "rating": 0.0
        }, 
        {
            "genre": "Drama", 
            "movieId": "45bb031c-373e-46df-8558-4c582f04b895", 
            "movieName": "GodFather", 
            "rating": 1.0
        }, 
        {
            "genre": "Adventure", 
            "movieId": "35c25c68-51f7-4371-a3da-2b9579cd8d6a", 
            "movieName": "Star Wars: Episode I - The Phantom Menace", 
            "rating": 5.0
        }, 
        {
            "genre": "Action", 
            "movieId": "3890b95d-d7f4-4577-a5eb-2cdbae571be4", 
            "movieName": "Star Trek Beyond", 
            "rating": 5.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "b79a76b5-cb0a-4f94-8b0e-c8bc2c71f68b", 
            "movieName": "Star Wars: Revelations", 
            "rating": 10.0
        }, 
        {
            "genre": "Action", 
            "movieId": "0aab9a46-e6ee-48ec-b009-6ba25cf7c407", 
            "movieName": "Star Wars: Episode IX", 
            "rating": 2.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "aa1eef60-79ee-4e12-8115-6139e5bc8031", 
            "movieName": "Star Wars: Broken Allegiance", 
            "rating": 1.0
        }, 
        {
            "genre": "Action", 
            "movieId": "aa696624-d51b-4c2a-9d5b-e014cbd54ff8", 
            "movieName": "Phineas and Ferb: Star Wars", 
            "rating": 4.0
        }, 
        {
            "genre": "Adventure", 
            "movieId": "ab91b755-dac1-4c8c-93e4-671a0c75b328", 
            "movieName": "Star Trek: Deception", 
            "rating": 0.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "d6fed7fa-b378-43a5-991a-0c0ba04f93fd", 
            "movieName": "Star Trek: Nemesis", 
            "rating": 2.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "4180e510-cf25-46bc-b8e4-c47229925705", 
            "movieName": "Star Trek: Insurrection", 
            "rating": 4.0
        }, 
        {
            "genre": "Drama", 
            "movieId": "c25ba25e-d1e5-417e-bd2b-4d1f31a12820", 
            "movieName": "The Godfather: Part III", 
            "rating": 4.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "90912fd9-cfe2-417e-bd97-105ff9199ad0", 
            "movieName": "Star Trek: Generations", 
            "rating": 8.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "405a6336-9cd8-46fc-b14b-bbe977566b28", 
            "movieName": "Star Trek", 
            "rating": 5.0
        }, 
        {
            "genre": "Adventure", 
            "movieId": "c0d1aede-7408-4d01-9d03-fb6d2217cc57", 
            "movieName": "The Star Wars Holiday Special", 
            "rating": 9.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "c9c5c12f-b520-4f54-9f4f-31415d339ea6", 
            "movieName": "Star Trek: Axanar", 
            "rating": 7.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "27358e45-0a78-4b34-812a-e1c1e9bd7b1b", 
            "movieName": "Star Trek II: The Wrath of Khan", 
            "rating": 9.0
        }, 
        {
            "genre": "Action", 
            "movieId": "0bc84e35-8ef9-4d5e-a6a3-662191217edc", 
            "movieName": "Star Trek: Renegades", 
            "rating": 6.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "b15cb0a0-d5fb-49e0-9771-0266ed199f10", 
            "movieName": "Star Wars: Episode III - Revenge of the Sith", 
            "rating": 10.0
        }, 
        {
            "genre": "Documentary", 
            "movieId": "76f657f0-7f75-4ea6-9837-1e7b523ad66d", 
            "movieName": "Star Wars: Greatest Moments", 
            "rating": 3.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "e83d041d-69f2-458f-83ad-01fab2f5c67e", 
            "movieName": "Star Trek V: The Final Frontier", 
            "rating": 8.0
        }, 
        {
            "genre": "Animation", 
            "movieId": "c0855be4-d03d-4d8b-bc35-81b231c894cf", 
            "movieName": "Robot Chicken: Star Wars", 
            "rating": 10.0
        }, 
        {
            "genre": "Action", 
            "movieId": "e05188e7-83eb-470f-b54d-58b471ec0f26", 
            "movieName": "Star Wars: Episode VIII", 
            "rating": 3.0
        }, 
        {
            "genre": "Science Fiction", 
            "movieId": "6b3a05b4-3dca-46b9-a79d-a6c8d8d6a8ec", 
            "movieName": "Star Trek: The Motion Picture", 
            "rating": 4.0
        }, 
        {
            "genre": "Documentary", 
            "movieId": "4630fa77-81eb-49be-af9f-520e75a0da9c", 
            "movieName": "Star Trek Evolutions", 
            "rating": 2.0
        }
    ], 
    "totalCount": 54, 
    "userId": "f0f1efa0-9aad-4e28-ade9-c8be03acf2d1"
}
```

Given a parameter of "movieId", return its average rating.

Request
```
$ http -v GET localhost:8080/api/movie/dfc2e946-3700-4415-98f3-cfde12f96d84/averageRating
GET /api/movie/dfc2e946-3700-4415-98f3-cfde12f96d84/averageRating HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:8080
User-Agent: HTTPie/0.9.3
```

Response
```
HTTP/1.1 200 OK
Content-Length: 3
Content-Type: text/plain;charset=UTF-8
Date: Sun, 31 Jul 2016 23:06:24 GMT
Server: Apache-Coyote/1.1
X-Application-Context: application:production

4.7
```

Given a genre such as "action" and a userId, return the top 5 movies for that genre by average rating, where the rating was made by other users within 5 years (older and younger) of the user's age.
Request
```
$ http -v GET localhost:8080/api/movie/top/Drama/f0f1efa0-9aad-4e28-ade9-c8be03acf2d1
GET /api/movie/top/Drama/f0f1efa0-9aad-4e28-ade9-c8be03acf2d1 HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:8080
User-Agent: HTTPie/0.9.3
```

Response
```
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Date: Sun, 31 Jul 2016 23:07:50 GMT
Server: Apache-Coyote/1.1
Transfer-Encoding: chunked
X-Application-Context: application:production

[
    {
        "genre": "Drama", 
        "movieId": "e8a2f451-4d75-4bad-84eb-cff452d1ad9a", 
        "movieName": "The Godfather Squad", 
        "rating": 5.790697674418604
    }, 
    {
        "genre": "Drama", 
        "movieId": "45bb031c-373e-46df-8558-4c582f04b895", 
        "movieName": "GodFather", 
        "rating": 5.1395348837209305
    }, 
    {
        "genre": "Drama", 
        "movieId": "491ff777-59d3-49fe-b829-4f7550e38ac8", 
        "movieName": "The Godfather: Part II", 
        "rating": 5.046511627906977
    }, 
    {
        "genre": "Drama", 
        "movieId": "758d2fc1-2d15-4571-b59c-aefe82673e41", 
        "movieName": "Father and Godfather", 
        "rating": 4.906976744186046
    }, 
    {
        "genre": "Drama", 
        "movieId": "c25ba25e-d1e5-417e-bd2b-4d1f31a12820", 
        "movieName": "The Godfather: Part III", 
        "rating": 4.5813953488372094
    }
]
```


### Data was received from 
https://www.themoviedb.org/account/glawson6/api
https://www.govtrack.us/api/v2/role?current=true

