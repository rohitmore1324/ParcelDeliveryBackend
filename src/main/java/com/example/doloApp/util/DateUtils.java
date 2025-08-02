package com.example.doloApp.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Check if a date is in the future
     */
    public static boolean isFutureDate(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }
    
    /**
     * Check if a date is today or in the future
     */
    public static boolean isTodayOrFuture(LocalDate date) {
        return date != null && !date.isBefore(LocalDate.now());
    }
    
    /**
     * Format date to string
     */
    public static String formatDate(LocalDate date) {
        return date != null ? date.format(DATE_FORMATTER) : null;
    }
    
    /**
     * Format datetime to string
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATETIME_FORMATTER) : null;
    }
    
    /**
     * Parse date from string
     */
    public static LocalDate parseDate(String dateStr) {
        return dateStr != null ? LocalDate.parse(dateStr, DATE_FORMATTER) : null;
    }
    
    /**
     * Get current date as string
     */
    public static String getCurrentDateAsString() {
        return LocalDate.now().format(DATE_FORMATTER);
    }
    
    /**
     * Get current datetime as string
     */
    public static String getCurrentDateTimeAsString() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }
}
