package recipes.ingredients;

import common.PositiveInteger;
import ingredients.crud.api.Ingredient;
import ingredients.crud.api.IngredientId;
import ingredients.crud.api.IngredientName;
import ingredients.crud.api.IngredientRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class IngredientJdbcRepository implements IngredientRepository {

    private final JdbcTemplate template;

    private final PreparedStatementCreatorFactory readByIdPreparedStatementCreatorFactory = new PreparedStatementCreatorFactory("SELECT id,name from ingredients where id = ?", Types.INTEGER);

    private final RowMapper<Ingredient> rowToIngredientMapper = (rs, row) -> {
        Integer id = rs.getInt(1);
        String name = rs.getString(2);
        IngredientId ingredientId = new IngredientId(new PositiveInteger(id));
        IngredientName ingredientName = new IngredientName(name);
        return new Ingredient(ingredientId, ingredientName);
    };

    private final PreparedStatementCreatorFactory saveIngredientPreparedStatementCreatorFactory = new PreparedStatementCreatorFactory("INSERT INTO ingredients (name, canonical) VALUES (?,?)", Types.VARCHAR, Types.VARCHAR);

    private final PreparedStatementCreatorFactory deleteIngredientPreparedStatementCreatorFactory = new PreparedStatementCreatorFactory("DELETE FROM ingredients WHERE id = ?", Types.INTEGER);

    public IngredientJdbcRepository(JdbcTemplate template) {
        this.template = template;
        saveIngredientPreparedStatementCreatorFactory.setReturnGeneratedKeys(true);
    }

    @Override
    public Optional<Ingredient> readById(IngredientId id) {
        PreparedStatementCreator readById = readByIdPreparedStatementCreatorFactory.newPreparedStatementCreator(List.of(id.asInteger()));
        List<Ingredient> ingredients = template.query(readById, rowToIngredientMapper);
        return ingredients.stream().findFirst();
    }

    @Override
    public Ingredient save(IngredientName name) {
        Integer primaryKey = saveReturningPrimaryKey(name);
        return new Ingredient(new IngredientId(new PositiveInteger(primaryKey)), name);
    }

    private Integer saveReturningPrimaryKey(IngredientName name) {
        String ingredientName = name.asString();
        PreparedStatementCreator save = saveIngredientPreparedStatementCreatorFactory.newPreparedStatementCreator(List.of(ingredientName, ingredientName.toLowerCase()));
        KeyHolder generatedPrimaryKeyHolder = new GeneratedKeyHolder();
        template.update(save, generatedPrimaryKeyHolder);
        Number generatedPrimaryKey = generatedPrimaryKeyHolder.getKey();
        assert generatedPrimaryKey != null;
        return generatedPrimaryKey.intValue();
    }


    @Override
    public void delete(IngredientId id) {
        template.update(deleteIngredientPreparedStatementCreatorFactory.newPreparedStatementCreator(List.of(id.asInteger())));
    }

    @Override
    public List<Ingredient> read() {
        return template.query("select id,name from ingredients", rowToIngredientMapper);
    }
}
