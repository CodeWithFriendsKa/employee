package de.tamino.employee.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

    @Override
    List<Employee> findAll();
}
