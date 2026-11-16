package com.example.rental.application.service;

import com.example.rental.domain.model.PiesaDeSchimb;
import com.example.rental.domain.service.IPiesaDeSchimbValidatingService;
import com.example.rental.infrastructure.persistence.PiesaDeSchimbRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class CatalogPieseServiceImpl implements ICatalogPieseService {
    private final IPiesaDeSchimbValidatingService validatingService;
    private final PiesaDeSchimbRepository piesaRepository;          // Spring Data JPA

    public CatalogPieseServiceImpl(IPiesaDeSchimbValidatingService validatingService,
                                   PiesaDeSchimbRepository piesaRepository) {
        this.validatingService = validatingService;
        this.piesaRepository = piesaRepository;
    }

    @Override
    public PiesaDeSchimb creeazaPiesa(PiesaDeSchimb piesa) {
        validatingService.validate(piesa);                           // VR-01, VR-02, VR-04
        if (piesaRepository.findByCodPiesa(piesa.getCodPiesa()).isPresent())
            throw new IllegalStateException("VR-01: Cod piesa deja existent");
        return piesaRepository.save(piesa);
    }
}
