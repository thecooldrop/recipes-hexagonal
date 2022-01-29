package udarnicka.recipes.application.ingredients;

import udarnicka.recipes.application.ingredients.api.*;

import java.util.Optional;

public class IngredientCrud implements IngredientCrudUsecase {

    private final IngredientRepository ingredientRepository;

    IngredientCrud(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient createIngredient(CreateIngredient createIngredient) {
        return ingredientRepository.create(createIngredient);
    }

    @Override
    public Optional<Ingredient> deleteIngredient(IngredientId ingredientId) {
        return ingredientRepository.deleteById(ingredientId);
    }

    @Override
    public Optional<Ingredient> readIngredient(IngredientId ingredientId) {
        return ingredientRepository.readById(ingredientId);
    }
}
