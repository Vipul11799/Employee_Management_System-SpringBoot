package com.qsp.employee_management_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.employee_management_system.dto.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	Employee findEmployeeByPhone(long phone);

	Employee getEmployeeByEmail(String email);
	
	@Query("SELECT e FROM Employee e WHERE e.employeeAddress=?1")
	List<Employee> empByAddress(String address);
	
	@Query("SELECT e FROM Employee e WHERE e.employeeName=?1")
	List<Employee> empByName(String name);
	
	List<Employee> findEmployeeBySalaryLessThan(double salary);

	List<Employee> findEmployeeBySalaryGreaterThan(double salary);
	
	@Query("SELECT e FROM Employee e WHERE e.salary BETWEEN ?1 AND ?2")
	List<Employee> salBetween(double salary1,double salary2);

	
	List<Employee> getEmployeeByGrade(char grade);
}
