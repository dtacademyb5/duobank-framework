package api;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestGithub {




    @Test
    public void testGit(){

        baseURI = "https://api.github.com";


        given().
                header("Content-Type", "multipart/form-data").
                pathParam("username", "EldarGus").
         when().log().all().
                get("/users/{username}").
         then().log().all().
                statusCode(is(200)).
                body("login", equalTo("EldarGus"));

    }
}
