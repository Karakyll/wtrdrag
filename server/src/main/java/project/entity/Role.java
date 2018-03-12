package project.entity;

import javax.persistence.*;
import java.util.Set;

/**
 *  Entity class for "roles" table in dataBase
 */
@Entity
@Table(name = "roles")
public class Role {

    private Long roleId;
    private String roleName;
    private Set<User> roleUsers;

    /**
     * Getter for role id
     * @return - role id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true, nullable = false)
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * Getter for role name
     * @return - role name
     */
    @Column(name = "role_name", unique = true, nullable = false)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Getter for set of users, what have this role
     * @return
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
    public Set<User> getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(Set<User> roleUsers) {
        this.roleUsers = roleUsers;
    }
}
