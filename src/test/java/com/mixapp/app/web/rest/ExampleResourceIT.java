package com.mixapp.app.web.rest;

import com.mixapp.app.MixwebApp;
import com.mixapp.app.domain.Example;
import com.mixapp.app.repository.ExampleRepository;
import com.mixapp.app.service.ExampleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ExampleResource} REST controller.
 */
@SpringBootTest(classes = MixwebApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ExampleResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_AGE = 1L;
    private static final Long UPDATED_AGE = 2L;

    @Autowired
    private ExampleRepository exampleRepository;

    @Autowired
    private ExampleService exampleService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExampleMockMvc;

    private Example example;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Example createEntity(EntityManager em) {
        Example example = new Example()
            .name(DEFAULT_NAME)
            .age(DEFAULT_AGE);
        return example;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Example createUpdatedEntity(EntityManager em) {
        Example example = new Example()
            .name(UPDATED_NAME)
            .age(UPDATED_AGE);
        return example;
    }

    @BeforeEach
    public void initTest() {
        example = createEntity(em);
    }

    @Test
    @Transactional
    public void createExample() throws Exception {
        int databaseSizeBeforeCreate = exampleRepository.findAll().size();

        // Create the Example
        restExampleMockMvc.perform(post("/api/examples").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(example)))
            .andExpect(status().isCreated());

        // Validate the Example in the database
        List<Example> exampleList = exampleRepository.findAll();
        assertThat(exampleList).hasSize(databaseSizeBeforeCreate + 1);
        Example testExample = exampleList.get(exampleList.size() - 1);
        assertThat(testExample.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testExample.getAge()).isEqualTo(DEFAULT_AGE);
    }

    @Test
    @Transactional
    public void createExampleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = exampleRepository.findAll().size();

        // Create the Example with an existing ID
        example.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExampleMockMvc.perform(post("/api/examples").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(example)))
            .andExpect(status().isBadRequest());

        // Validate the Example in the database
        List<Example> exampleList = exampleRepository.findAll();
        assertThat(exampleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = exampleRepository.findAll().size();
        // set the field null
        example.setName(null);

        // Create the Example, which fails.

        restExampleMockMvc.perform(post("/api/examples").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(example)))
            .andExpect(status().isBadRequest());

        List<Example> exampleList = exampleRepository.findAll();
        assertThat(exampleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExamples() throws Exception {
        // Initialize the database
        exampleRepository.saveAndFlush(example);

        // Get all the exampleList
        restExampleMockMvc.perform(get("/api/examples?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(example.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE.intValue())));
    }
    
    @Test
    @Transactional
    public void getExample() throws Exception {
        // Initialize the database
        exampleRepository.saveAndFlush(example);

        // Get the example
        restExampleMockMvc.perform(get("/api/examples/{id}", example.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(example.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingExample() throws Exception {
        // Get the example
        restExampleMockMvc.perform(get("/api/examples/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExample() throws Exception {
        // Initialize the database
        exampleService.save(example);

        int databaseSizeBeforeUpdate = exampleRepository.findAll().size();

        // Update the example
        Example updatedExample = exampleRepository.findById(example.getId()).get();
        // Disconnect from session so that the updates on updatedExample are not directly saved in db
        em.detach(updatedExample);
        updatedExample
            .name(UPDATED_NAME)
            .age(UPDATED_AGE);

        restExampleMockMvc.perform(put("/api/examples").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedExample)))
            .andExpect(status().isOk());

        // Validate the Example in the database
        List<Example> exampleList = exampleRepository.findAll();
        assertThat(exampleList).hasSize(databaseSizeBeforeUpdate);
        Example testExample = exampleList.get(exampleList.size() - 1);
        assertThat(testExample.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testExample.getAge()).isEqualTo(UPDATED_AGE);
    }

    @Test
    @Transactional
    public void updateNonExistingExample() throws Exception {
        int databaseSizeBeforeUpdate = exampleRepository.findAll().size();

        // Create the Example

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExampleMockMvc.perform(put("/api/examples").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(example)))
            .andExpect(status().isBadRequest());

        // Validate the Example in the database
        List<Example> exampleList = exampleRepository.findAll();
        assertThat(exampleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExample() throws Exception {
        // Initialize the database
        exampleService.save(example);

        int databaseSizeBeforeDelete = exampleRepository.findAll().size();

        // Delete the example
        restExampleMockMvc.perform(delete("/api/examples/{id}", example.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Example> exampleList = exampleRepository.findAll();
        assertThat(exampleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
