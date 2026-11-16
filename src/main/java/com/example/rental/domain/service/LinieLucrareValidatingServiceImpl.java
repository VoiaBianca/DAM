package com.example.rental.domain.service;

import com.example.rental.domain.model.LinieLucrare;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LinieLucrareValidatingServiceImpl implements ILinieLucrareValidatingService {
    private final Validator validator;                       // jakarta.validation.Validator

    public LinieLucrareValidatingServiceImpl(Validator validator) { this.validator = validator; }

    @Override
    public void validate(LinieLucrare linie) {               // VR-03, VR-05
        Set<ConstraintViolation<LinieLucrare>> v = validator.validate(linie);
        if (!v.isEmpty())
            throw new IllegalArgumentException(v.stream()
                .map(ConstraintViolation::getMessage).sorted()
                .collect(Collectors.joining("; ")));
    }
}
