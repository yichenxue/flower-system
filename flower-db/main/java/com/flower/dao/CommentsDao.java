package com.flower.dao;


import com.flower.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by yumaoying on 2018/5/15.
 */
public interface CommentsDao extends JpaRepository<Comments, Integer>, JpaSpecificationExecutor<Comments> {
    //保存评论
    public Comments save(Comments comments);
}
