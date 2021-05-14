package com.tweetapp.app.demo.repository;

import com.tweetapp.app.demo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{}", fields = "{loginID : 1,firstName:1,lastName:1,status:1,intro:1,loggedOutTime:1,gender:1,_id : 0}")
    List<User> findAll();

    User findByLoginID(String loginID);

    @Query("{'loginID':{'$regex':'?0'}}")
    List<User> findByLoginIDS(String loginID);

    @Query(value = "{loginID : ?0}", exists = true)
    boolean findUserByLoginID(String loginID);


}
