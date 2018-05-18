package com.flower.service.impl;

import com.flower.dao.CommentsDao;
import com.flower.entity.Comments;
import com.flower.entity.Notice;
import com.flower.service.CommentsService;
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
 * Created by yumaoying on 2018/5/15.
 * 评论
 */
@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsDao commentsDao;

    @Transactional
    public Comments save(Comments comments) {
        return commentsDao.save(comments);
    }

    public Page<Comments> findAll(Comments comments, Pageable pageable) {
        Specification<Comments> specification = new Specification<Comments>() {
            @Override
            public Predicate toPredicate(Root<Comments> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (comments.getCommentTitle() != null && !comments.getCommentTitle().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("commentTitle").as(String.class), "%" + comments.getCommentTitle() + "%"));
                }
                if (comments.getCommentDate() != null && !comments.getCommentDate().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("commentDate").as(String.class), "%" + comments.getCommentDate() + "%"));
                }
                if (comments.getCommentId() != null && !comments.getCommentId().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("commentId").as(Integer.class), comments.getCommentId()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return commentsDao.findAll(specification, pageable);
    }

    //评论删除
    public void delete(Integer commentId) {
        commentsDao.delete(commentId);
    }
}
