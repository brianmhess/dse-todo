CREATE KEYSPACE IF NOT EXISTS demo_sdc WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE IF NOT EXISTS demo_sdc.todo(
       id INT,
       category TEXT,
       name TEXT,
       complete BOOLEAN,
       PRIMARY KEY ((id))
);
