package ru.raiffeisen.pfrxml.repository.jpa;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.raiffeisen.pfrxml.model.DataFile;
import ru.raiffeisen.pfrxml.model.Pack;
import ru.raiffeisen.pfrxml.model.User;
import ru.raiffeisen.pfrxml.repository.PackRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaPackRepository implements PackRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Pack save(Pack pack, int userId) {
        pack.setUser(em.getReference(User.class, userId));
        if (pack.isNew()) {
            em.persist(pack);
            return pack;
        } else if (get(pack.id(), userId) == null) {
            return null;
        }
        return em.merge(pack);
    }

    @Override
    @Transactional
    public Pack save(Pack pack) {
        if (pack.isNew()) {
            em.persist(pack);
            return pack;
        } else if (get(pack.id()) == null) {
            return null;
        }
        return em.merge(pack);
    }

    @Override
    @Transactional
    @Modifying
    public boolean delete(int id, int userId) {
        Query nq = em.createNamedQuery(Pack.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId);
        return nq.executeUpdate() != 0;
//            return em.createNamedQuery(Pack.DELETE)
//                    .setParameter("id", id)
//                    .setParameter("userId", userId)
//                    .executeUpdate() != 0;
    }

    @Override
    public Pack get(int id) {
        return em.find(Pack.class, id);
    }

    @Override
    public Pack get(int id, int userId) {
        Pack pack = em.find(Pack.class, id);
        return pack != null && pack.getUser().getId() == userId ? pack : null;
    }

    @Override
    public List<Pack> getAll() {
        return em.createNamedQuery(Pack.ALL_SORTED, Pack.class)
                .getResultList();
    }

    @Override
    public List<Pack> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return em.createNamedQuery(Pack.GET_BETWEEN, Pack.class)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .getResultList();
    }

}
