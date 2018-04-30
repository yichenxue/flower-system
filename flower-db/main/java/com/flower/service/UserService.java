package com.flower.service;

import com.flower.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by yumaoying on 2018/4/29.
 */
public interface UserService {
    //分页查找
    public Page<User> getUserPage(User user, Pageable pageable);

    public void saveUser(User user);

    public void delete(Integer id);

    public User findByUserEamilOrUserTel(String userEmail, String userTel);

    public User findById(Integer id);

}
