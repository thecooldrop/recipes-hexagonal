package udarnicka.infrastructure;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Configuration
@Profile("LOCAL_DATABASE")
public class LocalDatasourceConfiguration {

    @Bean("dataSource")
    DataSource localPostgresDatasource(PostgreSQLContainer<?> databaseContainer) {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .username(databaseContainer.getUsername())
                .password(databaseContainer.getPassword())
                .url(databaseContainer.getJdbcUrl())
                .build();
    }

    @Bean
    PostgreSQLContainer<?> localPostgresDatabaseContainer() {
        var localDatabase = new PostgreSQLContainer<>("postgres:14");
        localDatabase.start();
        while(!localDatabase.isRunning()) {
            // wait until database becomes ready
        }
        return localDatabase;
    }
}
