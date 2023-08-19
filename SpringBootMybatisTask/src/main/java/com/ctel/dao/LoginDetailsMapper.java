package com.ctel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ctel.model.LoginDetails;

@Mapper
public interface LoginDetailsMapper<T extends LoginDetails> {

	void saveLoginDetails(LoginDetails lds);

	LoginDetails findlogintimebyId(LoginDetails loginDetails);

	void savelogouttimes(LoginDetails loginDetails);

	List<LoginDetails> getDetails() ;
	

}
