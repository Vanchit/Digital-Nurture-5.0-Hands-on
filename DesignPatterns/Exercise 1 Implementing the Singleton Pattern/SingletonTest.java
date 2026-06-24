public class SingletonTest {

    public static void main(String[] args) {

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Application Started");
        logger2.log("User Logged In");

        // Checking if both references point to same object
        if (logger1 == logger2) {
            System.out.println("Only one Logger object exists.");
        } else {
            System.out.println("Different Logger objects exist.");
        }
    }
}