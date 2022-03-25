package udarnicka.recipes.crud.persistence.jpa;

import udarnicka.common.SerialInteger;
import udarnicka.recipes.crud.domain.ports.*;

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
                new ArrayList<>());
    }

    @Override
    public void deleteById(RecipeId id) {
        springJpaRecipeRepository.deleteById(id.toInteger());
    }

    @Override
    public Optional<Recipe> readById(RecipeId id) {
        List<RecipeStep> recipeSteps = springDataRecipeStepRepository.findByRecipeIdOrderByStepOrderIndex(id.toInteger())
                .stream()
                .map(RecipeStepJpaEntity::getStep)
                .map(RecipeStep::new)
                .toList();
        return springJpaRecipeRepository.findById(id.toInteger())
                .map(elem -> new Recipe(elem.getName(), id, recipeSteps));
    }
}
