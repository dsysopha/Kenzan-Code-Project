/*
 *  The Controller is the layer above the service which handles the HTTP endpoints
 *  It's my first time working with Spring and it's annotations but it does make it easy
 */
package com.example.kenzancodeproject.api;

import com.example.kenzancodeproject.model.Employee;
import com.example.kenzancodeproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/employee")  // the path entered after localhost:8080/
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // For searching for an employee by id, attach the id to the path, this is represented by the "/{id}"
    @GetMapping(path = "/{id}")
    public Optional<Employee> getEmployee(@PathVariable("id") UUID id){
        return employeeService.getEmployee(id);
    }
    @PostMapping
    public void addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }
    @PutMapping(path = "/{id}") // Requires a JSON body with all information but id
    public void updateEmployee(@PathVariable("id") UUID id, @RequestBody Employee employee){
        employeeService.updateEmployee(id, employee);
    }
    @PatchMapping(path = "/{id}") // Used patch to be able to update status without needing anything other than the id
    public void deleteEmployee(@PathVariable("id") UUID id){
        employeeService.deleteEmployee(id);
    }

    @GetMapping // The first call that also loads in the JSON, typically it would be the first thing you see in my mind.
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
}
