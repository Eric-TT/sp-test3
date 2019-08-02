package com.db.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static String toJSON(Object obj) {
		String result = null;
		try {
			result = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	public static <T> T toObject(String json, Class<T> target) {
		T t = null;
		try {
			t = mapper.readValue(json, target);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return t;
	}
}
