package Database_Сalls;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.*;
import java.util.*;

import Connection.ConnectionResourceBundle;
import DBO.Address;
import DBO.People;
import UseAnnotations.ResultSetAnnotation;

public class CreateQueryForDB<T> {

    T typeObject;

    public T getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(T typeObject) {
        this.typeObject = typeObject;
    }

    Connection connection = ConnectionResourceBundle.connection;

    public int executePreparedStatement(String sql, Map<Integer, Object> param) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            int index = 1;
            for (Map.Entry<Integer, Object> entry : param.entrySet()) {
                ps.setObject(index, param.get(index));
                index++;
            }
            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }

    }

    public Boolean executeStatement(String sql) {
        try {
            Statement st = connection.createStatement();
            return st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @ResultSetAnnotation
    public T executeResultSet(String sql, @ResultSetAnnotation T t) throws SQLException, NoSuchFieldException, IllegalAccessException {
        if (t instanceof Address) {
            sql = String.format(sql, t.getClass().getSimpleName());
        }
        if (t instanceof People) {
            sql = String.format(sql, t.getClass().getSimpleName());
        }
        Statement st = connection.createStatement();
        Map<String, String> param = new HashMap<>();
        Class<?> clazz = this.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Annotation[] ann = method.getAnnotations();
            for (Annotation annotation : ann) {
                if (annotation.annotationType().isAnnotationPresent(ResultSetAnnotation.class)) {
                    if (t instanceof Address) {
                        Field field = t.getClass().getDeclaredField("id");
                        field.setAccessible(true);
                        sql = String.format(sql, t.getClass().getSimpleName(), field.get(t));
                    }
                    if (t instanceof People) {
                        Field field = t.getClass().getDeclaredField("id");
                        field.setAccessible(true);
                        sql = String.format(sql, t.getClass().getSimpleName(), field.get(t));
                        sql = String.format(sql, t.getClass().getSimpleName());
                    }
                    Class[] params = {int.class, String.class};
                    clazz.getMethod(method.getName(), params);
                    Parameter[] parameter = method.getParameters();
                    for (Parameter p : parameter) {
                        if (p.isAnnotationPresent(ResultSetAnnotation.class)) {

                        }
                    }
                }
            }
        }

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation ann : annotations) {
                if (ann.equals(ResultSetAnnotation.class)) {

                }

            }
            if (sql.contains(field.getName())) {

            }


            ResultSet resultSet = null;
            if (t instanceof Address) {
                sql = String.format(sql, t.getClass().getSimpleName());
                resultSet = st.executeQuery(sql);
            }
            if (t instanceof People) {
                sql = String.format(sql, t.getClass().getSimpleName());
                resultSet = st.executeQuery(sql);
            }
            return null;
            //return st.executeQuery();
        }

        public void executeBatch (List < String > sqlList, T object){
            Statement statement;
            try {
                connection.setAutoCommit(false);
                statement = connection.createStatement();
                for (String sql : sqlList) {
                    sql = String.format(sql, object.getClass().getSimpleName());
                    statement.addBatch(sql);
                }
                statement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                e.printStackTrace();
            }
        }

        public void executeProcedure (String sql, Map < Integer, Object > paramIn,
                Map < Integer, Integer > paramOut) throws SQLException {
            CallableStatement callableStatement = connection.prepareCall(sql);

            int index = 1;
            for (Map.Entry<Integer, Object> entry : paramIn.entrySet()) {
                callableStatement.setObject(index, entry.getValue());
                index++;
            }
            for (Map.Entry<Integer, Integer> entry : paramOut.entrySet()) {
                callableStatement.registerOutParameter(index, entry.getValue());
                index++;
            }
            callableStatement.execute();
        }

    }
