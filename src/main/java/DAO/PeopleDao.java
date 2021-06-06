package DAO;

import DBO.Address;
import DBO.People;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Connection.ConnectionResourceBundle;


public class PeopleDao implements IPeopleDao {
    ConnectionResourceBundle connectionResourceBundle = new ConnectionResourceBundle();

    public int save(People people) throws SQLException {
        String sql = "INSERT INTO test.people(name,surname,age,address_id) VALUES(?,?,?,?)";
        PreparedStatement ps = connectionResourceBundle.getConnection().prepareStatement(sql);
        ps.setString(1, people.getName());
        ps.setString(2, people.getSurname());
        ps.setInt(3, people.getAge());
        ps.setInt(4, people.getAddress().getId());
        return ps.executeUpdate();
    }

    public People get(Serializable id) throws SQLException {
        return null;
    }

    public void update(People people) throws SQLException {

    }

    public int delete(Serializable id) throws SQLException {
        Statement stmt = connectionResourceBundle.getConnection().createStatement();
        return stmt.executeUpdate("delete FROM people where id=" + id + " ;");
    }

    @Override
    public List<People> getAllPeoples() throws SQLException {
        Statement stmt = connectionResourceBundle.getConnection().createStatement();
        ResultSet rsPeople = stmt.executeQuery("SELECT * FROM people");
        List<People> peopleList = new ArrayList<>();
        ResultSetMetaData metadata = rsPeople.getMetaData();
        System.out.println(metadata.getColumnName(1));
        System.out.println(metadata.getColumnName(2));
        System.out.println(metadata.getColumnName(3));
        System.out.println(metadata.getColumnName(4));
        while (rsPeople.next()) {
            System.out.println("pfikb");
            int integer = rsPeople.getInt(1);
            String s1 = rsPeople.getString(2);
            String s2 = rsPeople.getString(3);
            int s3 = rsPeople.getInt(4);
            System.out.println(integer);
            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s3);
            peopleList.add(People.builder().id(integer).name(s1).surname(s2).age(s3).build());
            System.out.println(rsPeople.getString("name") + "=========");
        }
        System.out.println(3);
        return peopleList;
    }
}
