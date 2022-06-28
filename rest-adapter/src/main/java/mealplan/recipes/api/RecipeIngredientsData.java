package mealplan.recipes.api;

import common.PositiveInteger;
import ingredients.crud.api.IngredientId;
import lombok.Getter;
import lombok.Setter;
import recipes.crud.api.CountableIngredient;
import recipes.crud.api.RecipeName;
import recipes.crud.api.VolumetricIngredient;
import recipes.crud.api.WeightedIngredient;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;
import java.util.ArrayList;
import java.util.List;

@Getter
class RecipeIngredientsData {

    @Setter
    private RecipeName recipeName;
    private List<CountableIngredient> countableIngredientList = new ArrayList<>();
    private List<VolumetricIngredient> volumetricIngredients = new ArrayList<>();
    private List<WeightedIngredient> weightedIngredients = new ArrayList<>();

    public void addCountableIngredient(Integer id, Integer count) {
        countableIngredientList.add(
                new CountableIngredient(
                        new PositiveInteger(count),
                        new IngredientId(new PositiveInteger(id))
                )
        );
    }

    public void addVolumetricIngredient(Integer id, Quantity<Volume> volume) {
        volumetricIngredients.add(
                new VolumetricIngredient(
                        volume,
                        new IngredientId(new PositiveInteger(id))
                )
        );
    }

    public void addWeightedIngredient(Integer id, Quantity<Mass> mass) {
        weightedIngredients.add(
                new WeightedIngredient(
                        mass,
                        new IngredientId(new PositiveInteger(id))
                )
        );
    }
}
