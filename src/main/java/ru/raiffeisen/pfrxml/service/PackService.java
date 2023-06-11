package ru.raiffeisen.pfrxml.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.raiffeisen.pfrxml.model.Pack;
import ru.raiffeisen.pfrxml.repository.DataFileRepository;
import ru.raiffeisen.pfrxml.repository.PackRepository;
import ru.raiffeisen.pfrxml.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.raiffeisen.pfrxml.util.DateTimeUtil.atStartOfDayOrMin;
import static ru.raiffeisen.pfrxml.util.DateTimeUtil.atStartOfNextDayOrMax;
import static ru.raiffeisen.pfrxml.util.ValidationUtil.checkNotFoundWithId;

@Service
public class PackService  {

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

}
