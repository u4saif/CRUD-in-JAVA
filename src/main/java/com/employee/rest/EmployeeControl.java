package com.employee.rest;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeControl {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeControl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public List<Employee> getEmplist(){
        return this.employeeService.allEmployeeList();
    }

    @GetMapping("list/{id}")
    public Employee getEmpById(@PathVariable int id){
        return this.employeeService.getEmployeeById(id);
    }

    @PostMapping("/create")
    public String createEmp(@RequestBody Employee emp){
        return this.employeeService.createEmployee(emp);
    }

    @PostMapping("/update")
    public Employee updateEmp(@RequestBody Employee emp){
        return this.employeeService.updateEmployee(emp);
    }

    @DeleteMapping("list/{id}")
    public Employee deleteEmpById(@PathVariable int id){
        return this.employeeService.deleteEmployeeById(id);
    }
}
