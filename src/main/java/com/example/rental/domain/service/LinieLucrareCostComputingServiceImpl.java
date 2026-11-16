package com.example.rental.domain.service;

import com.example.rental.domain.model.LinieLucrare;
import org.springframework.stereotype.Service;

@Service
public class LinieLucrareCostComputingServiceImpl implements ILinieLucrareCostComputingService {
    @Override
    public Double calculeazaCostLinie(LinieLucrare linie) {            // CR-01
        if (linie.getCantitate() == null || linie.getPiesa() == null
                || linie.getPiesa().getPretUnitar() == null) return 0.0;
        return linie.getCantitate() * linie.getPiesa().getPretUnitar();
    }
}
