package com.employee.dao;

import com.employee.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    private EntityManager entityManager;
    @Autowired
    public EmployeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> allEmployeeList() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee",Employee.class);
        return theQuery.getResultList();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return this.entityManager.find(Employee.class,id);
    }

    @Override
    public void createEmployee(Employee employee) {
        this.entityManager.persist(employee);

    }

    @Override
    public Employee deleteEmployeeById(int id) {
        Employee theEmployee = this.entityManager.find(Employee.class,id);
        this.entityManager.remove(theEmployee);
        return theEmployee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return (Employee) this.entityManager.merge(employee);
    }
}
