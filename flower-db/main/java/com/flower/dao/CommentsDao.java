package com.flower.dao;


import com.flower.entity.Comments;
import com.flower.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by yumaoying on 2018/5/15.
 * 评论
 */
public interface CommentsDao extends JpaRepository<Comments, Integer>, JpaSpecificationExecutor<Comments> {
    //保存评论
    public Comments save(Comments comments);

    //按条件查找评论
    Page<Comments> findAll(Specification<Comments> spec, Pageable pagebble);

    //删除评论
    public void delete(Integer commentId);
}
