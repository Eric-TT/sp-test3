package com.jt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JDBCConnectionService {
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.driverName}")
	private String driverName;
	@Value("${jdbc.userName}")
	private String userName;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "JDBCConnectionService [url=" + url + ", driverName=" + driverName + ", userName=" + userName + "]";
	}
	
}
