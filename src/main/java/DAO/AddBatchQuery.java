package DAO;

import DBO.Address;
import DBO.People;
import DBO.People_Address;
import Database_Сalls.CreateQueryForDB;

import java.util.Arrays;
import java.util.List;

public class AddBatchQuery<T> {
    CreateQueryForDB<T> createQueryForDB = new CreateQueryForDB();

    private static final List<String> SQL_LIST_PEOPLE = Arrays.asList(
            "INSERT INTO test.%s VALUES ('James', 'Smith', 50)",
            "INSERT INTO test.%s VALUES ('Robert', 'Johnson', 37)",
            "INSERT INTO test.%s VALUES ('Michael', 'Williams', 12)",
            "INSERT INTO test.%s VALUES ('David', 'Brown', 25)",
            "INSERT INTO test.%s VALUES ('Richard', 'Jones', 18)");

    private static final List<String> SQL_LIST_ADDRESS = Arrays.asList(
            "INSERT INTO test.%s VALUES ('Wall Street', 11, 125)",
            "INSERT INTO test.%s VALUES ('Broadway', 7, 109)",
            "INSERT INTO test.%s VALUES ('Bowery', 28, 43)",
            "INSERT INTO test.%s VALUES ('Houston Street', 33, 150)",
            "INSERT INTO test.%s VALUES ('Canal Street', 45, 211)");

    private static final List<String> SQL_LIST_PEOPLE_ADDRESS = Arrays.asList(
            //todo исправить на получение index-ов
            "INSERT INTO test.%s VALUES (1, 1, 2)",
            "INSERT INTO test.%s VALUES (2, 2, 2)",
            "INSERT INTO test.%s VALUES (3, 3, 1)",
            "INSERT INTO test.%s VALUES (4, 4, 5)",
            "INSERT INTO test.%s VALUES (5, 5, 4)");

    public void save(T object) {
        if (object instanceof People) {
            createQueryForDB.executeBatch(SQL_LIST_PEOPLE, object);
        }
        if (object instanceof Address) {
            createQueryForDB.executeBatch(SQL_LIST_ADDRESS, object);
        }
        if (object instanceof People_Address) {
            createQueryForDB.executeBatch(SQL_LIST_PEOPLE_ADDRESS, object);
        } else {
            System.out.println("Значение параметризации AddBatchQuery, создано классом: " + object.getClass().getSimpleName());
        }
    }
}
