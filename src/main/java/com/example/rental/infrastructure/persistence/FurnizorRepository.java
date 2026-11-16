package com.example.rental.infrastructure.persistence;

import com.example.rental.domain.model.Furnizor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FurnizorRepository extends JpaRepository<Furnizor, Long> {
    Optional<Furnizor> findByDenumire(String denumire);
}
