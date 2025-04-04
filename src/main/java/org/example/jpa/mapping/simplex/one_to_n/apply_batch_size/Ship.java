package org.example.jpa.mapping.simplex.one_to_n.apply_batch_size;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @OneToMany(mappedBy = "ship", cascade = CascadeType.PERSIST)
    @BatchSize(size = 10)
    private List<Crew> crews = new ArrayList<>();

    public Ship(String name) {
        this.name = name;
    }
}
