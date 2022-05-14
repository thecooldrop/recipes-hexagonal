package recipes.crud.api;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.List;

@EqualsAndHashCode
public class CreateRecipeCommand {

    public final RecipeName recipeName;
    public final List<RecipeStep> steps;
    public final List<CountableIngredient> countableIngredients;
    public final List<WeightedIngredient> weightedIngredients;
    public final List<VolumetricIngredient> volumetricIngredients;

    public CreateRecipeCommand(@NonNull RecipeName recipeName, @NonNull List<RecipeStep> steps, @NonNull List<CountableIngredient> countableIngredients, @NonNull List<WeightedIngredient> weightedIngredients, @NonNull List<VolumetricIngredient> volumetricIngredients) {
        this.recipeName = recipeName;
        this.steps = steps;
        this.countableIngredients = countableIngredients;
        this.weightedIngredients = weightedIngredients;
        this.volumetricIngredients = volumetricIngredients;
    }
}
