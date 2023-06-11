package ru.raiffeisen.pfrxml.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.raiffeisen.pfrxml.model.DataFile;
import ru.raiffeisen.pfrxml.model.Pack;
import ru.raiffeisen.pfrxml.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.raiffeisen.pfrxml.DataFileTestData.*;
import static ru.raiffeisen.pfrxml.PackTestData.*;
import static ru.raiffeisen.pfrxml.UserTestData.USER_ID;

class DataFileServiceTest extends AbstractServiceTest{

    @Autowired
    protected DataFileService service;

    @Test
    void get() {
        DataFile actual = service.get(DATA_FILE1_ID);
        DATA_FILE_MATCHER.assertMatch(actual, dataFile1);
    }

    @Test
    void getWithPack() {
        DataFile actual = service.get(DATA_FILE1_ID, PACK1_ID);
        DATA_FILE_MATCHER.assertMatch(actual, dataFile1);
    }

    @Test
    void getWithWrongPack() {
        assertThrows(NotFoundException.class, () -> service.get(DATA_FILE1_ID, PACK2_ID));
    }

    @Test
    void getWithWrongId() {
        assertThrows(NotFoundException.class, () -> service.get(DATA_FILE_NOT_FOUND));
    }

    @Test
    void getByPack() {
        DATA_FILE_MATCHER.assertMatch(service.getByPack(PACK1_ID), pack1DataFiles);
    }

    @Test
    void create() {
        DataFile created = service.create(getNewDataFile(), PACK2_ID);
        int newId = created.id();
        DataFile newDataFile = getNewDataFile();
        newDataFile.setId(newId);
        DATA_FILE_MATCHER.assertMatch(created, newDataFile);
        DATA_FILE_MATCHER.assertMatch(service.get(newId), newDataFile);
    }

    @Test
    void delete() {
        service.delete(DATA_FILE1_ID);
        assertThrows(NotFoundException.class, () -> service.get(DATA_FILE1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(DATA_FILE_NOT_FOUND));
    }

    @Test
    void deleteByPack() {
        service.deleteByPack(ADMIN_PACK_ID);
        assertThrows(NotFoundException.class, () -> service.getByPack(ADMIN_PACK_ID));
    }

    @Test
    void update() {
    }
}