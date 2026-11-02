package com.example.rental.domain.service;
import com.example.rental.domain.entity.PiesaDeSchimb;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Collection;
@Service public class PiesaDeSchimbComputationService {
 public BigDecimal calculeazaCostTotal(PiesaDeSchimb p){return p.calculeazaCostTotal();}
 public BigDecimal calculeazaCostTotalLucrare(Collection<PiesaDeSchimb> piese){return piese.stream().map(PiesaDeSchimb::calculeazaCostTotal).reduce(BigDecimal.ZERO,BigDecimal::add);}
}
