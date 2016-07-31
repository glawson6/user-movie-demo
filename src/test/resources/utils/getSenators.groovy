import com.fasterxml.jackson.databind.ObjectMapper
@Grab('com.mashape.unirest:unirest-java:1.4.7')
@Grab('org.mongodb:bson:3.0.4')
@Grab('joda-time:joda-time:2.9.1')
@Grab('com.fasterxml.jackson.datatype:jackson-datatype-joda:2.4.4')
@GrabConfig(systemClassLoader=true)

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest
import groovy.transform.BaseScript;
import org.bson.Document
import org.bson.json.JsonWriterSettings
import java.time.LocalDate
import java.time.temporal.TemporalField
import java.util.Random
/*
def operationURL = "http://api.themoviedb.org/3/genre/movie/list?api_key=2b9effed81c541c8beab81eddf2cda29"
HttpResponse<String> searchResponse = Unirest.get(operationURL)
        .header("content-type", "application/json")
        .asString();
System.out.println("Response Body => " + searchResponse.getBody());
System.out.println("Response Headers => " + searchResponse.headers);
System.out.println("Response Status => " + searchResponse.status);
System.out.println("Location => " + searchResponse.headers.get("location"))
Document jsonFileDoc = Document.parse(searchResponse.getBody());
*/

LocalDate nowDate = LocalDate.now();
String fileName = "senators.json";
System.out.println("")
System.out.println("Using file => ${fileName}");
String jsonFileText = new File(fileName).getText('UTF-8')
Document jsonFileDoc = Document.parse(jsonFileText);
jsonFileDoc.objects.each{senatorObject ->
    Document senator = senatorObject.get("person")
    def senatorName = new StringBuilder().with {
        append(senator.get("firstname"));
        append(" ")
        append(senator.get("lastname"))
    }.toString()
    LocalDate birthDate = LocalDate.parse(senator.get("birthday"));
    int senatorAge =  nowDate.getYear() - birthDate.getYear()
    System.out.println("senator => "+senatorName+" age => "+senatorAge);

    def builder = new groovy.json.JsonBuilder()
    def root = builder{
        name senatorName
        age senatorAge
    }
    System.out.println("JSON for insert => "+builder.toString() );


    def operationURL = "http://localhost:8080/users"
    HttpResponse<String> searchResponse = Unirest.post(operationURL)
            .header("content-type", "application/json")
            .body(builder.toString())
            .asString();
 /*   System.out.println("Response Body => " + searchResponse.getBody());
    System.out.println("Response Headers => " + searchResponse.headers);
    System.out.println("Response Status => " + searchResponse.status);*/
    def senatorLocation = searchResponse.headers.get("location")[0];
    System.out.println("Location => " + senatorLocation)

    def moviesURL = "http://localhost:8080/movies"
    HttpResponse<String> moviesResponse = Unirest.get(moviesURL)
            .header("content-type", "application/json")
            .asString();
    //System.out.println("Movie Response Body => " + moviesResponse.getBody());
    //System.out.println("Movie Response Headers => " + moviesResponse.headers);
    System.out.println("Movie Response Status => " + moviesResponse.status);
    int max = 10;
    if (!moviesResponse.status.toString().equals("404")) {
        Document moviesDoc = Document.parse(moviesResponse.getBody())
        moviesDoc.get("_embedded").movies.each {movie ->
            def movieLocation = movie.get("_links").get("self").get("href")
            Random random = new Random()
            int senatorRating = random.nextInt(max+1)
            def builder2 = new groovy.json.JsonBuilder()
            def root2 = builder2{
                rating senatorRating
                movieid movieLocation
                userid senatorLocation
            }
            System.out.println("Movie rating JSON for insert => "+builder2.toString() );
            def movieRatingURL = "http://localhost:8080/movieRatings"
            HttpResponse<String> movieRatingResponse = Unirest.post(movieRatingURL)
                    .header("content-type", "application/json")
                    .body(builder2.toString())
                    .asString();
               System.out.println("Response Body => " + movieRatingResponse.getBody());
               System.out.println("Response Headers => " + movieRatingResponse.headers);
               System.out.println("Response Status => " + movieRatingResponse.status);
        }


    }



    //localhost:8080/movies

}

/*
LocalDate nowDate = LocalDate.now();
System.out.printf("date=[%s]%n", nowDate); // 2014-08-15

LocalDate date = LocalDate.parse("2014-08-15");
System.out.printf("date=[%s]%n", date); // 2014-08-15

int diff = nowDate.getYear() - date.getYear();
System.out.printf("age = "+diff);
*/
