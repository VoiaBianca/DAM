package com.example.rental.repository;
import com.example.rental.domain.entity.PiesaDeSchimb;
import java.util.*;
public interface IPiesaDeSchimbRepository {PiesaDeSchimb save(PiesaDeSchimb p);Optional<PiesaDeSchimb> findById(Long id);List<PiesaDeSchimb> findByCodPiesa(String cod);List<PiesaDeSchimb> findByLucrareServiceId(Long id);void delete(PiesaDeSchimb p);}
