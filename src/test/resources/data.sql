insert into ingredients (id, name, canonical_name) values
    (1, "Salt", "salt"),
    (2, "Water", "water"),
    (3, "Flour", "flour"),
    (4, "Yeast", "yeast"),
    (5, "Egg", "egg"),
    (6, "Chicken breast filet", "chicken breast filet"),
    (7, "Risotto rice", "rissoto rice"),
    (8, "King prawns frozen", "king prawns frozen"),
    (9, "Fish stock", "fish stock"),
    (10, "Mascarpone cheese", "mascarpone cheese"),
    (11, "Onion", "onion"),
    (12, "White wine - dry", "white wine - dry"),
    (13, "Olive Oil", "olive oil"),
    (14, "Sun dried Tomatoes in Oil", "sun dried tomatoes in oil");

insert into recipes (id, name, canonical_name) values
    (1, "Creamy Light King Prawn Risotto with White Wine", "creamy light king prawn risotto with white wine"),
    (2, "Pizza dough", "pizza dough");

insert into recipe_steps (id, step, step_order_index, recipe_id) values
    (1, "Chop the onion as finely as possible", 1, 1),
    (2, "Prepare the fish stock and heat it up, so that it is hot but not boiling. Keep the temperature of the stock high. Keep the lid on to prevent evaporation", 2, 1),
    (3, "Thaw the king prawns. If you are lacking time put the prawn into microwave for 30 seconds and then leave them to rest for few minutes. Repeat this proces between 3 and 5 times until prawns are thawed", 3, 1),
    (4, "Prepare the rice in a cup", 4, 1),
    (5, "Add the oil to a cold pot and turn on the heat.", 5, 1);
    (6, "As soon as the oil starts heating up, but before it is hot, add the onions", 6, 1),
    (7, "Sweat the onions very lightly until translucent. Taste to check if onion have lost their strong taste.", 7, 1),
    (8, "Once the onions lose the strength, but are not brown add the rice and mix well with onions", 8, 1),
    (9, "Add the white wine and turn the heat up to max", 9, 1),
    (10, "Stir vigorously until the wine and rice form a thick, saucy mixture.", 10, 1),
    (11, "Add the fish stock pot and stir without stopping for about 10 minutes, keep the fire on maximum", 11, 1),
    (12, "Add the prawns and sun dried tomatoes to the mixture", 12, 1),
    (13, "Cook for additional few minutes, until the prawn start going pink", 13, 1),
    (14, "Taste the rice. If the rice is not yet cooked quite cooked and the prawns are pink in color proceed to next step", 14, 1)
    (15, "Add the mascarpone cheese and mix it in vigorously for about 30 seconds. Remove from fire and let cool.", 15, 1),
    (16, "Do not worry about rice not being cooked. Residual heat will cook it until the end. Do not worry that mixture is runny, it will thicken as it cools", 16, 1);

insert into recipe_steps (id, step, step_order_index, recipe_id) values
    (17, "Add yeast to luke warm water", 1, 2),
    (18, "Add the yeast and water to flour", 2, 2),
    (19, "Mix the dough", 3, 2),
    (20, "Put the dough in a bowl", 4, 2),
    (21, "Cover the bowl with plastic wrap", 5, 2),
    (22, "Refrigerate for 24 hours minimum", 6, 2);


with descriptive_type_id as (
    select id from recipe_ingredient_type where type = "DESCRIPTIVE"
), volumetric_type_id as (
    select id from recipe_ingredient_type where type = "VOLUMETRIC"
), weighted_type_id as (
    select id from recipe_ingredient_type where type = "WEIGHTED"
), pinch_id as (
    select id from ingredient_measurement_units where type_id = descriptive_type_id and unit = "PINCH"
), milliliter_id as (
    select id from ingredient_measurement_units where type_id = volumetric_type_id and unit = "MILLILITER"
), liter_id as (
    select id from ingredient_measurement_units where type_id = volumetric_type_id and unit = "LITER"
), gram_id as (
    select id from ingredient_measurement_units where type = weighted_type_id and unit = "GRAM"
)
insert into recipe_measured_ingredients (id, recipe_id, ingredient_id, unit_id, magnitude) values
    (1, 1, 11, gram_id, 100),
    (2, 1, 9, milliliter_id, 800),
    (3, 1, 7, gram_id, 350),
    (4, 1, 12, milliliter_id, 120),
    (5, 1, 8, gram_id, 400),
    (6, 1, 14, gram_id, 100),
    (7, 1, 10, gram_id, 60),
    (8, 2, 1, pinch_id, 1),
    (9, 2, 2, milliliter_id, 300),
    (10, 2, 3 gram_id, 500),
    (11, 2, 4, gram_id, 10);