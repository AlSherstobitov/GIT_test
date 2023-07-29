import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;

public class PersonTest {
    private static final String BASE_URL = "https://swapi.dev/api";
    private ObjectMapper objectMapper;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetPersonInfo() {
        String endpoint = "/people/1";

        // Выполняем GET-запрос и получаем ответ
        Response response = RestAssured.get(endpoint);

        // Проверяем статус-код
        response.then().statusCode(200);

        // Получаем тело ответа в виде строки JSON
        String responseBody = response.getBody().asString();

        // Десериализуем JSON в объект класса Person с помощью Jackson
        Person person = null;
        try {
            person = objectMapper.readValue(responseBody, Person.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Проверяем значения полей объекта person с помощью Hamcrest
        org.hamcrest.MatcherAssert.assertThat(person, Matchers.notNullValue());
        org.hamcrest.MatcherAssert.assertThat(person.getName(), Matchers.equalTo("Luke Skywalker"));
        org.hamcrest.MatcherAssert.assertThat(person.getHeight(), Matchers.equalTo("172"));
        org.hamcrest.MatcherAssert.assertThat(person.getMass(), Matchers.equalTo("77"));
        org.hamcrest.MatcherAssert.assertThat(person.getHair_color(), Matchers.equalTo("blond"));
        org.hamcrest.MatcherAssert.assertThat(person.getSkin_color(), Matchers.equalTo("fair"));
        org.hamcrest.MatcherAssert.assertThat(person.getEye_color(), Matchers.equalTo("blue"));
        org.hamcrest.MatcherAssert.assertThat(person.getBirth_year(), Matchers.equalTo("19BBY"));
        org.hamcrest.MatcherAssert.assertThat(person.getGender(), Matchers.equalTo("male"));
        org.hamcrest.MatcherAssert.assertThat(person.getHomeworld(), Matchers.equalTo("https://swapi.dev/api/planets/1/"));

        // Дополнительные проверки полей films, species, vehicles и starships
        org.hamcrest.MatcherAssert.assertThat(person.getFilms(), Matchers.arrayWithSize(4));
        org.hamcrest.MatcherAssert.assertThat(person.getSpecies(), Matchers.emptyArray());
        org.hamcrest.MatcherAssert.assertThat(person.getVehicles(), Matchers.arrayWithSize(2));
        org.hamcrest.MatcherAssert.assertThat(person.getStarships(), Matchers.arrayWithSize(2));
    }
}

// Тестовое изменение №1