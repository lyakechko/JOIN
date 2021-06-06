SELECT * FROM test.people WHERE id % 2 != 0;

SELECT street, COUNT(street)
FROM address
GROUP BY street
HAVING COUNT(street) > 1;

SELECT DATE_ADD(1620163533286,  1 day_microsecond) as timeToLunch FROM test.person;

SELECT DISTINCT street FROM test.address;

SELECT AVG(id) FROM test.address;

SELECT * FROM test.address
WHERE id > (SELECT AVG (id) FROM test.address);

SELECT CONCAT(street, house) AS new_field FROM test.address;