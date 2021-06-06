package DAO;

import Connection.ConnectionResourceBundle;
import DBO.Address;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressDao implements IAddressDao {
    ConnectionResourceBundle connectionResourceBundle = new ConnectionResourceBundle();

    //ResultSet rsAddress = stmt.executeQuery("SELECT * FROM address");
    public int save(Address address) throws SQLException {
        String sql = "INSERT INTO test.address(street,house) VALUES(?,?)";
        PreparedStatement ps = connectionResourceBundle.getConnection().prepareStatement(sql);
        ps.setString(1, address.getStreet());
        ps.setInt(2, address.getHouse());
        return ps.executeUpdate();
    }

    public Address get(Serializable id) throws SQLException {
        Statement stmt = connectionResourceBundle.getConnection().createStatement();
        return null;
    }

    public void update(Address address) throws SQLException {
        Statement stmt = connectionResourceBundle.getConnection().createStatement();
        System.out.println(address);
        int rowsAffected = stmt.executeUpdate("UPDATE test.address SET house ='" + address.getHouse()+"'" + "where id=" + address.getId()+" ;");
        System.out.println();
    }

    public int delete(Serializable id) throws SQLException {
        Statement stmt = connectionResourceBundle.getConnection().createStatement();
        return stmt.executeUpdate("delete FROM address where id=" + id + " ;");
    }

    @Override
    public List<Address> getAllAddress() throws SQLException {
        Statement stmt = connectionResourceBundle.getConnection().createStatement();
        ResultSet rsPeople = stmt.executeQuery("SELECT * FROM address");
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
