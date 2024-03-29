package ru.mshamanin.pfrxml.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.mshamanin.pfrxml.model.Pack;
import ru.mshamanin.pfrxml.repository.PackRepository;
import ru.mshamanin.pfrxml.util.PackUtil;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static ru.mshamanin.pfrxml.util.DateTimeUtil.atStartOfDayOrMin;
import static ru.mshamanin.pfrxml.util.DateTimeUtil.atStartOfNextDayOrMax;
import static ru.mshamanin.pfrxml.util.ValidationUtil.checkNotFoundWithId;

@Service
public class PackService {

    private final PackRepository packRepository;

    public PackService(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    public Pack get(int id) {
        return checkNotFoundWithId(packRepository.get(id), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(packRepository.delete(id, userId), id);
    }

    public List<Pack> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate) {
        return packRepository.getBetweenHalfOpen(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate));
    }

    public List<Pack> getAll() {
        return packRepository.getAll();
    }

    public void update(Pack pack, int userId) {
        Assert.notNull(pack, "pack must not be null");
        checkNotFoundWithId(packRepository.save(pack, userId), pack.id());
    }

    public Pack create(Pack pack) {
        Assert.notNull(pack, "pack must not be null");
        return packRepository.save(pack);
    }

    public Pack create(Pack pack, int userId) {
        Assert.notNull(pack, "pack must not be null");
        return packRepository.save(pack, userId);
    }

    public void upload(@NotNull File uploadingFile, int userId) throws IOException {
        Pack pack = PackUtil.unzipPack(uploadingFile);
        packRepository.save(pack, userId);
    }

}
