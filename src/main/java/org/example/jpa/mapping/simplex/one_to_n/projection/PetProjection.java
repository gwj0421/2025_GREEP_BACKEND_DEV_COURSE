package org.example.jpa.mapping.simplex.one_to_n.projection;

public interface PetProjection {
    String getName();

    EmployeeProjection getEmployee();
}
