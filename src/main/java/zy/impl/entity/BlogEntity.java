package zy.impl.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "blog")
public class BlogEntity {
    private int mId;
    private int mUser;
    private String mTitle;
    private String mContent;
    private Timestamp mCreationDate;
    private Timestamp mModifiedDate;

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
    @Column(name = "user", nullable = false)
    public int getUser() {
        return mUser;
    }

    public void setUser(int user) {
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

    @Basic
    @Column(name = "modifiedDate", nullable = false)
    public Timestamp getModifiedDate() {
        return mModifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        mModifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogEntity that = (BlogEntity) o;

        if (mId != that.mId) return false;
        if (mUser != that.mUser) return false;
        if (mTitle != null ? !mTitle.equals(that.mTitle) : that.mTitle != null) return false;
        if (mContent != null ? !mContent.equals(that.mContent) : that.mContent != null) return false;
        if (mCreationDate != null ? !mCreationDate.equals(that.mCreationDate) : that.mCreationDate != null)
            return false;
        if (mModifiedDate != null ? !mModifiedDate.equals(that.mModifiedDate) : that.mModifiedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + mUser;
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + (mContent != null ? mContent.hashCode() : 0);
        result = 31 * result + (mCreationDate != null ? mCreationDate.hashCode() : 0);
        result = 31 * result + (mModifiedDate != null ? mModifiedDate.hashCode() : 0);
        return result;
    }
}
