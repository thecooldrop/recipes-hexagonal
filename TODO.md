- I have started working on data model for recipes CRUD (feature/recipes-crud branch to be created)
- I have noticed that I am unable to differentiate between same ingredients which are entered
  into database in different cases. Namely ingredient "Flour" and "flour" would be treated as
  different ingredients. This needs to be fixed by adding a column for ingredients which
  would contain the originally entered spelling, while the second column should contain the
  canonical variation of the text. (feature/canonical-ingredients branch to be created )... Started working on
  this but still have some failing tests. Need to check that the JPA entity is properly mapped
  into the API types, and that it returns the user inputted name and not the canonical name.