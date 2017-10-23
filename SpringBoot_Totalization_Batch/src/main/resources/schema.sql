CREATE TABLE IF NOT EXISTS item(
  id serial PRIMARY KEY,
  title varchar(100),
  note varchar(500),
  price integer,
  image bytea
);

CREATE TABLE IF NOT EXISTS pre_item(
  id integer NOT NULL,
  title varchar(100),
  note varchar(500),
  price integer,
  image bytea
);

CREATE TABLE IF NOT EXISTS date_total(
  id serial PRIMARY KEY,
  date date,
  created_total integer,
  deleted_total integer
);

CREATE TABLE IF NOT EXISTS created_item(
  id integer NOT NULL,
  date_id integer,
  title varchar(100),
  note varchar(500),
  price integer,
  image bytea
);

CREATE TABLE IF NOT EXISTS deleted_item(
  id integer NOT NULL,
  date_id integer,
  title varchar(100),
  note varchar(500),
  price integer,
  image bytea
);
