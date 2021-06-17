/*
 *  The next layer up from the DAO, usually handles business requirements
 *  In this case, the qualifier "Employees" is connected to my EmployeeDataAccessService's Repository("Employees")
 *  which connects the two classes together. Really helpful for when you need to switch classes if you have multiple
 */
package com.example.kenzancodeproject.service;

import com.example.kenzancodeproject.dao.EmployeeDao;
import com.example.kenzancodeproject.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(@Qualifier("Employees") EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Optional<Employee> getEmployee(UUID id){
        return employeeDao.getEmployeeById(id);
    }

    public int addEmployee(Employee employee){
        return employeeDao.createNewEmployee(employee);
    }

    public int updateEmployee(UUID id, Employee employee){
        return employeeDao.updateEmployee(id, employee);
    }

    public int deleteEmployee(UUID id){
        return employeeDao.deleteEmployee(id);
    }

    public List<Employee> getAllEmployees(){
        return employeeDao.getAllEmployee();
    }
}
