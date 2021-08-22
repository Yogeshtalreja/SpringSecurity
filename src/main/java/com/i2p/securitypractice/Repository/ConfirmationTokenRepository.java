package com.i2p.securitypractice.Repository;

import com.i2p.securitypractice.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken,String> {

    public ConfirmationToken findByConfirmationToken(String confirmationToken);
}
