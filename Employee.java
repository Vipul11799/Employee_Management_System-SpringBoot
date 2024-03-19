package com.qsp.employee_management_system.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	
	@NotBlank(message = "Name can not be Blank")
	@NotNull(message = "Name can not be Null")
	private String employeeName;
	
	@Min(value = 6000000000l)
	@Max(value=9999999999l)
	@Column(unique = true)
	private long phone;
	//this valaidation we can use when phone number is in String type
	//@Pattern(regexp = "[6-9][0-9]{9}",message = "Invalid Phone Number")
	
	@NotBlank(message = "Address can not be Blank")
	@NotNull(message = "Address can not be Null")
	private String employeeAddress;
	
	@NotBlank(message = "Email can not be Blank")
	@NotNull(message = "Email can not be Null")
	@Email(regexp = "[a-z0-9._$]+@[a-z]+\\.[a-z]{2,3}",message = "Invalid Email")
	@Column(unique = true)
	private String email;
	
	@Min(value = 1)
	private double salary;
	private char grade;
}
