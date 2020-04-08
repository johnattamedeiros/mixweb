package com.mixapp.app.web.rest;

import com.mixapp.app.domain.Example;
import com.mixapp.app.service.ExampleService;
import com.mixapp.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mixapp.app.domain.Example}.
 */
@RestController
@RequestMapping("/api")
public class ExampleResource {

    private final Logger log = LoggerFactory.getLogger(ExampleResource.class);

    private static final String ENTITY_NAME = "example";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExampleService exampleService;

    public ExampleResource(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    /**
     * {@code POST  /examples} : Create a new example.
     *
     * @param example the example to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new example, or with status {@code 400 (Bad Request)} if the example has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/examples")
    public ResponseEntity<Example> createExample(@Valid @RequestBody Example example) throws URISyntaxException {
        log.debug("REST request to save Example : {}", example);
        if (example.getId() != null) {
            throw new BadRequestAlertException("A new example cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Example result = exampleService.save(example);
        return ResponseEntity.created(new URI("/api/examples/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /examples} : Updates an existing example.
     *
     * @param example the example to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated example,
     * or with status {@code 400 (Bad Request)} if the example is not valid,
     * or with status {@code 500 (Internal Server Error)} if the example couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/examples")
    public ResponseEntity<Example> updateExample(@Valid @RequestBody Example example) throws URISyntaxException {
        log.debug("REST request to update Example : {}", example);
        if (example.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Example result = exampleService.save(example);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, example.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /examples} : get all the examples.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examples in body.
     */
    @GetMapping("/examples")
    public ResponseEntity<List<Example>> getAllExamples(Pageable pageable) {
        log.debug("REST request to get a page of Examples");
        Page<Example> page = exampleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /examples/:id} : get the "id" example.
     *
     * @param id the id of the example to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the example, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/examples/{id}")
    public ResponseEntity<Example> getExample(@PathVariable Long id) {
        log.debug("REST request to get Example : {}", id);
        Optional<Example> example = exampleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(example);
    }

    /**
     * {@code DELETE  /examples/:id} : delete the "id" example.
     *
     * @param id the id of the example to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/examples/{id}")
    public ResponseEntity<Void> deleteExample(@PathVariable Long id) {
        log.debug("REST request to delete Example : {}", id);
        exampleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
