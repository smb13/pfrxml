package ru.mshamanin.pfrxml.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.mshamanin.pfrxml.model.Pack;
import ru.mshamanin.pfrxml.model.User;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PackTo extends BaseTo {
    private final String name;
    private final LocalDateTime loaded;
    private final boolean processed;
    private final User user;
    private final List<DataFileTo> dataFileTos;

    public PackTo(Pack pack, List<DataFileTo> dataFileTos) {
        super(pack.id());
        this.name = pack.getName();
        this.loaded = pack.getLoaded();
        this.processed = pack.isProcessed();
        this.user = pack.getUser();
        this.dataFileTos = dataFileTos;
    }
}
