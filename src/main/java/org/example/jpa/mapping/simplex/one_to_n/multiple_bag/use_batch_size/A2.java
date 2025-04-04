package org.example.jpa.mapping.simplex.one_to_n.multiple_bag.use_batch_size;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class A2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "a", cascade = CascadeType.PERSIST)
    @OrderColumn(name = "b_order")
    private List<B2> bs = new ArrayList<>();

    @OneToMany(mappedBy = "a", cascade = CascadeType.PERSIST)
    @OrderColumn(name = "c_order")
    private List<C2> cs = new ArrayList<>();
}
