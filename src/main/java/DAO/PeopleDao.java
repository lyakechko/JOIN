package DAO;

import DBO.Address;
import DBO.People;

import java.io.Serializable;
import java.sql.*;
import java.util.*;

import Connection.ConnectionResourceBundle;
import Database_Сalls.CreateQueryForDB;


public class PeopleDao implements IPeopleDao {

    Connection connection = ConnectionResourceBundle.connection;

    private static final String GET_ALL = "SELECT * FROM people";
    private static final String SQL_SAVE = "INSERT INTO test.people(name, surname, age)" +
            "VALUE (?, ?, ?)";
    private static final String SQL_GET = "SELECT * FROM test.%s WHERE id = %s";
    private static final String SQL_UPDATE = "UPDATE test.people SET age = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM test.%s WHERE id = %s";
    CreateQueryForDB<People> createQueryForDB = new CreateQueryForDB();

    public int save(People people) throws SQLException {
        Map<Integer, Object> param = new HashMap<>();
        param.put(1, people.getName());
        param.put(2, people.getSurname());
        param.put(3, people.getAge());
        return createQueryForDB.executePreparedStatement(SQL_SAVE, param);
    }

    public People get(Serializable id) throws SQLException {
        People people = new People();
        createQueryForDB.executeResultSet(String.format(SQL_GET,
                createQueryForDB.getClass().getSimpleName(), id), people);
    }

    public void update(People people) throws SQLException {
        Map<Integer, Object> param = new HashMap<>();
        param.put(2, people.getId());
        param.put(1, people.getAge());
        createQueryForDB.executePreparedStatement(SQL_UPDATE,param);
    }

    public int delete(Serializable id) {
        if (createQueryForDB.executeStatement(String.format(SQL_DELETE,
                createQueryForDB.getClass().getSimpleName(), id))) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<People> getAllPeoples() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rsPeople = stmt.executeQuery(String.format(GET_ALL));
        List<People> peopleList = new ArrayList<>();
        ResultSetMetaData metadata = rsPeople.getMetaData();
        System.out.println(metadata.getColumnName(1));
        System.out.println(metadata.getColumnName(2));
        System.out.println(metadata.getColumnName(3));
        System.out.println(metadata.getColumnName(4));
        while (rsPeople.next()) {
            int integer = rsPeople.getInt(1);
            String s1 = rsPeople.getString(2);
            String s2 = rsPeople.getString(3);
            int s3 = rsPeople.getInt(4);
            System.out.println(integer);
            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s3);
            peopleList.add(People.builder().id(integer).name(s1).surname(s2).age(s3).build());

        }
        return peopleList;
    }
}
