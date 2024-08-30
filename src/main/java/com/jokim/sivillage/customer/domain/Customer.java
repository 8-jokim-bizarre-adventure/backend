package com.jokim.sivillage.customer.domain;

import com.jokim.sivillage.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Getter
@Entity
@ToString
@NoArgsConstructor
public class Customer extends BaseEntity implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 36)
    private String customerUuid;

    @Comment("회원 이메일")
    @Column(nullable = false, length = 50)
    private String email;

    @Comment("회원 비밀번호")
    @Column(nullable = false, length = 64)
    private String password;

    @Comment("회원 이름")
    @Column(nullable = false, length = 50)
    private String name;

    @Comment("회원 생년월일")
    @Column(length = 20)
    private Date birth;

    @Comment("회원 전화번호")
    @Column(nullable = false, length = 20)
    private String phone;

    @Comment("회원상태")
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToOne(mappedBy = "customer")
    private Marketing marketing;

    @OneToOne(mappedBy = "customer")
    private Policy policy;

    @OneToMany(mappedBy = "customer")
    private List<DefaultAddress> defaultAddress;

    @Builder
    public  Customer(
            Long id,
            String customerUuid,
            String email,
            String password,
            String name,
            Date birth,
            String phone,
            State state

    ){
        this.id = id;
        this.customerUuid = customerUuid;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.state = state;
    }


    public void hashPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
