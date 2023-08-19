package com.ctel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class EmployeeController {

	// private static final LocalDateTime Timestamp = null;
	@Autowired
	EmployeeMapper<Employee> employeemapper;

	@Autowired
	LoginMapper<LoginStatus> login;
	
	@Autowired
	LoginDetailsMapper<LoginDetails> loginDetailsMapper;

	@GetMapping("/")
	ResponseEntity<String> hello() {
		return ResponseEntity.ok("Hello Welcome to Employee Management System!");
	}

	@PostMapping("/insert")
	public ResponseEntity<String> save(@RequestBody Employee emp) throws Exception {
		try {
			if ("".equals(emp.getName()) || emp.getSalary() <= 0 || "".equals(emp.getMobile())
					|| "".equals(emp.getEmailId())) {
				// throw new Exception("Empty mandatory field - name");
				return ResponseEntity.ok("OOPS..! Enter valid Details");
			}

			else {

				String lastId = employeemapper.fetchL();
				if(lastId.equals(null)) {
					lastId = "CTIS0001";
				}
				emp.setId(lastId.substring(0, 4) + ((Long.parseLong(lastId.substring(4))) + 1));
				// emp.setPassword(passwordEncoder.encode(emp.getPassword()));
				emp.setCreatedBy(emp.getName());
				employeemapper.create(emp);
				
				/*
				 * LoginStatus status = new LoginStatus(); status.setId(emp.getId());
				 * status.setName(emp.getName()); login.insertStatus(status);
				 */
				
				return ResponseEntity.ok(
						emp.getName() + " Hurray...! Record Inserted successfully\n" + "Your Id is :" + emp.getId());
			}
		} catch (NullPointerException e) {
			return ResponseEntity.ok("Required few more details");
		} catch (Exception e) {
			return ResponseEntity.ok("Mobile or Mail Id already existed");
		}

	}

	@PostMapping("/batchinsert")
	public ResponseEntity<?> batchsave(@RequestBody List<Employee> emplist) {

		// try {
		for (Employee record : emplist) {

			if ("".equals((record).getName()) || (record).getSalary() <= 0) {
				ResponseEntity.ok("OOPS..! Enter valid Details ");
			} else {
				String lastId = employeemapper.fetchL();

				record.setId(lastId.substring(0, 4) + ((Long.parseLong(lastId.substring(4))) + 1));
				// record.setPassword(passwordEncoder.encode(record.getPassword()));
				employeemapper.batchcreate(record);
				System.out.println("Id is :" + record.getId() + "   Name:" + record.getName());

				/*
				 * LoginStatus status = new LoginStatus(); status.setId(record.getId());
				 * status.setName(record.getName()); login.insertStatus(status);
				 */
			}
		}
		return ResponseEntity.ok("Records Added Successfully");
	} /*
		 * catch (NullPointerException e) { return
		 * ResponseEntity.ok("Required few more details"); } catch (Exception e) {
		 * return ResponseEntity.ok("Mobile or Mail Id already existed"); }
		 */
	// }

	

	@PutMapping("update")
	public ResponseEntity<?> update(@RequestBody Employee emp) throws Exception {
		try {
			if ("".equals(emp.getId())) {
				return ResponseEntity.ok("OOPS..! Enter valid id");
			} else {
				System.out.println();
				LoginStatus status = login.fetchLoginstatus(emp.getId());
				if (status ==null) {
					emp.setStatus("Updated");
					emp.setUpdatedOn(java.time.LocalDateTime.now());
					emp.setUpdatedBy(emp.getName());

					employeemapper.update(emp);
					/*
					 * LoginStatus s = new LoginStatus(); s.setName(emp.getName());
					 * s.setId(emp.getId()); login.loginNameUpdate(s);
					 */
					return ResponseEntity.ok("Record updated Successfully.");
				} else {
					return ResponseEntity.ok("Please Login");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok("Data Not Found or Duplicate Entry od Mobile or EmailId");
		}

	}

	@GetMapping("/findbyid")
	public ResponseEntity<?> fetchById(@RequestBody Employee emp) throws Exception {

		if (emp.getId() == null)
			throw new Exception("invalid id");
		LoginStatus status = login.fetchLoginstatus(emp.getId());
		if (status ==null) {
			Employee emp1 = employeemapper.getEmployeeById(emp);
			return ResponseEntity.ok(emp1);
		} else {
			return ResponseEntity.ok("Please Login");
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> fetchAll(@RequestBody Employee emp) throws Exception {
		if (emp.getId() == null)
			throw new Exception("invalid id");
		LoginStatus status = login.fetchLoginstatus(emp.getId());
		if (status ==null) {
			List<Object> obj = new ArrayList<Object>();
			List<Employee> employobj = employeemapper.getAll();
			List<LoginStatus> loginobj = login.getDetails();
			List<LoginDetails> logindetailsobj = loginDetailsMapper.getDetails();
			obj.addAll(employobj);
			obj.addAll(loginobj);
			obj.addAll(logindetailsobj);
			return ResponseEntity.ok().body(obj);
		} else {
			return ResponseEntity.ok("Please Login");
		}
	}

	@PutMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Employee emp) {

		if ("".equals(emp.getId()) || "".equals(emp.getName())) {
			return ResponseEntity.ok("OOPS..! Enter valid id or Name");
		} else {
			LoginStatus status = login.fetchLoginstatus(emp.getId());
			if (status ==null) {
				emp.setStatus("deleted");
				emp.setUpdatedBy(emp.getName());
				emp.setUpdatedOn(java.time.LocalDateTime.now());
				employeemapper.delete(emp);
				return ResponseEntity.ok(emp.getId() + " Record Deleted Successfully.");
			} else {
				return ResponseEntity.ok("Please Login");
			}
		}
	}

	
}
