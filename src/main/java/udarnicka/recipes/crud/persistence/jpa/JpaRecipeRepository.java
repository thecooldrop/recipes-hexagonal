package udarnicka.recipes.crud.persistence.jpa;

import udarnicka.recipes.crud.domain.ports.CreateRecipe;
import udarnicka.recipes.crud.domain.ports.Recipe;
import udarnicka.recipes.crud.domain.ports.RecipeId;
import udarnicka.recipes.crud.domain.ports.RecipeRepository;

import java.util.Optional;

public class JpaRecipeRepository implements RecipeRepository {

    private final SpringDataRecipeRepository springJpaRecipeRepository;

    public JpaRecipeRepository(SpringDataRecipeRepository springJpaRecipeRepository) {
        this.springJpaRecipeRepository = springJpaRecipeRepository;
    }

    @Override
    public Recipe create(CreateRecipe recipe) {
        return null;
    }

    @Override
    public Optional<Recipe> deleteById(RecipeId id) {
        return Optional.empty();
    }

    @Override
    public Optional<Recipe> readById(RecipeId id) {
        return Optional.empty();
    }
}
