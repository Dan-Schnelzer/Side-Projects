-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'flashcard_db';

DROP DATABASE flashcard_db;

DROP USER flashcard_db_owner;
DROP USER flashcard_db_app_user;
