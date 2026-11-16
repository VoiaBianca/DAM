package com.example.rental.infrastructure.persistence;

import com.example.rental.domain.model.LucrareService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LucrareServiceRepository extends JpaRepository<LucrareService, Long> {

    // @Query cu JOIN FETCH – încarcă lucrarea împreună cu liniile (evită LazyInitializationException)
    @Query("SELECT DISTINCT l FROM LucrareService l LEFT JOIN FETCH l.linii WHERE l.id = :id")
    Optional<LucrareService> findByIdCuLinii(@Param("id") Long id);
}
