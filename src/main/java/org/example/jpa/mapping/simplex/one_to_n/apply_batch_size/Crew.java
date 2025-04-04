package org.example.jpa.mapping.simplex.one_to_n.apply_batch_size;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Entity
@Getter
@NoArgsConstructor
@BatchSize(size = 10)
public class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;

    public Crew(String name, Ship ship) {
        this.name = name;
        this.ship = ship;
    }
}
