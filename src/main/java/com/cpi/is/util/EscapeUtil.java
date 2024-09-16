package com.cpi.is.util;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class EscapeUtil {

	public static JSONArray escapeQuotes(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.length(); i++) {
			jsonArray.put(i, escapeQuotes((JSONObject) jsonArray.get(i)));
		}
		return jsonArray;
	}

	public static JSONObject escapeQuotes(JSONObject jsonObject) {
		Iterator<String> keys = jsonObject.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			Object obj = null;
			try {
				obj = jsonObject.get(key);
			} catch (Exception e) {
				continue;
			} finally {
				if (obj != null && obj instanceof String) {
					String value = obj.toString();
					if (value.contains("'")) {
						value = value.replaceAll("'", "&apos;");
						jsonObject.put(key, value);
					} else if (value.contains("\"")) {
						value = value.replaceAll("\"", "&quot;");
						jsonObject.put(key, value);
					}
				}
			}
		}
		return jsonObject;
	}

}