package org.example.jpa.mapping.simplex.one_to_n.with_middle_table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Student> students = new ArrayList<>();

    public Classroom(String name) {
        this.name = name;
    }
}
