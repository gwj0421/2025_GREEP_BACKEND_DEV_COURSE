package org.example.jpa.mapping.simplex.one_to_n.without_middle_table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Nation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "nation_id")
    private List<Region> regions = new ArrayList<>();

    public Nation(String name) {
        this.name = name;
    }
}