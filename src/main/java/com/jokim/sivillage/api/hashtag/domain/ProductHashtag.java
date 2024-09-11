package com.jokim.sivillage.api.hashtag.domain;


import jakarta.persistence.*;

@Entity
public class ProductHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;

    @Column(nullable = false, length = 36)
    private String productCode;

}
