package com.example.rental.repository;
import com.example.rental.domain.entity.PiesaDeSchimb;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.*;
public interface PiesaDeSchimbRepository extends JpaRepository<PiesaDeSchimb,Long>{
 List<PiesaDeSchimb> findByCodPiesa(String codPiesa);
 List<PiesaDeSchimb> findByFurnizor(String furnizor);
 List<PiesaDeSchimb> findByLucrareServiceId(Long lucrareId);
 boolean existsByLucrareServiceIdAndCodPiesa(Long lucrareId,String codPiesa);
 @Query("select p from PiesaDeSchimb p where p.cantitate > 0") List<PiesaDeSchimb> findPieseCuCantitateUtilizata();
 @Query("select p from PiesaDeSchimb p where p.lucrareService.id=:id and p.cantitate>0") List<PiesaDeSchimb> findPieseUtilizateInLucrare(@Param("id") Long id);
}
