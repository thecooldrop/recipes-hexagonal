package recipes.ingredients;

import common.PositiveInteger;
import ingredients.crud.api.IngredientName;
import org.springframework.web.bind.annotation.*;
import recipes.ingredients.api.IngredientRestResponse;
import ingredients.crud.api.Ingredient;
import ingredients.crud.api.IngredientCrud;
import ingredients.crud.api.IngredientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RestController
public class IngredientsController {

    private final IngredientCrud ingredientCrud;

    @Autowired
    public IngredientsController(IngredientCrud ingredientCrud) {
        this.ingredientCrud = ingredientCrud;
    }

    @GetMapping(path="/ingredients/{id}")
    public ResponseEntity<IngredientRestResponse> getIngredientById(@PathVariable("id") Integer id) {
        Optional<Ingredient> maybeIngredient = ingredientCrud.readById(new IngredientId(new PositiveInteger(id)));
        Optional<IngredientRestResponse> maybeResponse = maybeIngredient.map(ingredient -> new IngredientRestResponse(ingredient.id.asInteger(), ingredient.name.asString()));
        return ResponseEntity.of(maybeResponse);
    }

    @PostMapping(path="/ingredients")
    public ResponseEntity<IngredientRestResponse> post(@RequestBody String ingredientName) {
        Ingredient ingredient = ingredientCrud.create(new IngredientName(ingredientName));
        IngredientRestResponse ingredientRestResponse = new IngredientRestResponse(ingredient.id.asInteger(), ingredient.name.asString());
        return ResponseEntity.ok(ingredientRestResponse);
    }
}
