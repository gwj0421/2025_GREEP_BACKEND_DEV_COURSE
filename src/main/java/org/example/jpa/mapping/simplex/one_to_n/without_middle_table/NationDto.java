package org.example.jpa.mapping.simplex.one_to_n.without_middle_table;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NationDto {
    private Long nationId;
    private List<RegionDto> regions;
}
