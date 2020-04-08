package com.mixapp.app.service;

import com.mixapp.app.domain.Example;
import com.mixapp.app.repository.ExampleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Example}.
 */
@Service
@Transactional
public class ExampleService {

    private final Logger log = LoggerFactory.getLogger(ExampleService.class);

    private final ExampleRepository exampleRepository;

    public ExampleService(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    /**
     * Save a example.
     *
     * @param example the entity to save.
     * @return the persisted entity.
     */
    public Example save(Example example) {
        log.debug("Request to save Example : {}", example);
        return exampleRepository.save(example);
    }

    /**
     * Get all the examples.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Example> findAll(Pageable pageable) {
        log.debug("Request to get all Examples");
        return exampleRepository.findAll(pageable);
    }

    /**
     * Get one example by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Example> findOne(Long id) {
        log.debug("Request to get Example : {}", id);
        return exampleRepository.findById(id);
    }

    /**
     * Delete the example by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Example : {}", id);
        exampleRepository.deleteById(id);
    }
}
