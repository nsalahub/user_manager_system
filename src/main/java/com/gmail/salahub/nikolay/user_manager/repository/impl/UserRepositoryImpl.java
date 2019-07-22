package com.gmail.salahub.nikolay.user_manager.repository.impl;

import com.gmail.salahub.nikolay.user_manager.repository.UserRepository;
import com.gmail.salahub.nikolay.user_manager.repository.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {

    @Override
    public User findByUsername(String username) {
        String hqlQuery = "from User as U where U.username=:username";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("username", username);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
