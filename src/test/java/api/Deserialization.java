package api;

import io.restassured.common.mapper.TypeRef;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Deserialization {


    @Test
    public void testDesrializae() throws FileNotFoundException {

        baseURI = "https://api.github.com";


//        Map responseAsMap = given().
//                header("Content-Type", "multipart/form-data").
//                pathParam("username", "EldarGus").
//                when().log().all().
//                get("/users/{username}").
//                then().log().all().
//                statusCode(is(200)).
//                body("login", equalTo("EldarGus")).extract().as(Map.class);  //Raw type

        Map<String, String> responseAsMap = given().
                header("Content-Type", "multipart/form-data").
                pathParam("username", "EldarGus").
                when().log().all().
                get("/users/{username}").
                then().log().all().
                statusCode(is(200)).
                body("login", equalTo("EldarGus")).extract().as(new TypeRef<Map<String, String>>() {} );


        String login = responseAsMap.get("login");

        System.out.println(responseAsMap.getClass());

        System.out.println(login.toUpperCase());


        List<String> list = new ArrayList<>();
        Set<Map.Entry<String, String>> entries = responseAsMap.entrySet();

        PrintWriter pw = new PrintWriter(new File("info.xml"));
        for (Map.Entry<String, String> entry : entries) {
            pw.println(entry);
        }

        pw.close();

        for (Map.Entry<String, String> entry : entries) {
            if(entry.getValue() == null ){
                list.add(entry.getKey());
            }
        }

        System.out.println(list);


    }



    @Test
    public void testDeserializePOJO() throws FileNotFoundException {

        baseURI = "http://localhost:8080/app";


        VideoGamePojo game = given().
                header("Accept", "application/json").
                pathParam("videoGameId", 2).

                when().
                get("/videogames/{videoGameId}").
                then().
                statusCode(200).extract().as(VideoGamePojo.class);


        System.out.println(game);

        System.out.println(game.getReviewScore());


    }


    @Test
    public void testDeserializeList() throws FileNotFoundException {

        baseURI = "http://localhost:8080/app";


//        List<VideoGamePojo> response = given().
//                header("Accept", "application/json").
//
//
//                when().log().all().
//                get("/videogames").
//                then().log().all().
//                statusCode(200).extract().as(new TypeRef<List<VideoGamePojo>>() {
//                });
//
//
//        for (VideoGamePojo videoGamePojo : response) {
//            System.out.println(videoGamePojo);
//            if(videoGamePojo.getId() != null){
//                System.out.println("Not null");
//            }
//        }


        List<Map<String, Object>> accept = given().
                header("Accept", "application/json").


                when().log().all().
                get("/videogames").
                then().log().all().
                statusCode(200).extract().as(new TypeRef<List<Map<String, Object>>>() {
                });


        for (Map<String, Object> stringObjectMap : accept) {
            System.out.println(stringObjectMap);
        }



    }



}
