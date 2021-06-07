package DAO;

import Connection.ConnectionResourceBundle;
import DBO.Address;
import DBO.People;
import Database_Сalls.CreateQueryForDB;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressDao implements IAddressDao {

    Connection connection = ConnectionResourceBundle.connection;

    private static final String SQL_SAVE = "INSERT INTO test.address(street, house) " +
            "VALUE (?, ?)";
    private static final String SQL_GET = "SELECT * FROM test.%s WHERE id = %s";
    private static final String GET_ALL = "SELECT * FROM address";
    private static final String SQL_UPDATE = "UPDATE test.address SET house = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM test.%s WHERE id = %s";

    CreateQueryForDB<Address> createQueryForDB= new CreateQueryForDB();

    //ResultSet rsAddress = stmt.executeQuery("SELECT * FROM address");
    public int save(Address address) throws SQLException {
        Map<Integer, Object> param = new HashMap<>();
        param.put(2, address.getStreet());
        param.put(1, address.getHouse());
        return createQueryForDB.executePreparedStatement(SQL_SAVE, param);
    }

    public Address get(Serializable id) throws SQLException {
        Address address = new Address();
        createQueryForDB.executeResultSet(String.format(SQL_GET,
                createQueryForDB.getClass().getSimpleName(), id), address);
    }

    public void update(Address address) throws SQLException {
        Map<Integer, Object> param = new HashMap<>();
        param.put(2, address.getStreet());
        param.put(1, address.getHouse());
        createQueryForDB.executePreparedStatement(SQL_UPDATE,param);
    }

    public int delete(Serializable id)  {
        if (createQueryForDB.executeStatement(String.format(SQL_DELETE,
                createQueryForDB.getClass().getSimpleName(), id))) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<Address> getAllAddress() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rsPeople = stmt.executeQuery(GET_ALL);
        List<Address> addressList = new ArrayList<>();
        while (rsPeople.next()) {
            int integer = rsPeople.getInt(1);
            String s1 = rsPeople.getString(2);
            int s2 = rsPeople.getInt(3);
            addressList.add(Address.builder().street(s1).house(s2).id(integer).build());
        }
        return addressList;
    }
}
