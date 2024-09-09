package com.cpi.is.util;

public class JsonUtil {
    public static String escapeForJson(String input) {
        // Escape special characters for JSON strings
        return input.replace("\"", "") // Escape double quotes
                    .replace("'", "")  // Escape single quotes
                    .replace("\\", "") // Escape backslashes
                    .replace("\n", "")  // Escape newlines
                    .replace("\r", "")  // Escape carriage returns
                    .replace("\t", "")  // Escape tabs
                    .replace(";", "");    // Optionally remove semicolons
    }
    
 // Function to sanitize input string
    public static String sanitize(String input) {
        // Convert to lowercase for consistent keyword removal
        String lowerCaseInput = input.toLowerCase();
        
        // List of SQL-like keywords to remove
        String[] sqlKeywords = {"select", "insert", "update", "delete", "drop", "create", "alter", "truncate"};

        // Remove SQL-like keywords from the input string
        for (String keyword : sqlKeywords) {
            lowerCaseInput = lowerCaseInput.replace(keyword, "");
        }

        // Remove special characters, keeping only alphanumeric and spaces
        String sanitized = lowerCaseInput.replaceAll("[^a-zA-Z0-9\\s]", "");

        return sanitized.trim(); // Trim leading and trailing whitespace
    }
}
