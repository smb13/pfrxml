package ru.raiffeisen.pfrxml.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "packs")
public class Pack extends AbstractBaseEntity {
    @Column(name = "name", nullable = false, unique = false)
    @NotBlank
    @Size(max = 12)
    private String name;

    @Column(name = "loaded", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime loaded = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    @NotNull
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "file")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    private List<File> files;

    public Pack(Integer id, String name, User user) {
        super(id);
        this.name = name;
        this.loaded = LocalDateTime.now();
        this.user = user;
    }

    public Pack() {
    }

    public Pack(Pack p) {
        this.id = p.getId();
        this.name = p.getName();
        this.loaded = p.getLoaded();
        this.user = p.getUser();
        this.files = p.getFiles();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLoaded() {
        return loaded;
    }

    public void setLoaded(LocalDateTime loaded) {
        this.loaded = loaded;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
