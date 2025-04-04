package org.example.jpa.transactional.v0;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class MemberV0 {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true)
    private String name;

    @Column(name = "create_dt")
    private LocalDateTime createDate;

    public MemberV0() {

    }

    public MemberV0(String name) {
        this.name = name;
        this.createDate = LocalDateTime.now();
    }
}
