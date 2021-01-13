package ru.raiffeisen.pfrxml.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.raiffeisen.pfrxml.model.DataFile;
import ru.raiffeisen.pfrxml.repository.DataFileRepository;

import java.util.List;

import static ru.raiffeisen.pfrxml.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DataFileService  {

    private final DataFileRepository repository;

    public DataFileService(DataFileRepository repository) {
        this.repository = repository;
    }

    public DataFile get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id), id);
    }

        public List<DataFile> getAll() {
        return repository.getAll();
    }

    public void update(DataFile dataFile) {
        Assert.notNull(dataFile, "dataFile must not be null");
        checkNotFoundWithId(repository.save(dataFile), dataFile.id());
    }

    public DataFile create(DataFile dataFile, int userId) {
        Assert.notNull(dataFile, "dataFile must not be null");
        return repository.save(dataFile);
    }
}
