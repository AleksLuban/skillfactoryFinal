--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Accounts" (
    id bigint NOT NULL,
    balance double precision
);


ALTER TABLE public."Accounts" OWNER TO postgres;

--
-- Name: Operations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Operations" (
    id bigint NOT NULL,
    client_id bigint NOT NULL,
    type_of_operation character varying NOT NULL,
    sum double precision NOT NULL,
    time_of_operation timestamp without time zone NOT NULL
);


ALTER TABLE public."Operations" OWNER TO postgres;

--
-- Name: operations_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.operations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.operations_id_seq OWNER TO postgres;

--
-- Name: operations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.operations_id_seq OWNED BY public."Operations".id;


--
-- Name: Operations id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Operations" ALTER COLUMN id SET DEFAULT nextval('public.operations_id_seq'::regclass);


--
-- Data for Name: Accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Accounts" (id, balance) FROM stdin;
1	10500
2	15000
\.


--
-- Data for Name: Operations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Operations" (id, client_id, type_of_operation, sum, time_of_operation) FROM stdin;
1	1	╨Я╨╛╨┐╨╛╨╗╨╜╨╡╨╜╨╕╨╡ ╤Б╤З╨╡╤В╨░	3500	2024-12-13 08:04:20.968289
2	1	╨Я╨╛╨┐╨╛╨╗╨╜╨╡╨╜╨╕╨╡ ╤Б╤З╨╡╤В╨░	3500	2024-12-13 08:04:48.49053
3	1	C╨╜╤П╤В╨╕╨╡ ╨┤╨╡╨╜╨╡╨│	2000	2024-12-13 08:05:07.440474
4	1	C╨╜╤П╤В╨╕╨╡ ╨┤╨╡╨╜╨╡╨│	2000	2024-12-13 08:06:31.296651
5	1	C╨╜╤П╤В╨╕╨╡ ╨┤╨╡╨╜╨╡╨│	2000	2024-12-13 08:06:34.396829
6	1	C╨╜╤П╤В╨╕╨╡ ╨┤╨╡╨╜╨╡╨│	2500	2024-12-13 08:17:29.786616
7	1	C╨╜╤П╤В╨╕╨╡ ╨┤╨╡╨╜╨╡╨│	2000	2024-12-13 09:12:35.323762
8	1	╨Я╨╡╤А╨╡╨▓╨╛╨┤ ╤Б╤А╨╡╨┤╤Б╤В╨▓	2500	2024-12-13 09:43:39.605737
9	2	╨Ч╨░╤З╨╕╤Б╨╗╨╡╨╜╨╕╨╡ ╤Б╤А╨╡╨┤╤Б╤В╨▓	2500	2024-12-13 09:43:39.605737
10	1	╨Я╨╡╤А╨╡╨▓╨╛╨┤ ╤Б╤А╨╡╨┤╤Б╤В╨▓	2500	2024-12-13 09:43:55.838861
11	2	╨Ч╨░╤З╨╕╤Б╨╗╨╡╨╜╨╕╨╡ ╤Б╤А╨╡╨┤╤Б╤В╨▓	2500	2024-12-13 09:43:55.838861
\.


--
-- Name: operations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.operations_id_seq', 11, true);


--
-- Name: Accounts Accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Accounts"
    ADD CONSTRAINT "Accounts_pkey" PRIMARY KEY (id);


--
-- Name: Operations operations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Operations"
    ADD CONSTRAINT operations_pkey PRIMARY KEY (id);


--
-- Name: Operations client_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Operations"
    ADD CONSTRAINT client_fk FOREIGN KEY (client_id) REFERENCES public."Accounts"(id) NOT VALID;


--
-- PostgreSQL database dump complete
--

