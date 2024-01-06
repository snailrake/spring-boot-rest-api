BEGIN;

DELETE
FROM songs;

DELETE
FROM artists;

ALTER SEQUENCE artists_id_seq
    RESTART WITH 1;
ALTER SEQUENCE songs_id_seq
    RESTART WITH 1;

INSERT INTO artists (birth_date, hometown, name)
VALUES ('1980-05-15', 'New York', 'John Doe'),
       ('1992-08-20', 'Los Angeles', 'Jane Smith'),
       ('1975-02-10', 'Chicago', 'Bob Johnson');

INSERT INTO songs (duration, size, artist_id, format, name)
VALUES (180, 1024, 1, 'MP3', 'Song 1'),
       (240, 2048, 2, 'FLAC', 'Song 2');

COMMIT;
