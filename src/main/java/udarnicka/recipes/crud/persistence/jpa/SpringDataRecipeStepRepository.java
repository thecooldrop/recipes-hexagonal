package udarnicka.recipes.crud.persistence.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpringDataRecipeStepRepository  extends CrudRepository<RecipeStepJpaEntity, Integer> {

    List<RecipeStepJpaEntity> findByRecipeIdOrderByStepOrderIndex(Integer recipeId);
}
