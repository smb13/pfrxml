package ru.raiffeisen.pfrxml.repository.jpa;

import ru.raiffeisen.pfrxml.model.DataFile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.raiffeisen.pfrxml.repository.DataFileRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaDataFileRepository implements DataFileRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public DataFile save(DataFile dataFile) {
        if (dataFile.isNew()) {
            em.persist(dataFile);
            return dataFile;
        } else if (get(dataFile.id()) == null) {
            return null;
        }
        return em.merge(dataFile);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(DataFile.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public DataFile get(int id) {
        DataFile dataFile = em.find(DataFile.class, id);
        return dataFile;
    }

    @Override
    public List<DataFile> getAllByPack(int packId) {
        return em.createNamedQuery(DataFile.ALL_BY_PACK)
                .setParameter("packId", packId)
                .getResultList();
    }

    @Override
    public List<DataFile> getAll() {
        return em.createNamedQuery(DataFile.ALL_SORTED, DataFile.class)
                .getResultList();
    }


}