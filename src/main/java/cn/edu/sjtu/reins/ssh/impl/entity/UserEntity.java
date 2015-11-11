package cn.edu.sjtu.reins.ssh.impl.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * If the code works, it was written by qqiangwu at 7:10 PM 11/11/15, otherwise I
 * don't know who wrote it.
 */
@Entity
@Table(name = "user")
public class UserEntity {
    private String mId;
    private String mPassword;
    private byte mEnabled;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private Timestamp mCreationDate;
    private Timestamp mLastAccessDate;

    @Id
    @Column(name = "id", nullable = false, length = 50)
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
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
    @Column(name = "firstName", nullable = true, length = 100)
    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    @Basic
    @Column(name = "lastName", nullable = true, length = 100)
    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 150)
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (mEnabled != that.mEnabled) return false;
        if (mId != null ? !mId.equals(that.mId) : that.mId != null) return false;
        if (mPassword != null ? !mPassword.equals(that.mPassword) : that.mPassword != null) return false;
        if (mFirstName != null ? !mFirstName.equals(that.mFirstName) : that.mFirstName != null) return false;
        if (mLastName != null ? !mLastName.equals(that.mLastName) : that.mLastName != null) return false;
        if (mEmail != null ? !mEmail.equals(that.mEmail) : that.mEmail != null) return false;
        if (mCreationDate != null ? !mCreationDate.equals(that.mCreationDate) : that.mCreationDate != null)
            return false;
        if (mLastAccessDate != null ? !mLastAccessDate.equals(that.mLastAccessDate) : that.mLastAccessDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mPassword != null ? mPassword.hashCode() : 0);
        result = 31 * result + (int) mEnabled;
        result = 31 * result + (mFirstName != null ? mFirstName.hashCode() : 0);
        result = 31 * result + (mLastName != null ? mLastName.hashCode() : 0);
        result = 31 * result + (mEmail != null ? mEmail.hashCode() : 0);
        result = 31 * result + (mCreationDate != null ? mCreationDate.hashCode() : 0);
        result = 31 * result + (mLastAccessDate != null ? mLastAccessDate.hashCode() : 0);
        return result;
    }
}
