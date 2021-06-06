CREATE TABLE test.order (
  order_id INT NOT NULL,
  user_id INT,
  PRIMARY KEY (order_id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);