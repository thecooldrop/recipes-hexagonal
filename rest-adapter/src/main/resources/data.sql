insert into ingredient_types(type) values ('VOLUMETRIC'), ('WEIGHTED'), ('COUNTABLE');
insert into type_units(type_id, unit) values ( select id from ingredient_types where type = 'VOLUMETRIC', 'LITER');
insert into type_units(type_id, unit) values ( select id from ingredient_types where type = 'VOLUMETRIC', 'MILLILITER');
insert into type_units(type_id, unit) values ( select id from ingredient_types where type = 'WEIGHTED', 'GRAM');
insert into type_units(type_id, unit) values ( select id from ingredient_types where type = 'WEIGHTED', 'KILOGRAM');
insert into type_units(type_id, unit) values ( select id from ingredient_types where type = 'COUNTABLE', 'COUNT');