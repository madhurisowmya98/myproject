package com.ctel.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctel.dao.EmployeeMapper;
import com.ctel.dao.LoginDetailsMapper;
import com.ctel.dao.LoginMapper;
import com.ctel.model.Employee;
import com.ctel.model.LoginDetails;
import com.ctel.model.LoginStatus;

@RestController
public class LoginController {

	@Autowired
	EmployeeMapper<Employee> employeemapper;

	@Autowired
	LoginMapper<LoginStatus> login;

	// LoginDetails loginDetails;

	@Autowired
	LoginDetailsMapper<LoginDetails> loginDetailsMapper;

	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestBody Employee emp) throws Exception {

		if ("".equals(emp.getId()) || "".equals(emp.getMobile())) {
			return ResponseEntity.ok("Fields can't be empty");
		} else {

			Employee em = employeemapper.login(emp);
			if (em != null) {
				LoginStatus status = login.fetchLoginstatus(emp.getId());

				if (status!=null) {
					return ResponseEntity.ok("you have already loggedin");
				} else {
					LoginStatus obj = new LoginStatus();
					obj.setId(em.getId());
					obj.setName(em.getName());
					obj.setLogintime(java.time.LocalDateTime.now());
					login.loginstatusCodeUpdate(obj);

					LoginDetails loginDetails = new LoginDetails();
					loginDetails.setId(em.getId());
					loginDetails.setName(em.getName());
					loginDetails.setDate(LocalDate.now());
					loginDetails.setLogintime(LocalTime.now());

					loginDetailsMapper.saveLoginDetails(loginDetails);

					return ResponseEntity.ok("Login Successful");
				}
			} else
				return ResponseEntity.ok("Invalid Details");
		}

	}

	@PutMapping("/logout")
	public ResponseEntity<?> logOut(@RequestBody LoginDetails logout) {
		
		//getting object from db using id  conditions on date, logout=null,  
		LoginDetails logout2 = loginDetailsMapper.findlogintimebyId(logout);
		
		//saving current time to set logout
		LocalTime logoutset = LocalTime.now();
		
		//updating loginstatus table to know active or inactive
		LoginStatus statuslogout = new LoginStatus();
		statuslogout.setId(logout.getId());
		statuslogout.setLogouttime(LocalDateTime.now());
		login.logOut(statuslogout);
		
		
		//getting login time from db
		LocalTime loginset = logout2.getLogintime();
		
		//set logout time
		logout.setLogouttime(logoutset);
		
		//calculate total time
		long total1 = loginset.until(logoutset, ChronoUnit.SECONDS);
		
		//set total time
		logout.setTotaltime(total1);
		loginDetailsMapper.savelogouttimes(logout);
		
		long hrs = total1/3600;
		long result = (total1%3600);
		long min= (result/60);
		long sec = total1%3600;
		return ResponseEntity.ok("Logout Successful "+hrs+": "+min+": "+sec);
	}
}
