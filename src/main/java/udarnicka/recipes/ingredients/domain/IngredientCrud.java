package udarnicka.recipes.ingredients.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udarnicka.recipes.ingredients.domain.api.CreateIngredient;
import udarnicka.recipes.ingredients.domain.api.IngredientCrudUsecase;

import java.util.Optional;

@Service
public class IngredientCrud implements IngredientCrudUsecase {

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
