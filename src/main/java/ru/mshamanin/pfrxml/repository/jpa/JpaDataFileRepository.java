package ru.mshamanin.pfrxml.repository.jpa;

import ru.mshamanin.pfrxml.model.DataFile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mshamanin.pfrxml.model.Pack;
import ru.mshamanin.pfrxml.repository.DataFileRepository;

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
    public DataFile save(DataFile dataFile, int packId) {
        dataFile.setPack(em.getReference(Pack.class, packId));
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
    public boolean deleteByPack(int packId){
        return em.createNamedQuery(DataFile.DELETE_BY_PACK)
                .setParameter("packId", packId)
                .executeUpdate() != 0;
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
        return em.find(DataFile.class, id);
    }

    @Override
    public List<DataFile> getByPack(int packId) {
        List <DataFile> result = em.createNamedQuery(DataFile.ALL_BY_PACK, DataFile.class)
                .setParameter("packId", packId)
                .getResultList();
        return result.size() == 0 ? null : result;
    }

    @Override
    public DataFile get(int id, int packId) {
        List <DataFile> result = em.createNamedQuery(DataFile.GET_WITH_PACK, DataFile.class)
                .setParameter("id", id)
                .setParameter("packId", packId)
                .getResultList();
        return result.size() == 0 ? null : result.get(0);
//                .getSingleResult();
    }

}