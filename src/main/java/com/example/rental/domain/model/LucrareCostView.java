package com.example.rental.domain.model;

/** Entity view calculat de serviciul CR-02 (cost total piese pentru o lucrare). */
public record LucrareCostView(Long lucrareId, Integer numarLinii, Double costTotalPiese) {}
