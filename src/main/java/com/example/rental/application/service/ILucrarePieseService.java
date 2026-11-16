package com.example.rental.application.service;

import com.example.rental.domain.model.LucrareCostView;
import com.example.rental.domain.model.LucrareService;

public interface ILucrarePieseService {
    LucrareService adaugaPiesaLaLucrare(Long lucrareId, Long piesaId, Integer cantitate);
    LucrareService modificaCantitate(Long lucrareId, Long piesaId, Integer cantitate);
    LucrareService eliminaPiesaDinLucrare(Long lucrareId, Long piesaId);
    LucrareCostView calculeazaCostPiese(Long lucrareId);               // CR-02
}
