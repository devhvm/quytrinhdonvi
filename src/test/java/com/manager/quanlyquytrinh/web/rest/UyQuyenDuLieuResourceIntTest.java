package com.manager.quanlyquytrinh.web.rest;

import com.manager.quanlyquytrinh.QuytrinhdonviApp;

import com.manager.quanlyquytrinh.domain.UyQuyenDuLieu;
import com.manager.quanlyquytrinh.repository.UyQuyenDuLieuRepository;
import com.manager.quanlyquytrinh.service.UyQuyenDuLieuService;
import com.manager.quanlyquytrinh.service.dto.UyQuyenDuLieuDTO;
import com.manager.quanlyquytrinh.service.mapper.UyQuyenDuLieuMapper;
import com.manager.quanlyquytrinh.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.manager.quanlyquytrinh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UyQuyenDuLieuResource REST controller.
 *
 * @see UyQuyenDuLieuResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuytrinhdonviApp.class)
public class UyQuyenDuLieuResourceIntTest {

    private static final String DEFAULT_FROM_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_FROM_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TO_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TO_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    @Autowired
    private UyQuyenDuLieuRepository uyQuyenDuLieuRepository;

    @Autowired
    private UyQuyenDuLieuMapper uyQuyenDuLieuMapper;

    @Autowired
    private UyQuyenDuLieuService uyQuyenDuLieuService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restUyQuyenDuLieuMockMvc;

    private UyQuyenDuLieu uyQuyenDuLieu;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UyQuyenDuLieuResource uyQuyenDuLieuResource = new UyQuyenDuLieuResource(uyQuyenDuLieuService);
        this.restUyQuyenDuLieuMockMvc = MockMvcBuilders.standaloneSetup(uyQuyenDuLieuResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UyQuyenDuLieu createEntity(EntityManager em) {
        UyQuyenDuLieu uyQuyenDuLieu = new UyQuyenDuLieu()
            .fromUserId(DEFAULT_FROM_USER_ID)
            .toUserId(DEFAULT_TO_USER_ID)
            .role(DEFAULT_ROLE);
        return uyQuyenDuLieu;
    }

    @Before
    public void initTest() {
        uyQuyenDuLieu = createEntity(em);
    }

    @Test
    @Transactional
    public void createUyQuyenDuLieu() throws Exception {
        int databaseSizeBeforeCreate = uyQuyenDuLieuRepository.findAll().size();

        // Create the UyQuyenDuLieu
        UyQuyenDuLieuDTO uyQuyenDuLieuDTO = uyQuyenDuLieuMapper.toDto(uyQuyenDuLieu);
        restUyQuyenDuLieuMockMvc.perform(post("/api/uy-quyen-du-lieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenDuLieuDTO)))
            .andExpect(status().isCreated());

        // Validate the UyQuyenDuLieu in the database
        List<UyQuyenDuLieu> uyQuyenDuLieuList = uyQuyenDuLieuRepository.findAll();
        assertThat(uyQuyenDuLieuList).hasSize(databaseSizeBeforeCreate + 1);
        UyQuyenDuLieu testUyQuyenDuLieu = uyQuyenDuLieuList.get(uyQuyenDuLieuList.size() - 1);
        assertThat(testUyQuyenDuLieu.getFromUserId()).isEqualTo(DEFAULT_FROM_USER_ID);
        assertThat(testUyQuyenDuLieu.getToUserId()).isEqualTo(DEFAULT_TO_USER_ID);
        assertThat(testUyQuyenDuLieu.getRole()).isEqualTo(DEFAULT_ROLE);
    }

    @Test
    @Transactional
    public void createUyQuyenDuLieuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uyQuyenDuLieuRepository.findAll().size();

        // Create the UyQuyenDuLieu with an existing ID
        uyQuyenDuLieu.setId(1L);
        UyQuyenDuLieuDTO uyQuyenDuLieuDTO = uyQuyenDuLieuMapper.toDto(uyQuyenDuLieu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUyQuyenDuLieuMockMvc.perform(post("/api/uy-quyen-du-lieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenDuLieuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UyQuyenDuLieu in the database
        List<UyQuyenDuLieu> uyQuyenDuLieuList = uyQuyenDuLieuRepository.findAll();
        assertThat(uyQuyenDuLieuList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFromUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = uyQuyenDuLieuRepository.findAll().size();
        // set the field null
        uyQuyenDuLieu.setFromUserId(null);

        // Create the UyQuyenDuLieu, which fails.
        UyQuyenDuLieuDTO uyQuyenDuLieuDTO = uyQuyenDuLieuMapper.toDto(uyQuyenDuLieu);

        restUyQuyenDuLieuMockMvc.perform(post("/api/uy-quyen-du-lieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenDuLieuDTO)))
            .andExpect(status().isBadRequest());

        List<UyQuyenDuLieu> uyQuyenDuLieuList = uyQuyenDuLieuRepository.findAll();
        assertThat(uyQuyenDuLieuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkToUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = uyQuyenDuLieuRepository.findAll().size();
        // set the field null
        uyQuyenDuLieu.setToUserId(null);

        // Create the UyQuyenDuLieu, which fails.
        UyQuyenDuLieuDTO uyQuyenDuLieuDTO = uyQuyenDuLieuMapper.toDto(uyQuyenDuLieu);

        restUyQuyenDuLieuMockMvc.perform(post("/api/uy-quyen-du-lieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenDuLieuDTO)))
            .andExpect(status().isBadRequest());

        List<UyQuyenDuLieu> uyQuyenDuLieuList = uyQuyenDuLieuRepository.findAll();
        assertThat(uyQuyenDuLieuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = uyQuyenDuLieuRepository.findAll().size();
        // set the field null
        uyQuyenDuLieu.setRole(null);

        // Create the UyQuyenDuLieu, which fails.
        UyQuyenDuLieuDTO uyQuyenDuLieuDTO = uyQuyenDuLieuMapper.toDto(uyQuyenDuLieu);

        restUyQuyenDuLieuMockMvc.perform(post("/api/uy-quyen-du-lieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenDuLieuDTO)))
            .andExpect(status().isBadRequest());

        List<UyQuyenDuLieu> uyQuyenDuLieuList = uyQuyenDuLieuRepository.findAll();
        assertThat(uyQuyenDuLieuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUyQuyenDuLieus() throws Exception {
        // Initialize the database
        uyQuyenDuLieuRepository.saveAndFlush(uyQuyenDuLieu);

        // Get all the uyQuyenDuLieuList
        restUyQuyenDuLieuMockMvc.perform(get("/api/uy-quyen-du-lieus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uyQuyenDuLieu.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromUserId").value(hasItem(DEFAULT_FROM_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].toUserId").value(hasItem(DEFAULT_TO_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())));
    }
    
    @Test
    @Transactional
    public void getUyQuyenDuLieu() throws Exception {
        // Initialize the database
        uyQuyenDuLieuRepository.saveAndFlush(uyQuyenDuLieu);

        // Get the uyQuyenDuLieu
        restUyQuyenDuLieuMockMvc.perform(get("/api/uy-quyen-du-lieus/{id}", uyQuyenDuLieu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uyQuyenDuLieu.getId().intValue()))
            .andExpect(jsonPath("$.fromUserId").value(DEFAULT_FROM_USER_ID.toString()))
            .andExpect(jsonPath("$.toUserId").value(DEFAULT_TO_USER_ID.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUyQuyenDuLieu() throws Exception {
        // Get the uyQuyenDuLieu
        restUyQuyenDuLieuMockMvc.perform(get("/api/uy-quyen-du-lieus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUyQuyenDuLieu() throws Exception {
        // Initialize the database
        uyQuyenDuLieuRepository.saveAndFlush(uyQuyenDuLieu);

        int databaseSizeBeforeUpdate = uyQuyenDuLieuRepository.findAll().size();

        // Update the uyQuyenDuLieu
        UyQuyenDuLieu updatedUyQuyenDuLieu = uyQuyenDuLieuRepository.findById(uyQuyenDuLieu.getId()).get();
        // Disconnect from session so that the updates on updatedUyQuyenDuLieu are not directly saved in db
        em.detach(updatedUyQuyenDuLieu);
        updatedUyQuyenDuLieu
            .fromUserId(UPDATED_FROM_USER_ID)
            .toUserId(UPDATED_TO_USER_ID)
            .role(UPDATED_ROLE);
        UyQuyenDuLieuDTO uyQuyenDuLieuDTO = uyQuyenDuLieuMapper.toDto(updatedUyQuyenDuLieu);

        restUyQuyenDuLieuMockMvc.perform(put("/api/uy-quyen-du-lieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenDuLieuDTO)))
            .andExpect(status().isOk());

        // Validate the UyQuyenDuLieu in the database
        List<UyQuyenDuLieu> uyQuyenDuLieuList = uyQuyenDuLieuRepository.findAll();
        assertThat(uyQuyenDuLieuList).hasSize(databaseSizeBeforeUpdate);
        UyQuyenDuLieu testUyQuyenDuLieu = uyQuyenDuLieuList.get(uyQuyenDuLieuList.size() - 1);
        assertThat(testUyQuyenDuLieu.getFromUserId()).isEqualTo(UPDATED_FROM_USER_ID);
        assertThat(testUyQuyenDuLieu.getToUserId()).isEqualTo(UPDATED_TO_USER_ID);
        assertThat(testUyQuyenDuLieu.getRole()).isEqualTo(UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void updateNonExistingUyQuyenDuLieu() throws Exception {
        int databaseSizeBeforeUpdate = uyQuyenDuLieuRepository.findAll().size();

        // Create the UyQuyenDuLieu
        UyQuyenDuLieuDTO uyQuyenDuLieuDTO = uyQuyenDuLieuMapper.toDto(uyQuyenDuLieu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUyQuyenDuLieuMockMvc.perform(put("/api/uy-quyen-du-lieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenDuLieuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UyQuyenDuLieu in the database
        List<UyQuyenDuLieu> uyQuyenDuLieuList = uyQuyenDuLieuRepository.findAll();
        assertThat(uyQuyenDuLieuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUyQuyenDuLieu() throws Exception {
        // Initialize the database
        uyQuyenDuLieuRepository.saveAndFlush(uyQuyenDuLieu);

        int databaseSizeBeforeDelete = uyQuyenDuLieuRepository.findAll().size();

        // Delete the uyQuyenDuLieu
        restUyQuyenDuLieuMockMvc.perform(delete("/api/uy-quyen-du-lieus/{id}", uyQuyenDuLieu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UyQuyenDuLieu> uyQuyenDuLieuList = uyQuyenDuLieuRepository.findAll();
        assertThat(uyQuyenDuLieuList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UyQuyenDuLieu.class);
        UyQuyenDuLieu uyQuyenDuLieu1 = new UyQuyenDuLieu();
        uyQuyenDuLieu1.setId(1L);
        UyQuyenDuLieu uyQuyenDuLieu2 = new UyQuyenDuLieu();
        uyQuyenDuLieu2.setId(uyQuyenDuLieu1.getId());
        assertThat(uyQuyenDuLieu1).isEqualTo(uyQuyenDuLieu2);
        uyQuyenDuLieu2.setId(2L);
        assertThat(uyQuyenDuLieu1).isNotEqualTo(uyQuyenDuLieu2);
        uyQuyenDuLieu1.setId(null);
        assertThat(uyQuyenDuLieu1).isNotEqualTo(uyQuyenDuLieu2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UyQuyenDuLieuDTO.class);
        UyQuyenDuLieuDTO uyQuyenDuLieuDTO1 = new UyQuyenDuLieuDTO();
        uyQuyenDuLieuDTO1.setId(1L);
        UyQuyenDuLieuDTO uyQuyenDuLieuDTO2 = new UyQuyenDuLieuDTO();
        assertThat(uyQuyenDuLieuDTO1).isNotEqualTo(uyQuyenDuLieuDTO2);
        uyQuyenDuLieuDTO2.setId(uyQuyenDuLieuDTO1.getId());
        assertThat(uyQuyenDuLieuDTO1).isEqualTo(uyQuyenDuLieuDTO2);
        uyQuyenDuLieuDTO2.setId(2L);
        assertThat(uyQuyenDuLieuDTO1).isNotEqualTo(uyQuyenDuLieuDTO2);
        uyQuyenDuLieuDTO1.setId(null);
        assertThat(uyQuyenDuLieuDTO1).isNotEqualTo(uyQuyenDuLieuDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(uyQuyenDuLieuMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(uyQuyenDuLieuMapper.fromId(null)).isNull();
    }
}
