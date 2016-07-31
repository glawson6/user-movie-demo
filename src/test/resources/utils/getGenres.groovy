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


String fileName = "genres.json";
System.out.println("")
System.out.println("Using file => ${fileName}");
String jsonFileText = new File(fileName).getText('UTF-8')
Document jsonFileDoc = Document.parse(jsonFileText);
jsonFileDoc.genres.each{genre ->
    System.out.println("Entry value => "+genre.get("id") );
    def builder = new groovy.json.JsonBuilder()
    def root = builder{
        externalid genre.get("id")
        name genre.get("name")
    }
    System.out.println("JSON for insert => "+builder.toString() );
    def operationURL = "http://localhost:8080/genres"
    HttpResponse<String> searchResponse = Unirest.post(operationURL)
            .header("content-type", "application/json")
            .body(builder.toString())
            .asString();
    System.out.println("Response Body => " + searchResponse.getBody());
    System.out.println("Response Headers => " + searchResponse.headers);
    System.out.println("Response Status => " + searchResponse.status);
    System.out.println("Location => " + searchResponse.headers.get("location"))
}
