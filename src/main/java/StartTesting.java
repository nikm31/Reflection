import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StartTesting {
    public void start(Class testClass) {
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

// создаем экземпляр переданного класса

        try {
            Class methodsClass = testClass.getClass();
            Constructor methodsConstructor = testClass.getConstructor();
            methodsConstructor.setAccessible(true);
            MethodsForTests newMethods = (MethodsForTests) methodsConstructor.newInstance();
            before.invoke(newMethods);
            for (Method method : methodsTest) {
                method.invoke(newMethods);
            }
            after.invoke(newMethods);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
