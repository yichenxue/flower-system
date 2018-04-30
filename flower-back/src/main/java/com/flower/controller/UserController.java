package com.flower.controller;


import com.flower.entity.User;
import com.flower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;

import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;
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
        System.out.println(page.getContent());
        return map;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public User findById(Integer id) {
        return userService.findById(id);
    }

    @RequestMapping(path = {"/add", "/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public String add(User user, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            System.out.println("=================user" + user);
            if (file != null && !file.isEmpty()) {
                String type = null;
                String fileName = file.getOriginalFilename();
                type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
                if (type != null) {
                    if ("gif".equalsIgnoreCase(type) || "png".equalsIgnoreCase(type) || "jpg".equalsIgnoreCase(type)) {
                        String name = String.valueOf(System.currentTimeMillis()) + "." + type;
                        Path filePath = Paths.get(path + name);
                        if (!filePath.toFile().getParentFile().exists()) filePath.toFile().getParentFile().mkdirs();
                        file.transferTo(filePath.toFile());
                        user.setUserImg(name);
                    }
                } else {
                    return "图片格式错误!";
                }
            }
            userService.saveUser(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "用户已存在";
            else
                return "保存失败";
        }
    }

//    @RequestMapping("/edit")
//    @ResponseBody
//    public String edit(User user) {
//        try {
//            userService.saveUser(user);
//            return "success";
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (e.getMessage().contains("constraint"))
//                return "fail";
//            else
//                return "error";
//        }
//    }

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

