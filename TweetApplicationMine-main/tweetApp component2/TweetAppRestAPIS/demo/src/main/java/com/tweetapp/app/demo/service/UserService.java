package com.tweetapp.app.demo.service;

import com.tweetapp.app.demo.domain.ResetPassword;
import com.tweetapp.app.demo.domain.User;
import com.tweetapp.app.demo.exception.DBOPerationException;
import com.tweetapp.app.demo.exception.InvalidDetailsException;
import com.tweetapp.app.demo.exception.UserAlreadyExists;
import com.tweetapp.app.demo.exception.UserDoesNotExists;
import com.tweetapp.app.demo.repository.UserRepository;
import com.tweetapp.app.demo.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getSearchUsers(String username) {
        return userRepository.findByLoginIDS(username);
    }

    public User loginUser(String loginID, String password) throws UserDoesNotExists, InvalidDetailsException {
        try {
            if (checkUserId(loginID)) {
                User user = userRepository.findByLoginID(loginID);
                byte[] decodedBytes = Base64.getDecoder().decode(user.getPassword());
                String decodedString = new String(decodedBytes);
                if (decodedString.equals(password)) {
                    user.setLoggedOutTime(null);
                    user.setStatus(Constants.Active);
                    user = userRepository.save(user);
                    return user;
                }
                throw new InvalidDetailsException(Constants.INVALID_LOGIN_DETAILS);
            } else
                throw new UserDoesNotExists(Constants.USER_DOES_NOT_EXISTS);
        } catch (InvalidDetailsException exception) {
            throw new InvalidDetailsException(Constants.INVALID_LOGIN_DETAILS);
        } catch (UserDoesNotExists exception) {
            throw new UserDoesNotExists(Constants.USER_DOES_NOT_EXISTS);
        } catch (Exception exception) {
            throw new DBOPerationException(Constants.DBERROR);
        }
    }

    public User registerNewUser(@RequestBody User user) throws UserAlreadyExists {
        try {
            if (!checkUserId(user.getLoginID())) {
                String encodedString = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
                user.setPassword(encodedString);
                user.setRegisteredDate(LocalDateTime.now());
                User addUser = userRepository.save(user);
                if (addUser != null) {
                    return addUser;
                }
            } else {
                throw new UserAlreadyExists(Constants.USER_ALREADY_EXISTS);
            }
        } catch (UserAlreadyExists exception) {
            throw new UserAlreadyExists(Constants.USER_ALREADY_EXISTS);
        } catch (Exception exception) {
            throw new DBOPerationException(Constants.DBERROR);
        }
        return null;
    }

    public User addBIO(User user) {
        try {
            User userForAddingIntro = userRepository.findByLoginID(user.getLoginID());
            userForAddingIntro.setIntro(user.getIntro());

            User addedUserIntro = userRepository.save(userForAddingIntro);
            if (addedUserIntro != null) {
                return addedUserIntro;
            }

        } catch (Exception exception) {
            throw new DBOPerationException(Constants.DBERROR);
        }
        return null;
    }

    public User forgotPassword(@RequestBody ResetPassword restPassword) throws UserDoesNotExists {
        try {
            if (checkUserId(restPassword.getLoginID())) {
                User user = userRepository.findByLoginID(restPassword.getLoginID());
                String encodedString = Base64.getEncoder().encodeToString(restPassword.getPassword().getBytes());
                user.setPassword(encodedString);
                User resetPasswordUser = userRepository.save(user);
                if (resetPasswordUser != null) {
                    return resetPasswordUser;
                }
            } else {
                throw new UserDoesNotExists(Constants.USER_DOES_NOT_EXISTS);
            }
        } catch (UserDoesNotExists exception) {
            throw new UserDoesNotExists(Constants.USER_DOES_NOT_EXISTS);
        } catch (Exception exception) {
            throw new DBOPerationException(Constants.DBERROR);
        }
        return null;
    }

    public String logout(String loginID) throws UserDoesNotExists {
        try {
            if (checkUserId(loginID)) {
                User user = userRepository.findByLoginID(loginID);
                user.setLoggedOutTime(LocalDateTime.now());
                user.setStatus(Constants.InActive);
                User loggedOutUser = userRepository.save(user);
                if (loggedOutUser != null) {
                    return Constants.USER_LOGGED_OUT;
                }
            } else {
                throw new UserDoesNotExists(Constants.USER_DOES_NOT_EXISTS);
            }
        } catch (UserDoesNotExists exception) {
            throw new UserDoesNotExists(Constants.USER_DOES_NOT_EXISTS);
        } catch (Exception exception) {
            throw new DBOPerationException(Constants.DBERROR);
        }
        return null;
    }

    private boolean checkUserId(String loginID) {
        try {
            return userRepository.findUserByLoginID(loginID);//check for email as well
        } catch (Exception exception) {
            throw new DBOPerationException(Constants.DBERROR);
        }
    }
}
