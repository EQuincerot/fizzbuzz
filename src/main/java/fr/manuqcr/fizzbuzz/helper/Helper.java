package fr.manuqcr.fizzbuzz.helper;

public class Helper {
    /**
     * Check if the value is a not null scrictly positive integer
     * @param value
     * @return true if value is a positive integer
     */
    public static boolean isPositiveInt(Integer value) {
        return value != null && value > 0;
    }
}
