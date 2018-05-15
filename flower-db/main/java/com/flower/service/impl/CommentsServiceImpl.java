package com.flower.service.impl;

import com.flower.dao.CommentsDao;
import com.flower.entity.Comments;
import com.flower.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
