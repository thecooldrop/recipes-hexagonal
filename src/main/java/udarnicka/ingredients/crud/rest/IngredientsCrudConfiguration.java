package udarnicka.ingredients.crud.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import udarnicka.ingredients.crud.domain.ports.IngredientCrudService;
import udarnicka.ingredients.crud.domain.ports.IngredientRepository;
import udarnicka.ingredients.crud.persistence.jpa.JpaIngredientRepository;
import udarnicka.ingredients.crud.persistence.jpa.SpringDataIngredientRepository;

@Configuration
public class IngredientsCrudConfiguration {

    @Bean
    IngredientCrudService ingredientCrudService(IngredientRepository ingredientRepository) {
        return new IngredientCrudService(ingredientRepository);
    }

    @Bean
    IngredientRepository ingredientRepository(SpringDataIngredientRepository springIngredientRepository) {
        return new JpaIngredientRepository(springIngredientRepository);
    }

}
