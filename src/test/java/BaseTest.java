import configuration.Properties;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        Properties properties = Properties.getInstance();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }


    public static RequestSpecification getRequestSpecificationByCityName(String city) {
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("baseUri"))
                .setBasePath(System.getProperty("pathToWeather"))
                .addParam("appId", System.getProperty("appId"))
                .addParam("q", city)
                .setContentType(ContentType.JSON)
                .build();
    }


    public static ResponseSpecification getResponseSpecification(String city, String country, int id) {
        return new ResponseSpecBuilder()
                .expectBody("name", is(city))
                .expectBody("sys", hasValue(country))
                .expectBody("id", is(id))
                .expectStatusCode(200)
                .build();
    }
}
