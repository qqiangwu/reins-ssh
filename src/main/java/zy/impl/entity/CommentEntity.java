package zy.impl.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class CommentEntity {
    private int mId;
    private String mContent;
    private Timestamp mCreationDate;
    private int mBlog;
    private int mUser;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 1024)
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

    @Basic
    @Column(name = "blog", nullable = false)
    public int getBlog() {
        return mBlog;
    }

    public void setBlog(int blog) {
        mBlog = blog;
    }

    @Basic
    @Column(name = "user", nullable = false)
    public int getUser() {
        return mUser;
    }

    public void setUser(int user) {
        mUser = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (mId != that.mId) return false;
        if (mBlog != that.mBlog) return false;
        if (mUser != that.mUser) return false;
        if (mContent != null ? !mContent.equals(that.mContent) : that.mContent != null) return false;
        if (mCreationDate != null ? !mCreationDate.equals(that.mCreationDate) : that.mCreationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (mContent != null ? mContent.hashCode() : 0);
        result = 31 * result + (mCreationDate != null ? mCreationDate.hashCode() : 0);
        result = 31 * result + mBlog;
        result = 31 * result + mUser;
        return result;
    }
}
