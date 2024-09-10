package com.cpi.is.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.json.JSONObject;

import com.cpi.is.exception.InvalidJsonException;

public class ValidationUtil {

	public static void isValidDate(String dateStr) throws InvalidJsonException{
		try {
        	LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
        	throw new InvalidJsonException("Date Time Pattern must be yyyy-MM-dd");
        }
	}
	
	
	public static void checkFields(String[] requiredFields, JSONObject jsonObject) throws InvalidJsonException {
	    for (String field : requiredFields) {
	        if (!jsonObject.has(field)) {
	            throw new InvalidJsonException("Missing required field: " + field);
	        }
	    }
	}
	
	public static void checkNumber(String quantityStr) throws InvalidJsonException {
	    if (quantityStr.length() <= 0 || quantityStr.length() >= 10) {
	    	throw new InvalidJsonException("Invalid value for field 'quantity'. Must be a non-negative integer within the integer range.");
	    }
	    if (!quantityStr.matches("^[0-9]*$")) {
	    	throw new InvalidJsonException("Quantity Must be a Number and not a String");

	    }
	}
}
