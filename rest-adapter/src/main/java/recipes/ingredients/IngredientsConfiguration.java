package recipes.ingredients;

import ingredients.crud.api.IngredientCrud;
import ingredients.crud.api.IngredientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class IngredientsConfiguration {

    @Bean
    public JdbcTemplate configureJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public IngredientRepository configureIngredientsRepository(JdbcTemplate template) {
        return new IngredientJdbcRepository(template);
    }

    @Bean
    public IngredientCrud configureIngredientsUsecase(IngredientRepository ingredientRepository) {
        return new IngredientCrud(ingredientRepository);
    }
}
