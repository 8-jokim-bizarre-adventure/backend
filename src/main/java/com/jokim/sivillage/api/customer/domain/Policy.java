package com.jokim.sivillage.api.customer.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity(name = "policy")
@ToString
@NoArgsConstructor
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false)
    private Boolean webUsageRight;

    @Column(nullable = false)
    private Boolean integratedMemberRight;

    @Column(nullable = false)
    private Boolean infoUsageRight;

    @Column(nullable = false)
    private Boolean tomboyInfoUsageRight;

    @Builder
    public Policy(
        Long id,
        String uuid,
        Boolean webUsageRight,
        Boolean integratedMemberRight,
        Boolean infoUsageRight,
        Boolean tomboyInfoUsageRight
    ) {
        this.id = id;
        this.uuid = uuid;
        this.webUsageRight = webUsageRight;
        this.integratedMemberRight = integratedMemberRight;
        this.infoUsageRight = infoUsageRight;
        this.tomboyInfoUsageRight = tomboyInfoUsageRight;
    }
}
