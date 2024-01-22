package com.unibuc.recap.repository;

import com.unibuc.recap.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    
    boolean existsByEmail(String email);
    
    boolean existsByEmailAndIdNot(String email, long id);

    List<Driver> findByName(String name);

    List<Driver> findByCity(String city);

    List<Driver> findByNameAndCity(String name, String city);
}
