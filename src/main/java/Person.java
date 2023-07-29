import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data // Аннотация Lombok для автоматической генерации геттеров, сеттеров, equals, hashCode и toString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private String[] films;
    private String[] species;
    private String[] vehicles;
    private String[] starships;
    private String created;
    private String edited;
    private String url;
}
//изменение из ветки new-branch_4
//изменение из ветки master