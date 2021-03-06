package com.flower.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 * 评价
 */
@Entity
public class Comments implements Serializable {
    private static final long serialVersionUID = -6035774223202472298L;
    private Integer commentId;//评价id
    private String commentTitle;//评价标题
    private String commentContent;//评价内容
    private String commentDate;//评价时间
    private User user;//评价用户id
    private Goods goods;//商品id

    @Id
    @Column(name = "comment_id", nullable = false)
    @GeneratedValue
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }


    @Column(name = "comment_title", nullable = true, length = 50)
    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }


    @Column(name = "comment_content", nullable = true, length = 300)
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }


    @Column(name = "comment_date", nullable = true)
    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return commentId == comments.commentId &&
                Objects.equals(commentTitle, comments.commentTitle) &&
                Objects.equals(commentContent, comments.commentContent) &&
                Objects.equals(commentDate, comments.commentDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(commentId, commentTitle, commentContent, commentDate);
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "comment_user_id", referencedColumnName = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "comment_goods_id", referencedColumnName = "goods_id")
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "commentId=" + commentId +
                ", commentTitle='" + commentTitle + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", commentDate='" + commentDate + '\'' +
                ", user=" + user +
                ", goods=" + goods +
                '}';
    }
}
