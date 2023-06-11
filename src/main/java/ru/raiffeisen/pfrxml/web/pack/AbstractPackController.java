package ru.raiffeisen.pfrxml.web.pack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import ru.raiffeisen.pfrxml.model.Pack;
import ru.raiffeisen.pfrxml.service.PackService;
import ru.raiffeisen.pfrxml.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;

import static ru.raiffeisen.pfrxml.util.ValidationUtil.assureIdConsistent;
import static ru.raiffeisen.pfrxml.util.ValidationUtil.checkNew;

public abstract class AbstractPackController {

    private static final Logger log = LoggerFactory.getLogger(AbstractPackController.class);

    @Autowired
    private PackService service;

    public Pack get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get pack {} for user {}", id, userId);
        return service.get(id);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete pack {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Pack> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll packs for user {}", userId);
        return service.getAll();
    }

    public Pack create(Pack pack) {
        int userId = SecurityUtil.authUserId();
        checkNew(pack);
        log.info("create pack {} for user {}", pack, userId);
        return service.create(pack, userId);
    }

    public void update(Pack pack, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(pack, id);
        log.info("update pack {} for user {}", pack, userId);
        service.update(pack, userId);
    }


    public List<Pack> getBetween(@Nullable LocalDate startDate, @Nullable LocalDate endDate) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, userId);

        List<Pack> packsDateFiltered = service.getBetweenInclusive(startDate, endDate);
        return packsDateFiltered;
    }
}
