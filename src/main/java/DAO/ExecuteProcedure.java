package DAO;

import Database_Сalls.CreateQueryForDB;

import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class ExecuteProcedure {

    private static final String SQL_UPDATE_AGE = "{call updateAge (?, ?)}";
    private static CreateQueryForDB<ExecuteProcedure> createQueryForDB = new CreateQueryForDB();

    public static void updateAge() throws SQLException {
        Map<Integer, Object> paramIn = new HashMap<>();
        paramIn.put(1, 5);
        Map<Integer, Integer> paramOut = new HashMap<>();
        paramOut.put(2, Types.INTEGER);
        createQueryForDB.executeProcedure(SQL_UPDATE_AGE, paramIn, paramOut);
    }
}
