package com.example.rental;

import com.example.rental.application.service.*;
import com.example.rental.domain.model.*;
import com.example.rental.domain.service.*;
import com.example.rental.infrastructure.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class Test_LucrarePieseDomainServices {

    @Autowired private ICatalogEntityFactory catalogFactory;
    @Autowired private ILucrareServiceEntityFactory lucrareFactory;
    @Autowired private FurnizorRepository furnizorRepository;
    @Autowired private PiesaDeSchimbRepository piesaRepository;
    @Autowired private LucrareServiceRepository lucrareRepository;
    @Autowired private ILinieLucrareCostComputingService costLinieService;
    @Autowired private ILucrarePieseService workflowService;
    @Autowired private ILucrareAuditingService auditService;

    private LucrareService lucrare;
    private PiesaDeSchimb filtru, bujie;

    @BeforeEach
    void setUp() {                                                    // Arrange
        Furnizor furnizor = furnizorRepository.save(
            catalogFactory.creeazaFurnizor("AutoParts SRL", null, null));
        filtru = piesaRepository.save(catalogFactory.creeazaPiesa(
            "PS-001", "OEM-12345", "Filtru ulei", null, 35.0, furnizor));
        bujie = piesaRepository.save(catalogFactory.creeazaPiesa(
            "PS-002", null, "Bujie", null, 12.5, furnizor));
        lucrare = lucrareRepository.save(
            lucrareFactory.creeazaLucrare("Revizie", null, 200.0));
    }

    private LucrareService reincarca() {
        return lucrareRepository.findByIdCuLinii(lucrare.getId()).orElseThrow();
    }

    @Test
    void adaugaPiesa_cantitateZero_VR03() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> workflowService.adaugaPiesaLaLucrare(lucrare.getId(), filtru.getId(), 0));
        assertTrue(ex.getMessage().contains("VR-03"));
        assertEquals(0, reincarca().getLinii().size());
    }

    @Test
    void adaugaPiesa_duplicat_VR06() {
        workflowService.adaugaPiesaLaLucrare(lucrare.getId(), filtru.getId(), 1);
        IllegalStateException ex = assertThrows(IllegalStateException.class,
            () -> workflowService.adaugaPiesaLaLucrare(lucrare.getId(), filtru.getId(), 2));
        assertTrue(ex.getMessage().contains("VR-06"));
        assertEquals(1, reincarca().getLinii().size());
    }

    @Test
    void costLinie_CR01() {
        LinieLucrare linie = lucrareFactory.creeazaLinie(lucrare, filtru, 2);   // Act
        assertEquals(70.0, costLinieService.calculeazaCostLinie(linie), 0.001); // Assert
    }

    @Test
    void costPiese_CR02() {
        workflowService.adaugaPiesaLaLucrare(lucrare.getId(), filtru.getId(), 2);   // 70
        workflowService.adaugaPiesaLaLucrare(lucrare.getId(), bujie.getId(), 4);    // 50
        LucrareCostView view = workflowService.calculeazaCostPiese(lucrare.getId());
        assertEquals(2, view.numarLinii());
        assertEquals(120.0, view.costTotalPiese(), 0.001);
    }

    @Test
    void adaugaPiesa_genereazaAudit() {
        workflowService.adaugaPiesaLaLucrare(lucrare.getId(), filtru.getId(), 1);
        assertEquals(1, auditService.getJurnal().size());
        assertTrue(auditService.getJurnal().get(0).contains("ADAUGA_PIESA"));
    }
}
