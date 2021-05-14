package com.tweetapp.app.demo.repository;

import com.tweetapp.app.demo.domain.Tweets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetsRepository extends MongoRepository<Tweets, String> {

    @Query(value = "{'createdBy.loginID' : ?0}")
    List<Tweets> findByCreatedBy(String createdBy);
}
