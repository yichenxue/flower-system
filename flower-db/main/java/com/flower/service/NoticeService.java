package com.flower.service;

import com.flower.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by yumaoying on 2018/4/30.
 * 公告信息
 */
public interface NoticeService {
    public void save(Notice notice);

    public void delete(Integer id);

    public Notice findByNoticeId(Integer id);

    public Page<Notice> findAll(Notice notice, Pageable pageable);

    //查找前10条记录，在首页显示
    public List<Notice> findFirst10ByOrderByNoticeDateAsc();
}
