package com.example.rental.domain.service;

import com.example.rental.domain.model.LucrareCostView;
import com.example.rental.domain.model.LucrareService;
import org.springframework.stereotype.Service;

@Service
public class LucrareServiceCostConsolidatingServiceImpl implements ILucrareServiceCostConsolidatingService {
    private final ILinieLucrareCostComputingService costLinieService;

    public LucrareServiceCostConsolidatingServiceImpl(ILinieLucrareCostComputingService costLinieService) {
        this.costLinieService = costLinieService;
    }

    @Override
    public LucrareCostView consolideazaCostPiese(LucrareService lucrare) {   // CR-02
        double total = lucrare.getLinii().stream()
            .mapToDouble(costLinieService::calculeazaCostLinie).sum();
        return new LucrareCostView(lucrare.getId(), lucrare.getLinii().size(), total);
    }
}
