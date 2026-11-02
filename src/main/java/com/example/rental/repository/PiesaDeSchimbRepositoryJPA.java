package com.example.rental.repository;
import com.example.rental.domain.entity.PiesaDeSchimb;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Repository @Transactional public class PiesaDeSchimbRepositoryJPA implements IPiesaDeSchimbRepository {
 @PersistenceContext private EntityManager em;
 public PiesaDeSchimb save(PiesaDeSchimb p){if(p.getId()==null){em.persist(p);return p;}return em.merge(p);}
 @Transactional(readOnly=true) public Optional<PiesaDeSchimb> findById(Long id){return Optional.ofNullable(em.find(PiesaDeSchimb.class,id));}
 @Transactional(readOnly=true) public List<PiesaDeSchimb> findByCodPiesa(String cod){return em.createQuery("select p from PiesaDeSchimb p where p.codPiesa=:cod",PiesaDeSchimb.class).setParameter("cod",cod).getResultList();}
 @Transactional(readOnly=true) public List<PiesaDeSchimb> findByLucrareServiceId(Long id){return em.createQuery("select p from PiesaDeSchimb p where p.lucrareService.id=:id",PiesaDeSchimb.class).setParameter("id",id).getResultList();}
 public void delete(PiesaDeSchimb p){em.remove(em.contains(p)?p:em.merge(p));}
}
