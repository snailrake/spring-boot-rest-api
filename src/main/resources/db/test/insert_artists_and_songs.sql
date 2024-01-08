BEGIN;

DELETE
FROM songs;

DELETE
FROM artists;

INSERT INTO artists (id, birth_date, hometown, name)
VALUES ('ec7f6150-be8b-428a-a11d-e51f69d1b0a4', '1980-05-15', 'New York', 'John Doe'),
       ('07f1d6a1-6b9b-4ae2-9736-5b35869ba828', '1992-08-20', 'Los Angeles', 'Jane Smith'),
       ('4191fb93-de0c-421d-9388-fb355067d3a7', '1975-02-10', 'Chicago', 'Bob Johnson');

INSERT INTO songs (id, duration, size, artist_id, format, name)
VALUES ('cfb5453b-9ded-48f1-a87f-0a6da75ad2d7', 180, 1024, 'ec7f6150-be8b-428a-a11d-e51f69d1b0a4', 'MP3', 'Song 1'),
       ('48e22cdd-3ec3-48d0-9327-0e0267f09501', 240, 2048, '07f1d6a1-6b9b-4ae2-9736-5b35869ba828', 'FLAC', 'Song 2');

COMMIT;
