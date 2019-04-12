package de.tamino.employee.service;


import de.tamino.employee.payroll.EmployeeNotFoundException;
import de.tamino.employee.persistence.Employee;
import de.tamino.employee.persistence.EmployeeRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public List<Employee> findAllEmployee(){
        var list =  employeeRepo.findAll();
        log.debug("findAllEmployee {}", list);
        return list;
    }

    public Employee findEmployeeById(Long id){
        var employee = employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        log.debug("findEmployeeById {}", employee);
        return employee;
    }

    public Employee updateEmployee(Employee employee){
        var e = employeeRepo.save(employee);
        log.debug("updateEmployee");
        return e;
    }

    public void deleteEmployee(Employee employee){
        log.debug("deleteEmployee");
        employeeRepo.delete(employee);
    }

    public Employee createEmployee(Employee employee){
        var e = employeeRepo.save(employee);
        log.debug("createEmployee", e);
        return e;
    }
}
