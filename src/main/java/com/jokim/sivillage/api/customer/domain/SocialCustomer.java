package com.jokim.sivillage.api.customer.domain;

import com.jokim.sivillage.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Collection;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class SocialCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false,length = 50)
    private String oauthProviderId;

    @Column(nullable = false,length = 50)
    private String name;


    @Builder
    public SocialCustomer(
        Long id,
        String uuid,
        String oauthProviderId,
        String name
    ){
        this.id = id;
        this.uuid = uuid;
        this.oauthProviderId = oauthProviderId;
        this.name = name;
    }



}
