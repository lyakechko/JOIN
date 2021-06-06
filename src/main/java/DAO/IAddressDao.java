package DAO;

import DBO.Address;

import java.sql.SQLException;
import java.util.List;

public interface IAddressDao extends IDao<Address>{
    List<Address> getAllAddress() throws SQLException;
}
