package com.flower.controller;

import com.flower.entity.Notice;
import com.flower.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping({"/notice"})
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    //查询前n条信息在首页显示
    @RequestMapping({"/showNotice10"})
    @ResponseBody
    public List<Notice> findFirst10ByOrderBy() {
        List<Notice> notices = noticeService.findFirst10ByOrderByNoticeDateAsc();
        System.out.println("==============" + notices);
        return notices;
    }
}
