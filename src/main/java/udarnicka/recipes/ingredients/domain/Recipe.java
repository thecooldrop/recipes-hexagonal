package udarnicka.recipes.ingredients.domain;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Recipe {
    private final RecipeId id;
    private final Set<VolumetricIngredient> volumetricIngredients;
    private final Set<WeightedIngredient> weightedIngredients;
    private final List<PreparationStep> steps;

    private Recipe(RecipeId id,
                  Set<VolumetricIngredient> volumetricIngredients,
                  Set<WeightedIngredient> weightedIngredients,
                  List<PreparationStep> steps) {
        this.id = id;
        this.volumetricIngredients = volumetricIngredients;
        this.weightedIngredients = weightedIngredients;
        this.steps = steps;
    }

    public Optional<Recipe> tryFrom(@NonNull RecipeId id,
                                    @NonNull Set<VolumetricIngredient> volumetricIngredients,
                                    @NonNull Set<WeightedIngredient> weightedIngredients,
                                    @NonNull List<PreparationStep> steps) {
        if(volumetricIngredients.isEmpty() && weightedIngredients.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new Recipe(id, volumetricIngredients, weightedIngredients, steps));
    }
}
