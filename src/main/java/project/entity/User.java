package project.entity;

import javax.persistence.*;
import java.util.Set;

/**
 *  Entity class for "users" table in dataBase
 */
@Entity
@Table(name = "users")
public class User {

    private Long userId;
    private String userName;
    private String userPassword;
    private Set<Role> userRoles;

    /**
     * Getter for user id
     * @return - user id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Getter for user name
     * @return - user name
     */
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for user password
     * @return - user password
     */
    @Column(name = "user_password")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    /**
     * Getter for set of roles, what user have
     * @return - set of roles
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", catalog = "wtrdb", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "role_id", nullable = false, updatable = false)})
    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }
}
