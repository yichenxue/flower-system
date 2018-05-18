package com.flower.service;

import com.flower.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by yumaoying on 2018/5/15.
 * 评论
 */
public interface CommentsService {
    //评论删除
    public Comments save(Comments comments);

    //分页查找评论
    public Page<Comments> findAll(Comments comments, Pageable pageable);
}
