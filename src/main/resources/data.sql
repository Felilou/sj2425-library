-- Clear existing data (optional, helps with re-runs)
DELETE FROM book;
DELETE FROM employee;
DELETE FROM author;
DELETE FROM location_connections_to;
DELETE FROM location_connections_from;
DELETE FROM location;

-- Insert locations
INSERT INTO location (uuid, type, city, country, house_number, postal_code, street)
VALUES
  (RANDOM_UUID(), 'LIBRARY', 'Vienna', 'Austria', 123, 1010, 'Main Street'),
  (RANDOM_UUID(), 'WAREHOUSE', 'Vienna', 'Austria', 45, 1020, 'Storage Road'),
  (RANDOM_UUID(), 'STORE', 'Vienna', 'Austria', 67, 1030, 'Shopping Avenue');

-- Create connections between locations
INSERT INTO location_connections_to (location_id, connections_to_id)
SELECT l1.id, l2.id
FROM location l1, location l2
WHERE l1.type = 'LIBRARY' AND l2.type = 'WAREHOUSE';

INSERT INTO location_connections_to (location_id, connections_to_id)
SELECT l1.id, l2.id
FROM location l1, location l2
WHERE l1.type = 'WAREHOUSE' AND l2.type = 'STORE';

-- Insert authors
INSERT INTO author (uuid, first_name, last_name, birth_date, gender, email, phone)
VALUES
  (RANDOM_UUID(), 'Jane', 'Austen', '1775-12-16', 'FEMALE', 'jane.austen@literature.com', '+43123456789'),
  (RANDOM_UUID(), 'Franz', 'Kafka', '1883-07-03', 'MALE', 'franz.kafka@literature.com', '+43987654321'),
  (RANDOM_UUID(), 'Virginia', 'Woolf', '1882-01-25', 'FEMALE', 'virginia.woolf@literature.com', '+43567891234');

-- Insert employees
INSERT INTO employee (uuid, first_name, last_name, birth_date, gender, email, phone, role, location_id)
VALUES
  (RANDOM_UUID(), 'Max', 'Mustermann', '1985-05-15', 'MALE', 'max.mustermann@library.com', '+43111222333', 'LIBRARIAN',
   (SELECT id FROM location WHERE type = 'LIBRARY' LIMIT 1)),
  (RANDOM_UUID(), 'Anna', 'Schmidt', '1990-10-20', 'FEMALE', 'anna.schmidt@library.com', '+43444555666', 'MANAGER',
   (SELECT id FROM location WHERE type = 'LIBRARY' LIMIT 1)),
  (RANDOM_UUID(), 'Thomas', 'Huber', '1988-03-25', 'MALE', 'thomas.huber@library.com', '+43777888999', 'WAREHOUSE_WORKER',
   (SELECT id FROM location WHERE type = 'WAREHOUSE' LIMIT 1)),
  (RANDOM_UUID(), 'Sophie', 'Bauer', '1992-07-12', 'FEMALE', 'sophie.bauer@library.com', '+43222333444', 'STORE_WORKER',
   (SELECT id FROM location WHERE type = 'STORE' LIMIT 1)),
  (RANDOM_UUID(), 'Michael', 'Wagner', '1975-11-30', 'MALE', 'michael.wagner@library.com', '+43555666777', 'DIRECTOR',
   (SELECT id FROM location WHERE type = 'LIBRARY' LIMIT 1));

-- Insert books
INSERT INTO book (uuid, title, description, pages, author_id, location_id)
VALUES
  (RANDOM_UUID(), 'Pride and Prejudice', 'A romantic novel of manners', 432,
   (SELECT id FROM author WHERE last_name = 'Austen' LIMIT 1),
   (SELECT id FROM location WHERE type = 'LIBRARY' LIMIT 1)),

  (RANDOM_UUID(), 'Sense and Sensibility', 'A novel about two sisters', 380,
   (SELECT id FROM author WHERE last_name = 'Austen' LIMIT 1),
   (SELECT id FROM location WHERE type = 'STORE' LIMIT 1)),

  (RANDOM_UUID(), 'The Metamorphosis', 'A novella about a man who transforms into an insect', 201,
   (SELECT id FROM author WHERE last_name = 'Kafka' LIMIT 1),
   (SELECT id FROM location WHERE type = 'LIBRARY' LIMIT 1)),

  (RANDOM_UUID(), 'The Trial', 'A novel about a man arrested for an unknown crime', 305,
   (SELECT id FROM author WHERE last_name = 'Kafka' LIMIT 1),
   (SELECT id FROM location WHERE type = 'WAREHOUSE' LIMIT 1)),

  (RANDOM_UUID(), 'To the Lighthouse', 'A novel centering on the Ramsay family', 320,
   (SELECT id FROM author WHERE last_name = 'Woolf' LIMIT 1),
   (SELECT id FROM location WHERE type = 'LIBRARY' LIMIT 1)),

  (RANDOM_UUID(), 'Mrs Dalloway', 'A novel that details a day in the life of Clarissa Dalloway', 280,
   (SELECT id FROM author WHERE last_name = 'Woolf' LIMIT 1),
   (SELECT id FROM location WHERE type = 'STORE' LIMIT 1));
