package com.example.rental.application.service;

import com.example.rental.domain.model.LinieLucrare;
import com.example.rental.domain.model.LucrareCostView;
import com.example.rental.domain.model.LucrareService;
import com.example.rental.domain.model.PiesaDeSchimb;
import com.example.rental.domain.service.ILucrareServiceCostConsolidatingService;
import com.example.rental.domain.service.ILucrareServiceEntityFactory;
import com.example.rental.domain.service.ILucrareServiceValidatingService;
import com.example.rental.domain.service.PiesaLucrareEvent;
import com.example.rental.infrastructure.persistence.LucrareServiceRepository;
import com.example.rental.infrastructure.persistence.PiesaDeSchimbRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class LucrarePieseServiceImpl implements ILucrarePieseService {
    private final LucrareServiceRepository lucrareRepository;       // Spring Data JPA
    private final PiesaDeSchimbRepository piesaRepository;          // Spring Data JPA
    private final ILucrareServiceEntityFactory lucrareFactory;
    private final ILucrareServiceValidatingService validatingService;
    private final ILucrareServiceCostConsolidatingService costService;
    private final ApplicationEventPublisher publisher;

    public LucrarePieseServiceImpl(LucrareServiceRepository lucrareRepository,
            PiesaDeSchimbRepository piesaRepository,
            ILucrareServiceEntityFactory lucrareFactory,
            ILucrareServiceValidatingService validatingService,
            ILucrareServiceCostConsolidatingService costService,
            ApplicationEventPublisher publisher) {
        this.lucrareRepository = lucrareRepository;
        this.piesaRepository = piesaRepository;
        this.lucrareFactory = lucrareFactory;
        this.validatingService = validatingService;
        this.costService = costService;
        this.publisher = publisher;
    }

    @Override
    public LucrareService adaugaPiesaLaLucrare(Long lucrareId, Long piesaId, Integer cantitate) {
        LucrareService lucrare = lucrareRepository.findByIdCuLinii(lucrareId)
            .orElseThrow(() -> new IllegalArgumentException("Lucrarea nu exista"));
        PiesaDeSchimb piesa = piesaRepository.findById(piesaId)
            .orElseThrow(() -> new IllegalArgumentException("Piesa nu exista"));
        lucrare.adaugaLinie(lucrareFactory.creeazaLinie(lucrare, piesa, cantitate));
        validatingService.validate(lucrare);          // VR-03, VR-05, VR-06 – la eroare: rollback automat
        LucrareService salvata = lucrareRepository.save(lucrare);
        publisher.publishEvent(new PiesaLucrareEvent(this, "ADAUGA_PIESA", lucrareId));
        return salvata;
    }

    // modificaCantitate si eliminaPiesaDinLucrare urmeaza acelasi tipar
    @Override
    public LucrareService modificaCantitate(Long lucrareId, Long piesaId, Integer cantitate) {
        LucrareService lucrare = gasesteLucrare(lucrareId);
        LinieLucrare linie = gasesteLinie(lucrare, piesaId);
        linie.setCantitate(cantitate);
        validatingService.validate(lucrare);          // VR-03, VR-05, VR-06 – la eroare: rollback automat
        LucrareService salvata = lucrareRepository.save(lucrare);
        publisher.publishEvent(new PiesaLucrareEvent(this, "MODIFICA_CANTITATE", lucrareId));
        return salvata;
    }

    @Override
    public LucrareService eliminaPiesaDinLucrare(Long lucrareId, Long piesaId) {
        LucrareService lucrare = gasesteLucrare(lucrareId);
        lucrare.eliminaLinie(gasesteLinie(lucrare, piesaId));     // orphanRemoval șterge rândul
        LucrareService salvata = lucrareRepository.save(lucrare);
        publisher.publishEvent(new PiesaLucrareEvent(this, "ELIMINA_PIESA", lucrareId));
        return salvata;
    }

    @Override @Transactional(readOnly = true)
    public LucrareCostView calculeazaCostPiese(Long lucrareId) {
        LucrareService lucrare = lucrareRepository.findByIdCuLinii(lucrareId)
            .orElseThrow(() -> new IllegalArgumentException("Lucrarea nu exista"));
        return costService.consolideazaCostPiese(lucrare);   // CR-02
    }

    private LucrareService gasesteLucrare(Long lucrareId) {
        return lucrareRepository.findByIdCuLinii(lucrareId)
            .orElseThrow(() -> new IllegalArgumentException("Lucrarea nu exista"));
    }

    private LinieLucrare gasesteLinie(LucrareService lucrare, Long piesaId) {
        return lucrare.getLinii().stream()
            .filter(l -> l.getPiesa().getId().equals(piesaId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Piesa nu exista in lucrare"));
    }
}
