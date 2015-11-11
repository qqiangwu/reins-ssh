package cn.edu.sjtu.reins.ssh.impl.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * If the code works, it was written by qqiangwu at 7:10 PM 11/11/15, otherwise I
 * don't know who wrote it.
 */
@Entity
@Table(name = "note")
public class NoteEntity {
    private String mId;
    private String mUser;
    private String mTitle;
    private String mContent;
    private Timestamp mCreationDate;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    @Basic
    @Column(name = "user", nullable = false, length = 32)
    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        mUser = user;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 32)
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Basic
    @Column(name = "content", nullable = false, length = -1)
    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Basic
    @Column(name = "creationDate", nullable = false)
    public Timestamp getCreationDate() {
        return mCreationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        mCreationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteEntity that = (NoteEntity) o;

        if (mId != null ? !mId.equals(that.mId) : that.mId != null) return false;
        if (mUser != null ? !mUser.equals(that.mUser) : that.mUser != null) return false;
        if (mTitle != null ? !mTitle.equals(that.mTitle) : that.mTitle != null) return false;
        if (mContent != null ? !mContent.equals(that.mContent) : that.mContent != null) return false;
        if (mCreationDate != null ? !mCreationDate.equals(that.mCreationDate) : that.mCreationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mUser != null ? mUser.hashCode() : 0);
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + (mContent != null ? mContent.hashCode() : 0);
        result = 31 * result + (mCreationDate != null ? mCreationDate.hashCode() : 0);
        return result;
    }
}
