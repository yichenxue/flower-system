package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 */
@Entity
public class Comments implements Serializable {
    private static final long serialVersionUID = -6035774223202472298L;
    private Integer commentId;
    private String commentTitle;
    private String commentContent;
    private String commentDate;
    private User user;
    private Goods goods;

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

    @ManyToOne
    @JoinColumn(name = "comment_user_id", referencedColumnName = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "comment_goods_id", referencedColumnName = "goods_id")
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
