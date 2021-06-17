package HibernateUtil;

import DBO.Address;
import DBO.People;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

public class HibernateDao {

    public static void save(EntityManager entityManager, Object object) {
        try {
            if (object instanceof People) {
                List<People> list = (List<People>) object;
                for (People people : list) {
                    entityManager.getTransaction().begin();
                    entityManager.persist(people);
                    entityManager.getTransaction().commit();
                    entityManager.close();
                }
            } else {
                List<Address> list = (List<Address>) object;
                for (Address address : list) {
                    entityManager.getTransaction().begin();
                    entityManager.persist(address);
                    entityManager.getTransaction().commit();
                    entityManager.close();
                }
            }
        } catch (Exception ex) {
            System.out.println("Ошибка приведения типов");
        }
    }

    public static void updateLast2(EntityManager entityManager) {
        try {
            entityManager.getTransaction().begin();
            List<People> peopleList = entityManager.createQuery("select '*' from People", People.class).getResultList();
            if (Objects.nonNull(peopleList) && !peopleList.isEmpty()) {
                int x = peopleList.size() - 1;
                int y = x - 2;
                for (int i = x; i <= y; i--) {
                    People people1 = peopleList.get(i);
                    people1.setAge(people1.getAge() + 1);
                    entityManager.find(People.class, peopleList.get(peopleList.size() - 1));
                }
            }
            entityManager.getTransaction().commit();
            entityManager.close();

            entityManager.getTransaction().begin();
            List<Address> addressList = entityManager.createQuery("select '*' from Address", Address.class).getResultList();
            if (Objects.nonNull(addressList) && !addressList.isEmpty()) {
                int x = addressList.size() - 1;
                int y = x - 2;
                for (int i = x; i <= y; i--) {
                    Address address1 = addressList.get(i);
                    address1.setHouse(address1.getHouse() + 1);
                    entityManager.find(Address.class, addressList.get(addressList.size() - 1));
                }
            }
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (Exception ex) {
            System.out.println("Исключение: " + ex.getMessage());
        }
    }

    public static void deleteFirst2(EntityManager entityManager) {
        try {
            entityManager.getTransaction().begin();
            List<People> peopleList = entityManager.createQuery("select '*' from People", People.class).getResultList();
            if (Objects.nonNull(peopleList) && !peopleList.isEmpty()) {
                entityManager.remove(peopleList.get(0));
            }
            entityManager.getTransaction().commit();
            entityManager.close();

            entityManager.getTransaction().begin();
            List<Address> addressList = entityManager.createQuery("select '*' from Address", Address.class).getResultList();
            if (Objects.nonNull(addressList) && !addressList.isEmpty()) {
                entityManager.remove(addressList.get(0));
            }
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (Exception ex) {
            System.out.println("Исключение: " + ex.getMessage());
        }
    }
}
