package com.jokim.sivillage.api.media.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 36)
    private String mediaCode;
    @Column(nullable = false, length = 2083)
    private String url;
    @Column(nullable = false, length = 255)
    private String name;
    @Column(nullable = false, length = 10)
    private String type;
}
