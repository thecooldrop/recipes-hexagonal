package udarnicka.recipes.crud.persistence.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SpringDataRecipeRepository extends CrudRepository<RecipeJpaEntity, Integer> {

    Optional<RecipeJpaEntity> findByCanonicalName(String name);
}
