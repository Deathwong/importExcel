truncate public.excel; 

INSERT INTO public.excel
(exc_id, exc_nom, exc_prenom, exc_age, exc_date)
VALUES(nextval('excel_seq'), 'MENSAH', 'jean', 19, now());
INSERT INTO public.excel
(exc_id, exc_nom, exc_prenom, exc_age, exc_date)
VALUES(nextval('excel_seq'), 'ANANI-TOULASSI', 'Edem', 35, now());
INSERT INTO public.excel
(exc_id, exc_nom, exc_prenom, exc_age, exc_date)
VALUES(nextval('excel_seq'), 'DIGBEU', 'Deborah', 34, now());
INSERT INTO public.excel
(exc_id, exc_nom, exc_prenom, exc_age, exc_date)
VALUES(nextval('excel_seq'), 'MENSAH', 'Jeff', 19, now());
