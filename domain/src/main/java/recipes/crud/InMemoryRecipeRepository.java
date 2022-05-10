package recipes.crud;

import common.PositiveInteger;
import recipes.crud.api.*;

import java.util.HashMap;
import java.util.Optional;

public class InMemoryRecipeRepository implements RecipeRepository {

    private final HashMap<RecipeId, Recipe> recipes = new HashMap<>();
    private Integer sequence = 1;

    @Override
    public Optional<Recipe> readById(RecipeId id) {
        return Optional.ofNullable(recipes.get(id));
    }

    @Override
    public Recipe save(CreateRecipeCommand command) {
        RecipeId id = new RecipeId(new PositiveInteger(sequence));
        Recipe savedRecipe = new Recipe(id, command.recipeName, command.steps, command.countableIngredients, command.descriptiveIngredients, command.weightedIngredients, command.volumetricIngredients);
        recipes.values()
                .stream()
                .filter(elem -> elem.recipeName.equals(command.recipeName))
                .findFirst()
                .ifPresent(elem -> {
                    throw new DuplicateRecipeException(elem);
                });
        recipes.put(id, savedRecipe);
        sequence++;
        return savedRecipe;
    }

    @Override
    public void delete(RecipeId id) {
        recipes.remove(id);
    }

}
