package com.flower.controller;

import com.flower.entity.Car;
import com.flower.entity.User;
import com.flower.service.CarService;
import com.flower.service.UserService;
import com.flower.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by yumaoying on 2018/5/6.
 * 用户
 */
@Controller
public class UserController {
    @Value("${web.upload-path}")
    private String path;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    //进入注册页
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    //进入登陆页面
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    //退出登陆
    @RequestMapping("/logout")
    public String logoutOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/login";
    }

    @RequestMapping("/userInfo")
    public String toUserInfo(HttpServletRequest request) {
        if (request.getSession().getAttribute("loginUser") == null) {
            return "redirect:toLogin";
        }
        return "/userInfo";
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(User user) {
        try {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            user.setUserRegister(time.format(new Date()));
            userService.saveUser(user);
            return "注册成功!";
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "用户已存在,请检查是账户名,邮箱,手机号!";
            return "注册失败!";
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(User user,
                        String randomCode,
                        String rememberMe,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        try {
            String validateCode = (String) request.getSession().getAttribute("validateCode");
            if (!randomCode.equalsIgnoreCase(validateCode)) {
                return "验证码不正确!";
            }
            User loginUser = userService.findByUserEamilOrUserTelOrUserName(user.getUserName(), user.getUserName(), user.getUserName());
            if (loginUser == null) {
                return "用户不存在!";
            } else if (loginUser.getUserPw().equals(user.getUserPw())) {
                //更新用户登陆时间
                SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                userService.updateLast(user.getUserId(), time.format(new Date()));
                request.getSession().setAttribute("loginUser", loginUser);
                //记住我功能的实现
                Cookie nameCookie = new Cookie("flower_name", user.getUserName());
                Cookie passwordCookie = new Cookie("flower_userId", loginUser.getUserId().toString());
                //设置cookie路径
                nameCookie.setPath(request.getContextPath() + "/");
                passwordCookie.setPath(request.getContextPath() + "/");
                if (rememberMe != null && "1".equals(rememberMe)) {
                    //以秒计算
                    nameCookie.setMaxAge(60);
                    passwordCookie.setMaxAge(60);
                } else {
                    nameCookie.setMaxAge(0);
                    passwordCookie.setMaxAge(0);
                }
                //将cookie设置到response
                response.addCookie(nameCookie);
                response.addCookie(passwordCookie);
                //用户登陆时,将session中的购物车信息保存到数据库中
                List<Car> loginUserCars = loginUser.getCars();
                Map<Integer, Car> userCars;
                userCars = (Map<Integer, Car>) request.getSession().getAttribute("buyCars");
                Map<Integer, Car> newCar = new HashMap<>();
                if (userCars != null && userCars.size() > 0) {
                    newCar = userCars;
                    if (loginUserCars != null && loginUserCars.size() > 0) {
                        for (Car car : loginUserCars) {
                            boolean flag = false;
                            for (Car sessionCar : userCars.values()) {
                                //设置用户
                                newCar.get(sessionCar.getGoods().getGoodsId()).setUser(loginUser);
                                //session中的商品在数据库中有记录,改变数量
                                if (sessionCar.getGoods().getGoodsId().equals(car.getGoods().getGoodsId())) {
                                    //设置数量
                                    newCar.get(sessionCar.getGoods().getGoodsId()).setMount(sessionCar.getMount() + car.getMount());
                                    //设置购物车编号
                                    newCar.get(sessionCar.getGoods().getGoodsId()).setCarId(car.getCarId());
                                    flag = true;
                                }
                            }
                            //数据库中有该商品未在session中,保存进去
                            if (!flag) {
                                newCar.put(car.getGoods().getGoodsId(), car);
                            }
                        }
                    } else {
                        //数据库中没有,session中有
                        for (Car sessionCar : userCars.values()) {
                            newCar.get(sessionCar.getGoods().getGoodsId()).setUser(loginUser);
                        }
                    }
                } else {
                    //session中没有记录,数据库中有记录
                    if (loginUserCars != null && loginUserCars.size() > 0) {
                        for (Car car : loginUserCars) {
                            newCar.put(car.getGoods().getGoodsId(), car);
                        }
                    }
                }
                //保存到数据库
                //购物车总量
                Integer carNumber = 0;
                carService.save(newCar);
                for (Car car : newCar.values()) {
                    carNumber = carNumber + car.getMount();
                }
                request.getSession().setAttribute("buyCars", newCar);
                request.getSession().setAttribute("carNumber", carNumber);
                return "登陆成功!";
            } else {
                return "密码错误!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误!";
        }
    }


    @PostMapping("/edit")
    @ResponseBody
    public String edit(User user, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            String msg = FileUploadUtil.upload(path, file);
            if ("图片格式错误".equals(msg) && "图片上传错误".equals(msg)) {
                return msg;
            }
            if (!msg.trim().isEmpty()) {
                user.setUserImg(msg);
            }
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            // 密码和注册时间不更改
            user.setUserPw(loginUser.getUserPw());
            user.setUserRegister(loginUser.getUserRegister());
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //修改登陆时间
            user.setUserLast(time.format(new Date()));
            User u = userService.saveUser(user);
            request.getSession().setAttribute("loginUser", u);
            return "修改成功";
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "用户信息已存在，请检查用户名，邮箱，手机号";
            return "修改失败";
        }
    }

    @PostMapping("/setPass")
    @ResponseBody
    public String modifyPass(Integer id, String oriUserPw, String userPw, HttpServletRequest request) {
        try {
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            if (!oriUserPw.equals(loginUser.getUserPw())) {
                return "原密码错误!";
            }
            userService.updateUserPw(id, userPw);
            return "重置密码成功,请妥善保存!";
        } catch (Exception e) {
            e.printStackTrace();
            return "重置密码失败!";
        }
    }
}
