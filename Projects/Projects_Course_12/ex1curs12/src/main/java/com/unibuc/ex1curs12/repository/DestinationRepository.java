package com.unibuc.ex1curs12.repository;

import com.unibuc.ex1curs12.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    Optional<Destination> findByName(String name);
}
