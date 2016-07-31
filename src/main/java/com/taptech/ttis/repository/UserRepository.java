package com.taptech.ttis.repository;

import com.taptech.ttis.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tap on 7/30/16.
 */
@Repository("user")
public interface UserRepository extends CrudRepository<User, String>{
}
