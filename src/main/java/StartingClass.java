import DBO.Address;
import DBO.People;
import HibernateUtil.HibernateDao;
import HibernateUtil.HibernateEntityManager;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

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

        //1 при помощи DAO создать 5 адресов и 5 человек.
        HibernateDao.save(HibernateEntityManager.getEntityManager(), People.createPeoples());
        HibernateDao.save(HibernateEntityManager.getEntityManager(), Address.createAddress());

        //2 при помощи DAO увеличить на  в 2-ух последних адресов дом на 1 и у двух последних людей возраст на 2.
        HibernateDao.updateLast2(HibernateEntityManager.getEntityManager());

        //       3 при помощи DAO удалить первый адрес и первого человека


        // entityManager.find(Address.class, 1L);


//        PeopleDao peopleDao = new PeopleDao();
//        AddressDao addressDao = new AddressDao();
//
//        //1 при помощи DAO создать 5 адресов и 5 человек.
//        for (People people : peopleList) {
//            peopleDao.save(people);
//        }
//        for (Address address : addressList) {
//            addressDao.save(address);
//        }
//        AddressChanges.changesHouse(addressDao.getAllAddress()).forEach(e -> {
//            try {
//                addressDao.update(e);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        });
//        //2 при помощи DAO увеличить на  в 2-ух последних адресов дом на 1 и у двух последних людей возраст на 2.
//        PeopleChanges.changesAge(peopleDao.getAllPeoples()).forEach(e -> {
//            try {
//                peopleDao.update(e);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        });


//            addressDao.delete(addressDao.getAllAddress().get(0).getId());
//            peopleDao.delete(peopleDao.getAllPeoples().get(0).getId());
//
//
//        Address address = Address.builder().street("Ленина 34").house(14).build();
//        Serializable id = addressDao.save(address);
//        address.setId(addressDao.getAllAddress().get(addressDao.getAllAddress().size() - 1).getId());
//
//        People people = People.builder().age(17).name("Ира").surname("Драгун").address(address).build();
//        People people1 = People.builder().age(17).name("Света").surname("Драгун").address(address).build();
//        peopleDao.save(people);
//        peopleDao.save(people1);

    }

}
