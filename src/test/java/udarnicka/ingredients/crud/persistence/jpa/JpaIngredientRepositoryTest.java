package udarnicka.ingredients.crud.persistence.jpa;

import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import udarnicka.ingredients.crud.domain.ports.DuplicateIngredientException;
import udarnicka.ingredients.crud.domain.ports.Ingredient;
import udarnicka.ingredients.crud.domain.ports.IngredientId;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JpaIngredientRepositoryTest {

    @Nested
    @Description("If there is an Postgresql database")
    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    @Testcontainers
    public class GivenAPostgresqlDatabase {

        @Container
        private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14");

        @DynamicPropertySource
        static void registerProperties(DynamicPropertyRegistry registry) {
            registry.add("spring.datasource.url", container::getJdbcUrl);
            registry.add("spring.datasource.username", container::getUsername);
            registry.add("spring.datasource.password", container::getPassword);
        }

        @Nested
        @Description("which contains no elements")
        public class WhichIsEmpty {

            @Autowired
            private SpringDataIngredientRepository springDataIngredientRepository;

            @PersistenceContext
            private EntityManager em;

            private JpaIngredientRepository ingredientRepositoryTested;

            @BeforeEach
            void setup() {
                ingredientRepositoryTested = new JpaIngredientRepository(springDataIngredientRepository);
            }

            @Test
            @Description("then new ingredients can be saved into the database")
            void thenNewIngredientCanBeSavedIntoTheDatabase() {
                CreateIngredient createIngredientCommand = new CreateIngredient("Flour");
                ingredientRepositoryTested.create(createIngredientCommand);

                int ingredientCount = 0;
                for(IngredientJpaEntity ingredient : springDataIngredientRepository.findAll()) {
                    Assertions.assertThat(ingredient.getId()).isNotNull();
                    Assertions.assertThat(ingredient.getName()).isEqualTo("Flour");
                    Assertions.assertThat(ingredient.getCanonicalName()).isEqualTo("flour");
                    ingredientCount++;
                }

                Assertions.assertThat(ingredientCount).isEqualTo(1);
            }

            @Test
            @Description("then same ingredient can not be saved into database twice")
            void sameIngredientCanNotBeSavedIntoDatabaseTwice() {
                CreateIngredient createIngredientCommand = new CreateIngredient("Flour");
                ingredientRepositoryTested.create(createIngredientCommand);
                assertThatThrownBy(() -> ingredientRepositoryTested.create(createIngredientCommand)).isInstanceOf(DuplicateIngredientException.class);
            }

            @Test
            @Description("then the ingredient with same canonical name can not be saved into database twice")
            void ingredientWithSameCanonicalNameCanNotBeSavedIntoDbTwice() {
                CreateIngredient createIngredient = new CreateIngredient("Flour");
                ingredientRepositoryTested.create(createIngredient);
                assertThatThrownBy(() -> ingredientRepositoryTested.create(new CreateIngredient("FLour"))).isInstanceOf(DuplicateIngredientException.class);
            }

            @Test
            @Description("then deleting any ingredient returns an empty Optional")
            void thenDeletingNonExistentIngredientReturnsEmptyOptional() {
                assertThat(ingredientRepositoryTested.deleteById(new IngredientId(1))).isEqualTo(Optional.empty());
            }

            @Test
            @Description("then reading any ingredient returns an empty optional")
            void thenReadingANonexsistentIngredientReturnsEmptyOptional() {
                assertThat(ingredientRepositoryTested.readById(new IngredientId(1))).isEqualTo(Optional.empty());
            }
        }

        @Nested
        public class WhichContainsSomeIngredients {

            @Autowired
            private SpringDataIngredientRepository springDataIngredientRepository;

            @PersistenceContext
            private EntityManager em;

            private JpaIngredientRepository ingredientRepositoryTested;

            private List<Ingredient> ingredientsInDatabase;

            @BeforeEach
            private void setup() {
                ingredientRepositoryTested = new JpaIngredientRepository(springDataIngredientRepository);

                ingredientsInDatabase = new ArrayList<>();
                CreateIngredient createFlourIngredient = new CreateIngredient("Flour");
                CreateIngredient createChickenBreastIngredient = new CreateIngredient("Chicken Breast");
                CreateIngredient createSaltIngredient = new CreateIngredient("Salt");
                CreateIngredient createPepperIngredient = new CreateIngredient("Pepper");

                for(CreateIngredient command : Arrays.asList(createFlourIngredient, createChickenBreastIngredient, createSaltIngredient, createPepperIngredient)) {
                    Ingredient ingredient = ingredientRepositoryTested.create(command);
                    ingredientsInDatabase.add(ingredient);
                }
            }

            @Test
            void whenIngredientIsDeletedItIsRemovedFromTheDatabase() {
                Ingredient randomIngredient = ingredientsInDatabase.get(new Random().nextInt(ingredientsInDatabase.size()));

                long numOfIngredientsInDbBeforeDeletion = springDataIngredientRepository.count();
                Optional<Ingredient> randomIngredientFromDb = ingredientRepositoryTested.readById(randomIngredient.getId());
                Optional<Ingredient> removedIngredient = ingredientRepositoryTested.deleteById(randomIngredient.getId());
                long numOfIngredientsInDatabaseAfterDeletion = springDataIngredientRepository.count();
                em.flush();
                em.clear();

                Optional<Ingredient> shouldBeEmptyIngredient = ingredientRepositoryTested.readById(randomIngredient.getId());

                assertThat(randomIngredientFromDb).isEqualTo(Optional.of(randomIngredient));
                assertThat(removedIngredient).isEqualTo(randomIngredientFromDb);
                assertThat(numOfIngredientsInDbBeforeDeletion).isGreaterThan(numOfIngredientsInDatabaseAfterDeletion);
                assertThat(shouldBeEmptyIngredient).isEqualTo(Optional.empty());
            }

            @Test
            void whenIngredientIsDeletedItIsReturned() {
                Ingredient randomIngredient = ingredientsInDatabase.get(new Random().nextInt(ingredientsInDatabase.size()));
                Optional<Ingredient> deletedIngredient = ingredientRepositoryTested.deleteById(randomIngredient.getId());
                assertThat(deletedIngredient).isEqualTo(Optional.of(randomIngredient));
            }

            @Test
            void thenExistingIngredientCanBeRetrieved() {
                Ingredient randomIngredient = ingredientsInDatabase.get(new Random().nextInt(ingredientsInDatabase.size()));
                Optional<Ingredient> ingredientInDb = ingredientRepositoryTested.readById(randomIngredient.getId());
                assertThat(ingredientInDb).isEqualTo(Optional.of(randomIngredient));
            }
        }
    }
}