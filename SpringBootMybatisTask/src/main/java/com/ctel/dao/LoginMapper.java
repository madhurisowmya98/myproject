package com.ctel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ctel.model.LoginDetails;
import com.ctel.model.LoginStatus;

@Mapper
public interface LoginMapper<T extends LoginStatus> {

	int loginstatusCodeUpdate(LoginStatus status);

	LoginStatus fetchLoginstatus(String id);

	void insertStatus(LoginStatus emp);

	void logOut(LoginStatus statuslogout);

	List<LoginStatus> getDetails();

	void loginNameUpdate(LoginStatus s);
	
	LoginDetails saveLoginDetails(LoginDetails lds);

}
