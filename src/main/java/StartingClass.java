import DAO.AddressDao;
import DAO.PeopleDao;
import DBO.Address;
import DBO.People;
import DatabaseDataChanges.AddressChanges;
import DatabaseDataChanges.PeopleChanges;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StartingClass {
    /**
     * //    Реализовать для этих двух таблиц DTO и DAO.
     * //    С основной программы:
     * //       1 при помощи DAO создать 5 адресов и 5 человек.
     * //       2 при помощи DAO увеличить на  в 2-ух последних адресов дом на 1 и у двух последних людей возраст на 2.
     * //       3 при помощи DAO удалить первый адрес и первого человека
     * //
     * //    Залить в ветку JOIN в git.
     * //   Создать две ветки в git от ветки JOIN:
     * //   - JOIN_ONE_TO_MANY
     * //   - JOIN_MANY_TO_MANY
     * //
     * //    В ветке JOIN_ONE_TO_MANY  доработать DAO и связи в таблице  где Один адресс может принадлежать разным людям.
     * //    В ветке JOIN_MANY_TO_MANY  доработать DAO и связи в таблице  где множестов адрессов могут принадлежать разным людям.
     */
    public static void main(String[] args) throws SQLException {
        List<People> peopleList = People.createPeoples();
        List<Address> addressList = Address.createAddress();
        PeopleDao peopleDao = new PeopleDao();
        AddressDao addressDao = new AddressDao();

        //1 при помощи DAO создать 5 адресов и 5 человек.
        for (People people : peopleList) {
            peopleDao.save(people);
        }
        for (Address address : addressList) {
            addressDao.save(address);
        }
        AddressChanges.changesHouse(addressDao.getAllAddress()).forEach(e -> {
            try {
                addressDao.update(e);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        //2 при помощи DAO увеличить на  в 2-ух последних адресов дом на 1 и у двух последних людей возраст на 2.
        PeopleChanges.changesAge(peopleDao.getAllPeoples()).forEach(e -> {
            try {
                peopleDao.update(e);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


            addressDao.delete(addressDao.getAllAddress().get(0).getId());
            peopleDao.delete(peopleDao.getAllPeoples().get(0).getId());


        People people = People.builder().age(17).name("Ира").surname("Драгун").build();
        Address address1 = Address.builder().street("Гончарова").house(14).build();
        peopleDao.save(people);
        addressDao.save(address1);
        int idPeople = peopleDao.getAllPeoples().get(peopleDao.getAllPeoples().size() - 1).getId();
        int idAdress = addressDao.getAllAddress().get(addressDao.getAllAddress().size() - 1).getId();
        peopleDao.addPeopleAddress(idPeople, idAdress);

    }

}
