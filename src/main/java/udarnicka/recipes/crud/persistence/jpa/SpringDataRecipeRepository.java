package udarnicka.recipes.crud.persistence.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SpringDataRecipeRepository extends CrudRepository<RecipeJpaEntity, Integer> {

    @Query(nativeQuery = true, value = "DELETE FROM ingredients where id=?1 RETURNING *")
    Optional<RecipeJpaEntity> deleteByIdReturning(Integer id);

    Optional<RecipeJpaEntity> findByCanonicalName(String name);
}
