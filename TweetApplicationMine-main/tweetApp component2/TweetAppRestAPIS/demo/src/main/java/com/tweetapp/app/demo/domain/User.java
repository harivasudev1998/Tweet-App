package com.tweetapp.app.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "users")
public class User {
    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @Field(name = "firstName")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;

    @Field(name = "lastName")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    @Field(name = "email")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @Field(name = "loginID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String loginID;

    @Field(name = "password")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    @Field(name = "contactNumber")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String contactNumber;

    @Field(name = "gender")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String gender;

    @Field(name = "status")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;

    @Field(name = "intro")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String intro;

    @Field(name = "registeredDate")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime registeredDate;


    @Field(name = "loggedOutTime")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime loggedOutTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public LocalDateTime getLoggedOutTime() {
        return loggedOutTime;
    }

    public void setLoggedOutTime(LocalDateTime loggedOutTime) {
        this.loggedOutTime = loggedOutTime;
    }

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }

}
