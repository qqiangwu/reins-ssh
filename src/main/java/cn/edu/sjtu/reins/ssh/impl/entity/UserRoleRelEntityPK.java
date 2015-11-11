package cn.edu.sjtu.reins.ssh.impl.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * If the code works, it was written by qqiangwu at 7:10 PM 11/11/15, otherwise I
 * don't know who wrote it.
 */
public class UserRoleRelEntityPK implements Serializable {
    private String mId;
    private String mRole;

    @Column(name = "id", nullable = false, length = 50)
    @Id
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    @Column(name = "role", nullable = false, length = 50)
    @Id
    public String getRole() {
        return mRole;
    }

    public void setRole(String role) {
        mRole = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleRelEntityPK that = (UserRoleRelEntityPK) o;

        if (mId != null ? !mId.equals(that.mId) : that.mId != null) return false;
        if (mRole != null ? !mRole.equals(that.mRole) : that.mRole != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mRole != null ? mRole.hashCode() : 0);
        return result;
    }
}
