package com.flower.dao;

import com.flower.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * Created by yumaoying on 2018/4/22.
 */
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    public User save(User user);

    public void delete(Integer id);

    public User findByUserEamilOrUserTel(String userEmail, String userTel);

    public User findByUserId(Integer id);

    public Page<User> findAll(Specification<User> spec, Pageable pageable);
}
