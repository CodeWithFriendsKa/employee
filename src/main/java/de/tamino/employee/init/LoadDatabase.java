package de.tamino.employee.init;

import de.tamino.employee.persistence.Employee;
import de.tamino.employee.persistence.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    public CommandLineRunner initDatabase(EmployeeRepo employeeRepo){
        return args -> {
            log.info("preload db {}", employeeRepo.save(new Employee("Tamino")));
            log.info("preload db {}", employeeRepo.save(new Employee("Bora")));
            log.info("preload db {}", employeeRepo.save(new Employee("Juli")));
            log.info("preload db {}", employeeRepo.save(new Employee("Kai")));
        };
    }
}
