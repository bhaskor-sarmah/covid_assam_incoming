package com.bohniman.incomingportal.service;

import com.bohniman.incomingportal.model.Citizen;
import com.bohniman.incomingportal.repository.CitizenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitizenService {

    @Autowired
    CitizenRepository citizenRepository;

    public Citizen saveCitizen(Citizen citizen) {
        return citizenRepository.save(citizen);
    }

}