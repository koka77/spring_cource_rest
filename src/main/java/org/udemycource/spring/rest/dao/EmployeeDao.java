package org.udemycource.spring.rest.dao;

import org.udemycource.spring.rest.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    public List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployee(int id);

    void deletEmployee(int id);
}
