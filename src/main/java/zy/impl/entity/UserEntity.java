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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (mId != that.mId) return false;
        if (mEnabled != that.mEnabled) return false;
        if (mEmail != null ? !mEmail.equals(that.mEmail) : that.mEmail != null) return false;
        if (mPassword != null ? !mPassword.equals(that.mPassword) : that.mPassword != null) return false;
        if (mName != null ? !mName.equals(that.mName) : that.mName != null) return false;
        if (mCreationDate != null ? !mCreationDate.equals(that.mCreationDate) : that.mCreationDate != null)
            return false;
        if (mLastAccessDate != null ? !mLastAccessDate.equals(that.mLastAccessDate) : that.mLastAccessDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (mEmail != null ? mEmail.hashCode() : 0);
        result = 31 * result + (mPassword != null ? mPassword.hashCode() : 0);
        result = 31 * result + (int) mEnabled;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mCreationDate != null ? mCreationDate.hashCode() : 0);
        result = 31 * result + (mLastAccessDate != null ? mLastAccessDate.hashCode() : 0);
        return result;
    }
}
