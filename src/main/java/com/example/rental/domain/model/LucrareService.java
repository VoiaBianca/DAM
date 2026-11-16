package com.example.rental.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity @Table(name = "lucrari_service")
public class LucrareService {                       // Aggregate Root
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "tip_lucrare", length = 100) private String tipLucrare;
    @Column(length = 500) private String descriere;
    @Column(name = "manopera_estimata") private Double manoperaEstimata;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusLucrare status = StatusLucrare.DESCHISA;
    @Column(name = "cost_real") private Double costReal;
    @Column(name = "data_deschidere") private LocalDate dataDeschidere;
    @Column(name = "data_finalizare") private LocalDate dataFinalizare;

    @OneToMany(mappedBy = "lucrareService", cascade = CascadeType.ALL,
               orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LinieLucrare> linii = new ArrayList<>();   // fără final

    public LucrareService() { }

    public List<LinieLucrare> getLinii() { return Collections.unmodifiableList(linii); }

    public void adaugaLinie(LinieLucrare linie) {
        linie.setLucrareService(this);     // parte proprietar a relației
        linii.add(linie);
    }
    public void eliminaLinie(LinieLucrare linie) { linii.remove(linie); }   // orphanRemoval șterge rândul

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipLucrare() { return tipLucrare; }
    public void setTipLucrare(String tipLucrare) { this.tipLucrare = tipLucrare; }
    public String getDescriere() { return descriere; }
    public void setDescriere(String descriere) { this.descriere = descriere; }
    public Double getManoperaEstimata() { return manoperaEstimata; }
    public void setManoperaEstimata(Double manoperaEstimata) { this.manoperaEstimata = manoperaEstimata; }
    public StatusLucrare getStatus() { return status; }
    public void setStatus(StatusLucrare status) { this.status = status; }
    public Double getCostReal() { return costReal; }
    public void setCostReal(Double costReal) { this.costReal = costReal; }
    public LocalDate getDataDeschidere() { return dataDeschidere; }
    public void setDataDeschidere(LocalDate dataDeschidere) { this.dataDeschidere = dataDeschidere; }
    public LocalDate getDataFinalizare() { return dataFinalizare; }
    public void setDataFinalizare(LocalDate dataFinalizare) { this.dataFinalizare = dataFinalizare; }
}
