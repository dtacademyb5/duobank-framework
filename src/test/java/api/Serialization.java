package api;

import com.github.javafaker.Faker;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Serialization {





    static {
        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";
    }




    @Test
    public void serializeUsingMap(){




        String email = new Faker().internet().emailAddress();
        String pass =  new Faker().internet().password();

        System.out.println(email);

        Map<String,Object> map = new LinkedHashMap<>();
        map.put("first_name", "Duo");
        map.put("last_name", "Bank");
        map.put("email", email);
        map.put("password", pass);


        given().
                body(map).  // java map is being serialized to json
                when().log().all().
                post("/register.php").
                then(). log().all().
                statusCode(is(200)).
                body("success", equalTo(1)).
                body("status", equalTo(201)).
                body("message", equalTo("You have successfully registered.")).
                header("Content-Type", equalTo("application/json; charset=UTF-8")).
                time(lessThan(2000L));

    }



    @Test
    public void serializeUsingPOJO(){




        String email = new Faker().internet().emailAddress();
        String pass =  new Faker().internet().password();


        UserPOJO userPOJO = new UserPOJO("Duo", "Bank", email, pass);


        given().
                body(userPOJO).  // POJO - plain old java object
                when().log().all().
                post("/register.php").
                then(). log().all().
                statusCode(is(200)).
                body("success", equalTo(1)).
                body("status", equalTo(201)).
                body("message", equalTo("You have successfully registered.")).
                header("Content-Type", equalTo("application/json; charset=UTF-8")).
                time(lessThan(2000L));

    }

    }
