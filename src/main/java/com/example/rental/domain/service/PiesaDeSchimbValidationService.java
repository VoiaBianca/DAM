package com.example.rental.domain.service;
import com.example.rental.domain.entity.PiesaDeSchimb;
import jakarta.validation.*;
import org.springframework.stereotype.Service;
import java.util.Set;
@Service public class PiesaDeSchimbValidationService {
 private final Validator validator;
 public PiesaDeSchimbValidationService(Validator validator){this.validator=validator;}
 public void validate(PiesaDeSchimb p){if(p==null)throw new IllegalArgumentException("Piesa lipseste");Set<ConstraintViolation<PiesaDeSchimb>> errors=validator.validate(p);if(!errors.isEmpty())throw new IllegalArgumentException(errors.iterator().next().getMessage());}
}
