package com.example.rental.domain.model;

import com.example.rental.domain.service.audit.PiesaDeSchimbAuditListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity @Table(name = "piese_schimb")
@EntityListeners(PiesaDeSchimbAuditListener.class)
public class PiesaDeSchimb {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "cod_piesa", nullable = false, unique = true, length = 50)
    @NotBlank(message = "VR-01: Codul piesei este obligatoriu")
    private String codPiesa;
    @Column(name = "numar_identificare", length = 100) private String numarIdentificare;
    @Column(nullable = false, length = 100)
    @NotBlank(message = "VR-02: Denumirea piesei este obligatorie")
    private String denumire;
    @Column(length = 500) private String descriere;
    @Column(name = "pret_unitar", nullable = false)
    @NotNull(message = "VR-04: Pretul unitar este obligatoriu")
    @DecimalMin(value = "0.0", message = "VR-04: Pretul nu poate fi negativ")
    private Double pretUnitar;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "furnizor_id", nullable = false)
    @NotNull(message = "VR-02: Furnizorul este obligatoriu")
    private Furnizor furnizor;

    public PiesaDeSchimb() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodPiesa() { return codPiesa; }
    public void setCodPiesa(String codPiesa) { this.codPiesa = codPiesa; }
    public String getNumarIdentificare() { return numarIdentificare; }
    public void setNumarIdentificare(String numarIdentificare) { this.numarIdentificare = numarIdentificare; }
    public String getDenumire() { return denumire; }
    public void setDenumire(String denumire) { this.denumire = denumire; }
    public String getDescriere() { return descriere; }
    public void setDescriere(String descriere) { this.descriere = descriere; }
    public Double getPretUnitar() { return pretUnitar; }
    public void setPretUnitar(Double pretUnitar) { this.pretUnitar = pretUnitar; }
    public Furnizor getFurnizor() { return furnizor; }
    public void setFurnizor(Furnizor furnizor) { this.furnizor = furnizor; }
}
