
DO $$
    BEGIN
        IF NOT EXISTS (SELECT FROM pg_roles WHERE rolname = 'postgres') THEN
            CREATE ROLE postgres LOGIN PASSWORD '1234' SUPERUSER;
        END IF;
    END $$ LANGUAGE plpgsql;



CREATE TABLE public.artists
(
    id         bigserial PRIMARY KEY,
    birth_date date,
    hometown   varchar(255),
    name       varchar(255)
);

ALTER TABLE public.artists
    OWNER TO postgres;

CREATE TABLE public.songs
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

ALTER TABLE public.songs
    OWNER TO postgres;
