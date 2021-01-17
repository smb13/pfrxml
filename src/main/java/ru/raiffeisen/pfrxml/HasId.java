package ru.raiffeisen.pfrxml;

import org.springframework.util.Assert;

public interface HasId {
    Integer getId();

    void setId(int id);

    default boolean isNew() {
        return getId() == null;
    }

    // doesn't work for hibernate lazy proxy
    default int id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}