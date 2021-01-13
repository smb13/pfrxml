package ru.raiffeisen.pfrxml.repository;


import ru.raiffeisen.pfrxml.model.DataFile;

import java.time.LocalDateTime;
import java.util.List;

public interface DataFileRepository {

    DataFile save(DataFile dataFile);

    boolean delete(int id);

    DataFile get(int id);

    List<DataFile> getAllByPack(int packId);

    List<DataFile> getAll();
}
