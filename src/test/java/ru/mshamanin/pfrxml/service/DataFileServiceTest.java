package ru.mshamanin.pfrxml.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mshamanin.pfrxml.DataFileTestData;
import ru.mshamanin.pfrxml.PackTestData;
import ru.mshamanin.pfrxml.model.DataFile;
import ru.mshamanin.pfrxml.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class DataFileServiceTest extends AbstractServiceTest{

    @Autowired
    protected DataFileService service;

    @Test
    void get() {
        DataFile actual = service.get(DataFileTestData.DATA_FILE1_ID);
        DataFileTestData.DATA_FILE_MATCHER.assertMatch(actual, DataFileTestData.dataFile1);
    }

    @Test
    void getWithPack() {
        DataFile actual = service.get(DataFileTestData.DATA_FILE1_ID, PackTestData.PACK1_ID);
        DataFileTestData.DATA_FILE_MATCHER.assertMatch(actual, DataFileTestData.dataFile1);
    }

    @Test
    void getWithWrongPack() {
        assertThrows(NotFoundException.class, () -> service.get(DataFileTestData.DATA_FILE1_ID, DataFileTestData.PACK2_ID));
    }

    @Test
    void getWithWrongId() {
        assertThrows(NotFoundException.class, () -> service.get(DataFileTestData.DATA_FILE_NOT_FOUND));
    }

    @Test
    void getByPack() {
        DataFileTestData.DATA_FILE_MATCHER.assertMatch(service.getByPack(PackTestData.PACK1_ID), DataFileTestData.pack1DataFiles);
    }

    @Test
    void create() {
        DataFile created = service.create(DataFileTestData.getNewDataFile(), DataFileTestData.PACK2_ID);
        int newId = created.id();
        DataFile newDataFile = DataFileTestData.getNewDataFile();
        newDataFile.setId(newId);
        DataFileTestData.DATA_FILE_MATCHER.assertMatch(created, newDataFile);
        DataFileTestData.DATA_FILE_MATCHER.assertMatch(service.get(newId), newDataFile);
    }

    @Test
    void delete() {
        service.delete(DataFileTestData.DATA_FILE1_ID);
        assertThrows(NotFoundException.class, () -> service.get(DataFileTestData.DATA_FILE1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(DataFileTestData.DATA_FILE_NOT_FOUND));
    }

    @Test
    void deleteByPack() {
        service.deleteByPack(PackTestData.ADMIN_PACK_ID);
        assertThrows(NotFoundException.class, () -> service.getByPack(PackTestData.ADMIN_PACK_ID));
    }

    @Test
    void update() {
    }
}