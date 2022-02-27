- I should write tests for the REST layer of ingredients crud
- I have an idea to refactor the JPA repositories for Ingredient and Recipe into a single interface which would 
  enable the CRUD operations on all entities which implement specific interfaces. This may be too generic for Java lang
  though
- In the internal construction of domain classes such as ingredient ID, there is a lot of type composition going on
  which uses types to express both intent and constraints. This means that these deeply composed classes will pop up
  in our RESTful output to the clients, and will break API whenever they change. This means we need REST layer.
- I have started implementing the abstract base test class to enable running tests across all implementations of the
  recipe repository. The reason for doing this is that I am likely to have multiple different repositories, one for
  in memory, one for postgres and eventually one for dynamo db.