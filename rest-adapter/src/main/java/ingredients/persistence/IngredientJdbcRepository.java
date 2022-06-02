package ingredients.persistence;

import ingredients.crud.api.Ingredient;
import ingredients.crud.api.IngredientId;
import ingredients.crud.api.IngredientName;
import ingredients.crud.api.IngredientRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class IngredientJdbcRepository implements IngredientRepository {

    private JdbcTemplate template;

    public IngredientJdbcRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<Ingredient> readById(IngredientId id) {
        template.
        return Optional.empty();
    }

    @Override
    public Ingredient save(IngredientName name) {
        return null;
    }

    @Override
    public void delete(IngredientId id) {

    }
}
