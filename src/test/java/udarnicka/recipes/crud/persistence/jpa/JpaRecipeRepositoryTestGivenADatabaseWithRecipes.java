package udarnicka.recipes.crud.persistence.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import udarnicka.recipes.crud.persistence.RecipeRepositoryAbstractBaseTestGivenADatabaseWithRecipes;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaRecipeRepositoryTestGivenADatabaseWithRecipes extends RecipeRepositoryAbstractBaseTestGivenADatabaseWithRecipes<RecipeJpaEntity, SpringDataRecipeRepository> {

    @Container
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14");

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Autowired
    private SpringDataRecipeRepository springDataRecipeRepository;

    @BeforeEach
    protected void setup() {
        this.recipeRepository = new JpaRecipeRepository(springDataRecipeRepository);
        this.dataSource = springDataRecipeRepository;
    }
}
