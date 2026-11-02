package com.example.rental;
import com.example.rental.domain.entity.*;
import com.example.rental.domain.service.*;
import com.example.rental.repository.*;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class PiesaDeSchimbBusinessTest {
 PiesaDeSchimb piesa(int cant){return PiesaDeSchimb.create("COD","Furnizor",null,null,cant,"Filtru",new BigDecimal("12.50"));}
 @Test void calcul(){assertEquals(0,new BigDecimal("50.00").compareTo(piesa(4).calculeazaCostTotal()));assertEquals(0,new BigDecimal("75.00").compareTo(new PiesaDeSchimbComputationService().calculeazaCostTotalLucrare(List.of(piesa(4),piesa(2)))));}
 @Test void validare(){try(var factory=Validation.buildDefaultValidatorFactory()){var validator=new PiesaDeSchimbValidationService(factory.getValidator());assertDoesNotThrow(()->validator.validate(piesa(1)));assertThrows(IllegalArgumentException.class,()->validator.validate(piesa(-1)));}}
 @Test void workflowAdaugare(){try(var factory=Validation.buildDefaultValidatorFactory()){var repo=mock(PiesaDeSchimbRepository.class);var lucrari=mock(LucrareServiceRepository.class);LucrareService lucrare=LucrareService.create("Revizie");when(lucrari.findById(1L)).thenReturn(java.util.Optional.of(lucrare));when(repo.save(any())).thenAnswer(i->i.getArgument(0));var service=new PiesaDeSchimbServiceImpl(new PiesaDeSchimbValidationService(factory.getValidator()),new PiesaDeSchimbComputationService(),repo,lucrari);var p=service.addPiesaToLucrare(piesa(2),1L);assertSame(lucrare,p.getLucrareService());verify(repo).save(p);}}
}
