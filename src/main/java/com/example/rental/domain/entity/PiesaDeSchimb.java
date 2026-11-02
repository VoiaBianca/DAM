package com.example.rental.domain.entity;
import com.example.rental.domain.listener.PiesaDeSchimbAuditListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
@Entity @Table(name="piese_schimb") @EntityListeners(PiesaDeSchimbAuditListener.class)
public class PiesaDeSchimb {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Size(max=50) @Column(name="cod_piesa",nullable=false,length=50) private String codPiesa;
 @NotBlank @Size(max=100) @Column(nullable=false,length=100) private String furnizor;
 @Size(max=500) @Column(length=500) private String descriere;
 @Size(max=100) @Column(name="numar_identificare",length=100) private String numarIdentificare;
 @NotNull @Min(0) @Column(nullable=false) private Integer cantitate;
 @NotBlank @Size(max=100) @Column(nullable=false,length=100) private String denumire;
 @NotNull @DecimalMin("0.0") @Column(name="pret_unitar",nullable=false,precision=12,scale=2) private BigDecimal pretUnitar;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="lucrare_service_id",nullable=false) private LucrareService lucrareService;
 protected PiesaDeSchimb(){}
 public static PiesaDeSchimb create(String cod,String furnizor,String descriere,String nr,Integer cant,String denumire,BigDecimal pret){PiesaDeSchimb p=new PiesaDeSchimb();p.codPiesa=cod;p.furnizor=furnizor;p.descriere=descriere;p.numarIdentificare=nr;p.cantitate=cant;p.denumire=denumire;p.pretUnitar=pret;return p;}
 @Transient public BigDecimal calculeazaCostTotal(){return cantitate==null||pretUnitar==null?BigDecimal.ZERO:pretUnitar.multiply(BigDecimal.valueOf(cantitate));}
 public Long getId(){return id;} public String getCodPiesa(){return codPiesa;} public String getFurnizor(){return furnizor;} public String getDenumire(){return denumire;} public Integer getCantitate(){return cantitate;} public BigDecimal getPretUnitar(){return pretUnitar;} public LucrareService getLucrareService(){return lucrareService;}
 public void setCantitate(Integer cantitate){this.cantitate=cantitate;} void setLucrareService(LucrareService lucrare){this.lucrareService=lucrare;}
}
