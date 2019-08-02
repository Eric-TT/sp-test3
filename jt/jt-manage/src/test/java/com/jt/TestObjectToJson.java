package com.jt;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;

public class TestObjectToJson {
	private ObjectMapper mapper=new ObjectMapper();
	@Test
	public void Json() {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(1000L)
		.setItemDesc("测试类")
		.setCreated(new Date())
		.setUpdated(new Date());
		try {
			String json = mapper.writeValueAsString(itemDesc);
			System.out.println(json);
			ItemDesc itemDesc2=mapper.readValue(json, ItemDesc.class);
			System.out.println(itemDesc2);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
}
