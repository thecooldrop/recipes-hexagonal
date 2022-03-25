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
import udarnicka.common.CanonicalName;
import udarnicka.recipes.crud.domain.ports.RecipeRepository;
import udarnicka.recipes.crud.persistence.RecipeRepositoryEmptyDatabaseTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class JpaRecipeRepositoryTestGivenAnEmptyDatabaseTest implements RecipeRepositoryEmptyDatabaseTest {

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

    @Autowired
    private SpringDataRecipeStepRepository springDataRecipeStepRepository;

    private RecipeRepository recipeRepository;

    @BeforeEach
    protected void setup() {
        this.recipeRepository = new JpaRecipeRepository(springDataRecipeRepository,
                springDataRecipeStepRepository);
    }

    @Override
    public RecipeRepository getRepository() {
        return recipeRepository;
    }

    @Override
    public Runnable databaseContentChecker() {
        return () -> {
            int recipeCount = 0;
            for(RecipeJpaEntity recipeInDb : springDataRecipeRepository.findAll()) {
                assertThat(recipeInDb.getId()).isNotNull();
                assertThat(recipeInDb.getCanonicalName()).isEqualTo(new CanonicalName("Pizza Margarita"));
                assertThat(recipeInDb.getCanonicalName().getOriginal()).isEqualTo("Pizza Margarita");
                recipeCount++;
            }
            assertThat(recipeCount).isEqualTo(1);
        };
    }
}
