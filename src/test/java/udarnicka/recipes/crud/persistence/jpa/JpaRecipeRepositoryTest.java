package udarnicka.recipes.crud.persistence.jpa;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import udarnicka.recipes.crud.persistence.RecipeRepositoryAbstractTest;

public class JpaRecipeRepositoryTest extends RecipeRepositoryAbstractTest {

    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    @Testcontainers
    public class GivenAPostgresDatabase extends GivenADatabase {

        @Container
        private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgers:14");

        @DynamicPropertySource
        static void registerProperties(DynamicPropertyRegistry registry) {
            registry.add("spring.datasource.url", container::getJdbcUrl);
            registry.add("spring.datasource.username", container::getUsername);
            registry.add("spring.datasource.password", container::getPassword);
        }

        @Nested
        public class WhichIsEmptyPostgresDatabase extends WhichIsEmpty<RecipeJpaEntity, SpringDataRecipeRepository> {

            @Autowired
            private SpringDataRecipeRepository springDataRecipeRepository;

            @BeforeEach
            @Override
            protected void setup() {
                this.recipeRepository = new JpaRecipeRepository(springDataRecipeRepository);
                this.dataSource = springDataRecipeRepository;
            }
        }

        @Nested
        public class WhichContainsSomeRecipesPostgres extends WhichContainsSomeRecipes<RecipeJpaEntity, SpringDataRecipeRepository> {

            @Autowired
            private SpringDataRecipeRepository springDataRecipeRepository;

            @BeforeEach()
            void setup() {
                this.recipeRepository = new JpaRecipeRepository(springDataRecipeRepository);
                this.dataSource = springDataRecipeRepository;
            }
        }
    }
}
