package DatabaseDataChanges;

import DBO.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressChanges {
    public static List<Address> changesHouse(List<Address> addressList) {
        Address address1 = addressList.get(addressList.size() - 1);
        Address address2 = addressList.get(addressList.size() - 2);
        address1.setHouse(address1.getHouse() + 1);
        address2.setHouse(address2.getHouse() + 1);
        List<Address> list = new ArrayList<>();
        list.add(address1);
        list.add(address2);
        return list;
    }
}
