package com.employee.dao;

import com.employee.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> allEmployeeList();

    Employee getEmployeeById(int id);

    void createEmployee(Employee employee);

    Employee deleteEmployeeById(int id);

    Employee updateEmployee(Employee employee);


}
