package udarnicka.ingredients.crud.persistence.jpa;

import jdk.jfr.Description;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

class JpaIngredientRepositoryTest {

    @Nested
    @Description("If there is an Postgresql database")
    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    @Testcontainers
    public class GivenAPostgresqlDatabase {

        @Container
        private static PostgreSQLContainer container = new PostgreSQLContainer("postgres:14");

        @DynamicPropertySource
        static void registerProperties(DynamicPropertyRegistry registry) {
            registry.add("spring.datasource.url", container::getJdbcUrl);
            registry.add("spring.datasource.username", container::getUsername);
            registry.add("spring.datasource.password", container::getPassword);
        }

    }

}