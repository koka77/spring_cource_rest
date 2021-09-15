package org.udemycource.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.udemycource.spring.rest.entity.Employee;
import org.udemycource.spring.rest.exception_handling.EmployeeIncorrectData;
import org.udemycource.spring.rest.exception_handling.NoSuchEmployeeException;
import org.udemycource.spring.rest.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);

        if (employee == null){
            throw new NoSuchEmployeeException("There is no employee with ID " + id +
                    " in Databaase");
        }

        return employee;
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleEsception(NoSuchEmployeeException exception) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleEsception(Exception exception) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);

    }

}
