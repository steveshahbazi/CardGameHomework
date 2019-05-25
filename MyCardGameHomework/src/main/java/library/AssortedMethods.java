package main.java.library;

/**
 * Created by Steven Shahbazi on 24/05/2019.
 */

public class AssortedMethods {
    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }

    public static int randomIntInRange(int min, int max) {
        return randomInt(max + 1 - min) + min;
    }
}
