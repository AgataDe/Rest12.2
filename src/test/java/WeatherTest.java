import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;


public class WeatherTest extends BaseTest {

    @ParameterizedTest(name = "{index}. Test for city {0} with id {2}")
    @CsvFileSource(resources = "/testData.csv")
    @Tag("Weather")
    public void shouldGetWeatherByCityName(String city, String country, int id) {
        given()
                .spec(getRequestSpecificationByCityName(city))
                .when()
                .get()
                .then()
                .spec(getResponseSpecification(city, country, id));
    }
}
