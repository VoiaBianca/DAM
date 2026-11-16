package com.example.rental.domain.service;

import org.springframework.context.ApplicationEvent;

public class PiesaLucrareEvent extends ApplicationEvent {
    private final String operatie;
    private final Long lucrareId;

    public PiesaLucrareEvent(Object source, String operatie, Long lucrareId) {
        super(source);
        this.operatie = operatie;
        this.lucrareId = lucrareId;
    }

    public String getOperatie() { return operatie; }
    public Long getLucrareId() { return lucrareId; }
}
