package com.example.rental.infrastructure.persistence;

import com.example.rental.domain.model.PiesaDeSchimb;

import java.util.List;
import java.util.Optional;

public interface IPiesaDeSchimbRepository extends IRepository<PiesaDeSchimb, Long> {
    Optional<PiesaDeSchimb> findByCodPiesa(String codPiesa);
    List<PiesaDeSchimb> findByFurnizorId(Long furnizorId);   // înlocuiește findByLucrareServiceId
}
