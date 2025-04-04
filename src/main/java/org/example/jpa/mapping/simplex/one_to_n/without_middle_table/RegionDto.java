package org.example.jpa.mapping.simplex.one_to_n.without_middle_table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegionDto {
    private Long regionId;
    private Long nationId;
    private String regionName;
}
