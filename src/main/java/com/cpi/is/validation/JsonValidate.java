package com.cpi.is.validation;

import org.json.JSONObject;

import com.cpi.is.exception.InvalidJsonException;

public interface JsonValidate {
	void validateJson(JSONObject jsonObject) throws InvalidJsonException;
}
