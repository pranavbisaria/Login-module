package com.otpverification.OTPemailVerifivation.Repository;

import com.otpverification.OTPemailVerifivation.token.ConformationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("conformationTokenRepository")
public interface ConformationTokenRepository extends CrudRepository<ConformationToken, String> {
    ConformationToken findByConformationToken(String conformationToken);
}