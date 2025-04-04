package org.example.jpa.mapping.simplex.one_to_n.multiple_bag.problem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class A {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "a", cascade = CascadeType.PERSIST)
    private List<B> bs = new ArrayList<>();

    @OneToMany(mappedBy = "a", cascade = CascadeType.PERSIST)
    private List<C> cs = new ArrayList<>();
}
