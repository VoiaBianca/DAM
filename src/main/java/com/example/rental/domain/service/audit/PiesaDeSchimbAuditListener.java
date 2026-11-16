package com.example.rental.domain.service.audit;

import com.example.rental.domain.model.PiesaDeSchimb;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class PiesaDeSchimbAuditListener {

    private static final Logger log = LoggerFactory.getLogger(PiesaDeSchimbAuditListener.class);

    @PrePersist
    public void prePersist(PiesaDeSchimb piesa) {
        log.info("[AUDIT][INSERT] codPiesa={} la {}", piesa.getCodPiesa(), LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(PiesaDeSchimb piesa) {
        log.info("[AUDIT][UPDATE] codPiesa={} la {}", piesa.getCodPiesa(), LocalDateTime.now());
    }

    @PreRemove
    public void preRemove(PiesaDeSchimb piesa) {
        log.info("[AUDIT][DELETE] codPiesa={} la {}", piesa.getCodPiesa(), LocalDateTime.now());
    }
}
