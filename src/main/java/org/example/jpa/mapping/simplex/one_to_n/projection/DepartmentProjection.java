package org.example.jpa.mapping.simplex.one_to_n.projection;

public interface DepartmentProjection {
    String getName();

    CompanyProjection getCompany();
}
