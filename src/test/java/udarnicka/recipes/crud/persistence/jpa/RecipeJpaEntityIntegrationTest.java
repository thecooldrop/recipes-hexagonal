package udarnicka.recipes.crud.persistence.jpa;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import udarnicka.common.CanonicalName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class RecipeJpaEntityIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14");

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SpringDataRecipeRepository springDataRecipeRepository;

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }


    @Test
    @Description("The canonical name transient field should be populated properly on first access")
    void transientFieldCanonicalNameIsPopulatedCorrectlyOnFirstAccessAfterLoadingFromDatabase() {
        RecipeJpaEntity entity = new RecipeJpaEntity();
        entity.setName("Pizza");
        springDataRecipeRepository.save(entity);
        em.clear();
        RecipeJpaEntity retrieved = springDataRecipeRepository.findByCanonicalName("pizza").get();
        assertThat(retrieved.getCanonicalName()).isEqualTo(new CanonicalName("Pizza"));
        assertThat(retrieved.getCanonicalName().getOriginal()).isEqualTo("Pizza");
    }
}
