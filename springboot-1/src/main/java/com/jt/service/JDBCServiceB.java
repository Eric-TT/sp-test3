package com.jt.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "jdbcb")
public class JDBCServiceB {
	private String jdbcName;
	private String jdbcDrvicer;
	public String getJdbcName() {
		return jdbcName;
	}
	public void setJdbcName(String jdbcName) {
		this.jdbcName = jdbcName;
	}
	public String getJdbcDrvicer() {
		return jdbcDrvicer;
	}
	public void setJdbcDrvicer(String jdbcDrvicer) {
		this.jdbcDrvicer = jdbcDrvicer;
	}
	@Override
	public String toString() {
		return "JDBCServiceB [jdbcName=" + jdbcName + ", jdbcDrvicer=" + jdbcDrvicer + "]";
	}

}
