package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/3/25.
 * 商品评论
 */
@Entity
public class Comment implements Serializable {
    private int commentId; //评价id
    private String commentTitle;//评价标题
    private String commentContent;//评价内容
    private String commentDate;//评价日期
    private Integer commentUserId;//评价用户id
    private Goods goods; //评价商品
    private User user;//评价用户id

    @Id
    @Column(name = "comment_id", nullable = false)
    @GeneratedValue
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "comment_title", nullable = true, length = 50)
    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    @Basic
    @Column(name = "comment_content", nullable = true, length = 300)
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Basic
    @Column(name = "comment_date", nullable = true, length = 50)
    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    @Basic
    @Column(name = "comment_user_id", nullable = true)
    public Integer getCommentUserId() {
        return commentUserId;
    }


    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comments = (Comment) o;
        return commentId == comments.commentId &&
                Objects.equals(commentTitle, comments.commentTitle) &&
                Objects.equals(commentContent, comments.commentContent) &&
                Objects.equals(commentDate, comments.commentDate) &&
                Objects.equals(commentUserId, comments.commentUserId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(commentId, commentTitle, commentContent, commentDate, commentUserId);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_user_id", referencedColumnName = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Goods getGoods() {
        return goods;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_goods_id", referencedColumnName = "goods_id")
    public void setGoods(Goods goods) {
        this.goods = goods;
    }

}
