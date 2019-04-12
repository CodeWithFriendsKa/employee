package de.tamino.employee.persistence;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Employee(String name) {
        this.name = name;
    }
}
