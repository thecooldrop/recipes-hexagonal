package udarnicka.ingredients.crud.persistence.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SpringDataIngredientRepository extends CrudRepository<IngredientJpaEntity, Integer> {

    @Query(nativeQuery = true, value = "DELETE FROM ingredients where id=?1 RETURNING *")
    Optional<IngredientJpaEntity> deleteByIdReturning(Integer id);

    Optional<IngredientJpaEntity> findByName(String name);
}
