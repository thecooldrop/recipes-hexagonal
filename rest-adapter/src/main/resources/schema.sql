create table ingredients(
    id identity primary key,
    name varchar not null,
    canonical varchar unique not null
);

create table recipes(
    id identity primary key,
    name varchar not null,
    canonical varchar unique not null check(canonical = lower(name))
);

create table ingredient_types(
    type varchar unique not null check(type = upper(type)) primary key
);

create table type_units(
    id identity primary key,
    type_id varchar not null,
    unit varchar not null check(unit=upper(unit)),
    foreign key (type_id) references ingredient_types(type),
    constraint unit_has_single_type unique (type_id, unit)
);

create table recipe_ingredients(
    id identity primary key,
    recipe_id integer not null,
    ingredient_id integer not null,
    type_unit_id integer not null,
    amount integer not null check( amount >= 0 ),
    foreign key (recipe_id) references recipes(id) on delete cascade,
    foreign key (ingredient_id) references ingredients(id) on delete restrict,
    foreign key (type_unit_id) references type_units(id) on delete restrict
);

create table recipe_steps(
    id identity primary key,
    recipe_id integer not null,
    step text not null,
    order_index integer not null,
    foreign key (recipe_id) references recipes(id) on delete cascade
);