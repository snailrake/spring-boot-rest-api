--liquibase formatted sql

--changeset Viktor Dyukov:1

CREATE TABLE artists
(
    id         bigserial PRIMARY KEY,
    birth_date date,
    hometown   varchar(255),
    name       varchar(255)
);

CREATE TABLE songs
(
    id        bigserial PRIMARY KEY,
    duration  integer NOT NULL,
    size      integer NOT NULL,
    artist_id bigint,
    format    varchar(255) CONSTRAINT songs_format_check CHECK ((format)::text = ANY
                                                                ((ARRAY ['MP3'::character varying, 'AAC'::character varying, 'FLAC'::character varying, 'WAV'::character varying, 'OGG_VORBIS'::character varying])::text[])),
    name      varchar(255),
    CONSTRAINT artist_id_constraint FOREIGN KEY (artist_id) REFERENCES public.artists
);