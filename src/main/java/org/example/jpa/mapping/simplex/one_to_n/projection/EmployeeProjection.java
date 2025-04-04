package org.example.jpa.mapping.simplex.one_to_n.projection;

import java.util.List;

public interface EmployeeProjection {
    String getName();
    DepartmentProjection getDepartment();

    List<PetProjection> getPets();
}
