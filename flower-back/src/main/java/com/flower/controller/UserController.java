package com.flower.controller;

import com.flower.entity.User;
import com.flower.service.UserService;
import com.flower.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by yumaoying on 2018/4/29.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${web.upload-path}")
    private String path;

    @Autowired
    private UserService userService;

    @RequestMapping
    public String userPage() {
        return "user/users";
    }

    @RequestMapping("/users")
    @ResponseBody
    public Map<String, Object> get(User user, String draw,
                                   @RequestParam(required = false, defaultValue = "0") int start,
                                   @RequestParam(required = false, defaultValue = "10") int length) {
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.ASC, "userId");//按用户id降序排
        Pageable pageable = new PageRequest(start / length, length, sort);
        Page page = userService.getUserPage(user, pageable);
        map.put("draw", draw);
        map.put("recordsTotal", page.getTotalElements());
        map.put("recordsFiltered", page.getTotalElements());
        map.put("data", page.getContent());
        return map;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public User findById(Integer id) {
        return userService.findById(id);
    }


    @PostMapping("/add")
    @ResponseBody
    public String add(User user, @RequestParam("file") MultipartFile file) {
        try {
            String msg = FileUploadUtil.upload(path, file);
            if (!"图片格式错误".equals(msg) && !"图片上传错误".equals(msg)) {
                user.setUserImg(msg);
                SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
                user.setUserRegister(time.format(new Date()));
                userService.saveUser(user);
                return "success";
            } else {
                return msg;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "用户已存在,请检查是账户名,邮箱,手机号!";
            return "添加失败!";
        }
    }

    @PostMapping("/edit")
    @ResponseBody
    public String edit(User user, @RequestParam("file") MultipartFile file) {
        try {
            String msg = FileUploadUtil.upload(path, file);
            if (!"图片格式错误".equals(msg) && !"图片上传错误".equals(msg)) {
                user.setUserImg(msg);
                userService.saveUser(user);
                return "success";
            } else {
                return msg;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "用户已存在";
            return "修改失败";
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        try {
            userService.delete(id);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

}

