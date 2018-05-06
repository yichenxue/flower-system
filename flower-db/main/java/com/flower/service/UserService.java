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

    public User saveUser(User user);

    public void delete(Integer id);

    public User findByUserEamilOrUserTelOrUserName(String userEmail, String userTel, String userName);

    public User findById(Integer id);

    //更新用户登陆时间
    public void updateLast(Integer id, String last);
}
