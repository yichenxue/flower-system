package com.flower.controller;

import com.flower.entity.Notice;
import com.flower.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return noticeService.findFirst10ByOrderByNoticeDateAsc();
    }

    @RequestMapping({"/getNotice"})
    public String findNoticeById(@RequestParam(required = false, defaultValue = "1") Integer id, Model model) {
        Notice notice = noticeService.findByNoticeId(id);
        model.addAttribute("notice", notice);
        return "notices";
    }

    //查找所有公告
    @RequestMapping({"/findAll"})
    public String findAll(Model model) {
        List<Notice> notices = noticeService.findAllOrderByNoticeDateAsc();
        model.addAttribute("notices", notices);
        return "notices";
    }
}
