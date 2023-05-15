package com.codeinstance.hibernatelab;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDAO {

	private final Session session;

	public EmployeeDAO(Session session) {
		this.session = session;
	}

	public void createEmployee(String employeeId, String employeeName, String employeeDept) {
		Employee e = new Employee(employeeId, employeeName, employeeDept);
		Transaction t = session.beginTransaction();
		session.persist(e);
		t.commit();
	}

    public Employee findByIdUsingEMFind(String id) {
        //find method is inherited from EntityManager. Session interface extends EntityManager
        return session.find(Employee.class, id);
    }

    public Employee findByIdUsingGet(String id) {
        return session.get(Employee.class, id);
    }

    public Employee findByIdUsingLoad(String id) {
        return session.getReference(Employee.class, id);
    }

    public List<Employee> findByNameAndDept(String name, String department) {
        String selectQuery = "from employee e where e.employeeName = :name and e.employeeDept = :dept";
        Query<Employee> query = session.createQuery(selectQuery, Employee.class);
        query.setParameter("name", name);
        query.setParameter("dept", department);
        return query.getResultList();
    }
	
}
