package cn.edu.sjtu.reins.ssh.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * If the code works, it was written by qqiangwu at 7:10 PM 11/11/15, otherwise I
 * don't know who wrote it.
 */
@Entity
@Table(name = "role")
public class RoleEntity {
    private String mRole;

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

        RoleEntity that = (RoleEntity) o;

        if (mRole != null ? !mRole.equals(that.mRole) : that.mRole != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mRole != null ? mRole.hashCode() : 0;
    }
}
