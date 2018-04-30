package com.flower.service.impl;

import com.flower.dao.UserDao;
import com.flower.entity.User;
import com.flower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yumaoying on 2018/4/29.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //分页查找
    public Page<User> getUserPage(User user, Pageable pageable) {
        Specification<User> userSpecification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (user.getUserName() != null && !user.getUserName().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("userName").as(String.class), "%" + user.getUserName() + "%"));
                }
                if (user.getUserEamil() != null && !user.getUserEamil().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("userEamil").as(String.class), "%" + user.getUserEamil() + "%"));
                }
                if (user.getUserTel() != null && !user.getUserTel().isEmpty()) {
                    predicates.add(cb.like(root.get("userTel").as(String.class), "%" + user.getUserTel() + "%"));
                }
                if (user.getUserId() != null && !user.getUserId().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("userId").as(Integer.class), user.getUserId()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return userDao.findAll(userSpecification, pageable);
    }

    @Transactional
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Transactional
    public void delete(Integer id) {
        userDao.delete(id);
    }

    public User findByUserEamilOrUserTel(String userEmail, String userTel) {
        return userDao.findByUserEamilOrUserTel(userEmail, userTel);
    }

    public User findById(Integer id) {
        return userDao.findByUserId(id);
    }
}
