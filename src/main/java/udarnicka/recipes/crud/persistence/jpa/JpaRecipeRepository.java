package udarnicka.recipes.crud.persistence.jpa;

import udarnicka.common.SerialInteger;
import udarnicka.recipes.crud.domain.ports.*;

import java.util.Optional;

public class JpaRecipeRepository implements RecipeRepository {

    private final SpringDataRecipeRepository springJpaRecipeRepository;

    public JpaRecipeRepository(SpringDataRecipeRepository springJpaRecipeRepository) {
        this.springJpaRecipeRepository = springJpaRecipeRepository;
    }

    @Override
    public Recipe create(CreateRecipe recipe) {
        RecipeJpaEntity recipeEntity = new RecipeJpaEntity();
        recipeEntity.setName(recipe.name());
        springJpaRecipeRepository.findByCanonicalName(recipeEntity.getCanonicalName().getCanonical())
                .map(elem -> new Recipe(elem.getName(), new RecipeId(new SerialInteger(elem.getId()))))
                .map(elem -> new DuplicateRecipeException("The recipe is already contained in the database", elem))
                .ifPresent(elem -> {
                    throw elem;
                });
        recipeEntity = springJpaRecipeRepository.save(recipeEntity);
        return new Recipe(recipeEntity.getName(), new RecipeId(new SerialInteger(recipeEntity.getId())));
    }

    @Override
    public Optional<Recipe> deleteById(RecipeId id) {
        return springJpaRecipeRepository.deleteByIdReturning(id.id().toInteger())
                .map(elem -> new Recipe(elem.getName(), id));
    }

    @Override
    public Optional<Recipe> readById(RecipeId id) {
        return springJpaRecipeRepository.findById(id.id().toInteger())
                .map(elem -> new Recipe(elem.getName(), id));
    }
}
