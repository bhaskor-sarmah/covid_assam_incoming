package com.bohniman.incomingportal.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bohniman.incomingportal.model.Citizen;
import com.bohniman.incomingportal.model.Journey;
import com.bohniman.incomingportal.model.ScreeningLocation;
import com.bohniman.incomingportal.repository.CitizenRepository;
import com.bohniman.incomingportal.repository.JourneyRepository;
import com.bohniman.incomingportal.repository.ScreeningLocationRepository;
import com.bohniman.incomingportal.utils.PdfPass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CitizenService {

    @Autowired
    CitizenRepository citizenRepository;

    @Autowired
    JourneyRepository journeyRepository;

    @Autowired
    PdfPass pdfPass;

    @Autowired
    ScreeningLocationRepository locationRepository;

    public Citizen saveCitizen(Citizen citizen) {
        return citizenRepository.save(citizen);
    }

    public Citizen getByMobileNo(String mobile) {
        Optional<Citizen> citizen = citizenRepository.findByMobile(mobile);
        if (citizen.isPresent()) {
            return citizen.get();
        }
        return null;
    }

    public Journey saveJourney(Journey journey) {
        return journeyRepository.save(journey);
    }

    public List<Journey> getAllJourneyList(Citizen citizen) {
        return journeyRepository.findAllByCitizen_idOrderByName(citizen.getId());
    }

    public Journey getJourneyById(Long id) {
        Optional<Journey> journey = journeyRepository.findById(id);
        if (journey.isPresent()) {
            return journey.get();
        }
        return null;
    }

    public ByteArrayInputStream generatePassPdf(Journey journey) {
        return pdfPass.generatePassPdf(journey);
    }

    public ByteArrayInputStream generateErrorPdf(String string) {
        return pdfPass.generateErrorPdf(string);
    }

    public List<Journey> findJourney(Journey journey) {
        return journeyRepository.findAllByNameAndDateOfTravelAndFlightNo(journey.getName(), journey.getDateOfTravel(),
                journey.getFlightNo());
    }

    public List<ScreeningLocation> getScreeningCenter(String districtCode) {
        List<ScreeningLocation> locations = locationRepository.findByDistrict_districtCode(districtCode);
        return locations;
    }
}