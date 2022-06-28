package mealplan.recipes.api;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import recipes.crud.api.*;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class JdbcRecipeRepository implements RecipeRepository {

    private final JdbcTemplate template;

    private final PreparedStatementCreatorFactory readRecipeIngredientsByRecipeId = new PreparedStatementCreatorFactory(
            """
            select recipes.id, recipes.name, ingredients.id, ingredient_types.type, type_units.unit, recipe_ingredients.amount
            from recipes, recipe_ingredients, ingredients, type_units, ingredient_types where
            recipes.id = ? and
            recipes.id = recipe_ingredient.recipe_id and
            ingredients.id = recipe_ingredients.ingredient_id and
            type_units.id = recipe_ingredients.type_unit_id and 
            ingredient_types = type_units.type_id;
            """, Types.INTEGER
    );

    private final PreparedStatementCreatorFactory readRecipeStepsForRecipe = new PreparedStatementCreatorFactory(
            "select step from recipe_steps where recipe_steps.recipe_id = ? order by recipe_steps.order_index asc",
            Types.INTEGER
    );

    private final ResultSetExtractor<RecipeIngredientsData> recipeIngredientsExtractor = rs -> {
        if(!rs.next()) {
            return null;
        }
        return extractRecipeIngredients(rs);
    };

    public JdbcRecipeRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<Recipe> readById(RecipeId id) {
        RecipeIngredientsData recipeIngredientsData = template.query(readRecipeIngredientsByRecipeId.newPreparedStatementCreator(List.of(id.id.asInteger())), recipeIngredientsExtractor);
        if(recipeIngredientsData == null) {
            return Optional.empty();
        }

        List<RecipeStep> recipeSteps = template.query(readRecipeStepsForRecipe.newPreparedStatementCreator(List.of(id.id.asInteger())), (rs, num) -> new RecipeStep(rs.getString(1)));

        return Optional.of(
                new Recipe(
                        id,
                        recipeIngredientsData.getRecipeName(),
                        recipeSteps,
                        recipeIngredientsData.getCountableIngredientList(),
                        recipeIngredientsData.getWeightedIngredients(),
                        recipeIngredientsData.getVolumetricIngredients()
                )
        );
    }

    private static RecipeIngredientsData extractRecipeIngredients(ResultSet rs) throws SQLException {
        RecipeIngredientsData ingredientsData = new RecipeIngredientsData();
        ingredientsData.setRecipeName(new RecipeName(rs.getString(2)));
        do {
            Integer ingredientId = rs.getInt(3);
            String ingredientType = rs.getString(4);
            String ingredientUnit = rs.getString(5);
            Integer ingredientQuantity = rs.getInt(6);

            switch (IngredientType.valueOf(ingredientType)) {
                case COUNTABLE -> ingredientsData.addCountableIngredient(ingredientId, ingredientQuantity);
                case WEIGHTED -> {
                    Quantity<Mass> mass = WeightUnits.valueOf(ingredientUnit).quantity(ingredientQuantity);
                    ingredientsData.addWeightedIngredient(ingredientId, mass);
                }
                case VOLUMETRIC -> {
                    Quantity<Volume> volume = VolumeUnits.valueOf(ingredientUnit).quantity(ingredientQuantity);
                    ingredientsData.addVolumetricIngredient(ingredientId, volume);
                }
            }
        } while (rs.next());
        return ingredientsData;
    }

    @Override
    public Recipe save(CreateRecipeCommand command) {
        return null;
    }

    @Override
    public void delete(RecipeId id) {

    }
}
