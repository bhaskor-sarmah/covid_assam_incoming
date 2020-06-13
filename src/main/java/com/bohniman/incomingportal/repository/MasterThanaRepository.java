package com.bohniman.incomingportal.repository;

import java.util.List;

import com.bohniman.incomingportal.model.MasterThana;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterThanaRepository extends JpaRepository<MasterThana, String> {

    List<MasterThana> findByDistrictCodeOrderByThanaName(String districtCode);

}