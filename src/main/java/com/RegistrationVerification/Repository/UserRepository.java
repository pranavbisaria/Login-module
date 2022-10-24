package com.RegistrationVerification.Repository;

    import com.RegistrationVerification.Model.UserModel;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.stereotype.Repository;

    @Repository("userRepository")
    public interface UserRepository extends CrudRepository<UserModel, String> {

        UserModel findByEmailIdIgnoreCase(String emailId);

    }
