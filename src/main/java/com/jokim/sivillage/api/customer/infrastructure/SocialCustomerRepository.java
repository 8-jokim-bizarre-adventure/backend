package com.jokim.sivillage.api.customer.infrastructure;

import com.jokim.sivillage.api.customer.domain.SocialCustomer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialCustomerRepository extends JpaRepository<SocialCustomer,Long> {

    Optional<SocialCustomer> findByUuidAndOauthProviderId(String uuid, String OauthProviderId);

}
