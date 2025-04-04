package org.example.jpa.transactional.v0;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberV0Repository extends JpaRepository<MemberV0,Long> {
}
