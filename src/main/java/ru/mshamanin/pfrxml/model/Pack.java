package ru.mshamanin.pfrxml.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Pack.ALL_SORTED, query = "SELECT p FROM Pack p ORDER BY p.loaded DESC"),
        @NamedQuery(name = Pack.DELETE, query = "DELETE FROM Pack p WHERE p.id=:id AND p.user.id=:userId"),
        @NamedQuery(name = Pack.GET_BETWEEN, query = "SELECT p FROM Pack p WHERE p.loaded >= :startDateTime AND p.loaded < :endDateTime ORDER BY p.loaded DESC")
})

@Entity
@Table(name = "packs")
public class Pack extends AbstractBaseEntity {

    public static final String ALL_SORTED = "Pack.getAll";
    public static final String DELETE = "Pack.delete";
    public static final String GET_BETWEEN = "Pack.getBetween";

    @Column(name = "name", nullable = false, unique = false)
    @NotBlank
    @Size(max = 13)
    private String name;

    @Column(name = "loaded", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime loaded = LocalDateTime.now();

    @Column(name = "processed", nullable = false, columnDefinition = "bool default false")
    private boolean processed = true;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    @NotNull
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pack")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<DataFile> dataFiles;

    public Pack(Integer id, String name, LocalDateTime loaded, boolean processed) {
        super(id);
        this.name = name;
        this.loaded = loaded;
        this.processed = processed;
    }

    public Pack(Integer id, String name, User user) {
        super(id);
        this.name = name;
        this.loaded = LocalDateTime.now();
        this.user = user;
    }

    public Pack(String name, User user) {
        super();
        this.name = name;
        this.loaded = LocalDateTime.now();
        this.user = user;
        this.processed = false;
    }

    public Pack() {
    }

    public Pack(Pack p) {
        this.id = p.getId();
        this.name = p.getName();
        this.loaded = p.getLoaded();
        this.user = p.getUser();
        this.processed = p.isProcessed();
        this.dataFiles = p.getFiles();
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
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

    public List<DataFile> getFiles() {
        return dataFiles;
    }

    public void setFiles(List<DataFile> dataFiles) {
        this.dataFiles = dataFiles;
    }
}
