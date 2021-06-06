UPDATE test.address SET house =
CASE
WHEN house = 7 THEN 101
ELSE 103
END;