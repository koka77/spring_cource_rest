package org.udemycource.spring.rest.service;

import org.udemycource.spring.rest.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployee(int id);

    void deletEmployee(int id);
}
