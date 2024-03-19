package com.qsp.employee_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.qsp.employee_management_system.dto.Employee;
import com.qsp.employee_management_system.repo.EmployeeRepo;

@Repository
public class EmployeeDao {
	@Autowired
	EmployeeRepo employeeRepo;

	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	public Employee featchEmployee(int id) {

		// return employeeRepo.findById(id).get(); //using this we can get
		// NoSuchElementException

		// findById returns option type of data ,optional class present in java.util pkg
		// in this class there is 2 mothods 1.isEmpty() 2.isPresent() boths return type
		// is boolean

		// to avoid exception we can write like this

		Optional<Employee> optional = employeeRepo.findById(id);
		if (optional.isPresent()) {
			// get() if value is present it returns value
			return optional.get();
		}
		return null;

		// using isEmpty() method
//		if (optional.isEmpty()) {
//			return null;
//		}
//		return optional.get();

	}

	public List<Employee> fetchAllEmployee() {
		return employeeRepo.findAll();
	}

	// for old implimentation(without Service)

//	public Employee deleteEmployee(int id) {
//		//there are 2 mothods to delete record 1.delete(Entity e) 2. deleteById(id)
//		
//		
//		Optional<Employee> optional= employeeRepo.findById(id);
//		//deleteById(id) method
////		if (optional.isPresent()) {
////			employeeRepo.deleteById(id);
////			return optional.get();
////		}
////		return null;
//		
//		//or
//		//delete(Entity e)
//		
//		if (optional.isPresent()) {
//			Employee employee=optional.get();
//			employeeRepo.delete(employee);
//			return employee;
//		}
//		return null;
//	}

	//with Service
	public Employee deleteEmployee(Employee employee) {
		employeeRepo.delete(employee);
		return employee;
	}
	
	//=========no need because of Service=======================
//	public Employee updateEmployee(int id, Employee employee) {
//		Optional<Employee> optional = employeeRepo.findById(id);
//		if (optional.isPresent()) {
//			employee.setEmployeeId(id);
//			return employeeRepo.save(employee);
//		}
//		return null;
//	}

	public List<Employee> saveAllEmployee(List<Employee> employees) {
		return employeeRepo.saveAll(employees);
	}

//	public Employee updatePhone(int id, long phone) {
//		Optional<Employee> optional = employeeRepo.findById(id);
//		if (optional.isPresent()) {
//			Employee employee = optional.get();
//			employee.setPhone(phone);
//			return employeeRepo.save(employee);
//		}
//		return null;
//	}

//	public Employee updateEmail(int id, String email) {
//		Optional<Employee> optional = employeeRepo.findById(id);
//		if (optional.isPresent()) {
//			Employee employee = optional.get();
//			employee.setEmail(email);
//			return employeeRepo.save(employee);
//		}
//		return null;
//	}

//	public Employee updateSalary(int id, double salary) {
//		Optional<Employee> optional = employeeRepo.findById(id);
//		if (optional.isPresent()) {
//			Employee employee = optional.get();
//			employee.setSalary(salary);
//			return employeeRepo.save(employee);
//		}
//		return null;
//	}

	public Employee findByPhone(int phone) {
		return employeeRepo.findEmployeeByPhone(phone);
	}

	public Employee findByEmail(String email) {
		return employeeRepo.getEmployeeByEmail(email);
	}

	public List<Employee> findByAddress(String address) {
		return employeeRepo.empByAddress(address);
	}

	public List<Employee> findByName(String name) {
		return employeeRepo.empByName(name);
	}

	public List<Employee> salLessThan(double salary) {
		return employeeRepo.findEmployeeBySalaryLessThan(salary);
	}

	public List<Employee> salGreaterThan(double salary) {
		return employeeRepo.findEmployeeBySalaryGreaterThan(salary);
	}

	public List<Employee> salBetween(double salary1, double salary2) {
		return employeeRepo.salBetween(salary1, salary2);
	}

	public List<Employee> findByGrade(char grade) {
		return employeeRepo.getEmployeeByGrade(grade);
	}
}
