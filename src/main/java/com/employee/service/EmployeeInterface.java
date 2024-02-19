package com.employee.service;

import com.employee.entity.Employee;

import java.util.List;

public interface EmployeeInterface {
    List<Employee> allEmployeeList();

    Employee getEmployeeById(int id);

    String createEmployee(Employee employee);

    Employee deleteEmployeeById(int id);

    Employee updateEmployee(Employee employee);
}
