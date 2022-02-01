package udarnicka.ingredients.crud.persistence.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import udarnicka.ingredients.crud.domain.ports.IngredientId;

import java.util.Optional;

interface SpringDataIngredientRepository extends CrudRepository<IngredientJpaEntity, Integer> {

    @Query(nativeQuery = true, value = "DELETE FROM ingredients where id=?1 RETURNING *")
    Optional<IngredientJpaEntity> deleteByIdReturning(Integer id);
}
