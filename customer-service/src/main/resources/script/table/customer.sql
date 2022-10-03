-- Table: public.customer

-- DROP TABLE IF EXISTS public.customer;

CREATE TABLE IF NOT EXISTS public.customer
(
    id integer NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    firstname character varying(255) COLLATE pg_catalog."default",
    lastname character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT customer_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.customer
    OWNER to rastech;


-- SEQUENCE: public.customer_id_seq

-- DROP SEQUENCE IF EXISTS public.customer_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.customer_id_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.customer_id_seq
    OWNER TO rastech;