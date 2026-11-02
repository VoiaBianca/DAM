package com.example.rental.domain.service;
import com.example.rental.domain.entity.*;
import com.example.rental.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
@Service @Transactional public class PiesaDeSchimbServiceImpl implements IPiesaDeSchimbService {
 private final PiesaDeSchimbValidationService validation;
 private final PiesaDeSchimbComputationService computation;
 private final PiesaDeSchimbRepository piese;
 private final LucrareServiceRepository lucrari;
 public PiesaDeSchimbServiceImpl(PiesaDeSchimbValidationService validation,PiesaDeSchimbComputationService computation,PiesaDeSchimbRepository piese,LucrareServiceRepository lucrari){this.validation=validation;this.computation=computation;this.piese=piese;this.lucrari=lucrari;}
 public PiesaDeSchimb addPiesaToLucrare(PiesaDeSchimb piesa,Long lucrareId){LucrareService lucrare=lucrari.findById(lucrareId).orElseThrow(()->new IllegalArgumentException("Lucrare inexistenta"));validation.validate(piesa);if(piese.existsByLucrareServiceIdAndCodPiesa(lucrareId,piesa.getCodPiesa()))throw new IllegalStateException("Codul exista deja in aceasta lucrare");lucrare.addPiesa(piesa);return piese.save(piesa);}
 public PiesaDeSchimb updateCantitate(Long id,Integer cant){PiesaDeSchimb p=piese.findById(id).orElseThrow(()->new IllegalArgumentException("Piesa inexistenta"));p.setCantitate(cant);validation.validate(p);return piese.save(p);}
 public void removePiesaFromLucrare(Long id){PiesaDeSchimb p=piese.findById(id).orElseThrow(()->new IllegalArgumentException("Piesa inexistenta"));p.getLucrareService().removePiesa(p);piese.delete(p);}
 @Transactional(readOnly=true) public List<PiesaDeSchimb> getPieseByLucrare(Long id){return piese.findByLucrareServiceId(id);}
 @Transactional(readOnly=true) public BigDecimal calculeazaCostTotalLucrare(Long id){return computation.calculeazaCostTotalLucrare(piese.findByLucrareServiceId(id));}
}
