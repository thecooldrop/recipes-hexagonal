create table recipes(
    id serial primary key,
    name text not null check (coalesce(trim(name),'') != ''),
    canonical_name text unique not null check (coalesce(trim(canonical_name), '') != '' and canonical_name = lower(name))
)