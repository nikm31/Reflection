import java.lang.reflect.*;

public class NotForWork {
    public static void main(String[] args) {

        System.out.println("================ Методы =====================");

        MethodsForTests methodsForTests1 = new MethodsForTests();
        Class clazz = MethodsForTests.class;

        Method[] methods = clazz.getDeclaredMethods(); // методы
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        try {
            Method m1 = clazz.getMethod("test1");
            System.out.println(m1);
            System.out.println(m1.invoke(methodsForTests1));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("============= Модификаторы =================");

        int modifiers = clazz.getModifiers();
        if (Modifier.isPublic(modifiers)) {
            System.out.println(clazz.getSimpleName() + " is " + "public");
        }
        if (Modifier.isAbstract(modifiers)) {
            System.out.println(clazz.getSimpleName() + " is " + "Abstract");
        }
        if (Modifier.isFinal(modifiers)) {
            System.out.println(clazz.getSimpleName() + " is " + "Final");
        }
        System.out.println("=============================================");

        Field[] fieldsClass = clazz.getDeclaredFields();
        for (Field fields : fieldsClass) {
            System.out.println(fields.getType().getName());
        }

        System.out.println("===== Получение доступа к private полям =====");

        try {
            MethodsForTests methodsForTests = new MethodsForTests();
            Field privateFields = MethodsForTests.class.getDeclaredField("a");
            privateFields.setAccessible(true);
            privateFields.set(methodsForTests, 10);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("=========== Конструкторы класса ===========");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("=========== Создание объектов  ===========");

        try {
            Class methodsClass = MethodsForTests.class;
            Constructor methConstructor = MethodsForTests.class.getConstructor();
            methConstructor.setAccessible(true);
            MethodsForTests newMethods = (MethodsForTests) methConstructor.newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("============== Аннотации  ==============");

        Method[] methods2 = clazz.getDeclaredMethods();

        for (Method methodz : methods2) {
            if (methodz.getAnnotation(Test.class) != null) {
                if (methodz.getAnnotation(Test.class).value() > 10) {
                    System.out.println(methodz);
                }
            }
        }
    }
}
