package org.example.jpa.mapping.simplex.one_to_n.multiple_bag.list_to_set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class A1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "a1", cascade = CascadeType.PERSIST)
    private Set<B1> b1s = new HashSet<>();

    @OneToMany(mappedBy = "a1", cascade = CascadeType.PERSIST)
    private Set<C1> c1s = new HashSet<>();
}
