-- Film
CREATE TABLE IF NOT EXISTS Film (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    director VARCHAR(255),
    duration INT,
    year_of_release INT,
    genre VARCHAR(255),
    rating VARCHAR(10),
    production_country VARCHAR(255),
    awards TEXT,
    synopsis TEXT
    );

-- Scene
CREATE TABLE IF NOT EXISTS Scene (
     id SERIAL PRIMARY KEY,
     description TEXT,
     budget DECIMAL,
     minutes INT,
     location VARCHAR(255),
    filming_date DATE,
    key_characters TEXT,
    film_id INT,
    FOREIGN KEY (film_id) REFERENCES Film(id)
    );

-- Character
CREATE TABLE IF NOT EXISTS Character (
     id SERIAL PRIMARY KEY,
     name VARCHAR(255),
    actor_name VARCHAR(255),
    race VARCHAR(255),
    alignment VARCHAR(255),
    backstory TEXT,
    appearance_in_films TEXT,
    cost DECIMAL,
    stock INT,
    scene_id INT,
    FOREIGN KEY (scene_id) REFERENCES Scene(id)
    );
