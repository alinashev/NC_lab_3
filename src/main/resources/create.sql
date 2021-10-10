BEGIN;


CREATE TABLE public."LAB3_ASH_Project"
(
    project_id integer NOT NULL,
    project_name character varying NOT NULL,
    PRIMARY KEY (project_id)
);

CREATE TABLE public."LAB3_ASH_Ticket"
(
    ticket_id integer NOT NULL,
    bug_status character varying NOT NULL,
    severity character varying NOT NULL,
    priority character varying NOT NULL,
    name character varying NOT NULL,
    summary character varying NOT NULL,
    created_date character varying NOT NULL,
    expected_result character varying NOT NULL,
    actual_result character varying NOT NULL,
    author character varying,
    project_id integer,
    PRIMARY KEY (ticket_id)
);

CREATE TABLE public."LAB3_ASH_User"
(
    id integer NOT NULL,
    username character varying(255) NOT NULL,
    email character varying(255),
    password character varying(255),
    PRIMARY KEY (id, username)
);

CREATE TABLE public."LAB3_ASH_History"
(
    history_id integer,
    field_name character varying,
    changed_date date,
    old_value character varying,
    new_value character varying,
    ticket_id integer,
    PRIMARY KEY (history_id)
);

CREATE TABLE public."LAB3_ASH_SubTicket"
(
    name character varying,
    bug_status character varying,
    summary character varying,
    parent_id integer
);

ALTER TABLE public."LAB3_ASH_Ticket"
    ADD FOREIGN KEY (project_id)
    REFERENCES public."LAB3_ASH_Project" (project_id)
    NOT VALID;


ALTER TABLE public."LAB3_ASH_SubTicket"
    ADD FOREIGN KEY (parent_id)
    REFERENCES public."LAB3_ASH_Ticket" (ticket_id)
    NOT VALID;


ALTER TABLE public."LAB3_ASH_History"
    ADD FOREIGN KEY (ticket_id)
    REFERENCES public."LAB3_ASH_Ticket" (ticket_id)
    NOT VALID;

END;