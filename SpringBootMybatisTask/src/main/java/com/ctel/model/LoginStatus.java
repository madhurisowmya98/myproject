package com.ctel.model;

import java.time.LocalDateTime;

public class LoginStatus {
	private int status;
	private String id;
	private String name;
	private LocalDateTime logintime;
	private LocalDateTime logouttime;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getLogintime() {
		return logintime;
	}

	public void setLogintime(LocalDateTime logintime) {
		this.logintime = logintime;
	}

	public LocalDateTime getLogouttime() {
		return logouttime;
	}

	public void setLogouttime(LocalDateTime logouttime) {
		this.logouttime = logouttime;
	}

	@Override
	public String toString() {
		return "LoginStatus [status=" + status + ", id=" + id + ", name=" + name + ", logintime=" + logintime
				+ ", logouttime=" + logouttime + "]";
	}

}
