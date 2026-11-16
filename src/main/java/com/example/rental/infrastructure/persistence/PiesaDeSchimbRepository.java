package com.example.rental.infrastructure.persistence;

import com.example.rental.domain.model.PiesaDeSchimb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PiesaDeSchimbRepository extends JpaRepository<PiesaDeSchimb, Long> {

    // findByPropertyName() – cod unic (VR-01)
    Optional<PiesaDeSchimb> findByCodPiesa(String codPiesa);

    // findByPropertyName() – piese ale unui furnizor (furnizor.id)
    List<PiesaDeSchimb> findByFurnizorId(Long furnizorId);

    // @Query cu @Param – piese cu preț maxim dat
    @Query("SELECT p FROM PiesaDeSchimb p WHERE p.pretUnitar <= :pretMax ORDER BY p.pretUnitar")
    List<PiesaDeSchimb> findByPretMaxim(@Param("pretMax") Double pretMax);
}
