package com.gmail.salahub.nikolay.user_manager.repository;

import com.gmail.salahub.nikolay.user_manager.repository.model.User;

public interface UserRepository extends GenericRepository<Long, User> {
    User findByUsername(String username);


}
