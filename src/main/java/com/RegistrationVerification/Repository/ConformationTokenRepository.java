package com.RegistrationVerification.Repository;

import com.RegistrationVerification.token.ConformationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("conformationTokenRepository")
public interface ConformationTokenRepository extends CrudRepository<ConformationToken, String> {
    ConformationToken findByConformationToken(String conformationToken);
}