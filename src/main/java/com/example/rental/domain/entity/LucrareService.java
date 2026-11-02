package com.example.rental.domain.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.*;
@Entity @Table(name="lucrari_service")
public class LucrareService {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Column(nullable=false,length=200) private String tipLucrare;
 @OneToMany(mappedBy="lucrareService",cascade=CascadeType.ALL,orphanRemoval=true) private List<PiesaDeSchimb> pieseSchimb=new ArrayList<>();
 protected LucrareService(){}
 private LucrareService(String tip){this.tipLucrare=tip;}
 public static LucrareService create(String tip){return new LucrareService(tip);}
 public Long getId(){return id;}
 public String getTipLucrare(){return tipLucrare;}
 public List<PiesaDeSchimb> getPieseSchimb(){return Collections.unmodifiableList(pieseSchimb);}
 public void addPiesa(PiesaDeSchimb p){Objects.requireNonNull(p);if(p.getLucrareService()!=null && p.getLucrareService()!=this)throw new IllegalArgumentException("Piesa apartine altei lucrari");if(!pieseSchimb.contains(p)){pieseSchimb.add(p);}p.setLucrareService(this);}
 public void removePiesa(PiesaDeSchimb p){if(pieseSchimb.remove(p))p.setLucrareService(null);}
}
