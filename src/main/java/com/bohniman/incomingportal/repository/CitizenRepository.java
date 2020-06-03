package com.bohniman.incomingportal.repository;

import java.util.Optional;

import com.bohniman.incomingportal.model.Citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    Optional<Citizen> findByMobile(String mobile);

    Optional<Citizen> findByMobileAndIsOtpValidated(String mobile, boolean b);

}