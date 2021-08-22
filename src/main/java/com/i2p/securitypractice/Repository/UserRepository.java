package com.i2p.securitypractice.Repository;

import com.i2p.securitypractice.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,String> {

public UserEntity findByEmail_idIgnoreCase(String email);


}
