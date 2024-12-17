-- Drop the Chat_User table if it already exists (for clean initialization)
DROP TABLE IF EXISTS Chat_User;

-- Create the Chat_User table
CREATE TABLE Chat_User (
    user_id UUID PRIMARY KEY, -- UUID type for H2
    user_name VARCHAR(100) NOT NULL
);

-- Insert 5 dummy users into Chat_User table
INSERT INTO Chat_User (user_id, user_name) VALUES (RANDOM_UUID(), 'JohnDoe');
INSERT INTO Chat_User (user_id, user_name) VALUES (RANDOM_UUID(), 'JaneDoe');
INSERT INTO Chat_User (user_id, user_name) VALUES (RANDOM_UUID(), 'AliceSmith');
INSERT INTO Chat_User (user_id, user_name) VALUES (RANDOM_UUID(), 'BobBrown');
INSERT INTO Chat_User (user_id, user_name) VALUES (RANDOM_UUID(), 'CharlieWhite');
