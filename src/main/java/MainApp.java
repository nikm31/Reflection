import java.lang.reflect.*;
import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        MethodsForTests test = new MethodsForTests();
        Class testClass = MethodsForTests.class;
        Method[] allMethods = testClass.getDeclaredMethods();
        List<Method> methodsTest = new ArrayList<>();
        short as = 0;
        short bs = 0;
        Method before = null;
        Method after = null;
        for (Method method : allMethods) {
            if (method.getAnnotation(Test.class) != null) {
                methodsTest.add(method);
            }
            if (method.getAnnotation(BeforeSuite.class) != null) {
                bs++;
                if (bs > 1) {
                    throw new RuntimeException("В ваших методах более одной аннотации BeforeSuite");
                }
                before = method;
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                as++;
                if (as > 1) {
                    throw new RuntimeException("В ваших методах более одной аннотации BeforeSuite");
                }
                after = method;
            }
        }

        methodsTest.sort(Comparator.comparingInt(obj -> obj.getAnnotation(Test.class).value()));

        try {
            before.invoke(test);
            for (Method method : methodsTest) {
                method.invoke(test);
            }
            after.invoke(test);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
