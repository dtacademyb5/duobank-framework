package api;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



public class RegisterAPI {



    static {
        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";
    }




    @Test
    public void testRegisterMultiStepWorkflow(){



        // POST request

        String email = new Faker().internet().emailAddress();
        String pass =  new Faker().internet().password();
        given().
                body("{\n" +
                        "\n" +
                        "\"first_name\" : \"Duo\",\n" +
                        "\"last_name\": \"Bank\",\n" +
                        "\"email\" : \""+email+"\",\n" +
                        "\"password\" : \""+pass+"\"\n" +
                        "\n" +
                        "}").
                when().log().all().
                post("/register.php").
                then(). log().all().
                statusCode(is(200)).
                body("success", equalTo(1)).
                body("status", equalTo(201)).
                body("message", equalTo("You have successfully registered.")).
                header("Content-Type", equalTo("application/json; charset=UTF-8")).
                time(lessThan(2000L));



         // Verify the api by logging in through the API


        JsonPath jsonPath = given().
                body("{\n" +
                        "\n" +
                        "\"email\" : \"" + email + "\",\n" +
                        "\"password\" : \"" + pass + "\"\n" +
                        "\n" +
                        "}").
                when().
                post("/login.php").
                then().
                statusCode(200).
                body("message", equalTo("You have successfully logged in.")).extract().jsonPath();

        String token = jsonPath.getString("token");


        System.out.println(token);

    }


    @Test
    public void testGroovyGpath(){


        baseURI = "https://maps.googleapis.com/maps/api";

        JsonPath jsonPath = given().
                pathParam("type", "json").
                queryParam("input", "Duotech Academy").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                queryParam("fields", "formatted_address,name,rating,opening_hours,geometry,photo").
                when().log().all().
                get("/place/findplacefromtext/{type}").
                then().log().all().
                body("candidates[0].formatted_address", equalTo("2735 Hartland Rd Suite 302, Falls Church, VA 22043, United States"))
                .extract().jsonPath();


        String address = jsonPath.getString("candidates[0].formatted_address");
        String lng  = jsonPath.getString("candidates[0].formatted_address");

    }
}
