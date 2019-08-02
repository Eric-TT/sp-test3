package com.jt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class drConnectionService {
	@Value("cat.id")
	private String id;
	@Value("cat.work")
	private String work;
	@Value("cat.password")
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "drConnectionService [id=" + id + ", work=" + work + ", password=" + password + "]";
	}

}
