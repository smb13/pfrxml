package ru.raiffeisen.pfrxml.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.raiffeisen.pfrxml.model.Pack;
import ru.raiffeisen.pfrxml.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.raiffeisen.pfrxml.PackTestData.*;
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
    void getBetweenInclusive() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void create() {
        Pack created = service.create(getNew(), USER_ID);
        int newId = created.id();
        Pack newPack = getNew();
        newPack.setId(newId);
        PACK_MATCHER.assertMatch(created, newPack);
        PACK_MATCHER.assertMatch(service.get(newId), newPack);
    }

    @Test
    void testCreate() {
    }
}