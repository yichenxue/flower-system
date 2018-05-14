package com.flower.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 */
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = -8207165732672998603L;
    private Integer userId; //用户id
    private String userName; //用户名
    private String userPw;  //用户密码
    private String userRealname; //真实姓名
    private String userAddress;//地址
    private String userSex;//性别
    private String userTel;//电话
    private String userEamil;//邮箱
    private String userQq;//用户qq
    private Integer userCore;//用户积分
    private String userLevel;//用户等级
    private String userBirthday;//生日
    private String userRegister;//注册日期
    private String userLast;//用户最后登陆时间
    private String userImg;//用户头像
    private List<Car> cars;
    private List<Comments> comments;
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

    @Column(name = "user_name", unique = true, nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Column(name = "user_pw", nullable = false, length = 50)
    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }


    @Column(name = "user_realname", nullable = true, length = 50)
    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }


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

    @Column(name = "user_tel", unique = true, nullable = true, length = 20)
    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Column(name = "user_eamil", unique = true, nullable = true, length = 50)
    public String getUserEamil() {
        return userEamil;
    }

    public void setUserEamil(String userEamil) {
        this.userEamil = userEamil;
    }


    @Column(name = "user_qq", nullable = true, length = 50)
    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    @Column(name = "user_core", nullable = true)
    public Integer getUserCore() {
        return userCore;
    }

    public void setUserCore(Integer userCore) {
        this.userCore = userCore;
    }

    @Column(name = "user_level", nullable = true, length = 20)
    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

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


    @Column(name = "user_last", nullable = true, length = 20)
    public String getUserLast() {
        return userLast;
    }

    public void setUserLast(String userLast) {
        this.userLast = userLast;
    }


    @Column(name = "user_img", nullable = true, length = 50)
    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userPw, user.userPw) &&
                Objects.equals(userRealname, user.userRealname) &&
                Objects.equals(userAddress, user.userAddress) &&
                Objects.equals(userSex, user.userSex) &&
                Objects.equals(userTel, user.userTel) &&
                Objects.equals(userEamil, user.userEamil) &&
                Objects.equals(userQq, user.userQq) &&
                Objects.equals(userCore, user.userCore) &&
                Objects.equals(userLevel, user.userLevel) &&
                Objects.equals(userBirthday, user.userBirthday) &&
                Objects.equals(userRegister, user.userRegister) &&
                Objects.equals(userLast, user.userLast) &&
                Objects.equals(userImg, user.userImg);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userName, userPw, userRealname, userAddress, userSex, userTel, userEamil, userQq, userCore, userLevel, userBirthday, userRegister, userLast, userImg);
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @OneToMany(mappedBy = "user")
    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userRealname='" + userRealname + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userEamil='" + userEamil + '\'' +
                ", userQq='" + userQq + '\'' +
                ", userCore=" + userCore +
                ", userLevel='" + userLevel + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                ", userRegister='" + userRegister + '\'' +
                ", userLast='" + userLast + '\'' +
                ", userImg='" + userImg + '\'' +
                '}';
    }
}
