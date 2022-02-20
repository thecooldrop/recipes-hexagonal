package udarnicka.ingredients.crud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udarnicka.ingredients.crud.domain.ports.*;

import java.net.URI;
import java.util.Optional;

@RestController
public class IngredientsController {

    private final IngredientCrudService ingredientsCrud;

    @Autowired
    IngredientsController(IngredientCrudService ingredientsCrud) {
        this.ingredientsCrud = ingredientsCrud;
    }

    @PostMapping(path = "/v1/ingredients", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Ingredient> post(@RequestBody CreateIngredient createIngredient) {
        try {
            Ingredient createdIngredient = ingredientsCrud.createIngredient(createIngredient);
            return ResponseEntity.created(URI.create("/v1/ingredients/" + createdIngredient.getId().id()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(createdIngredient);
        } catch (DuplicateIngredientException duplicateError) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .location(URI.create("/v1/ingredients/" + duplicateError.getAlreadyInDatabase().getId().id()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(duplicateError.getAlreadyInDatabase());
        }
    }

    @GetMapping(path = "/v1/ingredients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Ingredient> get(@PathVariable("id") int id) {
        return ResponseEntity.of(ingredientsCrud.readIngredient(new IngredientId(id)));
    }

    @DeleteMapping(path = "/v1/ingredients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Ingredient> delete(@PathVariable("id") int id) {
        Optional<Ingredient> deletedIngredient = ingredientsCrud.deleteIngredient(new IngredientId(id));
        return deletedIngredient.map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
