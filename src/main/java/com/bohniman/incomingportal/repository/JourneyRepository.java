package com.bohniman.incomingportal.repository;

import java.util.Date;
import java.util.List;

import com.bohniman.incomingportal.model.Journey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {

    List<Journey> findAllByCitizen_idOrderByName(Long id);

    List<Journey> findAllByNameAndDateOfTravelAndFlightNo(String name, Date dateOfTravel, String flightNo);

}