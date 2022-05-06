package recipes.crud.api;

import lombok.NonNull;

import java.util.List;

public class Recipe {

    private final RecipeId id;
    private final List<RecipeStep> steps;
    private final List<CountableIngredient> countableIngredients;
    private final List<DescriptiveIngredient> descriptiveIngredients;
    private final List<WeightedIngredient> weightedIngredients;
    private final List<VolumetricIngredient> volumetricIngredients;


    public Recipe(@NonNull RecipeId id, @NonNull List<RecipeStep> steps, @NonNull List<CountableIngredient> countableIngredients, @NonNull List<DescriptiveIngredient> descriptiveIngredients, @NonNull List<WeightedIngredient> weightedIngredients, @NonNull List<VolumetricIngredient> volumetricIngredients) {
        this.id = id;
        this.steps = steps;
        this.countableIngredients = countableIngredients;
        this.descriptiveIngredients = descriptiveIngredients;
        this.weightedIngredients = weightedIngredients;
        this.volumetricIngredients = volumetricIngredients;
    }
}
