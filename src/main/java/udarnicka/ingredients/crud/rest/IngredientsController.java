package udarnicka.ingredients.crud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import udarnicka.ingredients.crud.domain.ports.CreateIngredient;
import udarnicka.ingredients.crud.domain.ports.DuplicateIngredientException;
import udarnicka.ingredients.crud.domain.ports.Ingredient;
import udarnicka.ingredients.crud.domain.ports.IngredientCrudService;

import java.net.URI;

@RestController
public class IngredientsController {

    private final IngredientCrudService ingredientsCrud;

    @Autowired
    IngredientsController(IngredientCrudService ingredientsCrud) {
        this.ingredientsCrud = ingredientsCrud;
    }

    @PostMapping(path = "/v1/ingredients")
    ResponseEntity<Ingredient> post(CreateIngredient createIngredient) {
        Ingredient createdIngredient = ingredientsCrud.createIngredient(createIngredient);
        try {
            return ResponseEntity.created(URI.create("/v1/ingredients/" + createdIngredient.getId()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(createdIngredient);
        } catch (DuplicateIngredientException duplicateError) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .location(URI.create("/v1/ingredients/" + duplicateError.getAlreadyInDatabase().getId()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(duplicateError.getAlreadyInDatabase());
        }

    }
}
