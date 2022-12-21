package com.unibuc.ex1curs11.repository;

import com.unibuc.ex1curs11.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

}