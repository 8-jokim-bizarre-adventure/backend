package com.jokim.sivillage.api.customer.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity(name = "address")
@ToString
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 36)
    private String addressCode;

    @Column(length = 50)
    private String addressName;

    @Column(length = 50)
    private String recipient;

    @Column(length = 20)
    private String phone;

    @Column(length = 20)
    private String zipCode;

    @Column(length = 255)
    private String address;

    private String addressDetail;

    @Column(length = 100)
    private String message;

    @ManyToOne
    private CustomerAddressDefaultList customerAddressDefaultList;

    @Builder
    public Address(
        Long id,
        String addressCode,
        String addressName,
        String recipient,
        String phone,
        String zipCode,
        String address,
        String addressDetail,
        String message
    ) {
        this.id = id;
        this.addressCode = addressCode;
        this.addressName = addressName;
        this.recipient = recipient;
        this.phone = phone;
        this.zipCode = zipCode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.message = message;

    }
}
