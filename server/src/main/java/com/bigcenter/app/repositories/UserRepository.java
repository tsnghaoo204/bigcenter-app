package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    Optional<User> findByCognitoSub(String cognitoSub);
    Boolean existsByCognitoSub(String cognitoSub);
    Optional<User> findByEmail(String email);
}
