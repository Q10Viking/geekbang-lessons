import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@Configuration
public class AnnotationClassTest {
    public static void main(String[] args) {
        //  [@Configuration]
        Annotation[] annotations = AnnotationClassTest.class.getDeclaredAnnotations();

        Annotation annotation = annotations[0];

        //  interface org.springframework.context.annotation.Configuration
        Class<? extends Annotation> annotationType = annotation.annotationType();
        //  org.springframework.context.annotation.Configuration
        String name = annotationType.getName();

    }
}
