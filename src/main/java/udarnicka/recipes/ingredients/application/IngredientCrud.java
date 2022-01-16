package udarnicka.recipes.ingredients.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udarnicka.recipes.ingredients.application.ports.out.IngredientRepository;
import udarnicka.recipes.ingredients.application.ports.in.IngredientCrudUsecase;
import udarnicka.recipes.ingredients.domain.Ingredient;
import udarnicka.recipes.ingredients.domain.IngredientId;

import java.util.Optional;

@Service
class IngredientCrud implements IngredientCrudUsecase {

    private final IngredientRepository ingredientRepository;

    @Autowired
    IngredientCrud(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient createIngredient(CreateIngredient createIngredient) {
        return ingredientRepository.save(createIngredient);
    }

    @Override
    public Optional<Ingredient> deleteIngredient(IngredientId ingredientId) {
        return ingredientRepository.deleteById(ingredientId);
    }

    @Override
    public Optional<Ingredient> readIngredient(IngredientId ingredientId) {
        return ingredientRepository.getById(ingredientId);
    }
}
