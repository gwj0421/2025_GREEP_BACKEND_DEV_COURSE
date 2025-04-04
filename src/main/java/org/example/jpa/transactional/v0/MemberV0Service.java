package org.example.jpa.transactional.v0;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberV0Service {
    private final MemberV0Repository memberV0Repository;

    public MemberV0Service(MemberV0Repository memberV0Repository) {
        this.memberV0Repository = memberV0Repository;
    }

    @Transactional
    public void withTransactional() {
        memberV0Repository.save(new MemberV0("gunwoong"));
        memberV0Repository.save(new MemberV0("gunwoong"));
    }

    public void withoutTransactional() {
        memberV0Repository.save(new MemberV0("gunwoong"));
        memberV0Repository.save(new MemberV0("gunwoong"));
    }
}
