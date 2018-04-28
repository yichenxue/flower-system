package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by yumaoying on 2018/3/25.
 * 会员信息
 */
@Entity
public class User implements Serializable {
    private Integer userId;  //用户id
    private String userName; //用户名
    private String userPw; //用户密码
    private String userRealname; //真实姓名
    private String userAddress; //地址
    private String userSex; //性别
    private String userTel; //电话
    private String userEamil; //邮箱
    private String userQq; //用户qq
    private Integer userCore; //用户积分
    private String userLevel; //用户等级
    private String userBirthday; //生日
    private String userRegister; //注册日期
    private String userLast; //用户最后登陆时间
    private String userImg; //用户头像
    private List<Car> cars; //购物车
    private List<Comment> commons;
    private List<OrderDetail> orderDetails;

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_pw", nullable = false, length = 50)
    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    @Basic
    @Column(name = "user_realname", nullable = true, length = 50)
    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }

    @Basic
    @Column(name = "user_address", nullable = true, length = 50)
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Basic
    @Column(name = "user_sex", nullable = true, length = 5)
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Basic
    @Column(name = "user_tel", nullable = true, length = 20)
    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Basic
    @Column(name = "user_eamil", nullable = true, length = 50)
    public String getUserEamil() {
        return userEamil;
    }

    public void setUserEamil(String userEamil) {
        this.userEamil = userEamil;
    }

    @Basic
    @Column(name = "user_qq", nullable = true, length = 50)
    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    @Basic
    @Column(name = "user_core", nullable = true)
    public Integer getUserCore() {
        return userCore;
    }

    public void setUserCore(Integer userCore) {
        this.userCore = userCore;
    }

    @Basic
    @Column(name = "user_level", nullable = true, length = 20)
    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    @Basic
    @Column(name = "user_birthday", nullable = true, length = 10)
    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Basic
    @Column(name = "user_register", nullable = true, length = 20)
    public String getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(String userRegister) {
        this.userRegister = userRegister;
    }

    @Basic
    @Column(name = "user_last", nullable = true, length = 20)
    public String getUserLast() {
        return userLast;
    }

    public void setUserLast(String userLast) {
        this.userLast = userLast;
    }

    @Basic
    @Column(name = "user_img", nullable = true, length = 50)
    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    @OneToMany(mappedBy = "userByUserId", fetch = FetchType.LAZY)
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @OneToMany(mappedBy = "userByCommentUserId")
    public List<Comment> getcommons() {
        return commons;
    }

    public void setcommons(List<Comment> commons) {
        this.commons = commons;
    }

    @OneToMany(mappedBy = "user")
    public List<OrderDetail> getorderDetails() {
        return orderDetails;
    }

    public void setorderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
