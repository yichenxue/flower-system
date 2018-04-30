package com.flower.service;

import com.flower.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by yumaoying on 2018/4/30.
 */
public interface NoticeService {
    public void save(Notice notice);

    public void delete(Integer id);

    public Notice findByNoticeId(Integer id);

    public Page<Notice> findAll(Notice notice, Pageable pageable);
}
