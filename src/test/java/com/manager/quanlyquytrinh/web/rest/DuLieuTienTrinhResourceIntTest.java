package com.manager.quanlyquytrinh.web.rest;

import com.manager.quanlyquytrinh.QuytrinhdonviApp;

import com.manager.quanlyquytrinh.domain.DuLieuTienTrinh;
import com.manager.quanlyquytrinh.repository.DuLieuTienTrinhRepository;
import com.manager.quanlyquytrinh.service.DuLieuTienTrinhService;
import com.manager.quanlyquytrinh.service.dto.DuLieuTienTrinhDTO;
import com.manager.quanlyquytrinh.service.mapper.DuLieuTienTrinhMapper;
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
 * Test class for the DuLieuTienTrinhResource REST controller.
 *
 * @see DuLieuTienTrinhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuytrinhdonviApp.class)
public class DuLieuTienTrinhResourceIntTest {

    private static final String DEFAULT_TIEN_TRINH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TIEN_TRINH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DU_LIEU_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DU_LIEU_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FROM_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_FROM_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TO_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TO_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    @Autowired
    private DuLieuTienTrinhRepository duLieuTienTrinhRepository;

    @Autowired
    private DuLieuTienTrinhMapper duLieuTienTrinhMapper;

    @Autowired
    private DuLieuTienTrinhService duLieuTienTrinhService;

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

    private MockMvc restDuLieuTienTrinhMockMvc;

    private DuLieuTienTrinh duLieuTienTrinh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DuLieuTienTrinhResource duLieuTienTrinhResource = new DuLieuTienTrinhResource(duLieuTienTrinhService);
        this.restDuLieuTienTrinhMockMvc = MockMvcBuilders.standaloneSetup(duLieuTienTrinhResource)
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
    public static DuLieuTienTrinh createEntity(EntityManager em) {
        DuLieuTienTrinh duLieuTienTrinh = new DuLieuTienTrinh()
            .tienTrinhCode(DEFAULT_TIEN_TRINH_CODE)
            .duLieuCode(DEFAULT_DU_LIEU_CODE)
            .fromUserId(DEFAULT_FROM_USER_ID)
            .toUserId(DEFAULT_TO_USER_ID)
            .name(DEFAULT_NAME)
            .note(DEFAULT_NOTE);
        return duLieuTienTrinh;
    }

    @Before
    public void initTest() {
        duLieuTienTrinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createDuLieuTienTrinh() throws Exception {
        int databaseSizeBeforeCreate = duLieuTienTrinhRepository.findAll().size();

        // Create the DuLieuTienTrinh
        DuLieuTienTrinhDTO duLieuTienTrinhDTO = duLieuTienTrinhMapper.toDto(duLieuTienTrinh);
        restDuLieuTienTrinhMockMvc.perform(post("/api/du-lieu-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuTienTrinhDTO)))
            .andExpect(status().isCreated());

        // Validate the DuLieuTienTrinh in the database
        List<DuLieuTienTrinh> duLieuTienTrinhList = duLieuTienTrinhRepository.findAll();
        assertThat(duLieuTienTrinhList).hasSize(databaseSizeBeforeCreate + 1);
        DuLieuTienTrinh testDuLieuTienTrinh = duLieuTienTrinhList.get(duLieuTienTrinhList.size() - 1);
        assertThat(testDuLieuTienTrinh.getTienTrinhCode()).isEqualTo(DEFAULT_TIEN_TRINH_CODE);
        assertThat(testDuLieuTienTrinh.getDuLieuCode()).isEqualTo(DEFAULT_DU_LIEU_CODE);
        assertThat(testDuLieuTienTrinh.getFromUserId()).isEqualTo(DEFAULT_FROM_USER_ID);
        assertThat(testDuLieuTienTrinh.getToUserId()).isEqualTo(DEFAULT_TO_USER_ID);
        assertThat(testDuLieuTienTrinh.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDuLieuTienTrinh.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    public void createDuLieuTienTrinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = duLieuTienTrinhRepository.findAll().size();

        // Create the DuLieuTienTrinh with an existing ID
        duLieuTienTrinh.setId(1L);
        DuLieuTienTrinhDTO duLieuTienTrinhDTO = duLieuTienTrinhMapper.toDto(duLieuTienTrinh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDuLieuTienTrinhMockMvc.perform(post("/api/du-lieu-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DuLieuTienTrinh in the database
        List<DuLieuTienTrinh> duLieuTienTrinhList = duLieuTienTrinhRepository.findAll();
        assertThat(duLieuTienTrinhList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTienTrinhCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = duLieuTienTrinhRepository.findAll().size();
        // set the field null
        duLieuTienTrinh.setTienTrinhCode(null);

        // Create the DuLieuTienTrinh, which fails.
        DuLieuTienTrinhDTO duLieuTienTrinhDTO = duLieuTienTrinhMapper.toDto(duLieuTienTrinh);

        restDuLieuTienTrinhMockMvc.perform(post("/api/du-lieu-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<DuLieuTienTrinh> duLieuTienTrinhList = duLieuTienTrinhRepository.findAll();
        assertThat(duLieuTienTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDuLieuCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = duLieuTienTrinhRepository.findAll().size();
        // set the field null
        duLieuTienTrinh.setDuLieuCode(null);

        // Create the DuLieuTienTrinh, which fails.
        DuLieuTienTrinhDTO duLieuTienTrinhDTO = duLieuTienTrinhMapper.toDto(duLieuTienTrinh);

        restDuLieuTienTrinhMockMvc.perform(post("/api/du-lieu-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<DuLieuTienTrinh> duLieuTienTrinhList = duLieuTienTrinhRepository.findAll();
        assertThat(duLieuTienTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFromUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = duLieuTienTrinhRepository.findAll().size();
        // set the field null
        duLieuTienTrinh.setFromUserId(null);

        // Create the DuLieuTienTrinh, which fails.
        DuLieuTienTrinhDTO duLieuTienTrinhDTO = duLieuTienTrinhMapper.toDto(duLieuTienTrinh);

        restDuLieuTienTrinhMockMvc.perform(post("/api/du-lieu-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<DuLieuTienTrinh> duLieuTienTrinhList = duLieuTienTrinhRepository.findAll();
        assertThat(duLieuTienTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkToUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = duLieuTienTrinhRepository.findAll().size();
        // set the field null
        duLieuTienTrinh.setToUserId(null);

        // Create the DuLieuTienTrinh, which fails.
        DuLieuTienTrinhDTO duLieuTienTrinhDTO = duLieuTienTrinhMapper.toDto(duLieuTienTrinh);

        restDuLieuTienTrinhMockMvc.perform(post("/api/du-lieu-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<DuLieuTienTrinh> duLieuTienTrinhList = duLieuTienTrinhRepository.findAll();
        assertThat(duLieuTienTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = duLieuTienTrinhRepository.findAll().size();
        // set the field null
        duLieuTienTrinh.setName(null);

        // Create the DuLieuTienTrinh, which fails.
        DuLieuTienTrinhDTO duLieuTienTrinhDTO = duLieuTienTrinhMapper.toDto(duLieuTienTrinh);

        restDuLieuTienTrinhMockMvc.perform(post("/api/du-lieu-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<DuLieuTienTrinh> duLieuTienTrinhList = duLieuTienTrinhRepository.findAll();
        assertThat(duLieuTienTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNoteIsRequired() throws Exception {
        int databaseSizeBeforeTest = duLieuTienTrinhRepository.findAll().size();
        // set the field null
        duLieuTienTrinh.setNote(null);

        // Create the DuLieuTienTrinh, which fails.
        DuLieuTienTrinhDTO duLieuTienTrinhDTO = duLieuTienTrinhMapper.toDto(duLieuTienTrinh);

        restDuLieuTienTrinhMockMvc.perform(post("/api/du-lieu-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<DuLieuTienTrinh> duLieuTienTrinhList = duLieuTienTrinhRepository.findAll();
        assertThat(duLieuTienTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDuLieuTienTrinhs() throws Exception {
        // Initialize the database
        duLieuTienTrinhRepository.saveAndFlush(duLieuTienTrinh);

        // Get all the duLieuTienTrinhList
        restDuLieuTienTrinhMockMvc.perform(get("/api/du-lieu-tien-trinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(duLieuTienTrinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].tienTrinhCode").value(hasItem(DEFAULT_TIEN_TRINH_CODE.toString())))
            .andExpect(jsonPath("$.[*].duLieuCode").value(hasItem(DEFAULT_DU_LIEU_CODE.toString())))
            .andExpect(jsonPath("$.[*].fromUserId").value(hasItem(DEFAULT_FROM_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].toUserId").value(hasItem(DEFAULT_TO_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())));
    }
    
    @Test
    @Transactional
    public void getDuLieuTienTrinh() throws Exception {
        // Initialize the database
        duLieuTienTrinhRepository.saveAndFlush(duLieuTienTrinh);

        // Get the duLieuTienTrinh
        restDuLieuTienTrinhMockMvc.perform(get("/api/du-lieu-tien-trinhs/{id}", duLieuTienTrinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(duLieuTienTrinh.getId().intValue()))
            .andExpect(jsonPath("$.tienTrinhCode").value(DEFAULT_TIEN_TRINH_CODE.toString()))
            .andExpect(jsonPath("$.duLieuCode").value(DEFAULT_DU_LIEU_CODE.toString()))
            .andExpect(jsonPath("$.fromUserId").value(DEFAULT_FROM_USER_ID.toString()))
            .andExpect(jsonPath("$.toUserId").value(DEFAULT_TO_USER_ID.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDuLieuTienTrinh() throws Exception {
        // Get the duLieuTienTrinh
        restDuLieuTienTrinhMockMvc.perform(get("/api/du-lieu-tien-trinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDuLieuTienTrinh() throws Exception {
        // Initialize the database
        duLieuTienTrinhRepository.saveAndFlush(duLieuTienTrinh);

        int databaseSizeBeforeUpdate = duLieuTienTrinhRepository.findAll().size();

        // Update the duLieuTienTrinh
        DuLieuTienTrinh updatedDuLieuTienTrinh = duLieuTienTrinhRepository.findById(duLieuTienTrinh.getId()).get();
        // Disconnect from session so that the updates on updatedDuLieuTienTrinh are not directly saved in db
        em.detach(updatedDuLieuTienTrinh);
        updatedDuLieuTienTrinh
            .tienTrinhCode(UPDATED_TIEN_TRINH_CODE)
            .duLieuCode(UPDATED_DU_LIEU_CODE)
            .fromUserId(UPDATED_FROM_USER_ID)
            .toUserId(UPDATED_TO_USER_ID)
            .name(UPDATED_NAME)
            .note(UPDATED_NOTE);
        DuLieuTienTrinhDTO duLieuTienTrinhDTO = duLieuTienTrinhMapper.toDto(updatedDuLieuTienTrinh);

        restDuLieuTienTrinhMockMvc.perform(put("/api/du-lieu-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuTienTrinhDTO)))
            .andExpect(status().isOk());

        // Validate the DuLieuTienTrinh in the database
        List<DuLieuTienTrinh> duLieuTienTrinhList = duLieuTienTrinhRepository.findAll();
        assertThat(duLieuTienTrinhList).hasSize(databaseSizeBeforeUpdate);
        DuLieuTienTrinh testDuLieuTienTrinh = duLieuTienTrinhList.get(duLieuTienTrinhList.size() - 1);
        assertThat(testDuLieuTienTrinh.getTienTrinhCode()).isEqualTo(UPDATED_TIEN_TRINH_CODE);
        assertThat(testDuLieuTienTrinh.getDuLieuCode()).isEqualTo(UPDATED_DU_LIEU_CODE);
        assertThat(testDuLieuTienTrinh.getFromUserId()).isEqualTo(UPDATED_FROM_USER_ID);
        assertThat(testDuLieuTienTrinh.getToUserId()).isEqualTo(UPDATED_TO_USER_ID);
        assertThat(testDuLieuTienTrinh.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDuLieuTienTrinh.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void updateNonExistingDuLieuTienTrinh() throws Exception {
        int databaseSizeBeforeUpdate = duLieuTienTrinhRepository.findAll().size();

        // Create the DuLieuTienTrinh
        DuLieuTienTrinhDTO duLieuTienTrinhDTO = duLieuTienTrinhMapper.toDto(duLieuTienTrinh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDuLieuTienTrinhMockMvc.perform(put("/api/du-lieu-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duLieuTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DuLieuTienTrinh in the database
        List<DuLieuTienTrinh> duLieuTienTrinhList = duLieuTienTrinhRepository.findAll();
        assertThat(duLieuTienTrinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDuLieuTienTrinh() throws Exception {
        // Initialize the database
        duLieuTienTrinhRepository.saveAndFlush(duLieuTienTrinh);

        int databaseSizeBeforeDelete = duLieuTienTrinhRepository.findAll().size();

        // Delete the duLieuTienTrinh
        restDuLieuTienTrinhMockMvc.perform(delete("/api/du-lieu-tien-trinhs/{id}", duLieuTienTrinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DuLieuTienTrinh> duLieuTienTrinhList = duLieuTienTrinhRepository.findAll();
        assertThat(duLieuTienTrinhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DuLieuTienTrinh.class);
        DuLieuTienTrinh duLieuTienTrinh1 = new DuLieuTienTrinh();
        duLieuTienTrinh1.setId(1L);
        DuLieuTienTrinh duLieuTienTrinh2 = new DuLieuTienTrinh();
        duLieuTienTrinh2.setId(duLieuTienTrinh1.getId());
        assertThat(duLieuTienTrinh1).isEqualTo(duLieuTienTrinh2);
        duLieuTienTrinh2.setId(2L);
        assertThat(duLieuTienTrinh1).isNotEqualTo(duLieuTienTrinh2);
        duLieuTienTrinh1.setId(null);
        assertThat(duLieuTienTrinh1).isNotEqualTo(duLieuTienTrinh2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DuLieuTienTrinhDTO.class);
        DuLieuTienTrinhDTO duLieuTienTrinhDTO1 = new DuLieuTienTrinhDTO();
        duLieuTienTrinhDTO1.setId(1L);
        DuLieuTienTrinhDTO duLieuTienTrinhDTO2 = new DuLieuTienTrinhDTO();
        assertThat(duLieuTienTrinhDTO1).isNotEqualTo(duLieuTienTrinhDTO2);
        duLieuTienTrinhDTO2.setId(duLieuTienTrinhDTO1.getId());
        assertThat(duLieuTienTrinhDTO1).isEqualTo(duLieuTienTrinhDTO2);
        duLieuTienTrinhDTO2.setId(2L);
        assertThat(duLieuTienTrinhDTO1).isNotEqualTo(duLieuTienTrinhDTO2);
        duLieuTienTrinhDTO1.setId(null);
        assertThat(duLieuTienTrinhDTO1).isNotEqualTo(duLieuTienTrinhDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(duLieuTienTrinhMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(duLieuTienTrinhMapper.fromId(null)).isNull();
    }
}
