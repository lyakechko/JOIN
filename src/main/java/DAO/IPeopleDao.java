package DAO;

import DBO.Address;
import DBO.People;

import java.sql.SQLException;
import java.util.List;

public interface IPeopleDao extends IDao<People> {

    List<People> getAllPeoples() throws SQLException;
}
