--Creates the database tables

CREATE TABLE user_info (
    user_id INT NOT NULL,
    username VARCHAR(50) NOT NULL,
    email_address VARCHAR(50) NOT NULL,
    UNIQUE(email_address),
    PRIMARY KEY (user_id)
);
