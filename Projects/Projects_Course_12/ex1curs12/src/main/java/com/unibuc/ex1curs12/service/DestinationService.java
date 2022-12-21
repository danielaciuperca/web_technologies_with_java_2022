package com.unibuc.ex1curs12.service;


import com.unibuc.ex1curs12.exception.*;
import com.unibuc.ex1curs12.model.*;
import com.unibuc.ex1curs12.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class DestinationService {
    private DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public Destination create(Destination destination) {
        Optional<Destination> existingDestinationSameName = destinationRepository.findByName(destination.getName());
        existingDestinationSameName.ifPresent(e -> {
            throw new DuplicateDestinationException();
        });
        return destinationRepository.save(destination);
    }

    public Optional<Destination> findById(long id) {
        return destinationRepository.findById(id);
    }
}
