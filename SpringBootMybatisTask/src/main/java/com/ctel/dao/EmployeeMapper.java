package com.ctel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ctel.model.Employee;

@Mapper
public interface EmployeeMapper <T extends Employee>{
	


	void create(Employee emp);

	void batchcreate(Employee batchsave);

	//void insertBatchStatus(LoginStatus batchsave);

	String fetchL();

	int delete(Employee emp);

	Employee getEmployeeById(Employee emp1);

	List<Employee> getAll();

	void update(Employee emp);

	Employee login(Employee emp);

	//Optional<Employee> findByName(String username);

}
