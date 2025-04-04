package org.example.jpa.mapping.simplex.n_to_one.lazy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Caffeine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

    public Caffeine(String name, Coffee coffee) {
        this.name = name;
        this.coffee = coffee;
    }
}
