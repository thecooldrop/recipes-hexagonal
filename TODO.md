- I should write tests for the REST layer of ingredients crud
- I have an idea to refactor the JPA repositories for Ingredient and Recipe into a single interface which would 
  enable the CRUD operations on all entities which implement specific interfaces. This may be too generic for Java lang
  though
- In the internal construction of domain classes such as ingredient ID, there is a lot of type composition going on
  which uses types to express both intent and constraints. This means that these deeply composed classes will pop up
  in our RESTful output to the clients, and will break API whenever they change. This means we need REST layer.
- I have made progress on database design. I need to complete the unit tests to include a recipe which has many 
  different fields there. Currently struggling with how to insert the data properly which has relations across many tables.
- I have decided to create a test data set instead of inserting data for each test separately.
- I do not like that the IngredientJpaEntity must be public. Maybe the split between ingredient and recipe is not justified