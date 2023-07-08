package br.com.compassuol.pb.challenge.msauth.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(
        name = "tb_user",
        schema = "challenge",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UQ_USER_EMAIL",
                        columnNames = "email"
                )
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "firstName", nullable = false, length = 30)
    private String firstName;

    @Size(max = 30)
    @NotNull
    @Column(name = "lastName", nullable = false, length = 30)
    private String lastName;

    @Size(max = 50)
    @NotNull
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Size(max = 64)
    @NotNull
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role",
            joinColumns = @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "FK_USER_USER_ROLE")),
            inverseJoinColumns = @JoinColumn(name = "id_role", foreignKey = @ForeignKey(name = "FK_ROLE_USER_ROLE")))
    private Set<Role> roles = new LinkedHashSet<>();

    public User() {
    }

    public User(Long id, @NotNull String firstName, @NotNull String lastName, @NotNull String email, @NotNull String password, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
