public class MethodsForTests {
    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public MethodsForTests() {
    }

    @BeforeSuite
    void printBefore(){
        System.out.println("Run BEFORE tests");
    }

    @AfterSuite
    void printAfter(){
        System.out.println("Run AFTER tests");
    }

    @Test(value = 10)
    void test1() {
       System.out.println("TEST 1 - PRIORITY 10");
    }

    @Test(value = 1)
    void test2() {
        System.out.println("TEST 2 - PRIORITY 1");
    }

    @Test
    void test3() {
        System.out.println("TEST 3 - PRIORITY 5");
    }


    void doNotTesting() {
        System.out.println("THIS IS NOT FO TESTS");
    }

}
