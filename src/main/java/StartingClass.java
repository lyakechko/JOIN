import DBO.Address;
import DBO.People;
import SessionUtil.HibernateSessionCRUD;

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
    public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException {
        HibernateSessionCRUD<People> hibernateSessionCRUDPeople = new HibernateSessionCRUD<People>();
        HibernateSessionCRUD<Address> hibernateSessionCRUDAddress = new HibernateSessionCRUD<Address>();
        People people = new People();
        Address address = new Address();
        Class clazzAddress = address.getClass();
        Class clazzPeople = people.getClass();
        hibernateSessionCRUDAddress.setObject(clazzAddress);
        hibernateSessionCRUDPeople.setObject(clazzPeople);

        // 1 при помощи DAO создать 5 адресов и 5 человек.
        List<People> peopleList = People.createPeoples();
        List<Address> addressList = Address.createAddress();
        hibernateSessionCRUDAddress.saveObjects(addressList);
        hibernateSessionCRUDPeople.saveObjects(peopleList);

        //2 при помощи DAO увеличить на  в 2-ух последних адресов дом на 1 и у двух последних людей возраст на 2.
        hibernateSessionCRUDPeople.updateLast2Objects();
        hibernateSessionCRUDAddress.updateLast2Objects();


        // 3 при помощи DAO удалить первый адрес и первого человека
        hibernateSessionCRUDAddress.deleteFirstObject();
        hibernateSessionCRUDPeople.deleteFirstObject();


    }
}

