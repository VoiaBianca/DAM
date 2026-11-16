package com.example.rental.domain.service;

import com.example.rental.domain.model.LinieLucrare;
import com.example.rental.domain.model.LucrareService;
import com.example.rental.domain.model.PiesaDeSchimb;

public interface ILucrareServiceEntityFactory {
    LucrareService creeazaLucrare(String tipLucrare, String descriere, Double manoperaEstimata);
    LinieLucrare creeazaLinie(LucrareService lucrare, PiesaDeSchimb piesa, Integer cantitate);
}
