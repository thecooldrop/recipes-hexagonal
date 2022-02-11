package udarnicka.ingredients.crud.persistence.jpa;

import org.springframework.dao.DataIntegrityViolationException;
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
        Optional<IngredientJpaEntity> ingredientEntityInDatabase = springDataIngredientRepository.findByName(ingredient.name());
        if(ingredientEntityInDatabase.isPresent()) {
            Ingredient ingredientInDb = ingredientEntityInDatabase.map(elem -> new Ingredient(elem.getName(), new IngredientId(elem.getId())))
                    .orElseThrow(() -> new IllegalStateException("Element not found in database despite being recorded as duplicate. Possible transaction isolation error"));
            throw new DuplicateIngredientException("The ingredient " + ingredientInDb + " is already contained in the database", ingredientInDb);
        } else {
            springDataIngredientRepository.save(ingredientDataEntity);
            return new Ingredient(ingredientDataEntity.getName(), new IngredientId(ingredientDataEntity.getId()));
        }
    }

    @Override
    public Optional<Ingredient> deleteById(IngredientId id) {
        return springDataIngredientRepository.deleteByIdReturning(id.id())
                .map(ingredientData -> new Ingredient(ingredientData.getName(), new IngredientId(ingredientData.getId())));
    }

    @Override
    public Optional<Ingredient> readById(IngredientId id) {
        return springDataIngredientRepository.findById(id.id())
                .map(ingredientData -> new Ingredient(ingredientData.getName(), new IngredientId(ingredientData.getId())));
    }
}
