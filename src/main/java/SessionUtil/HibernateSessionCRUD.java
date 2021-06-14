package SessionUtil;

import DAO.IDao;
import DBO.Address;
import DBO.People;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.swing.*;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HibernateSessionCRUD<T> {

    Class<T> object;

    public Class<T> getObject() {
        return object;
    }

    public void setObject(Class<T> object) {
        this.object = object;
    }

    public void saveObjects(List<T> objects) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (T object : objects) {
                session.save(object);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public void updateLast2Objects() throws SQLException, IllegalAccessException, InstantiationException {
        Session session = null;
        Object objectBeforeValidator = validObj(this.getObject().getSimpleName());
        try {
            if (objectBeforeValidator instanceof People) {
                objectBeforeValidator = validObj(this.getObject().getSimpleName());
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                Query query = session.createQuery(
                        " select * "
                                + " from " + objectBeforeValidator.getClass().getSimpleName() + " ;");
                List<People> peopleList = query.getResultList();
                for (int i = peopleList.size() - 1; i >= peopleList.size() - 3; i--) {
                    People people = peopleList.get(i);
                    people.setAge(people.getAge() + 1);
                    updateOblect(people);
                }

            } else {
                objectBeforeValidator = validObj(this.getObject().getSimpleName());
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                Query query = session.createQuery(
                        " select * "
                                + " from " + objectBeforeValidator.getClass().getSimpleName() + " ;");
                List<Address> addressList = query.getResultList();
                for (int i = addressList.size() - 1; i >= addressList.size() - 3; i--) {
                    Address address = addressList.get(i);
                    address.setHouse(address.getHouse() + 1);
                    updateOblect(address);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOblect(Object object) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public T validObj(String name) {
        switch (name) {
            case "People":
                return (T) new People();
            case "Address":
                return (T) new Address();
            default:
                return (T) new Object();
        }
    }

    public void deleteFirstObject() throws SQLException {
        Session session = null;
        Object objectBeforeValidator = validObj(this.getObject().getSimpleName());
        try {
            if (objectBeforeValidator instanceof People) {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                objectBeforeValidator = validObj(this.getObject().getSimpleName());
                Query query = session.createQuery(
                        " select * "
                                + " from " + objectBeforeValidator.getClass() + " ;");

                List<People> list = query.getResultList();
                if (Objects.nonNull(list) && !list.isEmpty()) {
                    session.delete(list.get(0));
                } else {
                    System.out.println("Таблица People пустая. Нет элементов для удаления.");
                }
                session.getTransaction().commit();
            } else {
                objectBeforeValidator = validObj(this.getObject().getSimpleName());
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                Query query = session.createQuery(
                        " select * "
                                + " from " + objectBeforeValidator.getClass().getSimpleName() + " ;");
                List<Address> list = query.getResultList();
                if (Objects.nonNull(list) && !list.isEmpty()) {
                    session.delete(list.get(0));
                } else {
                    System.out.println("Таблица Address пустая. Нет элементов для удаления.");
                }
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}

