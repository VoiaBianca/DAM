package com.example.rental.infrastructure.persistence;

import com.example.rental.domain.model.PiesaDeSchimb;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository @Transactional
public class PiesaDeSchimbRepositoryJPA implements IPiesaDeSchimbRepository {
    @Autowired
    private EntityManager em;

    @Override public PiesaDeSchimb save(PiesaDeSchimb p) {
        if (p.getId() == null) { em.persist(p); return p; }
        return em.merge(p);
    }
    @Override public Optional<PiesaDeSchimb> findById(Long id) {
        return Optional.ofNullable(em.find(PiesaDeSchimb.class, id));
    }
    @Override public List<PiesaDeSchimb> findAll() {
        return em.createQuery("SELECT p FROM PiesaDeSchimb p", PiesaDeSchimb.class).getResultList();
    }
    @Override public Optional<PiesaDeSchimb> findByCodPiesa(String cod) {
        return em.createQuery("SELECT p FROM PiesaDeSchimb p WHERE p.codPiesa = :cod", PiesaDeSchimb.class)
                 .setParameter("cod", cod).getResultStream().findFirst();
    }
    @Override public List<PiesaDeSchimb> findByFurnizorId(Long furnizorId) {
        return em.createQuery("SELECT p FROM PiesaDeSchimb p WHERE p.furnizor.id = :fid", PiesaDeSchimb.class)
                 .setParameter("fid", furnizorId).getResultList();
    }
    @Override public void delete(PiesaDeSchimb p) { em.remove(em.contains(p) ? p : em.merge(p)); }
}
