package udarnicka.ingredients.crud.persistence.jpa;

import jdk.jfr.Description;
import org.junit.jupiter.api.Nested;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class JpaIngredientRepositoryTest {

    @Nested
    @Description("If there is an H2 database")
    public class GivenAnH2Database {

    }

}