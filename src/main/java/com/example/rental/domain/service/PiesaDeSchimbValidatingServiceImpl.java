package com.example.rental.domain.service;

import com.example.rental.domain.model.PiesaDeSchimb;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PiesaDeSchimbValidatingServiceImpl implements IPiesaDeSchimbValidatingService {
    private final Validator validator;                       // jakarta.validation.Validator

    public PiesaDeSchimbValidatingServiceImpl(Validator validator) { this.validator = validator; }

    @Override
    public void validate(PiesaDeSchimb piesa) {              // VR-01 (camp), VR-02, VR-04
        Set<ConstraintViolation<PiesaDeSchimb>> v = validator.validate(piesa);
        List<String> mesaje = new ArrayList<>();
        v.forEach(c -> mesaje.add(c.getMessage()));
        if (piesa.getFurnizor() != null)                     // VR-02: denumirea furnizorului nu poate fi blank
            validator.validate(piesa.getFurnizor()).forEach(c -> mesaje.add(c.getMessage()));
        if (!mesaje.isEmpty())
            throw new IllegalArgumentException(mesaje.stream().sorted()
                .collect(Collectors.joining("; ")));
    }
}
