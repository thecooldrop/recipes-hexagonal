package udarnicka.recipes.ingredients.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import udarnicka.recipes.ingredients.application.ports.in.IngredientCrudUsecase;

@RestController
public class IngredientsController {

    IngredientCrudUsecase ingredientsCrud;

    @Autowired
    public IngredientsController(IngredientCrudUsecase ingredientsCrud) {
        this.ingredientsCrud = ingredientsCrud;
    }
}
