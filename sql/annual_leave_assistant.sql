--create dataBase
CREATE DATABASE annual_leave_assistant
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_World.1252'
    LC_CTYPE = 'English_World.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

ALTER DATABASE annual_leave_assistant
    SET search_path TO "$user", public, topology, tiger;
	
	
--create table groups
CREATE TABLE public.groups
(
    id integer NOT NULL,
    name character varying(15) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.groups
    OWNER to postgres;
	
--create table leave_request_types
CREATE TABLE public.leave_request_types
(
    id integer NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT leave_request_types_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.leave_request_types
    OWNER to postgres;
	
--create table statuses
CREATE TABLE public.statuses
(
    id integer NOT NULL,
    name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT statuses_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.statuses
    OWNER to postgres;
--create table users
CREATE TABLE public.users
(
    id integer NOT NULL,
    name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(20) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(30) COLLATE pg_catalog."default" NOT NULL,
    employment_date date NOT NULL,
    group_id integer,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT group_id_fkey FOREIGN KEY (group_id)
        REFERENCES public.groups (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;

-- Index: fki_group_id_fkey

-- DROP INDEX public.fki_group_id_fkey;

CREATE INDEX fki_group_id_fkey
    ON public.users USING btree
    (group_id)
    TABLESPACE pg_default;
	
--create table leave_request_types
CREATE TABLE public.leave_requests
(
    id integer NOT NULL,
    leave_request_type_id integer NOT NULL,
    user_id integer NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    status_id integer NOT NULL,
    CONSTRAINT leave_requests_pkey PRIMARY KEY (id),
    CONSTRAINT leave_requests_status_id_fkey FOREIGN KEY (status_id)
        REFERENCES public.statuses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT leave_requests_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.leave_requests
    OWNER to postgres;

-- Index: fki_leave_requests_status_id_fkey

-- DROP INDEX public.fki_leave_requests_status_id_fkey;

CREATE INDEX fki_leave_requests_status_id_fkey
    ON public.leave_requests USING btree
    (status_id)
    TABLESPACE pg_default;

-- Index: fki_leave_requests_user_id_fkey

-- DROP INDEX public.fki_leave_requests_user_id_fkey;

CREATE INDEX fki_leave_requests_user_id_fkey
    ON public.leave_requests USING btree
    (user_id)
    TABLESPACE pg_default;
	
