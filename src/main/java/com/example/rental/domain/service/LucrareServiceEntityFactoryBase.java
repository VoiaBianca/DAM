package com.example.rental.domain.service;

import com.example.rental.domain.model.LinieLucrare;
import com.example.rental.domain.model.LucrareService;
import com.example.rental.domain.model.PiesaDeSchimb;
import org.springframework.stereotype.Component;

// Factory: fără AtomicLong și fără setId (identitatea o atribuie @GeneratedValue)
@Component
public class LucrareServiceEntityFactoryBase implements ILucrareServiceEntityFactory {
    @Override public LucrareService creeazaLucrare(String tip, String descriere, Double manopera) {
        LucrareService l = new LucrareService();
        l.setTipLucrare(tip); l.setDescriere(descriere); l.setManoperaEstimata(manopera);
        return l;
    }
    @Override public LinieLucrare creeazaLinie(LucrareService lucrare, PiesaDeSchimb piesa, Integer cantitate) {
        LinieLucrare linie = new LinieLucrare();
        linie.setLucrareService(lucrare); linie.setPiesa(piesa); linie.setCantitate(cantitate);
        return linie;
    }
}
