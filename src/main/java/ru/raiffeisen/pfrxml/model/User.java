package ru.raiffeisen.pfrxml.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import ru.raiffeisen.pfrxml.HasId;

import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

/*@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.name, u.email"),
})*/
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "login", name = "users_unique_login_idx")})
public class User extends AbstractBaseEntity implements HasId {

/*    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByLogin";
    public static final String ALL_SORTED = "User.getAllSorted";*/

    @Column(name = "login", nullable = false, unique = true)
    @NotBlank
    @Size(max = 100)
    private String login;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String lastName;

    @Column(name = "middle_name", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String middleName;

    @Column(name = "phone_number", nullable = false)
    @NotBlank
    @Size(max = 30)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    // https://stackoverflow.com/a/12505165/548473
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime registered = LocalDateTime.now();
//    private Date registered = new Date();

//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 200)
    @JoinColumn(name = "id") //https://stackoverflow.com/a/62848296/548473
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("loaded DESC")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    private List<Pack> packs;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getLogin(), u.getFirstName(), u.getLastName(),u.getMiddleName(), u.getPhoneNumber(), u.getPassword(), u.isEnabled(), u.getRegistered(), u.getRoles(), u.getPacks());
    }

    public User(Integer id, String login, String firstName, String lastName, String middleName, String phoneNumber, String password, Role role, Role... roles) {
        this(id, login, firstName, lastName, middleName, phoneNumber, password, true,  LocalDateTime.now(), EnumSet.of(role, roles));
    }

    public User(Integer id, String login, String firstName, String lastName, String middleName, String phoneNumber, String password, Collection<Role> roles) {
        this(id, login, firstName, lastName, middleName, phoneNumber, password, true,  LocalDateTime.now(), roles);
    }

    public User(String login, String firstName, String lastName, String middleName, String phoneNumber, String password, boolean enabled, LocalDateTime registered, Collection<Role> roles) {
        super();
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }

    public User(Integer id, String login, String firstName, String lastName, String middleName, String phoneNumber, String password, boolean enabled, LocalDateTime registered, Collection<Role> roles) {
        super(id);
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }

    public User(Integer id, String login, String firstName, String lastName, String middleName, String phoneNumber, String password, boolean enabled, LocalDateTime registered, Collection<Role> roles, Collection<Pack> packs) {
        super(id);
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
        setPacks(packs);
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = new HashSet<>(roles);
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(Collection<Pack> packs) {
        this.packs = new ArrayList<>(packs);
    }
}