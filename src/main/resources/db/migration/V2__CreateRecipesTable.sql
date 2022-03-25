create table recipes (
    id serial primary key,
    name text not null check (coalesce(trim(name),'') != ''),
    canonical_name text unique not null check (coalesce(trim(canonical_name), '') != '' and canonical_name = lower(name))
);
create table recipe_steps (
    id serial primary key,
    step text not null check (coalesce(trim(step), '') != ''),
    step_order_index smallint not null,
    recipe_id integer not null references recipes(id) on delete cascade
);