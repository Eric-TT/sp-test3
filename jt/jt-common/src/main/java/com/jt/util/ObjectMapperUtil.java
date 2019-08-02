package com.jt.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
	private static final ObjectMapper mapper=new ObjectMapper();
	
	public static String toJson(Object tager) {
		String result=null;
		try {
			result=mapper.writeValueAsString(tager);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
	public static <T> T toObject(String json,Class<T> classtage) {
		T t=null;
		try {
			t=mapper.readValue(json, classtage);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return t;
	}
}
