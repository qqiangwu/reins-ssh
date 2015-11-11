package cn.edu.sjtu.reins.ssh.impl.entity;

import javax.persistence.*;

/**
 * If the code works, it was written by qqiangwu at 7:10 PM 11/11/15, otherwise I
 * don't know who wrote it.
 */
@Entity
@Table(name = "user_role_rel")
@IdClass(UserRoleRelEntityPK.class)
public class UserRoleRelEntity {
    private String mId;
    private String mRole;

    @Id
    @Column(name = "id", nullable = false, length = 50)
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    @Id
    @Column(name = "role", nullable = false, length = 50)
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

        UserRoleRelEntity that = (UserRoleRelEntity) o;

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
