INSERT INTO public.subject (id, description, name)
VALUES (DEFAULT, 'Some math' ||
                 ' subject', 'MATH');

INSERT INTO public.course (id, name, subject_id)
VALUES (DEFAULT, '1.1', 1);