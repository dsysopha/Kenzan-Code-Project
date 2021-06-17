/*  I had not yet fully studied design patterns, but I did stumble across Data Access Objects (DAO) and
 *  that happened to be a pattern. Typically this pattern separates the business layer and the specific persistence layer
 *  I personally like how it splits things up on levels, so like how this file is just a generic interface and that
 *  you can see what it needs, but not how its done so that you can make your own version and not mess up any other
 *  class because they're separate. It also makes things easier to read in my mind.
 */
package com.example.kenzancodeproject.dao;

import com.example.kenzancodeproject.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeDao {
    void loadJson();
    void writeJson();
    Optional<Employee> getEmployeeById(UUID id);
    int createNewEmployee(UUID id, Employee employee);
    default int createNewEmployee(Employee employee){
        UUID id = UUID.randomUUID();
        return createNewEmployee(id, employee);
    }
    int updateEmployee(UUID id, Employee employee);
    int deleteEmployee(UUID id);
    List<Employee> getAllEmployee();
}
