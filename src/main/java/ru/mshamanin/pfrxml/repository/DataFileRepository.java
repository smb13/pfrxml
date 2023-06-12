package ru.mshamanin.pfrxml.repository;


import ru.mshamanin.pfrxml.model.DataFile;

import java.util.List;

public interface DataFileRepository {
    DataFile get(int id);

    DataFile get(int id, int packId);

    List<DataFile> getByPack(int packId);

    boolean deleteByPack(int packId);

    DataFile save(DataFile dataFile, int packId);

    boolean delete(int id);

}
