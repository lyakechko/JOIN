package HibernateUtil;

public interface IHibernateDao<T> {

    Object getAllObjects(Class<T> tClass);

}
