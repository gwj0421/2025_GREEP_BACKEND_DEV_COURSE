package org.example.jpa.mapping.simplex.one_to_n.projection;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.PERSIST)
    private List<Pet> pets = new ArrayList<>();

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }
}