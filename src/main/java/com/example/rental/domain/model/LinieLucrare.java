package com.example.rental.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "linii_lucrare",
       uniqueConstraints = @UniqueConstraint(columnNames = {"lucrare_service_id", "piesa_id"}))
public class LinieLucrare {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lucrare_service_id", nullable = false)
    @NotNull(message = "VR-05: Linia trebuie asociata unei lucrari")
    private LucrareService lucrareService;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "piesa_id", nullable = false)
    @NotNull(message = "VR-05: Piesa este obligatorie")
    private PiesaDeSchimb piesa;
    @Column(nullable = false)
    @NotNull(message = "VR-03: Cantitatea este obligatorie")
    @Min(value = 1, message = "VR-03: Cantitatea trebuie sa fie minim 1")
    private Integer cantitate;

    public LinieLucrare() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LucrareService getLucrareService() { return lucrareService; }
    public void setLucrareService(LucrareService lucrareService) { this.lucrareService = lucrareService; }
    public PiesaDeSchimb getPiesa() { return piesa; }
    public void setPiesa(PiesaDeSchimb piesa) { this.piesa = piesa; }
    public Integer getCantitate() { return cantitate; }
    public void setCantitate(Integer cantitate) { this.cantitate = cantitate; }
}
