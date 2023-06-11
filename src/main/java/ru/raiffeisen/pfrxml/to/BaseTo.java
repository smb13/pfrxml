package ru.raiffeisen.pfrxml.to;

import ru.raiffeisen.pfrxml.HasId;

public abstract class BaseTo implements HasId {
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}