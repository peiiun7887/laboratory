DROP TABLE IF EXISTS van;

CREATE TABLE van (
	van_id varchar NOT NULL,
	van_name varchar NULL,
	CONSTRAINT van_pkey PRIMARY KEY (van_id)
);
