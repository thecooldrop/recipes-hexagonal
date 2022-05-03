package udarnicka.recipes.crud.persistence.jpa;

import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;
import udarnicka.common.CanonicalName;
import udarnicka.common.SerialInteger;
import udarnicka.ingredients.crud.domain.ports.Ingredient;
import udarnicka.ingredients.crud.domain.ports.IngredientId;
import udarnicka.ingredients.crud.domain.ports.IngredientName;
import udarnicka.recipes.crud.domain.ports.*;

import javax.measure.Quantity;
import javax.measure.quantity.Volume;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaRecipeRepository implements RecipeRepository {

    private final SpringDataRecipeRepository springJpaRecipeRepository;

    private final SpringDataRecipeStepRepository springDataRecipeStepRepository;

    public JpaRecipeRepository(SpringDataRecipeRepository springJpaRecipeRepository, SpringDataRecipeStepRepository springDataRecipeStepRepository) {
        this.springJpaRecipeRepository = springJpaRecipeRepository;
        this.springDataRecipeStepRepository = springDataRecipeStepRepository;
    }

    @Override
    public Recipe create(CreateRecipe recipe) {
        RecipeJpaEntity recipeEntity = new RecipeJpaEntity();
        recipeEntity.setName(recipe.name());
        springJpaRecipeRepository.findByCanonicalName(recipeEntity.getCanonicalName().getCanonical())
                .map(elem -> new RecipeId(new SerialInteger(elem.getId())))
                .map(elem -> new DuplicateRecipeException("The recipe is already contained in the database", elem))
                .ifPresent(elem -> {
                    throw elem;
                });
        recipeEntity = springJpaRecipeRepository.save(recipeEntity);

        return new Recipe(recipeEntity.getName(),
                new RecipeId(new SerialInteger(recipeEntity.getId())),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    @Override
    public void deleteById(RecipeId id) {
        springJpaRecipeRepository.deleteById(id.toInteger());
    }

    @Override
    public Optional<Recipe> readById(RecipeId id) {
        return springJpaRecipeRepository.findById(id.toInteger())
                .map(this::convertToDomainRecipe);
        List<RecipeStep> recipeSteps = springDataRecipeStepRepository.findByRecipeIdOrderByStepOrderIndex(id.toInteger())
                .stream()
                .map(RecipeStepJpaEntity::getStep)
                .map(RecipeStep::new)
                .toList();
        // TODO: I need to complete the implementation of this method
        return springJpaRecipeRepository.findById(id.toInteger())
                .map(elem -> new Recipe(elem.getName(), id, recipeSteps, descriptiveIngredients, weightedIngredients, volumetricIngredients));
    }

    private Recipe convertToDomainRecipe(RecipeJpaEntity recipeJpaEntity) {
        String name = recipeJpaEntity.getName();
        RecipeId recipeId = new RecipeId(new SerialInteger(recipeJpaEntity.getId()));
        List<RecipeStep> recipeSteps = recipeJpaEntity.getRecipeSteps()
                .stream()
                .map(RecipeStepJpaEntity::getStep)
                .map(RecipeStep::new)
                .toList();
        List<DescriptiveIngredient> descriptiveIngredients = recipeJpaEntity.getRecipeMeasuredIngredients()
                .stream()
                .filter(elem -> elem.getUnitId().getTypeId().getType().equals(IngredientType.DESCRIPTIVE))
                .map(elem -> {
                    IngredientMeasureDescription ingredientMeasureDescription = new IngredientMeasureDescription(elem.getUnitId().getUnit());
                    Ingredient ingredient = new Ingredient(new IngredientName(new CanonicalName(elem.getIngredientId().getName())), new IngredientId(new SerialInteger(elem.getIngredientId().getId())))
                    DescriptiveIngredient descriptiveIngredient = new DescriptiveIngredient(ingredientMeasureDescription, ingredient);
                    return descriptiveIngredient;
                })
                .toList();
        // TODO: Complete the implementation of reading and implement tests
        List<WeightedIngredient> weightedIngredients = new ArrayList<>();
        List<VolumetricIngredient> volumetricIngredients = recipeJpaEntity.getRecipeMeasuredIngredients()
                .stream()
                .filter(elem -> elem.getUnitId().getTypeId().getType().equals(IngredientType.VOLUMETRIC))
                .map(elem -> {
                    int quantityDivisor = 1;
                    if(elem.getUnitId().getUnit().equals("MILLILITER")) {
                        quantityDivisor = 1000;
                    } else if(elem.getUnitId().getUnit().equals("LITER")) {
                        quantityDivisor = 1;
                    } else {
                        throw new IllegalStateException("The database contains volumetric units which are neither LITER nor MILILITER.");
                    }
                    Quantity<Volume> ingredientVolume = Quantities.getQuantity(elem.getMagnitude(), Units.LITRE.divide(quantityDivisor));
                    IngredientName ingredientName = new IngredientName(new CanonicalName(elem.getIngredientId().getName()));
                    IngredientId ingredientId = new IngredientId(new SerialInteger(elem.getIngredientId().getId()));
                    Ingredient ingredient = new Ingredient(ingredientName, ingredientId);
                    return new VolumetricIngredient(ingredientVolume, ingredient);
                })
                .toList();
        return new Recipe(name, recipeId, recipeSteps, descriptiveIngredients, weightedIngredients, volumetricIngredients);
    }
}
