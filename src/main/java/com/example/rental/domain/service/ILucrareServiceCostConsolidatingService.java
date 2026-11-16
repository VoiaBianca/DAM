package com.example.rental.domain.service;

import com.example.rental.domain.model.LucrareCostView;
import com.example.rental.domain.model.LucrareService;

public interface ILucrareServiceCostConsolidatingService {
    LucrareCostView consolideazaCostPiese(LucrareService lucrare);
}
