package UseAnnotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Map;
import java.util.Objects;

public class ReflectionResult {

    public Map<String, String> checkAndAddValue(Class<?> clazz,Object o){

    }

//    public void printInfoMan(T man) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//
//        Class clazz = man.getClass();
//        Class[] paramTypes = new Class[]{String.class, Object.class};
//        Method searchMethod = clazz.getMethod("executeResultSet", paramTypes);
//        Object[] arguments = new Object[]{"First Calculate", 10};
//        Object object = searchMethod.invoke(man,arguments);
//    }
//
//    public String annotatedField(Class<?> clazz, String fieldName) {
//        try {
//            Field field = clazz.getDeclaredField(fieldName);
//            field.setAccessible(true);
//            if (field.isAnnotationPresent(FieldName.class)) {
//                fieldName = field.getAnnotation(FieldName.class).key();
//            }
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        return fieldName;
//    }
}




