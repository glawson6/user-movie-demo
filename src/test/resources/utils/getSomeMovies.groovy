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

@BaseScript Tools tools


if (args.length > 0) {

    args.eachWithIndex{
        it, index ->
            String fileName = args[index];
            System.out.println("")
            System.out.println("Using file => ${fileName}");
            String jsonFileText = new File(fileName).getText('UTF-8')
            Document jsonFileDoc = Document.parse(jsonFileText);
            def uri = jsonFileDoc.get("uri")
            def uid = jsonFileDoc.get("uid")
            Document jsonToSend = jsonFileDoc.get("jsonToSend")
            jsonToSend.put("tid",UUID.randomUUID().toString())
            String operationURL = "http://${getAPIHost()}:${getAPIPort()}${uri}";
            System.out.println("Sending operation to url => ${operationURL}")
            System.out.println("json => ${jsonToSend.toJson()}")
            HttpResponse<String> searchResponse = Unirest.post(operationURL)
                    .header("content-type", "application/json")
                    .header("uid",uid)
                    .body(jsonToSend.toJson())
                    .asString();

            System.out.println("Response Body => " + searchResponse.getBody());
            System.out.println("Response Headers => " + searchResponse.headers);
            System.out.println("Response Status => " + searchResponse.status);
            System.out.println("Location => " + searchResponse.headers.get("location"))
    }

}
/*
def operationURL = "http://api.themoviedb.org/3/search/movie?api_key=2b9effed81c541c8beab81eddf2cda29&query=star trek"
HttpResponse<String> searchResponse = Unirest.get(operationURL)
        .header("content-type", "application/json")
        .asString();
System.out.println("Response Body => " + searchResponse.getBody());
System.out.println("Response Headers => " + searchResponse.headers);
System.out.println("Response Status => " + searchResponse.status);
System.out.println("Location => " + searchResponse.headers.get("location"))

Document jsonFileDoc = Document.parse(searchResponse.getBody());
*/

