package udarnicka.recipes.crud.persistence.jpa;

import lombok.Getter;
import udarnicka.ingredients.crud.persistence.jpa.IngredientJpaEntity;

import javax.persistence.*;

/* TODO: I will likely need to complete the implementation of this class. It will most likely map to recipe_measured_ingredients
 *       table
 */
@Embeddable
@Table(name = "recipe_measured_ingredients")
@Getter
public class RecipeMeasuredIngredientJpaEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long recipeId;

    private IngredientJpaEntity ingredientId;

    private IngredientMeasurementUnitJpaEntity unitId;

    private Integer magnitude;
}
