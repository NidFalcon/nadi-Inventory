package com.cpi.is.util;

public class JsonUtil {
	
    public static String escapeForJson(String input) {
        return input.replace("\"", "\\") 
                    .replace("'", "")
                    .replace("\\", "\\") 
                    .replace("\n", "") 
                    .replace("\r", "") 
                    .replace("\t", "") 
                    .replace(";", "\\;");
    }
    

    public static String sanitize(String input) {

    	String sanitized = input.replaceAll("'", "\\\\'").replaceAll("\"", "\\\\\"");

        return sanitized.trim();
    }
}
