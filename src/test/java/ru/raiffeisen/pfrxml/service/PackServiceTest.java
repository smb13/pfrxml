package ru.raiffeisen.pfrxml.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.raiffeisen.pfrxml.model.Pack;
import ru.raiffeisen.pfrxml.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static ru.raiffeisen.pfrxml.PackTestData.*;
import static ru.raiffeisen.pfrxml.UserTestData.ADMIN_ID;
import static ru.raiffeisen.pfrxml.UserTestData.USER_ID;

class PackServiceTest extends AbstractServiceTest{

    @Autowired
    protected PackService service;

    @Test
    void get() {
        Pack actual = service.get(ADMIN_PACK_ID);
        PACK_MATCHER.assertMatch(actual, adminPack);
    }

    @Test
    void delete() {
        service.delete(PACK1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(PACK1_ID));
    }

    @Test
    void create() {
        Pack created = service.create(getNewPack(), USER_ID);
        int newId = created.id();
        Pack newPack = getNewPack();
        newPack.setId(newId);
        PACK_MATCHER.assertMatch(created, newPack);
        PACK_MATCHER.assertMatch(service.get(newId), newPack);
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(PACK1_ID, ADMIN_ID));
    }
    
    @Test
    void update() {
        Pack updated = getUpdated();
        service.update(updated, USER_ID);
        PACK_MATCHER.assertMatch(service.get(PACK1_ID), getUpdated());
    }

    @Test
    void updateNotOwn() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.update(pack1, ADMIN_ID));
        Assertions.assertEquals("Not found entity with id=" + PACK1_ID, exception.getMessage());
        PACK_MATCHER.assertMatch(service.get(PACK1_ID), pack1);
    }


    @Test
    void getAll() {
        PACK_MATCHER.assertMatch(service.getAll(), packs);
    }

    @Test
    void getBetweenInclusive() {
        PACK_MATCHER.assertMatch(service.getBetweenInclusive(
                LocalDate.of(2020, Month.DECEMBER, 19),
                LocalDate.of(2020, Month.DECEMBER, 20)),
                pack1, adminPack);
    }

    @Test
    void getBetweenWithNullDates() {
        PACK_MATCHER.assertMatch(service.getBetweenInclusive(null, null), packs);
    }
}