package com.ctel.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class LoginDetails {
	private String id;
	private String name;
	private LocalDate date;
	private LocalTime logintime;
	private LocalTime logouttime;
	private Long totaltime;

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getLogintime() {
		return logintime;
	}

	public void setLogintime(LocalTime logintime) {
		this.logintime = logintime;
	}

	public LocalTime getLogouttime() {
		return logouttime;
	}

	public void setLogouttime(LocalTime logouttime) {
		this.logouttime = logouttime;
	}

	public Long getTotaltime() {
		return totaltime;
	}

	public void setTotaltime(Long total) {
		this.totaltime = total;
	}

	public LoginDetails(String id, String name, LocalDate date, LocalTime logintime, LocalTime logouttime, Long totaltime) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.logintime = logintime;
		this.logouttime = logouttime;
		this.totaltime = totaltime;
	}

	public LoginDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LoginDetails [id=" + id + ", name=" + name + ", date=" + date + ", logintime=" + logintime
				+ ", logouttime=" + logouttime + ", totaltime=" + totaltime + "]";
	}

}
