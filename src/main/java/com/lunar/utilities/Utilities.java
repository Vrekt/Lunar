package com.lunar.utilities;

public class Utilities {

    /**
     * Log information.
     *
     * @param information the information.
     */
    public static void logInformation(String information) {
        System.out.println("[INFO] " + information);
    }

    /**
     * Log an error.
     *
     * @param error the error
     */
    public static void logError(String error) {
        System.out.println("[ERROR] " + error);
    }

    /**
     * Log a warning
     *
     * @param warning the warning
     */
    public static void logWarning(String warning) {
        System.out.println("[WARNING] " + warning);
    }

    /**
     * Log a critical message.
     *
     * @param critical the critical message.
     */
    public static void logCritical(String critical) {
        System.out.println("[CRITICAL] " + critical);
    }

}
