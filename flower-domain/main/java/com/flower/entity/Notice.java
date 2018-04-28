package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/3/25.
 * 公告信息
 */
@Entity
public class Notice implements Serializable {
    private int noticeId; //公告id
    private String noticeTitle; //公告标题
    private String noticeContent; //公告内容
    private Timestamp noticeDate; //公告发布时间
    private String noticeUserName; //公告发布用户名

    @Id
    @Column(name = "notice_id", nullable = false)
    @GeneratedValue
    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    @Basic
    @Column(name = "notice_title", nullable = true, length = 20)
    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    @Basic
    @Column(name = "notice_content", nullable = true, length = 500)
    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    @Basic
    @Column(name = "notice_date", nullable = true)
    public Timestamp getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Timestamp noticeDate) {
        this.noticeDate = noticeDate;
    }

    @Basic
    @Column(name = "notice_user_name", nullable = true)
    public String getNoticeUserName() {
        return noticeUserName;
    }

    public void setNoticeUserNamed(String noticeUserName) {
        this.noticeUserName = noticeUserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice notice = (Notice) o;
        return noticeId == notice.noticeId &&
                Objects.equals(noticeTitle, notice.noticeTitle) &&
                Objects.equals(noticeContent, notice.noticeContent) &&
                Objects.equals(noticeDate, notice.noticeDate) &&
                Objects.equals(noticeUserName, notice.noticeUserName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(noticeId, noticeTitle, noticeContent, noticeDate, noticeUserName);
    }
}
