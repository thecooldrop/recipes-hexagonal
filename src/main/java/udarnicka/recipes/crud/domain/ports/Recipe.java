package udarnicka.recipes.crud.domain.ports;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class Recipe {

    private final String name;
    private final RecipeId id;
    private final List<RecipeStep> recipeSteps;
    private final List<DescriptiveIngredient> descriptiveIngredients;
    private final List<WeightedIngredient> weightedIngredients;
    private final List<VolumetricIngredients> volumetricIngredients;

    public Recipe(@NonNull String name,
                  @NonNull RecipeId id,
                  @NonNull List<RecipeStep> recipeSteps,
                  @NonNull List<DescriptiveIngredient> descriptiveIngredients,
                  @NonNull List<WeightedIngredient> weightedIngredients,
                  @NonNull List<VolumetricIngredients> volumetricIngredients) {
        this.descriptiveIngredients = descriptiveIngredients;
        this.weightedIngredients = weightedIngredients;
        this.volumetricIngredients = volumetricIngredients;
        if(name.isBlank()) {
            throw new IllegalArgumentException("The name of the recipe may not be empty or blank");
        }
        this.name = name;
        this.id = id;
        this.recipeSteps = recipeSteps;
    }
}
