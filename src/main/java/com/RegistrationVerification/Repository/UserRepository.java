package com.otpverification.OTPemailVerifivation.Repository;

    import com.otpverification.OTPemailVerifivation.Model.UserModel;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.stereotype.Repository;

    @Repository("userRepository")
    public interface UserRepository extends CrudRepository<UserModel, String> {

        UserModel findByEmailIdIgnoreCase(String emailId);

    }
