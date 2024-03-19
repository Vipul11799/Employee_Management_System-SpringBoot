package com.qsp.employee_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.employee_management_system.dao.EmployeeDao;
import com.qsp.employee_management_system.dto.Employee;
import com.qsp.employee_management_system.exception.AddressNotFound;
import com.qsp.employee_management_system.exception.IdNotFound;
import com.qsp.employee_management_system.exception.MailIdNotFound;
import com.qsp.employee_management_system.exception.NoDataAvailable;
import com.qsp.employee_management_system.exception.PhoneNotFound;
import com.qsp.employee_management_system.util.ResponseStructure;

//Service is a pkg where we writing our business logic
//@Service developer get to know in this class we wrote business logic

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	ResponseStructure<Employee> structure = new ResponseStructure<>();

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {

		double salary = employee.getSalary();
		if (salary < 10000) {
			employee.setGrade('D');
		} else if (salary >= 10000 && salary < 20000) {
			employee.setGrade('C');
		} else if (salary >= 20000 && salary < 40000) {
			employee.setGrade('B');
		} else {
			employee.setGrade('A');
		}
		structure.setMessage("Employee Save Sucessful");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(employeeDao.saveEmployee(employee));
		// return structure;

		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Employee>> featchEmployee(int id) {
		Employee employee = employeeDao.featchEmployee(id);
		if (employee != null) {
			structure.setMessage("Employee Details Found Sucessful");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
//			return structure;

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {
//			structure.setMessage("Employee with given id is Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;

			throw new IdNotFound("Employee with given id is not found");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> fetchAllEmployee() {
		ResponseStructure<List<Employee>> listStructure = new ResponseStructure<>();
		List<Employee> list = employeeDao.fetchAllEmployee();
		if (list.isEmpty()) {
//			listStructure.setMessage("No Data Available");
//			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			listStructure.setData(list);
//			return listStructure;

			throw new NoDataAvailable("NO data Available");
		} else {
			listStructure.setMessage("Employee Details Found Sucessful");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(list);
			// return listStructure;

			return new ResponseEntity<ResponseStructure<List<Employee>>>(listStructure, HttpStatus.FOUND);

		}
	}

	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(int id) {
		Employee employee = employeeDao.featchEmployee(id);
		if (employee != null) {
			structure.setMessage("Employee Deleted Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employeeDao.deleteEmployee(employee));
			// return structure;

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
//			structure.setMessage("Employee with given id is Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;

			throw new NoDataAvailable("NO data Available");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(int id, Employee employee) {
		Employee dbemployee = employeeDao.featchEmployee(id);
		if (dbemployee != null) {
			employee.setEmployeeId(id);
			double salary = employee.getSalary();
			if (salary < 10000) {
				employee.setGrade('D');
			} else if (salary >= 10000 && salary < 20000) {
				employee.setGrade('C');
			} else if (salary >= 20000 && salary < 40000) {
				employee.setGrade('B');
			} else {
				employee.setGrade('A');
			}
			structure.setMessage("Salary Update Sucessful");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employeeDao.saveEmployee(employee));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
//			structure.setMessage("Employee with given id is Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(dbemployee);
//			return structure;

			throw new NoDataAvailable("NO data Available");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> saveAllEmployee(List<Employee> list) {
		for (Employee employee : list) {
			double salary = employee.getSalary();
			if (salary < 10000) {
				employee.setGrade('D');
			} else if (salary >= 10000 && salary < 20000) {
				employee.setGrade('C');
			} else if (salary >= 20000 && salary < 40000) {
				employee.setGrade('B');
			} else {
				employee.setGrade('A');
			}
		}
		List<Employee> list2 = employeeDao.saveAllEmployee(list);
		ResponseStructure<List<Employee>> listStructure = new ResponseStructure<>();
		listStructure.setMessage("Saved all the data Sucessfully");
		listStructure.setStatus(HttpStatus.CREATED.value());
		listStructure.setData(employeeDao.saveAllEmployee(list2));
		return new ResponseEntity<ResponseStructure<List<Employee>>>(listStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Employee>> updatePhone(int id, long phone) {

		Employee employee = employeeDao.featchEmployee(id);
		if (employee != null) {
			employee.setPhone(phone);

			structure.setMessage("Update Phone Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employeeDao.saveEmployee(employee));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
//			structure.setMessage("Employee with given id is Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;

			throw new IdNotFound("Employee with given id is not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmail(int id, String email) {
		Employee employee = employeeDao.featchEmployee(id);
		if (employee != null) {
			employee.setEmail(email);

			structure.setMessage("Update email Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employeeDao.saveEmployee(employee));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
//			structure.setMessage("Employee with given id is Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;

			throw new IdNotFound("Employee with given id is not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateSalary(int id, double salary) {
		Employee employee = employeeDao.featchEmployee(id);

		if (employee != null) {
			employee.setSalary(salary);
			double salary1 = employee.getSalary();
			if (salary1 < 10000) {
				employee.setGrade('D');
			} else if (salary1 >= 10000 && salary < 20000) {
				employee.setGrade('C');
			} else if (salary1 >= 20000 && salary < 40000) {
				employee.setGrade('B');
			} else {
				employee.setGrade('A');
			}
			structure.setMessage("Update Salary Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employeeDao.saveEmployee(employee));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
//			structure.setMessage("Employee with given id is Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;

			throw new IdNotFound("Employee with given id is not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> findByPhone(int phone) {
		Employee employee = employeeDao.findByPhone(phone);
		if (employee != null) {
			structure.setMessage("Employee Found Sucessfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {
//			structure.setMessage("Employee with given phone is Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;

			throw new PhoneNotFound("Phone number not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> findByEmail(String email) {
		Employee employee = employeeDao.findByEmail(email);
		if (employee != null) {
			structure.setMessage("Employee Found Sucessfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else {
//			structure.setMessage("Employee with given email is Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;

			throw new MailIdNotFound("Mail id Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findByAddress(String address) {

		List<Employee> employee = employeeDao.findByAddress(address);
		ResponseStructure<List<Employee>> listStructure = new ResponseStructure<>();
		if (employee.isEmpty()) {
//			listStructure.setMessage("Employee with given address is Not Found");
//			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			listStructure.setData(employee);
//			return listStructure;

			throw new AddressNotFound("Address Not Found");

		} else {

			listStructure.setMessage("Employee Found Sucessfully");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(listStructure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findByName(String name) {
		List<Employee> employee = employeeDao.findByName(name);
		ResponseStructure<List<Employee>> listStructure = new ResponseStructure<>();
		if (employee.isEmpty()) {
//			listStructure.setMessage("Employee with given name is Not Found");
//			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			listStructure.setData(employee);
//			return listStructure;

			throw new NoDataAvailable("NO data Available");

		} else {

			listStructure.setMessage("Employee Found Sucessfully");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(listStructure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salLessThan(double salary) {
		List<Employee> employee = employeeDao.salLessThan(salary);
		ResponseStructure<List<Employee>> listStructure = new ResponseStructure<>();
		if (employee.isEmpty()) {
//			listStructure.setMessage("Employee with less salary than given salary is Not Found");
//			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			listStructure.setData(employee);
//			return listStructure;
			throw new NoDataAvailable("NO data Available");

		} else {

			listStructure.setMessage("Employee Found Sucessfully");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(listStructure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salGreaterThan(double salary) {
		List<Employee> employee = employeeDao.salGreaterThan(salary);
		ResponseStructure<List<Employee>> listStructure = new ResponseStructure<>();
		if (employee.isEmpty()) {
//			listStructure.setMessage("Not Found");
//			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			listStructure.setData(employee);
//			return listStructure;

			throw new NoDataAvailable("NO data Available");

		} else {

			listStructure.setMessage("Employee Found Sucessfully");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(listStructure, HttpStatus.FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salBetween(double salary1, double salary2) {
		List<Employee> employee = employeeDao.salBetween(salary1, salary2);
		ResponseStructure<List<Employee>> listStructure = new ResponseStructure<>();
		if (employee.isEmpty()) {
//			listStructure.setMessage("Not Found");
//			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			listStructure.setData(employee);
//			return listStructure;

			throw new NoDataAvailable("NO data Available");

		} else {

			listStructure.setMessage("Employee Found Sucessfully");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(listStructure, HttpStatus.FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findByGrade(char grade) {
		List<Employee> employee = employeeDao.findByGrade(grade);
		ResponseStructure<List<Employee>> listStructure = new ResponseStructure<>();
		if (employee.isEmpty()) {
//			listStructure.setMessage("Not Found");
//			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			listStructure.setData(employee);
//			return listStructure;

			throw new NoDataAvailable("NO data Available");

		} else {

			listStructure.setMessage("Employee Found Sucessfully");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(employee);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(listStructure, HttpStatus.FOUND);		}
	}
}
