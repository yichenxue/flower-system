package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 * 公告
 */
@Entity
public class Notice implements Serializable {
    private static final long serialVersionUID = -4612184760578111454L;
    private Integer noticeId; //编号
    private String noticeTitle; //公告标题
    private String noticeContent; //公告名称
    private String noticeDate; //公告发布日期
    private String noticeUserName; //发布公告人用户名

    @Id
    @Column(name = "notice_id", nullable = false)
    @GeneratedValue
    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }


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


    @Column(name = "notice_date", nullable = true)
    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }


    @Column(name = "notice_user_name", nullable = true, length = 11)
    public String getNoticeUserName() {
        return noticeUserName;
    }

    public void setNoticeUserName(String noticeUserName) {
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
