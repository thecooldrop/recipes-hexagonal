package udarnicka.recipes.crud.persistence.jpa;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SpringDataRecipeRepository extends CrudRepository<RecipeJpaEntity, Integer> {

    @Query(nativeQuery = true, value = "DELETE FROM recipes where id=?1 RETURNING *")
    @Modifying
    Optional<RecipeJpaEntity> deleteByIdReturning(Integer id);

    Optional<RecipeJpaEntity> findByCanonicalName(String name);
}
