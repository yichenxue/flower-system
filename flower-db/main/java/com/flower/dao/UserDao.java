package com.flower.dao;

import com.flower.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yumaoying on 2018/4/22.
 */
public interface UserDao extends JpaRepository<User, Integer> {
//    public User save(User user);
}
