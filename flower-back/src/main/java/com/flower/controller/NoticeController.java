package com.flower.controller;

import com.flower.entity.Notice;
import com.flower.entity.SysUser;
import com.flower.service.NoticeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yumaoying on 2018/4/30.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping
    public String noticePage() {
        return "notice/notices";
    }

    @RequestMapping("/notices")
    @ResponseBody
    public Map<String, Object> get(Notice notice, String draw,
                                   @RequestParam(required = false, defaultValue = "0") int start,
                                   @RequestParam(required = false, defaultValue = "10") int length) {
        System.out.println("=================" + notice);
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.ASC, "noticeId");//按用户id降序排
        org.springframework.data.domain.Pageable pageable = new PageRequest(start / length, length, sort);
        Page page = noticeService.findAll(notice, pageable);
        map.put("draw", draw);
        map.put("recordsTotal", page.getTotalElements());
        map.put("recordsFiltered", page.getTotalElements());
        map.put("data", page.getContent());
        System.out.println(page.getContent());
        return map;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Notice findById(Integer id) {
        return noticeService.findByNoticeId(id);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(Notice notice) {
        try {
            SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
            notice.setNoticeUserName(user.getUsername());
            noticeService.save(notice);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "fail";
            else
                return "error";
        }
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Notice notice) {
        try {
            noticeService.save(notice);
            return "success";
        } catch (Exception e) {
            if (e.getMessage().contains("constraint"))
                return "fail";
            else
                return "error";
        }

    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        try {
            noticeService.delete(id);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
}
