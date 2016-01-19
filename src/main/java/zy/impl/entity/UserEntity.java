package zy.impl.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
public class UserEntity {
    private int mId;
    private String mEmail;
    private String mPassword;
    private byte mEnabled;
    private String mName;
    private Timestamp mCreationDate;
    private Timestamp mLastAccessDate;
    private int mCommentCount;
    private int mBlogCount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 64)
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 32)
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public byte getEnabled() {
        return mEnabled;
    }

    public void setEnabled(byte enabled) {
        mEnabled = enabled;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
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
    @Column(name = "lastAccessDate", nullable = false)
    public Timestamp getLastAccessDate() {
        return mLastAccessDate;
    }

    public void setLastAccessDate(Timestamp lastAccessDate) {
        mLastAccessDate = lastAccessDate;
    }

    @Basic
    @Column(name = "comment_count", nullable = false)
    public int getCommentCount() {
        return mCommentCount;
    }

    public void setCommentCount(int commentCount) {
        mCommentCount = commentCount;
    }

    @Basic
    @Column(name = "blog_count", nullable = false)
    public int getBlogCount() {
        return mBlogCount;
    }

    public void setBlogCount(int blogCount) {
        mBlogCount = blogCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (mId != that.mId) return false;
        if (mEnabled != that.mEnabled) return false;
        if (mCommentCount != that.mCommentCount) return false;
        if (mBlogCount != that.mBlogCount) return false;
        if (!mEmail.equals(that.mEmail)) return false;
        if (!mPassword.equals(that.mPassword)) return false;
        if (!mName.equals(that.mName)) return false;
        if (!mCreationDate.equals(that.mCreationDate)) return false;
        return mLastAccessDate.equals(that.mLastAccessDate);

    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + mEmail.hashCode();
        result = 31 * result + mPassword.hashCode();
        result = 31 * result + (int) mEnabled;
        result = 31 * result + mName.hashCode();
        result = 31 * result + mCreationDate.hashCode();
        result = 31 * result + mLastAccessDate.hashCode();
        result = 31 * result + mCommentCount;
        result = 31 * result + mBlogCount;
        return result;
    }
}
