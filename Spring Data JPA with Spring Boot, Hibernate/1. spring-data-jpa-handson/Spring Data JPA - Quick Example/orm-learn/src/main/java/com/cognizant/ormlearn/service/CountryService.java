package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    /**
     * @Transactional here matters more than it looks: findAll() returns
     * entities that may still be "attached" to a Hibernate session for
     * lazy-loaded fields. Wrapping this in a transaction ensures the
     * Hibernate session stays open for the duration of the method, avoiding
     * a LazyInitializationException if calling code tries to access a lazy
     * field after the method returns.
     */
    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
