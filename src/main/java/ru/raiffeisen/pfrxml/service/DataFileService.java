package ru.raiffeisen.pfrxml.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.raiffeisen.pfrxml.model.DataFile;
import ru.raiffeisen.pfrxml.repository.DataFileRepository;

import java.util.List;

import static ru.raiffeisen.pfrxml.util.ValidationUtil.checkNotFound;
import static ru.raiffeisen.pfrxml.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DataFileService  {

    private final DataFileRepository repository;

    public DataFileService(DataFileRepository repository) {
        this.repository = repository;
    }

    public DataFile get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public DataFile get(int id, int packId) {
        return checkNotFoundWithId(repository.get(id, packId), id);
    }

    public List<DataFile> getByPack(int packId) {
        return checkNotFoundWithId(repository.getByPack(packId), packId);
    }


    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void deleteByPack(int packId) {
        checkNotFoundWithId(repository.deleteByPack(packId), packId);
    }

    public DataFile create(DataFile dataFile, int packId) {
        Assert.notNull(dataFile, "dataFile must not be null");
        return repository.save(dataFile, packId);
    }

    public void update(DataFile dataFile, int packId) {
        Assert.notNull(dataFile, "dataFile must not be null");
        checkNotFoundWithId(repository.save(dataFile, packId), dataFile.id());
    }
}
