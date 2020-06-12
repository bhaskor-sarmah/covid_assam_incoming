
package com.bohniman.incomingportal.repository;

import java.util.List;

import com.bohniman.incomingportal.model.ScreeningLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningLocationRepository extends JpaRepository<ScreeningLocation, Long> {

    List<ScreeningLocation> findByDistrict_districtCode(String districtCode);

}