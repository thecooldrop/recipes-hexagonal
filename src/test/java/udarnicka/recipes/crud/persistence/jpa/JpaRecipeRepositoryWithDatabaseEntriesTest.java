package udarnicka.recipes.crud.persistence.jpa;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import udarnicka.common.SerialInteger;
import udarnicka.recipes.crud.domain.ports.Recipe;
import udarnicka.recipes.crud.domain.ports.RecipeId;
import udarnicka.recipes.crud.domain.ports.RecipeRepository;
import udarnicka.recipes.crud.persistence.RecipeRepositoryWithDatabaseEntriesTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class JpaRecipeRepositoryWithDatabaseEntriesTest implements RecipeRepositoryWithDatabaseEntriesTest {

    @Container
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14");

    @Autowired
    private SpringDataRecipeRepository springDataRecipeRepository;

    private RecipeRepository repositoryUnderTest;

    private Recipe recipeToDelete;
    private Recipe recipeToRead;

    @BeforeEach
    void setup() {
        RecipeJpaEntity recipeToDeleteEntity = new RecipeJpaEntity();
        recipeToDeleteEntity.setName("To Delete");
        springDataRecipeRepository.save(recipeToDeleteEntity);
        recipeToDelete = new Recipe("To Delete", new RecipeId(new SerialInteger(recipeToDeleteEntity.getId())));

        RecipeJpaEntity recipeToReadEntity = new RecipeJpaEntity();
        recipeToReadEntity.setName("To Read");
        springDataRecipeRepository.save(recipeToReadEntity);
        recipeToRead = new Recipe("To Read", new RecipeId(new SerialInteger(recipeToReadEntity.getId())));

        repositoryUnderTest = new JpaRecipeRepository(springDataRecipeRepository);
    }

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Override
    public RecipeRepository getRecipeRepository() {
        return repositoryUnderTest;
    }

    @Override
    public boolean isRecipeWithIdInDatabase(RecipeId id) {
        return springDataRecipeRepository.findById(id.toInteger()).isPresent();
    }

    @Override
    public Recipe recipeToDelete() {
        return recipeToDelete;
    }

    @Override
    public Recipe recipeToRead() {
        return recipeToRead;
    }

    @Override
    public Long countRecipesInDatabase() {
        return springDataRecipeRepository.count();
    }
}
