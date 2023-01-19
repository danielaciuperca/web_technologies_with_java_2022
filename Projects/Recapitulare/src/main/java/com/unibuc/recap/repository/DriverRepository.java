package com.unibuc.recap.repository;

import com.unibuc.recap.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByEmail(String email);

    Optional<Driver> findByEmailAndIdNot(String email, long id);

    List<Driver> findByName(String name);

    List<Driver> findByNameAndCity(String name, String city);

    List<Driver> findByCity(String city);
}
