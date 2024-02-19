package com.employee.service;

import com.employee.dao.EmployeeDao;
import com.employee.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements EmployeeInterface {
    private EmployeeDao employeeDao;
    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }



    @Override
    public List<Employee> allEmployeeList() {
        return this.employeeDao.allEmployeeList();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return this.employeeDao.getEmployeeById(id);
    }

    @Override
    @Transactional
    public String createEmployee(Employee employee) {
          this.employeeDao.createEmployee(employee);
          return "User created";
    }

    @Override
    @Transactional
    public Employee deleteEmployeeById(int id) {
        return this.employeeDao.deleteEmployeeById(id);
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        return this.employeeDao.updateEmployee(employee);
    }
}
