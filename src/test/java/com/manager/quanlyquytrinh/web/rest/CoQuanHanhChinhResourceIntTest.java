package com.manager.quanlyquytrinh.web.rest;

import com.manager.quanlyquytrinh.QuytrinhdonviApp;

import com.manager.quanlyquytrinh.domain.CoQuanHanhChinh;
import com.manager.quanlyquytrinh.repository.CoQuanHanhChinhRepository;
import com.manager.quanlyquytrinh.service.CoQuanHanhChinhService;
import com.manager.quanlyquytrinh.service.dto.CoQuanHanhChinhDTO;
import com.manager.quanlyquytrinh.service.mapper.CoQuanHanhChinhMapper;
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
 * Test class for the CoQuanHanhChinhResource REST controller.
 *
 * @see CoQuanHanhChinhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuytrinhdonviApp.class)
public class CoQuanHanhChinhResourceIntTest {

    private static final String DEFAULT_CO_QUAN_HANH_CHINH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CO_QUAN_HANH_CHINH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_MA_DINH_DANH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MA_DINH_DANH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_LEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private CoQuanHanhChinhRepository coQuanHanhChinhRepository;

    @Autowired
    private CoQuanHanhChinhMapper coQuanHanhChinhMapper;

    @Autowired
    private CoQuanHanhChinhService coQuanHanhChinhService;

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

    private MockMvc restCoQuanHanhChinhMockMvc;

    private CoQuanHanhChinh coQuanHanhChinh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CoQuanHanhChinhResource coQuanHanhChinhResource = new CoQuanHanhChinhResource(coQuanHanhChinhService);
        this.restCoQuanHanhChinhMockMvc = MockMvcBuilders.standaloneSetup(coQuanHanhChinhResource)
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
    public static CoQuanHanhChinh createEntity(EntityManager em) {
        CoQuanHanhChinh coQuanHanhChinh = new CoQuanHanhChinh()
            .coQuanHanhChinhCode(DEFAULT_CO_QUAN_HANH_CHINH_CODE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .maDinhDanhCode(DEFAULT_MA_DINH_DANH_CODE)
            .level(DEFAULT_LEVEL)
            .status(DEFAULT_STATUS);
        return coQuanHanhChinh;
    }

    @Before
    public void initTest() {
        coQuanHanhChinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoQuanHanhChinh() throws Exception {
        int databaseSizeBeforeCreate = coQuanHanhChinhRepository.findAll().size();

        // Create the CoQuanHanhChinh
        CoQuanHanhChinhDTO coQuanHanhChinhDTO = coQuanHanhChinhMapper.toDto(coQuanHanhChinh);
        restCoQuanHanhChinhMockMvc.perform(post("/api/co-quan-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanHanhChinhDTO)))
            .andExpect(status().isCreated());

        // Validate the CoQuanHanhChinh in the database
        List<CoQuanHanhChinh> coQuanHanhChinhList = coQuanHanhChinhRepository.findAll();
        assertThat(coQuanHanhChinhList).hasSize(databaseSizeBeforeCreate + 1);
        CoQuanHanhChinh testCoQuanHanhChinh = coQuanHanhChinhList.get(coQuanHanhChinhList.size() - 1);
        assertThat(testCoQuanHanhChinh.getCoQuanHanhChinhCode()).isEqualTo(DEFAULT_CO_QUAN_HANH_CHINH_CODE);
        assertThat(testCoQuanHanhChinh.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCoQuanHanhChinh.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCoQuanHanhChinh.getMaDinhDanhCode()).isEqualTo(DEFAULT_MA_DINH_DANH_CODE);
        assertThat(testCoQuanHanhChinh.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testCoQuanHanhChinh.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCoQuanHanhChinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coQuanHanhChinhRepository.findAll().size();

        // Create the CoQuanHanhChinh with an existing ID
        coQuanHanhChinh.setId(1L);
        CoQuanHanhChinhDTO coQuanHanhChinhDTO = coQuanHanhChinhMapper.toDto(coQuanHanhChinh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoQuanHanhChinhMockMvc.perform(post("/api/co-quan-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanHanhChinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoQuanHanhChinh in the database
        List<CoQuanHanhChinh> coQuanHanhChinhList = coQuanHanhChinhRepository.findAll();
        assertThat(coQuanHanhChinhList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCoQuanHanhChinhCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = coQuanHanhChinhRepository.findAll().size();
        // set the field null
        coQuanHanhChinh.setCoQuanHanhChinhCode(null);

        // Create the CoQuanHanhChinh, which fails.
        CoQuanHanhChinhDTO coQuanHanhChinhDTO = coQuanHanhChinhMapper.toDto(coQuanHanhChinh);

        restCoQuanHanhChinhMockMvc.perform(post("/api/co-quan-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanHanhChinhDTO)))
            .andExpect(status().isBadRequest());

        List<CoQuanHanhChinh> coQuanHanhChinhList = coQuanHanhChinhRepository.findAll();
        assertThat(coQuanHanhChinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = coQuanHanhChinhRepository.findAll().size();
        // set the field null
        coQuanHanhChinh.setName(null);

        // Create the CoQuanHanhChinh, which fails.
        CoQuanHanhChinhDTO coQuanHanhChinhDTO = coQuanHanhChinhMapper.toDto(coQuanHanhChinh);

        restCoQuanHanhChinhMockMvc.perform(post("/api/co-quan-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanHanhChinhDTO)))
            .andExpect(status().isBadRequest());

        List<CoQuanHanhChinh> coQuanHanhChinhList = coQuanHanhChinhRepository.findAll();
        assertThat(coQuanHanhChinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = coQuanHanhChinhRepository.findAll().size();
        // set the field null
        coQuanHanhChinh.setDescription(null);

        // Create the CoQuanHanhChinh, which fails.
        CoQuanHanhChinhDTO coQuanHanhChinhDTO = coQuanHanhChinhMapper.toDto(coQuanHanhChinh);

        restCoQuanHanhChinhMockMvc.perform(post("/api/co-quan-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanHanhChinhDTO)))
            .andExpect(status().isBadRequest());

        List<CoQuanHanhChinh> coQuanHanhChinhList = coQuanHanhChinhRepository.findAll();
        assertThat(coQuanHanhChinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaDinhDanhCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = coQuanHanhChinhRepository.findAll().size();
        // set the field null
        coQuanHanhChinh.setMaDinhDanhCode(null);

        // Create the CoQuanHanhChinh, which fails.
        CoQuanHanhChinhDTO coQuanHanhChinhDTO = coQuanHanhChinhMapper.toDto(coQuanHanhChinh);

        restCoQuanHanhChinhMockMvc.perform(post("/api/co-quan-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanHanhChinhDTO)))
            .andExpect(status().isBadRequest());

        List<CoQuanHanhChinh> coQuanHanhChinhList = coQuanHanhChinhRepository.findAll();
        assertThat(coQuanHanhChinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = coQuanHanhChinhRepository.findAll().size();
        // set the field null
        coQuanHanhChinh.setLevel(null);

        // Create the CoQuanHanhChinh, which fails.
        CoQuanHanhChinhDTO coQuanHanhChinhDTO = coQuanHanhChinhMapper.toDto(coQuanHanhChinh);

        restCoQuanHanhChinhMockMvc.perform(post("/api/co-quan-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanHanhChinhDTO)))
            .andExpect(status().isBadRequest());

        List<CoQuanHanhChinh> coQuanHanhChinhList = coQuanHanhChinhRepository.findAll();
        assertThat(coQuanHanhChinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = coQuanHanhChinhRepository.findAll().size();
        // set the field null
        coQuanHanhChinh.setStatus(null);

        // Create the CoQuanHanhChinh, which fails.
        CoQuanHanhChinhDTO coQuanHanhChinhDTO = coQuanHanhChinhMapper.toDto(coQuanHanhChinh);

        restCoQuanHanhChinhMockMvc.perform(post("/api/co-quan-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanHanhChinhDTO)))
            .andExpect(status().isBadRequest());

        List<CoQuanHanhChinh> coQuanHanhChinhList = coQuanHanhChinhRepository.findAll();
        assertThat(coQuanHanhChinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCoQuanHanhChinhs() throws Exception {
        // Initialize the database
        coQuanHanhChinhRepository.saveAndFlush(coQuanHanhChinh);

        // Get all the coQuanHanhChinhList
        restCoQuanHanhChinhMockMvc.perform(get("/api/co-quan-hanh-chinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coQuanHanhChinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].coQuanHanhChinhCode").value(hasItem(DEFAULT_CO_QUAN_HANH_CHINH_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].maDinhDanhCode").value(hasItem(DEFAULT_MA_DINH_DANH_CODE.toString())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getCoQuanHanhChinh() throws Exception {
        // Initialize the database
        coQuanHanhChinhRepository.saveAndFlush(coQuanHanhChinh);

        // Get the coQuanHanhChinh
        restCoQuanHanhChinhMockMvc.perform(get("/api/co-quan-hanh-chinhs/{id}", coQuanHanhChinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coQuanHanhChinh.getId().intValue()))
            .andExpect(jsonPath("$.coQuanHanhChinhCode").value(DEFAULT_CO_QUAN_HANH_CHINH_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.maDinhDanhCode").value(DEFAULT_MA_DINH_DANH_CODE.toString()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCoQuanHanhChinh() throws Exception {
        // Get the coQuanHanhChinh
        restCoQuanHanhChinhMockMvc.perform(get("/api/co-quan-hanh-chinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoQuanHanhChinh() throws Exception {
        // Initialize the database
        coQuanHanhChinhRepository.saveAndFlush(coQuanHanhChinh);

        int databaseSizeBeforeUpdate = coQuanHanhChinhRepository.findAll().size();

        // Update the coQuanHanhChinh
        CoQuanHanhChinh updatedCoQuanHanhChinh = coQuanHanhChinhRepository.findById(coQuanHanhChinh.getId()).get();
        // Disconnect from session so that the updates on updatedCoQuanHanhChinh are not directly saved in db
        em.detach(updatedCoQuanHanhChinh);
        updatedCoQuanHanhChinh
            .coQuanHanhChinhCode(UPDATED_CO_QUAN_HANH_CHINH_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .maDinhDanhCode(UPDATED_MA_DINH_DANH_CODE)
            .level(UPDATED_LEVEL)
            .status(UPDATED_STATUS);
        CoQuanHanhChinhDTO coQuanHanhChinhDTO = coQuanHanhChinhMapper.toDto(updatedCoQuanHanhChinh);

        restCoQuanHanhChinhMockMvc.perform(put("/api/co-quan-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanHanhChinhDTO)))
            .andExpect(status().isOk());

        // Validate the CoQuanHanhChinh in the database
        List<CoQuanHanhChinh> coQuanHanhChinhList = coQuanHanhChinhRepository.findAll();
        assertThat(coQuanHanhChinhList).hasSize(databaseSizeBeforeUpdate);
        CoQuanHanhChinh testCoQuanHanhChinh = coQuanHanhChinhList.get(coQuanHanhChinhList.size() - 1);
        assertThat(testCoQuanHanhChinh.getCoQuanHanhChinhCode()).isEqualTo(UPDATED_CO_QUAN_HANH_CHINH_CODE);
        assertThat(testCoQuanHanhChinh.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCoQuanHanhChinh.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCoQuanHanhChinh.getMaDinhDanhCode()).isEqualTo(UPDATED_MA_DINH_DANH_CODE);
        assertThat(testCoQuanHanhChinh.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testCoQuanHanhChinh.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCoQuanHanhChinh() throws Exception {
        int databaseSizeBeforeUpdate = coQuanHanhChinhRepository.findAll().size();

        // Create the CoQuanHanhChinh
        CoQuanHanhChinhDTO coQuanHanhChinhDTO = coQuanHanhChinhMapper.toDto(coQuanHanhChinh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoQuanHanhChinhMockMvc.perform(put("/api/co-quan-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanHanhChinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoQuanHanhChinh in the database
        List<CoQuanHanhChinh> coQuanHanhChinhList = coQuanHanhChinhRepository.findAll();
        assertThat(coQuanHanhChinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCoQuanHanhChinh() throws Exception {
        // Initialize the database
        coQuanHanhChinhRepository.saveAndFlush(coQuanHanhChinh);

        int databaseSizeBeforeDelete = coQuanHanhChinhRepository.findAll().size();

        // Delete the coQuanHanhChinh
        restCoQuanHanhChinhMockMvc.perform(delete("/api/co-quan-hanh-chinhs/{id}", coQuanHanhChinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CoQuanHanhChinh> coQuanHanhChinhList = coQuanHanhChinhRepository.findAll();
        assertThat(coQuanHanhChinhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoQuanHanhChinh.class);
        CoQuanHanhChinh coQuanHanhChinh1 = new CoQuanHanhChinh();
        coQuanHanhChinh1.setId(1L);
        CoQuanHanhChinh coQuanHanhChinh2 = new CoQuanHanhChinh();
        coQuanHanhChinh2.setId(coQuanHanhChinh1.getId());
        assertThat(coQuanHanhChinh1).isEqualTo(coQuanHanhChinh2);
        coQuanHanhChinh2.setId(2L);
        assertThat(coQuanHanhChinh1).isNotEqualTo(coQuanHanhChinh2);
        coQuanHanhChinh1.setId(null);
        assertThat(coQuanHanhChinh1).isNotEqualTo(coQuanHanhChinh2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoQuanHanhChinhDTO.class);
        CoQuanHanhChinhDTO coQuanHanhChinhDTO1 = new CoQuanHanhChinhDTO();
        coQuanHanhChinhDTO1.setId(1L);
        CoQuanHanhChinhDTO coQuanHanhChinhDTO2 = new CoQuanHanhChinhDTO();
        assertThat(coQuanHanhChinhDTO1).isNotEqualTo(coQuanHanhChinhDTO2);
        coQuanHanhChinhDTO2.setId(coQuanHanhChinhDTO1.getId());
        assertThat(coQuanHanhChinhDTO1).isEqualTo(coQuanHanhChinhDTO2);
        coQuanHanhChinhDTO2.setId(2L);
        assertThat(coQuanHanhChinhDTO1).isNotEqualTo(coQuanHanhChinhDTO2);
        coQuanHanhChinhDTO1.setId(null);
        assertThat(coQuanHanhChinhDTO1).isNotEqualTo(coQuanHanhChinhDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(coQuanHanhChinhMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(coQuanHanhChinhMapper.fromId(null)).isNull();
    }
}
