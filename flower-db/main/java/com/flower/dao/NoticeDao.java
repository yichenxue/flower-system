package com.flower.dao;

import com.flower.entity.Category;
import com.flower.entity.Notice;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by yumaoying on 2018/4/30.
 */
public interface NoticeDao extends JpaRepository<Notice, Integer>, JpaSpecificationExecutor<Notice> {
    public Notice save(Notice notice);

    public void delete(Integer id);

    public Notice findByNoticeId(Integer id);

    Page<Notice> findAll(Specification<Notice> spec, Pageable pagebble);

    //取前10条记录
    public List<Notice> findFirst10ByOrderByNoticeDateAsc();
}
