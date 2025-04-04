package org.example.jpa.mapping.simplex.one_to_n.projection;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Pet(String name, Employee employee) {
        this.name = name;
        this.employee = employee;
    }
}
