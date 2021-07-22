import java.lang.reflect.*;
import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        MethodsForTests tests = new MethodsForTests();
        StartTesting startTesting = new StartTesting();
        startTesting.start(tests.getClass());
    }



}

