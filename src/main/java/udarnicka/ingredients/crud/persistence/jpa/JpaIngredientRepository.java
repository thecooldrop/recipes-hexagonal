package udarnicka.ingredients.crud.persistence.jpa;

import org.springframework.dao.DataIntegrityViolationException;
import udarnicka.common.CanonicalName;
import udarnicka.common.SerialInteger;
import udarnicka.ingredients.crud.domain.ports.*;

import java.util.Optional;

public class JpaIngredientRepository implements IngredientRepository {

    private final SpringDataIngredientRepository springDataIngredientRepository;

    public JpaIngredientRepository(SpringDataIngredientRepository springDataIngredientRepository) {
        this.springDataIngredientRepository = springDataIngredientRepository;
    }


    @Override
    public Ingredient create(CreateIngredient ingredient) {
        IngredientJpaEntity ingredientDataEntity = new IngredientJpaEntity();
        ingredientDataEntity.setName(ingredient.name());
        springDataIngredientRepository.findByCanonicalName(ingredientDataEntity.getCanonicalName())
                .map(elem -> new Ingredient(new IngredientName(new CanonicalName(elem.getName())), new IngredientId(new SerialInteger(elem.getId()))))
                .map(elem -> new DuplicateIngredientException("The ingredient " + elem + " is already contained in the database", elem))
                .ifPresent(elem -> {
                    throw elem;
                });
        springDataIngredientRepository.save(ingredientDataEntity);
        return new Ingredient(new IngredientName(new CanonicalName(ingredientDataEntity.getName())), new IngredientId(new SerialInteger(ingredientDataEntity.getId())));
    }

    @Override
    public Optional<Ingredient> deleteById(IngredientId id) {
        return springDataIngredientRepository.deleteByIdReturning(id.id().toInteger())
                .map(ingredientData -> new Ingredient(new IngredientName(new CanonicalName(ingredientData.getName())), new IngredientId(new SerialInteger(ingredientData.getId()))));
    }

    @Override
    public Optional<Ingredient> readById(IngredientId id) {
        return springDataIngredientRepository.findById(id.id().toInteger())
                .map(ingredientData -> new Ingredient(new IngredientName(new CanonicalName(ingredientData.getName())), new IngredientId(new SerialInteger(ingredientData.getId()))));
    }
}
