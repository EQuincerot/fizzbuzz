package fr.manuqcr.fizzbuzz.helper;

import java.util.regex.Pattern;

public class Helper {
    public static final String PATTERN = "^(\\w| )+$";
    private static final Pattern COMPILED_PATTERN = Pattern.compile(PATTERN);

    /**
     * Check if the value is a not null scrictly positive integer
     *
     * @param value
     * @return true if value is a positive integer
     */
    public static boolean isPositiveInt(Integer value) {
        return value != null && value > 0;
    }

    /**
     * Check if s contains only word characters
     *
     * @return true if s contains only word characters
     */
    public static boolean isNotNullWord(String s) {
        return s != null && COMPILED_PATTERN.matcher(s).matches();
    }
}
