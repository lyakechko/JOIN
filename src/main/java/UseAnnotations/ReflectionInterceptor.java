package UseAnnotations;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.util.Objects;

@Interceptor
@ResultSetAnnotation
public class ReflectionInterceptor {

//    @AroundInvoke
//    public Objects printInfoMan(InvocationContext ic) {
//        Annotation[][] parametersAnnotations = ic.getMethod().getParameterAnnotations();
//        for (int i = 0; i < parametersAnnotations.length; i++) {
//            Annotation[] parametersAnnotation = parametersAnnotations[i];
//            for (int k = 0; k < parametersAnnotation.length; k++) {
//                Annotation annotation = parametersAnnotation[k];
//                if (annotation instanceof ResultSetAnnotation) {
//                    if (Parameters.SESSION_TOKEN.equals(((ResultSetAnnotation) annotation).value())) {
//                        session_token = (String) ic.getParameters()[i];
//                    }
//                }
//            }
//        }
//
//
//    }
}