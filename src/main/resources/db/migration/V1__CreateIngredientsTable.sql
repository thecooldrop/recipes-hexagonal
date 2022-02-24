create table ingredients (
    id serial primary key,
    name text not null check (coalesce(trim(name), '') != ''),
    canonical_name text unique not null check (coalesce(trim(name), '') != '' and canonical_name = lower(name))
);