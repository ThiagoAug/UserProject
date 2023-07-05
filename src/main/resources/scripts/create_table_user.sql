CREATE TABLE public."user"
(
    "cdUser" bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name character varying NOT NULL,
    birthdate date NOT NULL,
    image character varying,
    PRIMARY KEY ("cdUser")
);

ALTER TABLE IF EXISTS public."user"
    OWNER to admin;