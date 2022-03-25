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
import udarnicka.common.SerialInteger;
import udarnicka.recipes.crud.domain.ports.Recipe;
import udarnicka.recipes.crud.domain.ports.RecipeId;
import udarnicka.recipes.crud.domain.ports.RecipeRepository;
import udarnicka.recipes.crud.domain.ports.RecipeStep;
import udarnicka.recipes.crud.persistence.RecipeRepositoryWithDatabaseEntriesTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class JpaRecipeRepositoryWithDatabaseEntriesTest implements RecipeRepositoryWithDatabaseEntriesTest {

    @Container
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14");

    @Autowired
    private SpringDataRecipeRepository springDataRecipeRepository;

    @Autowired
    private SpringDataRecipeStepRepository springDataRecipeStepRepository;

    private RecipeRepository repositoryUnderTest;

    private Recipe recipeToDelete;
    private Recipe recipeToRead;

    @BeforeEach
    void setup() {
        setupRecipeToDelete();
        setupRecipeToRead();
        repositoryUnderTest = new JpaRecipeRepository(springDataRecipeRepository, springDataRecipeStepRepository);
    }

    private void setupRecipeToDelete() {
        RecipeJpaEntity recipeToDeleteEntity = new RecipeJpaEntity();
        recipeToDeleteEntity.setName("To Delete");
        springDataRecipeRepository.save(recipeToDeleteEntity);
        recipeToDelete = new Recipe("To Delete", new RecipeId(new SerialInteger(recipeToDeleteEntity.getId())), new ArrayList<>());
    }

    private void setupRecipeToRead() {
        RecipeJpaEntity recipeToReadEntity = writeRecipeToReadToDatabase();
        List<RecipeStepJpaEntity> recipeToReadStepEntites = writeStepsForRecipeToReadToDatabase(recipeToReadEntity.getId());
        List<RecipeStep> recipeToReadSteps = recipeToReadStepEntites.stream()
                .map(elem -> new RecipeStep(elem.getStep()))
                .toList();
        recipeToRead = new Recipe(recipeToReadEntity.getName(),
                new RecipeId(new SerialInteger(recipeToReadEntity.getId())),
                recipeToReadSteps);
    }

    private RecipeJpaEntity writeRecipeToReadToDatabase() {
        RecipeJpaEntity recipeJpaEntity = new RecipeJpaEntity();
        recipeJpaEntity.setName("To Read");
        springDataRecipeRepository.save(recipeJpaEntity);
        return recipeJpaEntity;
    }

    private List<RecipeStepJpaEntity> writeStepsForRecipeToReadToDatabase(Integer forRecipe) {
        List<RecipeStepJpaEntity> steps = new ArrayList<>(4);
        springDataRecipeStepRepository.saveAll(
                Arrays.asList(
                        createStepForRecipe(forRecipe, "First", 0),
                        createStepForRecipe(forRecipe, "Second", 1),
                        createStepForRecipe(forRecipe, "Third", 2),
                        createStepForRecipe(forRecipe, "Fourth", 3)
                )
        ).forEach(steps::add);
        return steps;
    }

    private RecipeStepJpaEntity createStepForRecipe(Integer recipeId, String step, Integer orderIndex) {
        RecipeStepJpaEntity stepEntity = new RecipeStepJpaEntity();
        stepEntity.setStep(step);
        stepEntity.setRecipeId(recipeId);
        stepEntity.setStepOrderIndex(orderIndex);
        return stepEntity;
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
