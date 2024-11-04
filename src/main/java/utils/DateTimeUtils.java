package utils;

public class DateTimeUtils {

    public static void wait(int timeoutInSeconds) {
        try {
            Thread.sleep(timeoutInSeconds * 1000L);
        } catch (InterruptedException e) {
            System.out.println("Exception in Thread.sleep()! Message: " + e.getMessage());
        }
    }
}
