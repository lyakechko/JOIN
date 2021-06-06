SELECT Orders.OrderID, Customers.CustomerName
FROM Orders
INNER JOIN Customers ON Orders.CustomerID = Customers.CustomerID;


SELECT A.name AS Name, B.name AS Name2, A.surname
FROM people A, people B
WHERE A.ID <> B.ID
AND A.name = B.name
ORDER BY A.name;