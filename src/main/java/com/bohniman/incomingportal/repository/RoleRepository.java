package com.bohniman.incomingportal.repository;

import java.util.List;

import com.bohniman.incomingportal.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByRole(String string);

}