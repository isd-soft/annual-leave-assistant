--populate groups
INSERT INTO public.groups(
	id, name)
	VALUES (1, 'Administrator');
INSERT INTO public.groups(
	id, name)
	VALUES (2, 'User');
	
--populate leave_request_types
INSERT INTO public.leave_request_types(
	id, name)
	VALUES (1, 'CONCEDIU ANUAL');
INSERT INTO public.leave_request_types(
	id, name)
	VALUES (2, 'CONCEDIU PENTRU STUDII');
INSERT INTO public.leave_request_types(
	id, name)
	VALUES (3, 'CONCEDIU DE MATERNITATE');
INSERT INTO public.leave_request_types(
	id, name)
	VALUES (4, 'CONCEDIU PATERNAL');
INSERT INTO public.leave_request_types(
	id, name)
	VALUES (5, 'CONCEDIU PENTRU CASATORIE');
INSERT INTO public.leave_request_types(
	id, name)
	VALUES (6, 'CONCEDIU DIN CONT PROPRIU');
	
--populate statuses

INSERT INTO public.statuses(
	id, name)
	VALUES (1, 'PENDING');
INSERT INTO public.statuses(
	id, name)
	VALUES (2, 'APPROVED');
	