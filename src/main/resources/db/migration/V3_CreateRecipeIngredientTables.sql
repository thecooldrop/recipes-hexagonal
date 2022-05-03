create type ingredient_type as ENUM ("DESCRIPTIVE", "VOLUMETRIC", "WEIGHTED")

create table recipe_ingredient_types(
    id serial primary key,
    type ingredient_type unique not null
);

insert into table recipe_ingredient_types(type) values ("DESCRIPTIVE"), ("VOLUMETRIC"), ("WEIGHTED");

create table ingredient_measurement_units(
    id serial primary key,
    type_id integer foreign key references recipe_ingredient_types(id),
    unit text unique not null check (coalesce(unit, '') != ''),
);

with weighted_id as (select id from recipe_ingredient_types where type = "WEIGHTED")
insert into table ingredient_measurement_units(type_id, unit) values (weighted_id, "GRAM"), (weighted_id, "KILOGRAM"), (weighted_id, "MILLIGRAM");

with volumetric_id as (select id from recipe_ingredient_types where type = "VOLUMETRIC")
insert into table ingredient_measurement_units(type, unit) values (volumetric_id, "LITER"), (volumetric_id, "MILLILITER");

with descriptive_id as (select id from recipe_ingredient_types where type = "DESCRIPTIVE")
insert into table ingredient_measurement_units(type, unit) values
    (descriptive_id, "PINCH"),
    (descriptive_id, "TO_TASTE"),
    (descriptive_id, "PARTS"),
    (descriptive_id, "PIECES");

create table recipe_measured_ingredients (
    id serial primary key,
    recipe_id integer not null foreign key references recipes(id) on delete cascade,
    ingredient_id integer not null foreign key references ingredients(id) on delete restrict,
    unit_id integer not null foreign key references ingredient_measurement_units(id) on delete restrict,
    magnitude integer not null check (magnitude >= 0)
);