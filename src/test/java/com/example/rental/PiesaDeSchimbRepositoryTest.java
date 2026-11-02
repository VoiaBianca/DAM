package com.example.rental;
import com.example.rental.domain.entity.*;
import com.example.rental.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest class PiesaDeSchimbRepositoryTest {
 @Autowired PiesaDeSchimbRepository piese;
 @Autowired LucrareServiceRepository lucrari;
 @Test void salveazaSiCautaPiesa(){LucrareService lucrare=lucrari.saveAndFlush(LucrareService.create("Revizie"));PiesaDeSchimb p=PiesaDeSchimb.create("PS-01","Furnizor",null,null,2,"Filtru",new BigDecimal("25.00"));lucrare.addPiesa(p);piese.saveAndFlush(p);assertNotNull(p.getId());assertEquals(1,piese.findByLucrareServiceId(lucrare.getId()).size());assertEquals(1,piese.findByCodPiesa("PS-01").size());}
 @Test void aceeasiPiesaPoateExistaInLucrariDiferite(){LucrareService a=lucrari.saveAndFlush(LucrareService.create("A"));LucrareService b=lucrari.saveAndFlush(LucrareService.create("B"));PiesaDeSchimb x=PiesaDeSchimb.create("COD","F",null,null,1,"Filtru",new BigDecimal("10"));PiesaDeSchimb y=PiesaDeSchimb.create("COD","F",null,null,2,"Filtru",new BigDecimal("10"));a.addPiesa(x);b.addPiesa(y);piese.saveAndFlush(x);piese.saveAndFlush(y);assertEquals(2,piese.findByCodPiesa("COD").size());}
}
