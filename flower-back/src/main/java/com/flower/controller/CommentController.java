package com.flower.controller;

import com.flower.entity.Comments;
import com.flower.service.CommentsService;
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
 * Created by yumaoying on 2018/5/15.
 * 评论管理
 */
@Controller
@RequestMapping({"/comment"})
public class CommentController {
    @Autowired
    private CommentsService commentsService;

    @RequestMapping
    public String commentsPage() {
        return "comment/comments";

    }

    @RequestMapping("/comments")
    @ResponseBody
    public Map<String, Object> get(Comments comments, String draw,
                                   @RequestParam(required = false, defaultValue = "0") int start,
                                   @RequestParam(required = false, defaultValue = "10") int length) {
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.ASC, "commentId");//按用户id降序排
        org.springframework.data.domain.Pageable pageable = new PageRequest(start / length, length, sort);
        Page page = commentsService.findAll(comments, pageable);
        map.put("draw", draw);
        map.put("recordsTotal", page.getTotalElements());
        map.put("recordsFiltered", page.getTotalElements());
        map.put("data", page.getContent());
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        try {
            commentsService.delete(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败!";
        }
    }
}
