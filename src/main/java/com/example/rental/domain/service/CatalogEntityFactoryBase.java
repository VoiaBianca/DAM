package com.example.rental.domain.service;

import com.example.rental.domain.model.Furnizor;
import com.example.rental.domain.model.PiesaDeSchimb;
import org.springframework.stereotype.Component;

// la fel ca LucrareServiceEntityFactoryBase: fără AtomicLong și fără setId
@Component
public class CatalogEntityFactoryBase implements ICatalogEntityFactory {

    @Override
    public Furnizor creeazaFurnizor(String denumire, String telefon, String email) {
        Furnizor f = new Furnizor();
        f.setDenumire(denumire);
        f.setTelefon(telefon);
        f.setEmail(email);
        return f;
    }

    @Override
    public PiesaDeSchimb creeazaPiesa(String codPiesa, String numarIdentificare, String denumire,
                                      String descriere, Double pretUnitar, Furnizor furnizor) {
        PiesaDeSchimb p = new PiesaDeSchimb();
        p.setCodPiesa(codPiesa);
        p.setNumarIdentificare(numarIdentificare);
        p.setDenumire(denumire);
        p.setDescriere(descriere);
        p.setPretUnitar(pretUnitar);
        p.setFurnizor(furnizor);
        return p;
    }
}
