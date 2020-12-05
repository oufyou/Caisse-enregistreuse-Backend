/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * The type User.
 *
 * @author King
 */

@Entity(name="users")
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name="first_name")
    private String firstName;

    @NotBlank
    @Size(max = 20)
    @Column(name="last_name")
    private String lastName;

    @NotBlank
    @Size(max = 20)
    @Column(name="sexe")
    private String sexe;

    @NotBlank
    @Size(max = 20)
    @Column(name="bdate")
    private Date bdate;

    @NotBlank
    @Size(max = 155)
    @Column(name="adress")
    private String adress;

    @NotBlank
    @Size(max = 20)
    @Column(name="phone")
    private String phone;


    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name="email")
    private String email;

    @NotBlank
    @Size(max = 20)
    @Column(name="username")
    private String username;

    @NotBlank
    @Size(max = 120)
    @Column(name="password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date createdAt;

    @CreatedBy
    @Column(name="created_by")
    private String createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updatedAt;

    @LastModifiedBy
    @Column(name="updated_by")
    private String updatedBy;

    @NotBlank
    @Column(columnDefinition="tinyint(1) default 1")
    private Boolean display;

    public User() {
    }

    public User(Long id, @NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String sexe, @NotBlank @Size(max = 20) Date bdate, @NotBlank @Size(max = 155) String adress, @NotBlank @Size(max = 20) String phone, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, Set<Role> roles, Date createdAt, String createdBy, Date updatedAt, String updatedBy, @NotBlank Boolean display) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.bdate = bdate;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.display = display;
    }

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(@NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, Set<Role> roles, String createdBy, String updatedBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public User(Long id, @NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String sexe, @NotBlank @Size(max = 20) Date bdate, @NotBlank @Size(max = 155) String adress, @NotBlank @Size(max = 20) String phone, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, Date createdAt, String createdBy, Date updatedAt, String updatedBy) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.bdate = bdate;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public User(Long id, @NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String sexe, @NotBlank @Size(max = 20) Date bdate, @NotBlank @Size(max = 155) String adress, @NotBlank @Size(max = 20) String phone, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, Set<Role> roles, Date createdAt, String createdBy, Date updatedAt, String updatedBy) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.bdate = bdate;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public User(@NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String sexe, @NotBlank @Size(max = 20) Date bdate, @NotBlank @Size(max = 155) String adress, @NotBlank @Size(max = 20) String phone, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, String createdBy, String updatedBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.bdate = bdate;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public User(@NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, String createdBy, String updatedBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public User(@NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String sexe, @NotBlank @Size(max = 20) Date bdate, @NotBlank @Size(max = 155) String adress, @NotBlank @Size(max = 20) String phone, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, Set<Role> roles, String updatedBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.bdate = bdate;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.updatedBy = updatedBy;
    }

    public User(@NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String sexe, @NotBlank @Size(max = 20) Date bdate, @NotBlank @Size(max = 155) String adress, @NotBlank @Size(max = 20) String phone, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, Set<Role> roles, String updatedBy, @NotBlank Boolean display) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.bdate = bdate;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.updatedBy = updatedBy;
        this.display = display;
    }

    public User(@NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName, @NotBlank @Size(max = 20) String sexe, @NotBlank @Size(max = 20) Date bdate, @NotBlank @Size(max = 155) String adress, @NotBlank @Size(max = 20) String phone, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, Set<Role> roles, Date createdAt, String createdBy, Date updatedAt, String updatedBy, @NotBlank Boolean display) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.bdate = bdate;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.display = display;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sexe='" + sexe + '\'' +
                ", bdate=" + bdate +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}