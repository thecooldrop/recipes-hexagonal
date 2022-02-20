package udarnicka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

@SpringBootApplication
public class RecipesApplication {

	private static final PostgreSQLContainer<?> localDatabase = new PostgreSQLContainer<>("postgres:14");

	public static void main(String[] args) throws InterruptedException {
		localDatabase.setPortBindings(List.of("12345:5432"));
		localDatabase.start();
		while(!localDatabase.isRunning());
		SpringApplication.run(RecipesApplication.class, args);
	}
}
