package com.flower.dao;

import com.flower.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


/**
 * Created by yumaoying on 2018/4/22.
 */
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    public User save(User user);

    public void delete(Integer id);

    public User findByUserEamilOrUserTelOrUserName(String userEmail, String userTel, String userName);

    public User findByUserId(Integer id);

    public Page<User> findAll(Specification<User> spec, Pageable pageable);

    //更新用户登陆时间
    @Modifying
    @Query("update User u set  u.userLast=?2 where u.userId=?1")
    public void updateLast(Integer id, String last);

    //重置密码
    @Modifying
    @Query("update User u set  u.userPw=?2 where u.userId=?1")
    public void updateUserPw(Integer id, String userPw);
}
