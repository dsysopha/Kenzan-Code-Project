package com.example.kenzancodeproject.dao;

import com.example.kenzancodeproject.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("Employees")
public class EmployeeDataAccessService implements EmployeeDao{
    private static List<Employee> employeeList = new ArrayList<>();
    private int initLoad = 0;

    // Loads in an external JSON into an ArrayList for ease of use
    public void loadJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try     // In the event that something goes wrong with the JSON
        {
            TypeReference<List<Employee>> listType = new TypeReference<List<Employee>>(){};
            employeeList = objectMapper.readValue(new File("employee.json"), listType);
        }
        catch(Exception e){
            System.out.println("Something went wrong with JSON reading");
        }
    }

    // Writes the ArrayList of Employees to the JSON
    public void writeJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try
        {
            objectMapper.writeValue(new File("employee.json"), employeeList);
        }
        catch(Exception e){
            System.out.println("Something went wrong with JSON writing");
        }
    }

    // Retrieves the information for an active employee
    @Override
    public Optional<Employee> getEmployeeById(UUID id) {
        for(int i = 0; i < employeeList.size(); i++){
            if(employeeList.get(i).getId().equals(id) && employeeList.get(i).getActive())
                return Optional.ofNullable(employeeList.get(i));
        }
        return Optional.empty();
    }

    // Adds a new employee to the JSON
    @Override
    public int createNewEmployee(UUID id, Employee employee) {
        Employee empObj = new Employee(id, employee.getFirstName(), employee.getMiddleInitial(), employee.getLastName(), employee.getDateOfBirth(), employee.getDateOfEmployment(), employee.getActive());
        employeeList.add(empObj);
        writeJson();
        return 0;
    }

    // Updates the elements of an Employee object specified by id
    @Override
    public int updateEmployee(UUID id, Employee employee) {
        //Optional<Employee> emp = getEmployeeById(id); ~originally just a getEmployee call, but it prevented bringing an employee back from inactive
        Optional<Employee> emp = Optional.empty();
        for(int i = 0; i < employeeList.size(); i++){   //Searches through ArrayList(the JSON) for the id
            if(employeeList.get(i).getId().equals(id))
                emp = Optional.ofNullable(employeeList.get(i));
        }
        if(emp.isPresent()){ //If the object is found, creates a new instance to overwrite the old object
            int index = employeeList.indexOf(emp.get());
            employeeList.set(index, new Employee(id, employee.getFirstName(), employee.getMiddleInitial(), employee.getLastName(), employee.getDateOfBirth(), employee.getDateOfEmployment(), employee.getActive()));
            writeJson();
        }
        return 0;
    }

    // "deletes" the employee by marking them as inactive, preventing them from appearing via GET endpoints
    @Override
    public int deleteEmployee(UUID id) {
        Optional<Employee> emp = getEmployeeById(id);
        if(emp.isPresent()){
            int index = employeeList.indexOf(emp.get());
            employeeList.get(index).setActive(false);
            writeJson();
        }
        return 0;
    }

    // The first method to be called when accessing this web app, gives all the information needed to perform other actions
    @Override
    public List<Employee> getAllEmployee() {
        //Since getAllEmployee() is called by default when visiting the url, this is where I loaded the JSON
        loadJson();

        // Create another list to hold the employees who are active and returns that new list
        List<Employee> activeList = new ArrayList<>();
        for(int i = 0; i < employeeList.size(); i++){
            if(employeeList.get(i).getActive()) // Checks to see if the employee is active
                activeList.add(employeeList.get(i));
        }
        return activeList;
    }
}
