package udarnicka.ingredients.crud.persistence.jpa;

import udarnicka.ingredients.crud.domain.ports.CreateIngredient;
import udarnicka.ingredients.crud.domain.ports.Ingredient;
import udarnicka.ingredients.crud.domain.ports.IngredientId;
import udarnicka.ingredients.crud.domain.ports.IngredientRepository;

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
        springDataIngredientRepository.save(ingredientDataEntity);
        return new Ingredient(ingredientDataEntity.getName(), new IngredientId(ingredientDataEntity.getId()));
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
