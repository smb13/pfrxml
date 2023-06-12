package ru.mshamanin.pfrxml.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mshamanin.pfrxml.PackTestData;
import ru.mshamanin.pfrxml.model.Pack;
import ru.mshamanin.pfrxml.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mshamanin.pfrxml.UserTestData.ADMIN_ID;
import static ru.mshamanin.pfrxml.UserTestData.USER_ID;

class PackServiceTest extends AbstractServiceTest {

    @Autowired
    protected PackService service;

    @Test
    void get() {
        Pack actual = service.get(PackTestData.ADMIN_PACK_ID);
        PackTestData.PACK_MATCHER.assertMatch(actual, PackTestData.adminPack);
    }

    @Test
    void delete() {
        service.delete(PackTestData.PACK1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(PackTestData.PACK1_ID));
    }

    @Test
    void create() {
        Pack created = service.create(PackTestData.getNewPack(), USER_ID);
        int newId = created.id();
        Pack newPack = PackTestData.getNewPack();
        newPack.setId(newId);
        PackTestData.PACK_MATCHER.assertMatch(created, newPack);
        PackTestData.PACK_MATCHER.assertMatch(service.get(newId), newPack);
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(PackTestData.NOT_FOUND, USER_ID));
    }

    @Test
    void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(PackTestData.PACK1_ID, ADMIN_ID));
    }

    @Test
    void update() {
        Pack updated = PackTestData.getUpdated();
        service.update(updated, USER_ID);
        PackTestData.PACK_MATCHER.assertMatch(service.get(PackTestData.PACK1_ID), PackTestData.getUpdated());
    }

    @Test
    void updateNotOwn() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.update(PackTestData.pack1, ADMIN_ID));
        Assertions.assertEquals("Not found entity with id=" + PackTestData.PACK1_ID, exception.getMessage());
        PackTestData.PACK_MATCHER.assertMatch(service.get(PackTestData.PACK1_ID), PackTestData.pack1);
    }


    @Test
    void getAll() {
        PackTestData.PACK_MATCHER.assertMatch(service.getAll(), PackTestData.packs);
    }

    @Test
    void getBetweenInclusive() {
        PackTestData.PACK_MATCHER.assertMatch(service.getBetweenInclusive(
                        LocalDate.of(2020, Month.DECEMBER, 19),
                        LocalDate.of(2020, Month.DECEMBER, 20)),
                PackTestData.pack1, PackTestData.adminPack);
    }

    @Test
    void getBetweenWithNullDates() {
        PackTestData.PACK_MATCHER.assertMatch(service.getBetweenInclusive(null, null), PackTestData.packs);
    }
}