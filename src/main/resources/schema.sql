CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  display_name VARCHAR(255),
  is_admin BOOLEAN,
  login_streak INT,
  last_login_date TIMESTAMP
);
