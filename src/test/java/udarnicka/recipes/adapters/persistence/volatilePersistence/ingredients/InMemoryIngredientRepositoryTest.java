package udarnicka.recipes.adapters.persistence.volatilePersistence.ingredients;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import udarnicka.recipes.application.CreateIngredient;
import udarnicka.recipes.application.Ingredient;
import udarnicka.recipes.application.IngredientId;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryIngredientRepositoryTest {


    @Nested
    @Description("If there is a database")
    public class GivenADatabase {

        private InMemoryIngredientRepository repositoryUnderTest;

        @BeforeEach
        void setup() {
            repositoryUnderTest = new InMemoryIngredientRepository();
        }


        @Nested
        @Description("Which is empty")
        public class WhichIsEmpty {

            @Test
            @Description("")
            void retrievingAnIngredientReturnsAnEmptyOptional() {
                assertTrue(repositoryUnderTest.readById(new IngredientId(1)).isEmpty());
            }
        }


        @Nested
        @Description("Which contains an ingredient")
        public class WhichContainsAnIngredient {

            private Ingredient ingredientInDatabase;

            @BeforeEach
            void setup() {
                ingredientInDatabase = repositoryUnderTest.create(new CreateIngredient("Flour"));
            }

            @Test
            @Description("then the ingredient contained in the database can be retrieved")
            void theIngredientCanBeRetrieved() {
                var maybeRetrievedIngredient = repositoryUnderTest.readById(ingredientInDatabase.getId());
                assertEquals(Optional.of(ingredientInDatabase), maybeRetrievedIngredient);
            }

            @Test
            @Description("then the ingredient can be removed from the dabatase")
            void theIngredientCanBeRemoved() {
                assertThat(repositoryUnderTest.deleteById(ingredientInDatabase.getId())).isEqualTo(Optional.of(ingredientInDatabase));
                assertThat(repositoryUnderTest.readById(ingredientInDatabase.getId())).isEqualTo(Optional.empty());
            }

            @AfterEach
            void teardown() {
                repositoryUnderTest.deleteById(ingredientInDatabase.getId());
            }
        }

        @Test
        void creatingAnIngredientReturnsAValidIngredientObject() {
            var createIngredientRequest = new CreateIngredient("Flour");
            var createdIngredient = repositoryUnderTest.create(createIngredientRequest);

            assertNotNull(createdIngredient);
            assertEquals("Flour", createdIngredient.getName());
            assertNotNull(createdIngredient.getId());
            assertNotNull(createdIngredient.getId().id());
        }

        @Test
        @Description("Trying to create a new Ingredient from a null input results in NullPointerException")
        void creatingAnIngredientFromNullInputFailsWithNullPointerException() {
            var repositoryUnderTest = new InMemoryIngredientRepository();
            assertThrows(NullPointerException.class, () -> repositoryUnderTest.create(null));
        }
    }
}
