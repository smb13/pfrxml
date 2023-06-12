package ru.mshamanin.pfrxml.web.datafile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mshamanin.pfrxml.model.DataFile;
import ru.mshamanin.pfrxml.service.DataFileService;

import java.util.List;

import static ru.mshamanin.pfrxml.util.ValidationUtil.assureIdConsistent;
import static ru.mshamanin.pfrxml.util.ValidationUtil.checkNew;

public abstract class AbstractDataFileController {

    private static final Logger log = LoggerFactory.getLogger(AbstractDataFileController.class);

    @Autowired
    private DataFileService service;

    public DataFile get(int id, int packId) {
        log.info("get dataFile {} for pack {}", id, packId);
        return service.get(id, packId);
    }

    public DataFile get(int id) {
        log.info("get dataFile {}", id);
        return service.get(id);
    }

    public List<DataFile> getByPack(int packId) {
        log.info("get dataFiles for pack {}", packId);
        return service.getByPack(packId);
    }

    public void delete(int id) {
        log.info("delete dataFile {} ", id);
        service.delete(id);
    }

    public void deleteByPack(int packId) {
        log.info("delete dataFiles for pack {} ", packId);
        service.deleteByPack(packId);
    }

    public DataFile create(DataFile dataFile, int packId) {
        checkNew(dataFile);
        log.info("create {} for pack {}", dataFile, packId);
        return service.create(dataFile, packId);
    }

    public void update(DataFile dataFile, int id, int packId) {
        assureIdConsistent(dataFile, id);
        log.info("update {} for pack {}", dataFile, packId);
        service.update(dataFile, packId);
    }

}
