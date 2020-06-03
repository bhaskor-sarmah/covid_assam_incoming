package com.bohniman.incomingportal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @NotBlank(message = "Password is required")
    private String password;

    @Size(max = 20, min = 6, message = "Enter a username between 6 to 20 characters long")
    @Column(unique = true, length = 20)
    private String username;

    @Pattern(regexp = "[\\d]{10}", message = "Invalid Mobile No")
    private String mobileNo;

    @Email(message = "Enter a valid email address")
    private String emailId;

    @Column(name = "status", columnDefinition = "tinyint(1) default 0")
    private boolean status = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "join_user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private UserScope userScope;

    public boolean getStatus() {
        return this.status;
    }

}
