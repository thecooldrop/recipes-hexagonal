package udarnicka.ingredients.crud.persistence.jpa;

import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import udarnicka.ingredients.crud.domain.ports.CreateIngredient;

class JpaIngredientRepositoryTest {

    @Nested
    @Description("If there is an Postgresql database")
    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    @Testcontainers
    public class GivenAPostgresqlDatabase {

        @Container
        private static PostgreSQLContainer container = new PostgreSQLContainer("postgres:14");

        @DynamicPropertySource
        static void registerProperties(DynamicPropertyRegistry registry) {
            registry.add("spring.datasource.url", container::getJdbcUrl);
            registry.add("spring.datasource.username", container::getUsername);
            registry.add("spring.datasource.password", container::getPassword);
        }

        @Nested
        public class WhichIsEmpty {

            @Autowired
            private SpringDataIngredientRepository springDataIngredientRepository;

            private JpaIngredientRepository ingredientRepositoryTested;

            @Autowired
            void setup() {
                ingredientRepositoryTested = new JpaIngredientRepository(springDataIngredientRepository);
            }

            @Test
            void thenNewIngredientCanBeSavedIntoTheDatabase() {
                CreateIngredient createIngredientCommand = new CreateIngredient("Flour");
                ingredientRepositoryTested.create(createIngredientCommand);

                int ingredientCount = 0;
                for(IngredientJpaEntity ingredient : springDataIngredientRepository.findAll()) {
                    Assertions.assertThat(ingredient.getId()).isNotNull();
                    Assertions.assertThat(ingredient.getName()).isEqualTo("Flour");
                    ingredientCount++;
                }

                Assertions.assertThat(ingredientCount).isEqualTo(1);
            }


        }

    }

}