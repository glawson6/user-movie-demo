import com.fasterxml.jackson.databind.ObjectMapper
@Grab('com.mashape.unirest:unirest-java:1.4.7')
@Grab('org.mongodb:bson:3.0.4')
@Grab('joda-time:joda-time:2.9.1')
@Grab('com.fasterxml.jackson.datatype:jackson-datatype-joda:2.4.4')
@GrabConfig(systemClassLoader = true)

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest
import groovy.transform.BaseScript;
import org.bson.Document
import org.bson.json.JsonWriterSettings


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

if (args.length > 0) {

    args.eachWithIndex {
        it, index ->
            String fileName = args[index];
            // String fileName = "starTrekMovies.json";
            System.out.println("")
            System.out.println("Using file => ${fileName}");
            String jsonFileText = new File(fileName).getText('UTF-8')
            Document jsonFileDoc = Document.parse(jsonFileText);
            def externalIdMap = new HashMap();
            jsonFileDoc.results.each { movie ->
                System.out.println("Entry value => " + movie.get("id"));
                def genreId = (movie.get("genre_ids"))[0]
                System.out.println("genreId => " + genreId);

                def operationURL = "http://localhost:8080/genres/search/findByExternalid?externalid=${genreId}"
                HttpResponse<String> searchResponse = Unirest.get(operationURL)
                        .header("content-type", "application/json")
                        .asString();
                System.out.println("Response Body => " + searchResponse.getBody());
                System.out.println("Response Headers => " + searchResponse.headers);
                System.out.println("Response Status => " + searchResponse.status);
                if (!searchResponse.status.toString().equals("404")) {
                    Document genreDoc = Document.parse(searchResponse.getBody())
                    def builder = new groovy.json.JsonBuilder()
                    def foundGenreid = (null != genreId) ? genreDoc.get("_links").get("self").get("href") : "http://localhost:8080/genres/search/findByExternalid?externalid=878"
                    def root = builder {
                        externalid movie.get("id")
                        name movie.get("title")
                        genreid foundGenreid
                    }
                    genreDoc.clear();
                    System.out.println("JSON for insert => " + builder.toString());

                    def postURL = "http://localhost:8080/movies"
                    HttpResponse<String> postResponse = Unirest.post(postURL)
                            .header("content-type", "application/json")
                            .body(builder.toString())
                            .asString();
                    System.out.println("Response Body => " + postResponse.getBody());
                    System.out.println("Response Headers => " + postResponse.headers);
                    System.out.println("Response Status => " + postResponse.status);
                    System.out.println("Location => " + postResponse.headers.get("location"))

                }
            }
    }
}
