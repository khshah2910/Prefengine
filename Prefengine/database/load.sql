--load users--
--Password is test1
INSERT INTO user (`id`, `username`, `email`, `password`, `saltkey`, `security_question`, `security_question_answer`)
VALUES (1, 'JSmith', 'jsmith@gmail.com', 'aM7UBIpLd5EYkVJfEK5JdIvn38QSnz8kl/Pr2JFga5c=', 
	'iDomC0oHKq5cnXpqKFOewLUAxBc=', 'What is your best drink', 'Java');
