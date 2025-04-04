package org.example.jpa.mapping.simplex.one_to_n.without_middle_table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "nation_id")
    private Long nationId;

    private String name;

    public Region(String name) {
        this.name = name;
    }
}
