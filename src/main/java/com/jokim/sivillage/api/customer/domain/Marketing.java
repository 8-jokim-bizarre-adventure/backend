package com.jokim.sivillage.api.customer.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity(name = "marketing")
@ToString
@NoArgsConstructor
public class Marketing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false)
    private Boolean smsAgreement;

    @Column(nullable = false)
    private Boolean emailAgreement;

    @Column(nullable = false)
    private Boolean dmAgreement;

    @Column(nullable = false)
    private Boolean callAgreement;


    @Builder
    public Marketing(
        Long id,
        String uuid,
        Boolean smsAgreement,
        Boolean emailAgreement,
        Boolean dmAgreement,
        Boolean callAgreement
    ) {
        this.id = id;
        this.uuid = uuid;
        this.smsAgreement = smsAgreement;
        this.emailAgreement = emailAgreement;
        this.dmAgreement = dmAgreement;
        this.callAgreement = callAgreement;
    }

}
