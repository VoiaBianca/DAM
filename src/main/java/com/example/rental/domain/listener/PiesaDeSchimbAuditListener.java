package com.example.rental.domain.listener;
import com.example.rental.domain.entity.PiesaDeSchimb;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.logging.Logger;
public class PiesaDeSchimbAuditListener {
 private static final Logger LOG=Logger.getLogger(PiesaDeSchimbAuditListener.class.getName());
 @PrePersist public void insert(PiesaDeSchimb p){log("INSERT",p);}
 @PreUpdate public void update(PiesaDeSchimb p){log("UPDATE",p);}
 @PreRemove public void delete(PiesaDeSchimb p){log("DELETE",p);}
 private void log(String action,PiesaDeSchimb p){LOG.info("[AUDIT]["+action+"] cod="+p.getCodPiesa()+" at "+LocalDateTime.now());}
}
