package udarnicka.recipes.application;

import udarnicka.recipes.application.ports.out.IngredientRepository;
import udarnicka.recipes.application.ports.in.IngredientCrudUsecase;
import udarnicka.recipes.domain.Ingredient;
import udarnicka.recipes.domain.IngredientId;

import java.util.Optional;

class IngredientCrud implements IngredientCrudUsecase {

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
