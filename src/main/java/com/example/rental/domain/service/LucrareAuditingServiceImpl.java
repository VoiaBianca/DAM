package com.example.rental.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class LucrareAuditingServiceImpl
        implements ILucrareAuditingService, ApplicationListener<PiesaLucrareEvent> {
    private static final Logger log = LoggerFactory.getLogger(LucrareAuditingServiceImpl.class);
    private final List<String> jurnal = new CopyOnWriteArrayList<>();

    @Override
    public void onApplicationEvent(PiesaLucrareEvent e) {
        String intrare = "[AUDIT] " + e.getOperatie() + " lucrare=" + e.getLucrareId()
                         + " la " + LocalDateTime.now();
        jurnal.add(intrare);
        log.info(intrare);
    }

    @Override public List<String> getJurnal() { return List.copyOf(jurnal); }
}
