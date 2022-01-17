package udarnicka.recipes.adapters.persistence;

import org.junit.jupiter.api.Test;
import udarnicka.recipes.application.CreateIngredient;
import udarnicka.recipes.domain.IngredientId;

import static org.junit.jupiter.api.Assertions.*;

class VolatileMapIngredientRepositoryTest {

    /**
     * If we try to retrieve an ingredient by ID, but we pass in an ID which is
     * not associated to any ingredient, then we should obtain an empty Optional
     * as a response
     */
    @Test
    void retrievingAnIngredientByNonExistentIdReturnsEmptyOptional() {
        var repositoryUnderTest = new VolatileMapIngredientRepository();
        var nonexistentIngredient = repositoryUnderTest.readById(new IngredientId(1));
        assertTrue(nonexistentIngredient.isEmpty());
    }

    /**
     * When new ingredient is created, then we can retrieve it in the future
     */
    @Test
    void createdIngredientCanBeRetrieved() {
        var repositoryUnderTest = new VolatileMapIngredientRepository();
        var createIngredientRequest = new CreateIngredient("Flour");
        var createdIngredient = repositoryUnderTest.create(createIngredientRequest);

        var maybeRetrievedIngredient = repositoryUnderTest.readById(createdIngredient.getId());
        assertTrue(maybeRetrievedIngredient.isPresent());
        var retrievedIngredient = maybeRetrievedIngredient.get();
        assertEquals(retrievedIngredient, createdIngredient);
    }

    /**
     * Creating a new Ingredient returns a properly formed new Ingredient object
     */
    @Test
    void creatingAnIngredientReturnsValidIngredientObject() {
        var repositoryUnderTest = new VolatileMapIngredientRepository();
        var createIngredientRequest = new CreateIngredient("Flour");
        var createdIngredient = repositoryUnderTest.create(createIngredientRequest);

        assertNotNull(createdIngredient);
        assertEquals("Flour", createdIngredient.getName());
        assertNotNull(createdIngredient.getId());
        assertNotNull(createdIngredient.getId().id());
    }

    /**
     * Trying to create a new Ingredient from a null input results in NullPointerException
     */
    @Test
    void creatingAnIngredientFromNullInputFailsWithNullPointerException() {
        var repositoryUnderTest = new VolatileMapIngredientRepository();
        assertThrows(NullPointerException.class, () -> repositoryUnderTest.create(null));
    }
}
