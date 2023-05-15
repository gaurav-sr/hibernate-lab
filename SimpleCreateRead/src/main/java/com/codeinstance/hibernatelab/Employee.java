package com.codeinstance.hibernatelab;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="employee")
public class Employee {

	@Id
	@Column(name="emp_id")
	private String employeeId;
	@Column(name="emp_name")
    private String employeeName;
	@Column(name = "emp_dept")
    private String employeeDept;
    
    public Employee() {
    	
    }
    
    public Employee(String employeeId, String employeeName, String employeeDept) {
    	this.employeeId = employeeId;
    	this.employeeName = employeeName;
    	this.employeeDept = employeeDept;
    }
    
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeDept() {
		return employeeDept;
	}
	public void setEmployeeDept(String employeeDept) {
		this.employeeDept = employeeDept;
	}    
}

