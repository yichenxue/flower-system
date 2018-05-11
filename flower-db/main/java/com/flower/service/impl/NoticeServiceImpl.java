package com.flower.service.impl;

import com.flower.dao.NoticeDao;
import com.flower.entity.Notice;
import com.flower.service.NoticeService;
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
 * Created by yumaoying on 2018/4/30.
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Transactional
    public void save(Notice notice) {
        noticeDao.save(notice);
    }

    @Transactional
    public void delete(Integer id) {
        noticeDao.delete(id);
    }

    public Notice findByNoticeId(Integer id) {
        return noticeDao.findByNoticeId(id);
    }

    public Page<Notice> findAll(Notice notice, Pageable pageable) {
        Specification<Notice> specification = new Specification<Notice>() {
            @Override
            public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (notice.getNoticeTitle() != null && !notice.getNoticeTitle().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("noticeTitle").as(String.class), "%" + notice.getNoticeTitle() + "%"));
                }
                if (notice.getNoticeDate() != null && !notice.getNoticeDate().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("noticeDate").as(String.class), notice.getNoticeDate()));
                }
                if (notice.getNoticeId() != null && !notice.getNoticeId().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("noticeId").as(Integer.class), notice.getNoticeId()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return noticeDao.findAll(specification, pageable);
    }

    public List<Notice> findFirst10ByOrderByNoticeDateAsc() {
        return noticeDao.findFirst10ByOrderByNoticeDateAsc();
    }
}
