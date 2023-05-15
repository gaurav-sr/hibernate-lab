package com.codeinstance.hibernatelab;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestEmployeeService {

	@Test
	public void testEmployeeCreate() {
	    createEmployee();
	}

    @Test
    public void testEmployeeRead() {
        Session session = CommonFactory.getSessionFactory().openSession();
        createEmployee(session, "245","BigB", "Films");
        EmployeeDAO es = new EmployeeDAO(session);
        Employee employee = es.findByIdUsingEMFind("245");
        Assert.assertNotNull(employee);
    }

    @Test
    public void testEmployeeReadInSession() {
        Session session = CommonFactory.getSessionFactory().openSession();
        createEmployee(session,"245", "BigB", "Films");
        EmployeeDAO es = new EmployeeDAO(session);
        Employee employee = es.findByIdUsingGet("245");
        Assert.assertNotNull(employee);
    }

    @Test
    public void testEmployeeReadUsingGet() {
        Session session = CommonFactory.getSessionFactory().openSession();
        createEmployee(session,"001", "GauravSr", "CS");
        createEmployee(session,"002", "Tejas", "CS");
        createEmployee(session,"003", "Arjun", "IT");
        //Session not cleared, so no DB hit expected
        EmployeeDAO es = new EmployeeDAO(session);
        Employee employee = es.findByIdUsingGet("001");
        Assert.assertNotNull(employee);
    }

    @Test
    public void testEmployeeReadUsingGetSessionClear() {
        Session session = CommonFactory.getSessionFactory().openSession();
        createEmployee(session,"001", "GauravSr", "CS");
        createEmployee(session,"002", "Tejas", "CS");
        createEmployee(session,"003", "Arjun", "IT");
        session.clear();
        //Session cleared, so DB hit expected
        EmployeeDAO es = new EmployeeDAO(session);
        Employee employee = es.findByIdUsingGet("001");
        Assert.assertNotNull(employee);
    }

    @Test
    public void testEmployeeReadUsingLoadSessionClear() {
        Session session = CommonFactory.getSessionFactory().openSession();
        EmployeeDAO es = new EmployeeDAO(session);
        es.createEmployee("001", "GauravSr", "CS");
        es.createEmployee("002", "Tejas", "CS");
        es.createEmployee("003", "Arjun", "IT");
        session.clear();
        //In load, no db hit expected even if the session is cleared
        Employee employee = es.findByIdUsingLoad("001");
        Assert.assertNotNull(employee);
        System.out.println("Accessing non-primary key attribute, a db hit expected");
        Assert.assertNotNull(employee.getEmployeeDept());
    }

    @Test
    public void testEmployeeReadUsingLoad() {
        Session session = CommonFactory.getSessionFactory().openSession();
        EmployeeDAO es = new EmployeeDAO(session);
        es.createEmployee("001", "GauravSr", "CS");
        es.createEmployee("002", "Tejas", "CS");
        es.createEmployee("003", "Arjun", "IT");
        //Session not cleared, so no DB hit expected
        Employee employee = es.findByIdUsingEMFind("001");
        Assert.assertNotNull(employee);

        session.clear();
        //Session not cleared, so DB hit expected
        employee = es.findByIdUsingEMFind("001");
        Assert.assertNotNull(employee);
    }

    /**
     * In non-primary key select, whether session.clear() is called or not,
     * there is always a hit to database
     */
    @Test
    public void testEmployeeReadNonPrimaryKey() {
        Session session = CommonFactory.getSessionFactory().openSession();
        EmployeeDAO es = new EmployeeDAO(session);
        es.createEmployee("245", "BigB", "Films");
        es.createEmployee("001", "GauravS", "CS");
        es.createEmployee("002", "SergeyP", "CS");
        es.createEmployee("003", "GauravS", "CS");
        //session.clear();
        List<Employee> employees = es.findByNameAndDept("GauravS", "CS");
        Assert.assertNotNull(employees);
        Assert.assertEquals(2, employees.size());
    }

    @Test
    public void testEmployeeReadInvalidNonPrimaryKey() {
        Session session = CommonFactory.getSessionFactory().openSession();
        EmployeeDAO es = new EmployeeDAO(session);
        es.createEmployee("245", "BigB", "Films");
        es.createEmployee("001", "GauravS", "CS");
        es.createEmployee("002", "SergeyP", "CS");
        es.createEmployee("003", "GauravS", "CS");

        List<Employee> employees = es.findByNameAndDept("GauravSr", "CS");
        Assert.assertNotNull(employees);
        Assert.assertEquals(0, employees.size());
    }

    private void createEmployee() {
        Session session = CommonFactory.getSessionFactory().openSession();
        EmployeeDAO es = new EmployeeDAO(session);
        es.createEmployee("245", "BigB", "Films");
        session.close();
    }

    private void createEmployee(Session session,String employeeId, String employeeName, String employeeDept) {
        EmployeeDAO es = new EmployeeDAO(session);
        es.createEmployee(employeeId, employeeName, employeeDept);
    }

}
