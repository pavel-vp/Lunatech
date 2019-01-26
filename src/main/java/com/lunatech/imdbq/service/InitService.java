package com.lunatech.imdbq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class InitService {

    @Autowired
    DataLoaderService dataLoaderService;
    @Autowired
    KevinBaconService kevinBaconService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        dataLoaderService.loadData();
        kevinBaconService.buildKevinBaconsUniverse();
    }



}
