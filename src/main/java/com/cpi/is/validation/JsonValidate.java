package com.cpi.is.validation;

import org.json.JSONException;
import org.json.JSONObject;

public interface JsonValidate {
	void validateJson(JSONObject jsonObject) throws JSONException;
}
