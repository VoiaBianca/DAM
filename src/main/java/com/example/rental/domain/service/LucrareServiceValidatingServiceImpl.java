package com.example.rental.domain.service;

import com.example.rental.domain.model.LinieLucrare;
import com.example.rental.domain.model.LucrareService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LucrareServiceValidatingServiceImpl implements ILucrareServiceValidatingService {
    private final ILinieLucrareValidatingService linieValidatingService;

    public LucrareServiceValidatingServiceImpl(ILinieLucrareValidatingService linieValidatingService) {
        this.linieValidatingService = linieValidatingService;
    }

    @Override
    public void validate(LucrareService lucrare) {
        lucrare.getLinii().forEach(linieValidatingService::validate);          // VR-03, VR-05
        Set<String> coduri = new HashSet<>();
        for (LinieLucrare l : lucrare.getLinii())                              // VR-06
            if (!coduri.add(l.getPiesa().getCodPiesa()))
                throw new IllegalStateException(
                    "VR-06: Piesa " + l.getPiesa().getCodPiesa() + " exista deja in lucrare");
    }
}
