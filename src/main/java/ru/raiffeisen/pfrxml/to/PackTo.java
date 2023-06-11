package ru.raiffeisen.pfrxml.to;

import ru.raiffeisen.pfrxml.model.Pack;
import java.time.LocalDateTime;

public class PackTo extends BaseTo{
    private final String name;
    private final LocalDateTime loaded;
    private final boolean processed;
    private final String username;

    public PackTo(Integer id, String name, LocalDateTime loaded, boolean processed, String username) {
        super(id);
        this.name = name;
        this.loaded = loaded;
        this.processed = processed;
        this.username = username;
    }

    public PackTo(Pack pack) {
        super(pack.id());
        this.name = pack.getName();
        this.loaded = pack.getLoaded();
        this.processed = pack.isProcessed();
        this.username = pack.getUser().getLogin();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getLoaded() {
        return loaded;
    }

    public boolean isProcessed() {
        return processed;
    }

    public String getUsername() {
        return username;
    }
}
