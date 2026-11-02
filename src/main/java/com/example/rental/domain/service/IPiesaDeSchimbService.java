package com.example.rental.domain.service;
import com.example.rental.domain.entity.PiesaDeSchimb;
import java.math.BigDecimal;
import java.util.List;
public interface IPiesaDeSchimbService {
 PiesaDeSchimb addPiesaToLucrare(PiesaDeSchimb piesa,Long lucrareId);
 PiesaDeSchimb updateCantitate(Long piesaId,Integer cantitate);
 void removePiesaFromLucrare(Long piesaId);
 List<PiesaDeSchimb> getPieseByLucrare(Long lucrareId);
 BigDecimal calculeazaCostTotalLucrare(Long lucrareId);
}
