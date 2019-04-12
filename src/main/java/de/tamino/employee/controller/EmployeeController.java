package de.tamino.employee.controller;

import de.tamino.employee.payroll.EmployeeResourceAssembler;
import de.tamino.employee.persistence.Employee;
import de.tamino.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Slf4j
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeResourceAssembler employeeResourceAssembler;

    @RequestMapping(method = RequestMethod.GET, value = "/employee/{id}")
    public Resource<Employee> getEmployeeById(@PathVariable(name = "id") Long id){
        var employee = employeeService.findEmployeeById(id);
        var resource = employeeResourceAssembler.toResource(employee);
        log.debug("getEmployeeById {}", resource);
        return resource;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employee")
    public Resources<Resource<Employee>> getAllEmployee(){
        var employees = employeeService
                .findAllEmployee()
                .stream()
                .map(e -> employeeResourceAssembler.toResource(e))
                .collect(Collectors.toList());
        var resources = new Resources<>(
                employees,
                linkTo(methodOn(EmployeeController.class).getAllEmployee()).withSelfRel()
        );
        log.debug("getAllEmployee {}", resources);
        return resources;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/employee")
    public Resource<Employee> postEmployee(@RequestBody Employee employee){
        var e = employeeService.createEmployee(employee);
        var resource = employeeResourceAssembler.toResource(e);
        log.debug("postEmployee {}", resource);
        return resource;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/employee")
    public void deleteEmployee(@RequestBody Employee employee){
        employeeService.deleteEmployee(employee);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/employee")
    public Resource<Employee> putEmployee(@RequestBody Employee employee){
        var e = employeeService.updateEmployee(employee);
        var resource = employeeResourceAssembler.toResource(e);
        log.debug("putEmployee {}", resource);
        return resource;
    }
}
