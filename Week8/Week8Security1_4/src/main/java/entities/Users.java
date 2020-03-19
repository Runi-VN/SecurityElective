/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Runi
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserEmail", query = "SELECT u FROM Users u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "Users.findByUserPass", query = "SELECT u FROM Users u WHERE u.userPass = :userPass"),
    @NamedQuery(name = "Users.findByUsersData", query = "SELECT u FROM Users u WHERE u.usersData = :usersData")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_email")
    private String userEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_pass")
    private String userPass;
    @Size(max = 255)
    @Column(name = "users_data")
    private String usersData;
    @JoinTable(name = "user_roles", joinColumns = {
        @JoinColumn(name = "user_email", referencedColumnName = "user_email")}, inverseJoinColumns = {
        @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    @ManyToMany
    private Collection<Roles> rolesCollection;

    public Users() {
    }

    public Users(String userEmail) {
        this.userEmail = userEmail;
    }

    public Users(String userEmail, String userPass) {
        this.userEmail = userEmail;
        this.userPass = userPass;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUsersData() {
        return usersData;
    }

    public void setUsersData(String usersData) {
        this.usersData = usersData;
    }

    @XmlTransient
    public Collection<Roles> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userEmail != null ? userEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userEmail == null && other.userEmail != null) || (this.userEmail != null && !this.userEmail.equals(other.userEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.week8security.Users[ userEmail=" + userEmail + " ]";
    }
    
}
