package udarnicka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Arrays;

@SpringBootApplication
public class RecipesApplication {

	private static PostgreSQLContainer<?> localDatabase;

	public static void main(String[] args) {
		if(System.getenv("RECIPES_LOCAL_DATABASE") != null) {
			localDatabase = new PostgreSQLContainer<>("postgres:14");
			localDatabase.setPortBindings(Arrays.asList(System.getenv("RECIPES_LOCAL_DATABASE_PORT_BINDINGS").split(",")));
			localDatabase.start();
			while(!localDatabase.isRunning());
		}
		SpringApplication.run(RecipesApplication.class, args);
	}
}
