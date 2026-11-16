package com.example.rental.domain.service;

import com.example.rental.domain.model.Furnizor;
import com.example.rental.domain.model.PiesaDeSchimb;

public interface ICatalogEntityFactory {
    Furnizor creeazaFurnizor(String denumire, String telefon, String email);
    PiesaDeSchimb creeazaPiesa(String codPiesa, String numarIdentificare, String denumire,
                               String descriere, Double pretUnitar, Furnizor furnizor);
}
