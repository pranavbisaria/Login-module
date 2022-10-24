package com.otpverification.OTPemailVerifivation.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="user_id")
        private long userid;

        private String emailId;

        private String password;

        @Column(name="first_name")
        private String firstName;

        @Column(name="last_name")
        private String lastName;

        private boolean isEnabled;

    public long getUserid() {
        return userid;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}